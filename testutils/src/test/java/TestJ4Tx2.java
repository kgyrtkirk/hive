import org.junit.Test;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

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


public class TestJ4Tx2 {

  public static class E1 implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
      System.out.println(getClass() + " beforeEach");
      System.out.println(getClass() + " v: " + context.getStore(Namespace.GLOBAL).get("asd"));
      context.getStore(Namespace.GLOBAL).put("asd", "YYY");
    }

  }

  public static class E2 implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
      context.getStore(Namespace.GLOBAL).put("asd", "xxx");
      System.out.println(getClass() + " beforeEach");
    }

  }

  @Test
  public void asdf() {
    System.out.println("asd");
  }
}
