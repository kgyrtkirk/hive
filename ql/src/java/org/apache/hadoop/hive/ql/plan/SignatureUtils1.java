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

package org.apache.hadoop.hive.ql.plan;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SignatureUtils1 {

  private static Map<Class<?>, SignatureMapper> mappers;

  public static void write(Map<String, Object> ret, Object o) {
    SignatureMapper mapper = getSigMapper(o.getClass());
    mapper.write(ret, o);
  }

  static class SignatureMapper {

    private List<Method> sigMethods;

    public SignatureMapper(Class<?> o) {
      Method[] f = o.getMethods();
      sigMethods = new ArrayList<>();
      for (Method method : f) {
        if (method.isAnnotationPresent(Signature.class)) {
          sigMethods.add(method);
        }
      }
    }

    public void write(Map<String, Object> ret, Object o) {
      if (sigMethods.isEmpty()) {
        // by supplying "this" we start relying on equals/hashcode
        // which will most probably make the signature very unique
        ret.put(o.getClass().getName(), this);
      } else {
        ret.put(o.getClass().getName(), "1");
        for (Method method : sigMethods) {
          try {
            ret.put(method.getName(), method.invoke(o));
          } catch (Exception e) {
            throw new RuntimeException("Error invoking signature method", e);
          }
        }
      }
    }

  }

  private static SignatureMapper getSigMapper(Class<?> o) {
    SignatureMapper m = mappers.get(o);
    if (m == null) {
      m = new SignatureMapper(o);
      mappers.put(o, m);
    }
    return m;
  }

}
