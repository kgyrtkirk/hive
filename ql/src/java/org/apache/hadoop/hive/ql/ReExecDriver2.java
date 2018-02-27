/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.ql;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.tree.Tree;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.Schema;
import org.apache.hadoop.hive.ql.exec.FetchTask;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.HiveParser;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHookContext;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.ql.plan.mapper.PlanMapper;
import org.apache.hadoop.hive.ql.processors.CommandProcessorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Enables to use re-execution logics.
 *
 * Covers the IDriver interface, handles query re-execution; and asks clear questions from the underlying re-execution plugins.
 */
public class ReExecDriver2 implements IDriver {

  private class HandleReOptimizationExplain implements HiveSemanticAnalyzerHook {

    @Override
    public ASTNode preAnalyze(HiveSemanticAnalyzerHookContext context, ASTNode ast) throws SemanticException {
      if (ast.getType() == HiveParser.TOK_EXPLAIN) {
        int childCount = ast.getChildCount();
        for (int i = 1; i < childCount; i++) {
          if (ast.getChild(i).getType() == HiveParser.KW_REOPTIMIZATION) {
            explainReOptimization = true;
            ast.deleteChild(i);
            break;
          }
        }
        if (explainReOptimization && firstExecution()) {
          Tree execTree = ast.getChild(0);
          execTree.setParent(ast.getParent());
          ast.getParent().setChild(0, execTree);
          return (ASTNode) execTree;
        }
      }
      return ast;
    }

    @Override
    public void postAnalyze(HiveSemanticAnalyzerHookContext context, List<Task<? extends Serializable>> rootTasks)
        throws SemanticException {
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(ReExecDriver2.class);
  private boolean explainReOptimization;
  protected Driver coreDriver;
  private QueryState queryState;
  private String currentQuery;
  // field
  @Deprecated
  private int executionIndex;

  private ArrayList<ReExecutionPlugin> plugins;

  @Override
  public HiveConf getConf() {
    return queryState.getConf();
  }

  public boolean firstExecution() {
    return executionIndex == 0;
  }

  public ReExecDriver2(QueryState queryState, String userName, QueryInfo queryInfo,
      ArrayList<ReExecutionPlugin> plugins) {
    this.queryState = queryState;
    coreDriver = new Driver(queryState, userName, queryInfo, null);
    coreDriver.getHookRunner().addSemanticAnalyzerHook(new HandleReOptimizationExplain());
    this.plugins = plugins;

    for (ReExecutionPlugin p : plugins) {
      p.initialize(coreDriver);
    }
  }

  @Override
  public int compile(String string) {
    return coreDriver.compile(string);
  }

  @Override
  public CommandProcessorResponse compileAndRespond(String statement) {
    currentQuery = statement;
    return coreDriver.compileAndRespond(statement);
  }

  @Override
  public QueryPlan getPlan() {
    return coreDriver.getPlan();
  }

  @Override
  public QueryDisplay getQueryDisplay() {
    return coreDriver.getQueryDisplay();
  }

  @Override
  public void setOperationId(String guid64) {
    coreDriver.setOperationId(guid64);
  }

  @Override
  public CommandProcessorResponse run() {
    executionIndex = 0;
    int maxExecutuions = 1 + coreDriver.getConf().getIntVar(ConfVars.HIVE_QUERY_MAX_REEXECUTION_COUNT);

    while (true) {
      executionIndex++;
      coreDriver.getContext().setExecutionIndex(executionIndex);
      LOG.info("Execution #{} of query", executionIndex);
      CommandProcessorResponse cpr = coreDriver.run();

      boolean shouldReExecute = explainReOptimization;
      shouldReExecute |= cpr.getResponseCode() != 0 && shouldReExecute();

      if (executionIndex >= maxExecutuions || !shouldReExecute) {
        return cpr;
      }
      LOG.info("Preparing to re-execute query");
      prepareToReExecute();
      PlanMapper oldPlanMapper = coreDriver.getPlanMapper();
      CommandProcessorResponse compile_resp = coreDriver.compileAndRespond(currentQuery);
      if (compile_resp.failed()) {
        // FIXME: somehow place pointers that re-execution compilation have failed; the query have been successfully compiled before?
        return compile_resp;
      }

      PlanMapper newPlanMapper = coreDriver.getPlanMapper();
      if (!shouldReExecuteAfterCompile(oldPlanMapper, newPlanMapper)) {
        // FIXME: retain old error; or create a new one?
        return cpr;
      }
    }
  }

  private boolean shouldReExecuteAfterCompile(PlanMapper oldPlanMapper, PlanMapper newPlanMapper) {
    boolean ret = false;
    for (ReExecutionPlugin p : plugins) {
      ret |= p.shouldReExecute2(executionIndex, oldPlanMapper, newPlanMapper);
    }
    return ret;
  }

  private boolean shouldReExecute() {
    boolean ret = false;
    for (ReExecutionPlugin p : plugins) {
      ret |= p.shouldReExecute(executionIndex);
    }
    return ret;
  }

  @Override
  public CommandProcessorResponse run(String command) {
    CommandProcessorResponse r0 = compileAndRespond(command);
    if (r0.getResponseCode() != 0) {
      return r0;
    }
    return run();
  }

  protected void prepareToReExecute() {
    for (ReExecutionPlugin p : plugins) {
      p.prepareToReExecute2();
    }
  }

  @Override
  public boolean getResults(List res) throws IOException {
    return coreDriver.getResults(res);
  }

  @Override
  public void setMaxRows(int maxRows) {
    coreDriver.setMaxRows(maxRows);
  }

  @Override
  public FetchTask getFetchTask() {
    return coreDriver.getFetchTask();
  }

  @Override
  public Schema getSchema() {
    return coreDriver.getSchema();
  }

  @Override
  public boolean isFetchingTable() {
    return coreDriver.isFetchingTable();
  }

  @Override
  public void resetFetch() throws IOException {
    coreDriver.resetFetch();
  }

  @Override
  public void close() {
    coreDriver.close();
  }

  @Override
  public void destroy() {
    coreDriver.destroy();
  }

  @Override
  public final Context getContext() {
    return coreDriver.getContext();
  }

}
