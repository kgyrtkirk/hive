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

package org.apache.hadoop.hive.ql.exec.tez;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.exec.vector.mapjoin.fast.VectorMapJoinFastTableContainer;
import org.apache.hadoop.hive.ql.optimizer.ConvertJoinMapJoin;
import org.apache.hadoop.hive.ql.plan.MapJoinDesc;
import org.apache.hadoop.hive.ql.plan.Statistics;
import org.apache.hadoop.hive.ql.plan.VectorMapJoinDesc;
import org.apache.hadoop.hive.ql.plan.VectorMapJoinDesc.HashTableImplementationType;
import org.apache.hadoop.hive.ql.plan.VectorMapJoinDesc.HashTableKeyType;
import org.apache.hadoop.hive.ql.plan.VectorMapJoinDesc.HashTableKind;
import org.apache.hadoop.hive.serde2.ByteStream.Output;
import org.apache.hadoop.hive.serde2.binarysortable.fast.BinarySortableSerializeWrite;
import org.apache.hadoop.io.BytesWritable;
import org.junit.Test;
import org.openjdk.jol.info.GraphLayout;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

public class asd {

  @Test
  public void a() throws Exception {
    MapJoinDesc desc = new MapJoinDesc();
    VectorMapJoinDesc vectorDesc = new VectorMapJoinDesc();
    vectorDesc.setHashTableKeyType(HashTableKeyType.LONG);
    //    vectorDesc.setHashTableKeyType(HashTableKeyType.MULTI_KEY);
    vectorDesc.setIsFastHashTableEnabled(true);
    vectorDesc.setHashTableImplementationType(HashTableImplementationType.FAST);
    //    vectorDesc.setHashTableKind(HashTableKind.HASH_MAP);
    vectorDesc.setHashTableKind(HashTableKind.HASH_MAP);
    desc.setVectorDesc(vectorDesc);
    Configuration hconf = new HiveConf();
    long keyCount = 10_000_000;
    //    MapJoinBytesTableContainer container = new MapJoinBytesTableContainer(hconf, null, keyCount, 11111l);
    VectorMapJoinFastTableContainer container = new VectorMapJoinFastTableContainer(desc, hconf, keyCount);

    container.setSerde(null, null);


    long dataSize = 0;
    //    ByteBuffer.allocate(8);
    BytesWritable value = new BytesWritable("123456789".getBytes());
    LongBuffer lb = LongBuffer.allocate(1);

    byte b[] = new byte[8];
    ByteBuffer bb = ByteBuffer.wrap(b);

    BinarySortableSerializeWrite nsw = new BinarySortableSerializeWrite(1);

    Output outp = new Output();
    Output outp2 = new Output();
    for (int i = 0; i < keyCount; i++) {
      nsw.set(outp);
      nsw.writeLong(i);
      nsw.set(outp2);
      nsw.writeLong(i * 2);

      //      nsw.
      //      bb.rewind();
//      bb.putLong(i);
      BytesWritable key = new BytesWritable(outp.getData());
      value = new BytesWritable(outp2.getData());
      container.putRow(key, value);
      dataSize += key.getLength();
      dataSize += 8;//value.getLength();
    }

    //    new Vecor

    GraphLayout x = GraphLayout.parseInstance(container); //    LOG.info("FFFF");
    System.out.println(x.toFootprint());

    System.out.println(container.getEstimatedMemorySize());
    Statistics stat = new Statistics(keyCount, dataSize, 0);
    ConvertJoinMapJoin cjm = new ConvertJoinMapJoin();
    cjm.hashTableLoadFactor = .75f;
    long est = cjm.computeOnlineDataSize(stat);
    long estO = cjm.computeOnlineDataSizeOptimized(stat);

    System.out.println("compilerF2:" + cjm.computeOnlineDataSizeFast2(stat));
    System.out.println("compilerF3:" + cjm.computeOnlineDataSizeFast3(stat));
    System.out.println("compilerO:" + cjm.computeOnlineDataSizeOptimized(stat));
    long s = ObjectSizeCalculator.getObjectSize(container);
    System.out.println("osc:" + s);

    //    LOG.info(x.toFootprint());
    //    LOG.info("Finished loading hash table for input: {} cacheKey: {} numEntries: {} " +
    //        "estimatedMemoryUsage: {}", inputName, cacheKey, numEntries,
    //        vectorMapJoinFastTableContainer.getEstimatedMemorySize());
  }


}
