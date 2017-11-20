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

package org.apache.hadoop.hive.ql.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.plan.AggregationDesc;
import org.apache.hadoop.hive.ql.plan.ExprNodeConstantDesc;
import org.apache.hadoop.hive.ql.plan.GroupByDesc;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFEvaluator.AbstractAggregationBuffer;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.LazySimpleStructObjectInspector;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyObjectInspectorParameters;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.IntWritable;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.google.common.collect.Lists;

public class TestGroupByOperator {

  public static class VoidAnswer implements Answer<Void> {

    @Override
    public Void answer(InvocationOnMock invocation) throws Throwable {
      return null;
    }
  }

  @Test
  public void checkEmptyAggregationConstantPassing() throws Exception {

    ExprNodeConstantDesc desc = new ExprNodeConstantDesc(137);

    GroupByOperator operator = new GroupByOperator() {
      private static final long serialVersionUID = 1L;

      protected void forward(Object row, ObjectInspector rowInspector) throws HiveException {
      }
    };
    GroupByDesc conf = new GroupByDesc();
    AggregationDesc aggregator = new AggregationDesc();

    GenericUDAFEvaluator evaluator = Mockito.mock(GenericUDAFEvaluator.class);
    doReturn(mock(AbstractAggregationBuffer.class)).when(evaluator).getNewAggregationBuffer();
    aggregator.setGenericUDAFEvaluator(evaluator);
    aggregator.setParameters(Lists.newArrayList(desc));

    conf.setAggregators(Lists.newArrayList(aggregator));
    conf.setKeys(Lists.newArrayList());
    conf.setOutputColumnNames(Lists.newArrayList());

    List<String> structFieldNames = Lists.newArrayList("asd");
    List<ObjectInspector> structFieldObjectInspectors = Lists.newArrayList(desc.getWritableObjectInspector());
    List<String> structFieldComments = Lists.newArrayList("asd");
    byte separator = 'x';
    LazyObjectInspectorParameters lazyParams = null;

    LazySimpleStructObjectInspector sso = new LazySimpleStructObjectInspector(structFieldNames, structFieldObjectInspectors,
        structFieldComments, separator, lazyParams);
    operator.setInputObjInspectors(new ObjectInspector[] { sso });
    operator.setConf(conf);
    operator.initializeOp(new HiveConf());
    operator.closeOp(false);

    ArgumentCaptor<Object[]> argument = (ArgumentCaptor<Object[]>) ArgumentCaptor.forClass(new Object[] {}.getClass());
    verify(evaluator).aggregate(Mockito.any(), argument.capture());
    Object[] v = argument.getValue();
    assertNotNull("expected an argument value", v[0]);
    IntWritable iw = (IntWritable) v[0];

    assertEquals(desc.getValue(), iw.get());

  }
}
