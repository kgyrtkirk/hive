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

package org.apache.hadoop.hive.ql.qoption;

import org.apache.hadoop.hive.ql.QTestUtil;
import org.apache.hive.testutils.HiveTestEnvSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QX implements QTestOptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(QX.class.getName());

	@Override
	public void processArguments(String arguments) {
	}

	@Override
	public void beforeTest(QTestUtil qt) throws Exception {
		String stsdbPath = HiveTestEnvSetup.HIVE_ROOT + "/metastore/scripts/upgrade/hive/hive-schema-4.0.0.hive.sql";
		qt.getCliDriver().processLine("source " + stsdbPath);
	}

	@Override
	public void afterTest(QTestUtil qt) throws Exception {

	}

}
