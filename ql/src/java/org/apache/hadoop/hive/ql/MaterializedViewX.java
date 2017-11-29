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

import java.io.Serializable;
import java.util.List;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.ql.exec.DDLTask;
import org.apache.hadoop.hive.ql.exec.Task;
import org.apache.hadoop.hive.ql.exec.TaskRunner;
import org.apache.hadoop.hive.ql.hooks.QueryLifeTimeHook;
import org.apache.hadoop.hive.ql.hooks.QueryLifeTimeHookContext;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.HiveMaterializedViewsRegistry;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.plan.DDLWork;

public class MaterializedViewX implements QueryLifeTimeHook {

  @Override
  public void beforeCompile(QueryLifeTimeHookContext ctx) {
  }

  @Override
  public void afterCompile(QueryLifeTimeHookContext ctx, boolean hasError) {
  }

  @Override
  public void beforeExecution(QueryLifeTimeHookContext ctx) {
  }

  @Override
  public void afterExecution(QueryLifeTimeHookContext ctx, boolean hasError) {
    if (hasError) {
      return;
    }
    HiveConf hiveConf = ctx.getHiveConf();
    try {
      List<TaskRunner> completedTasks = ctx.getHookContext().getCompleteTaskList();
      for (TaskRunner taskRunner : completedTasks) {
        Task<? extends Serializable> task = taskRunner.getTask();
        if (task instanceof DDLTask) {
          DDLTask ddlTask = (DDLTask) task;
          DDLWork work = ddlTask.getWork();
          String tableName = null;
          if (work.getCreateViewDesc() != null && work.getCreateViewDesc().isMaterialized()) {
            tableName = work.getCreateViewDesc().toTable(hiveConf).getFullyQualifiedName();
          }
          if (work.getAlterMaterializedViewDesc() != null) {
            tableName = work.getAlterMaterializedViewDesc().getMaterializedViewName();
          }
          if (tableName == null) {
            continue;
          }
          Table mvTable = Hive.get().getTable(tableName);

          if (mvTable.isRewriteEnabled()) {
            HiveMaterializedViewsRegistry.get().addMaterializedView(mvTable);
          } else {
            HiveMaterializedViewsRegistry.get().dropMaterializedView(mvTable);
          }
        }
      }
    } catch (HiveException e) {
      if (HiveConf.getBoolVar(hiveConf, ConfVars.HIVE_MATERIALIZED_VIEW_ENABLE_AUTO_REWRITING)) {
        throw new RuntimeException("Error updating materialized view cache");
      }
    }
  }

}
