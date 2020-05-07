import java.time.ZonedDateTime;
import java.util.Optional;

import org.apache.hadoop.hive.metastore.api.InvalidInputException;

import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;

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

public class asd {

  public static void main(String[] args) throws Exception {
    CronType cronType = CronType.QUARTZ;

    CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(cronType);
    CronParser parser = new CronParser(cronDefinition);

    // Get date for last execution
    try {
      ZonedDateTime now = ZonedDateTime.now();
      ExecutionTime executionTime = ExecutionTime.forCron(parser.parse("0 0 0 1 * ? 2016"));
      Optional<ZonedDateTime> nextExecution = executionTime.nextExecution(now);
      if (!nextExecution.isPresent()) {
        System.out.println("n/a");
      } else {
        System.out.println(nextExecution.get().toEpochSecond());
      }
    } catch (IllegalArgumentException iae) {
      throw new InvalidInputException("x");
    }

  }

}
