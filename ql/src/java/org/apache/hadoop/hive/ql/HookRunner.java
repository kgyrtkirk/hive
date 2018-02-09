/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package org.apache.hadoop.hive.ql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.hooks.ExecuteWithHookContext;
import org.apache.hadoop.hive.ql.hooks.Hook;
import org.apache.hadoop.hive.ql.hooks.HookContext;
import org.apache.hadoop.hive.ql.hooks.HooksLoader;
import org.apache.hadoop.hive.ql.hooks.MaterializedViewRegistryUpdateHook;
import org.apache.hadoop.hive.ql.hooks.MetricsQueryLifeTimeHook;
import org.apache.hadoop.hive.ql.hooks.QueryLifeTimeHook;
import org.apache.hadoop.hive.ql.hooks.QueryLifeTimeHookContext;
import org.apache.hadoop.hive.ql.hooks.QueryLifeTimeHookContextImpl;
import org.apache.hadoop.hive.ql.hooks.QueryLifeTimeHookWithParseHooks;
import org.apache.hadoop.hive.ql.log.PerfLogger;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHookContext;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.session.SessionState.LogHelper;

/**
 * Handles hook executions for {@link Driver}.
 */
class HookRunner {

  private static final String CLASS_NAME = Driver.class.getName();
  private final HiveConf conf;
  private final List<QueryLifeTimeHook> queryHooks;
  private LogHelper console;
  @Deprecated
  private HooksLoader hooksLoader;
  final List<HiveSemanticAnalyzerHook> saHooks;
  private List<HiveDriverRunHook> driverRunHooks;


  /**
   * Constructs a {@link HookRunner} that loads all hooks to be run via a {@link HooksLoader}.
   *
   * @param conf the {@link HiveConf} to use when creating {@link QueryLifeTimeHookContext} objects
   * @param hooksLoader the {@link HooksLoader} to use when loading all hooks to be run
   * @param console the {@link SessionState.LogHelper} to use when running {@link HooksLoader#getHooks(HiveConf.ConfVars)}
   */
  HookRunner(HiveConf conf, HooksLoader hooksLoader, SessionState.LogHelper console) {
    this.conf = conf;
    this.hooksLoader = hooksLoader;
    this.console = console;
    this.queryHooks = new ArrayList<>();

    if (conf.getBoolVar(HiveConf.ConfVars.HIVE_SERVER2_METRICS_ENABLED)) {
      queryHooks.add(new MetricsQueryLifeTimeHook());
    }
    queryHooks.add(new MaterializedViewRegistryUpdateHook());

    List<QueryLifeTimeHook> propertyDefinedHoooks;
    try {
      propertyDefinedHoooks =
          hooksLoader.getHooks(HiveConf.ConfVars.HIVE_QUERY_LIFETIME_HOOKS, console, QueryLifeTimeHook.class);
    } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
      throw new IllegalArgumentException(e);
    }
    if (propertyDefinedHoooks != null) {
      Iterables.addAll(queryHooks, propertyDefinedHoooks);
    }

    try {
      saHooks = hooksLoader.getHooks(HiveConf.ConfVars.SEMANTIC_ANALYZER_HOOK, console, HiveSemanticAnalyzerHook.class);
      driverRunHooks = hooksLoader.getHooks(HiveConf.ConfVars.HIVE_DRIVER_RUN_HOOKS, console, HiveDriverRunHook.class);
    } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
      throw new RuntimeException("Error loading hooks: " + e.getMessage(), e);
    }

  }

  /**
   * If {@link QueryLifeTimeHookWithParseHooks} have been loaded via the {@link HooksLoader} then invoke the
   * {@link QueryLifeTimeHookWithParseHooks#beforeParse(QueryLifeTimeHookContext)} method for each
   * {@link QueryLifeTimeHookWithParseHooks}.
   *
   * @param command the Hive command that is being run
   */
  void runBeforeParseHook(String command) {
    if (containsHooks()) {
      QueryLifeTimeHookContext qhc =
          new QueryLifeTimeHookContextImpl.Builder().withHiveConf(conf).withCommand(command).build();

      for (QueryLifeTimeHook hook : queryHooks) {
        if (hook instanceof QueryLifeTimeHookWithParseHooks) {
          ((QueryLifeTimeHookWithParseHooks) hook).beforeParse(qhc);
        }
      }
    }
  }

  /**
   * If {@link QueryLifeTimeHookWithParseHooks} have been loaded via the {@link HooksLoader} then invoke the
   * {@link QueryLifeTimeHookWithParseHooks#afterParse(QueryLifeTimeHookContext, boolean)} method for each
   * {@link QueryLifeTimeHookWithParseHooks}.
   *
   * @param command the Hive command that is being run
   * @param parseError true if there was an error while parsing the command, false otherwise
   */
  void runAfterParseHook(String command, boolean parseError) {
    if (containsHooks()) {
      QueryLifeTimeHookContext qhc =
          new QueryLifeTimeHookContextImpl.Builder().withHiveConf(conf).withCommand(command).build();

      for (QueryLifeTimeHook hook : queryHooks) {
        if (hook instanceof QueryLifeTimeHookWithParseHooks) {
          ((QueryLifeTimeHookWithParseHooks) hook).afterParse(qhc, parseError);
        }
      }
    }
  }

  /**
   * Invoke the {@link QueryLifeTimeHook#beforeCompile(QueryLifeTimeHookContext)} method for each {@link QueryLifeTimeHook}
   *
   * @param command the Hive command that is being run
   */
  void runBeforeCompileHook(String command) {
    if (containsHooks()) {
      QueryLifeTimeHookContext qhc =
          new QueryLifeTimeHookContextImpl.Builder().withHiveConf(conf).withCommand(command).build();

      for (QueryLifeTimeHook hook : queryHooks) {
        hook.beforeCompile(qhc);
      }
    }
  }

  /**
  * Invoke the {@link QueryLifeTimeHook#afterCompile(QueryLifeTimeHookContext, boolean)} method for each {@link QueryLifeTimeHook}
  *
  * @param command the Hive command that is being run
  * @param compileError true if there was an error while compiling the command, false otherwise
  */
  void runAfterCompilationHook(String command, boolean compileError) {
    if (containsHooks()) {
      QueryLifeTimeHookContext qhc =
          new QueryLifeTimeHookContextImpl.Builder().withHiveConf(conf).withCommand(command).build();

      for (QueryLifeTimeHook hook : queryHooks) {
        hook.afterCompile(qhc, compileError);
      }
    }
  }

  /**
   * Invoke the {@link QueryLifeTimeHook#beforeExecution(QueryLifeTimeHookContext)} method for each {@link QueryLifeTimeHook}
   *
   * @param command the Hive command that is being run
   * @param hookContext the {@link HookContext} of the command being run
   */
  void runBeforeExecutionHook(String command, HookContext hookContext) {
    if (containsHooks()) {
      QueryLifeTimeHookContext qhc = new QueryLifeTimeHookContextImpl.Builder().withHiveConf(conf).withCommand(command)
          .withHookContext(hookContext).build();

      for (QueryLifeTimeHook hook : queryHooks) {
        hook.beforeExecution(qhc);
      }
    }
  }

  /**
   * Invoke the {@link QueryLifeTimeHook#afterExecution(QueryLifeTimeHookContext, boolean)} method for each {@link QueryLifeTimeHook}
   *
   * @param command the Hive command that is being run
   * @param hookContext the {@link HookContext} of the command being run
   * @param executionError true if there was an error while executing the command, false otherwise
   */
  void runAfterExecutionHook(String command, HookContext hookContext, boolean executionError) {
    if (containsHooks()) {
      QueryLifeTimeHookContext qhc = new QueryLifeTimeHookContextImpl.Builder().withHiveConf(conf).withCommand(command)
          .withHookContext(hookContext).build();

      for (QueryLifeTimeHook hook : queryHooks) {
        hook.afterExecution(qhc, executionError);
      }
    }
  }

  // FIXME: make queryhooks not support null and inline this crap!
  @Deprecated
  private boolean containsHooks() {
    return queryHooks != null && !queryHooks.isEmpty();
  }

  public ASTNode runPreAnalyzeHooks(HiveSemanticAnalyzerHookContext hookCtx, ASTNode tree) throws HiveException {
    try {
      for (HiveSemanticAnalyzerHook hook : saHooks) {
        try {
        tree = hook.preAnalyze(hookCtx, tree);
        } catch (Exception e) {
          throw new HiveException("Error while invoking hook " + hook.getClass().getSimpleName() + ":" + e.getMessage(),
              e);
        }
      }
      return tree;
    } catch (Exception e) {
      throw new HiveException("Error while invoking PreAnalyzeHooks:" + e.getMessage(), e);
    }
  }

  public boolean hasPreAnalyzeHooks() throws HiveException {
    try {
      return !saHooks.isEmpty();
    } catch (Exception e) {
      throw new HiveException("Error while Parsing hoks?:" + e.getMessage(), e);
    }
  }

  public void runPostAnalyzeHooks(HiveSemanticAnalyzerHookContext hookCtx,
      List<Task<? extends Serializable>> allRootTasks) throws HiveException {
    try {
      for (HiveSemanticAnalyzerHook hook : saHooks) {
        hook.postAnalyze(hookCtx, allRootTasks);
      }
    } catch (Exception e) {
      throw new HiveException("Error while invoking PreAnalyzeHooks:" + e.getMessage(), e);
    }

  }

  public void runPreDriverHooks(HiveDriverRunHookContext hookContext) throws HiveException {
    try {
      for (HiveDriverRunHook driverRunHook : driverRunHooks) {
        driverRunHook.preDriverRun(hookContext);
      }
    } catch (Exception e) {
      throw new HiveException("Error while invoking PreAnalyzeHooks:" + e.getMessage(), e);
    }
  }

  public void runPostDriverHooks(HiveDriverRunHookContext hookContext) throws HiveException {
    List<HiveDriverRunHook> driverRunHooks;
    try {
      driverRunHooks = hooksLoader.getHooks(HiveConf.ConfVars.HIVE_DRIVER_RUN_HOOKS, console, HiveDriverRunHook.class);
      for (HiveDriverRunHook driverRunHook : driverRunHooks) {
        driverRunHook.postDriverRun(hookContext);
      }
    } catch (Exception e) {
      throw new HiveException("Error while invoking PreAnalyzeHooks:" + e.getMessage(), e);
    }
  }

  public void runPreHooks(HookContext hookContext) throws HiveException {
    try {
      PerfLogger perfLogger = SessionState.getPerfLogger();

      for (Hook peh : hooksLoader.getHooks(HiveConf.ConfVars.PREEXECHOOKS, console, ExecuteWithHookContext.class)) {
        perfLogger.PerfLogBegin(CLASS_NAME, PerfLogger.PRE_HOOK + peh.getClass().getName());

        ((ExecuteWithHookContext) peh).run(hookContext);

        perfLogger.PerfLogEnd(CLASS_NAME, PerfLogger.PRE_HOOK + peh.getClass().getName());
      }
    } catch (Exception e) {
      throw new HiveException("Error while invoking XXXXeAnalyzeHooks:" + e.getMessage(), e);
    }
  }

  public void runPostExecHooks(HookContext hookContext) throws HiveException {
    try {
      PerfLogger perfLogger = SessionState.getPerfLogger();
      // Get all the post execution hooks and execute them.
      for (Hook peh : hooksLoader.getHooks(HiveConf.ConfVars.POSTEXECHOOKS, console, ExecuteWithHookContext.class)) {
        perfLogger.PerfLogBegin(CLASS_NAME, PerfLogger.POST_HOOK + peh.getClass().getName());

        ((ExecuteWithHookContext) peh).run(hookContext);

        perfLogger.PerfLogEnd(CLASS_NAME, PerfLogger.POST_HOOK + peh.getClass().getName());
      }
    } catch (Exception e) {
      throw new HiveException("Error while invoking XXXXeAnalyzeHooks:" + e.getMessage(), e);
    }

  }

  public void runFailureHooks(HookContext hookContext) throws HiveException {
    try {
      PerfLogger perfLogger = SessionState.getPerfLogger();
      for (Hook ofh : hooksLoader.getHooks(HiveConf.ConfVars.ONFAILUREHOOKS, console, ExecuteWithHookContext.class)) {
        perfLogger.PerfLogBegin(CLASS_NAME, PerfLogger.FAILURE_HOOK + ofh.getClass().getName());

        ((ExecuteWithHookContext) ofh).run(hookContext);

        perfLogger.PerfLogEnd(CLASS_NAME, PerfLogger.FAILURE_HOOK + ofh.getClass().getName());
      }
    } catch (Exception e) {
      throw new HiveException("Error while invoking XXXXeAnalyzeHooks:" + e.getMessage(), e);
    }
  }
}
