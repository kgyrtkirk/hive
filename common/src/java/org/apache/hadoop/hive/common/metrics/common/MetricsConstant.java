/*
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
package org.apache.hadoop.hive.common.metrics.common;

/**
 * This class defines some metrics generated by Hive processes.
 */
public class MetricsConstant {

  public static final String API_PREFIX = "api_";
  public static final String ACTIVE_CALLS = "active_calls_";

  public static final String JVM_PAUSE_INFO = "jvm.pause.info-threshold";
  public static final String JVM_PAUSE_WARN = "jvm.pause.warn-threshold";
  public static final String JVM_EXTRA_SLEEP = "jvm.pause.extraSleepTime";

  public static final String OPEN_CONNECTIONS = "open_connections";
  public static final String OPEN_OPERATIONS = "open_operations";
  public static final String CUMULATIVE_CONNECTION_COUNT = "cumulative_connection_count";

  public static final String METASTORE_HIVE_LOCKS = "metastore_hive_locks";
  public static final String ZOOKEEPER_HIVE_SHAREDLOCKS = "zookeeper_hive_sharedlocks";
  public static final String ZOOKEEPER_HIVE_EXCLUSIVELOCKS = "zookeeper_hive_exclusivelocks";
  public static final String ZOOKEEPER_HIVE_SEMISHAREDLOCKS = "zookeeper_hive_semisharedlocks";

  public static final String EXEC_ASYNC_QUEUE_SIZE = "exec_async_queue_size";
  public static final String EXEC_ASYNC_POOL_SIZE = "exec_async_pool_size";

  public static final String OPERATION_PREFIX = "hs2_operation_";
  public static final String COMPLETED_OPERATION_PREFIX = "hs2_completed_operation_";

  public static final String SQL_OPERATION_PREFIX = "hs2_sql_operation_";
  public static final String COMPLETED_SQL_OPERATION_PREFIX = "hs2_completed_sql_operation_";

  // The number of Hive operations that are waiting to enter the compile block
  public static final String WAITING_COMPILE_OPS = "waiting_compile_ops";

  // The number of map reduce tasks executed by the HiveServer2 since the last restart
  public static final String HIVE_MR_TASKS = "hive_mapred_tasks";
  // The number of spark tasks executed by the HiveServer2 since the last restart
  public static final String HIVE_SPARK_TASKS = "hive_spark_tasks";
  // The number of tez tasks executed by the HiveServer2 since the last restart
  public static final String HIVE_TEZ_TASKS = "hive_tez_tasks";
  public static final String HS2_OPEN_SESSIONS = "hs2_open_sessions";
  public static final String HS2_ACTIVE_SESSIONS = "hs2_active_sessions";
  public static final String HS2_ABANDONED_SESSIONS = "hs2_abandoned_sessions";
  public static final String HS2_AVG_OPEN_SESSION_TIME = "hs2_avg_open_session_time";
  public static final String HS2_AVG_ACTIVE_SESSION_TIME = "hs2_avg_active_session_time";

  public static final String HS2_SUBMITTED_QURIES = "hs2_submitted_queries";
  public static final String HS2_COMPILING_QUERIES = "hs2_compiling_queries";
  public static final String HS2_EXECUTING_QUERIES = "hs2_executing_queries";
  public static final String HS2_FAILED_QUERIES = "hs2_failed_queries";
  public static final String HS2_SUCCEEDED_QUERIES = "hs2_succeeded_queries";
}