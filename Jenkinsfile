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

properties([
    // max 5 build/branch/day
//    rateLimitBuilds(throttle: [count: 5, durationName: 'day', userBoost: true]),
    // do not run multiple testruns on the same branch
  //  disableConcurrentBuilds(),
    parameters([
        string(name: 'SPLIT', defaultValue: '1', description: 'Number of buckets to split tests into.'),
        string(name: 'OPTS', defaultValue: '', description: 'additional maven opts'),
    ])
])

node {
  label 'master'
    sh 'sleep 30'
}
