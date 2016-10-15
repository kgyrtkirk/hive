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

package org.apache.hadoop.hive.ql.udf.generic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hive.common.type.HiveIntervalDayTime;
import org.apache.hadoop.hive.common.type.HiveIntervalYearMonth;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.HiveParser;
import org.apache.hadoop.hive.ql.plan.ExprNodeConstantDesc;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDFUtils.ReturnObjectInspectorResolver;
import org.apache.hadoop.hive.serde2.io.HiveIntervalDayTimeWritable;
import org.apache.hadoop.hive.serde2.io.HiveIntervalYearMonthWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector.Category;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.AbstractPrimitiveWritableObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils.PrimitiveGrouping;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hive.common.util.DateUtils;

/**
 * GenericUDF Class for support of "INTERVAL (expression) (DAY|YEAR|...)".
 */
@Description(name = "internal_interval", value = "_FUNC_(a1,a2) - later", extended = "Example:\n "
    + "probably later I may write something here...")

public class GenericUDFInternalInterval extends GenericUDF {
  private transient ObjectInspector[] argumentOIs;
  private transient GenericUDFUtils.ReturnObjectInspectorResolver returnOIResolver;
  private transient AbstractPrimitiveWritableObjectInspector resultOI;

  protected transient HiveIntervalDayTimeWritable intervalDayTimeResult =
      new HiveIntervalDayTimeWritable();
  private transient Integer operationMode;
  private transient IntervalProcessor processor;
  private transient PrimitiveObjectInspector inputOI;

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {

    argumentOIs = arguments;

    // read operation mode
    if (!(arguments[1] instanceof ConstantObjectInspector)) {
      throw new UDFArgumentTypeException(1,
          getFuncName() + ": may only accept constant as second argument");
    }
    operationMode = getConstantIntValue(arguments, 1);
    if (operationMode == null) {
      throw new UDFArgumentTypeException(1, "must supply operationmode");
    }

    processor = getProcessorMap().get(operationMode);
    if (processor == null) {
      throw new UDFArgumentTypeException(1,
          getFuncName() + ": unsupported operationMode: " + operationMode);
    }

    // FIXME: check arg0
    if (arguments[0].getCategory() != Category.PRIMITIVE) {
      throw new UDFArgumentTypeException(0,
          "The first argument to " + getFuncName() + " must be primitive");
    }

    inputOI = (PrimitiveObjectInspector) arguments[0];

    // if (PrimitiveGrouping.STRING_GROUP != PrimitiveObjectInspectorUtils
    // .getPrimitiveGrouping(inputOI.getPrimitiveCategory())) {
    // throw new UDFArgumentTypeException(0,
    // "The first argument to "+getFuncName()+" must be fromstring group");
    // }

    resultOI = PrimitiveObjectInspectorFactory
        .getPrimitiveWritableObjectInspector(processor.getTypeInfo());

    return resultOI;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    String argString = PrimitiveObjectInspectorUtils.getString(arguments[0].get(), inputOI);
    return processor.evaluate(argString);
    // stringResolver.convertIfNecessary(arguments[0], TypeInfoFactory.stringTypeInfo, false);
    //
    // if (PrimitiveObjectInspectorUtils.comparePrimitiveObjects(
    // arg0, compareOI,
    // ) {
    // return null;
    // }
    //
    //// Object arg0 = arguments[0].get();
    //// Object arg1 = arguments[1].get();
    //// if (arg0 == null || arg1 == null) {
    //// return arg0;
    //// }
    //// PrimitiveObjectInspector compareOI = (PrimitiveObjectInspector) returnOIResolver.get();
    //// if (PrimitiveObjectInspectorUtils.comparePrimitiveObjects(
    //// arg0, compareOI,
    //// returnOIResolver.convertIfNecessary(arg1, argumentOIs[1], false), compareOI)) {
    //// return null;
    //// }
    // HiveIntervalDayTime hidt = new HiveIntervalDayTime(100000,0);
    // intervalDayTimeResult.set(hidt);
    // return intervalDayTimeResult;
  }

  private static interface IntervalProcessor {

    Integer getKey();

    PrimitiveTypeInfo getTypeInfo();

    Object evaluate(String arg) throws UDFArgumentException;
  }

  private static abstract class AbstractDayTimeIntervalProcessor implements IntervalProcessor {
    private transient HiveIntervalDayTimeWritable intervalResult =
        new HiveIntervalDayTimeWritable();

    @Override
    public final PrimitiveTypeInfo getTypeInfo() {
      return TypeInfoFactory.intervalDayTimeTypeInfo;
    }

    @Override
    public final Object evaluate(String arg) throws UDFArgumentException {
      intervalResult.set(getIntervalDayTime(arg));
      return intervalResult;
    }

    abstract protected HiveIntervalDayTime getIntervalDayTime(String arg);
  }

  private static abstract class AbstractYearMonthIntervalProcessor implements IntervalProcessor {
    private transient HiveIntervalYearMonthWritable intervalResult =
        new HiveIntervalYearMonthWritable();

    @Override
    public final PrimitiveTypeInfo getTypeInfo() {
      return TypeInfoFactory.intervalYearMonthTypeInfo;
    }

    @Override
    public final Object evaluate(String arg) throws UDFArgumentException {
      intervalResult.set(getIntervalYearMonth(arg));
      return intervalResult;
    }

    abstract protected HiveIntervalYearMonth getIntervalYearMonth(String arg);
  }
  
  private static class IntervalDayLiteralProcessor extends AbstractDayTimeIntervalProcessor {

    @Override
    public Integer getKey() {
      return HiveParser.TOK_INTERVAL_DAY_LITERAL;
    }

    @Override
    protected HiveIntervalDayTime getIntervalDayTime(String arg) {
      return new HiveIntervalDayTime(Integer.parseInt(arg), 0, 0, 0, 0);
    }
  }

  private static class IntervalHourLiteralProcessor extends AbstractDayTimeIntervalProcessor {
    @Override
    public Integer getKey() {
      return HiveParser.TOK_INTERVAL_HOUR_LITERAL;
    }

    @Override
    protected HiveIntervalDayTime getIntervalDayTime(String arg) {
      return new HiveIntervalDayTime(0, Integer.parseInt(arg), 0, 0, 0);
    }
  }
  private static class IntervalMinuteLiteralProcessor extends AbstractDayTimeIntervalProcessor {
    @Override
    public Integer getKey() {
      return HiveParser.TOK_INTERVAL_MINUTE_LITERAL;
    }

    @Override
    protected HiveIntervalDayTime getIntervalDayTime(String arg) {
      return new HiveIntervalDayTime(0, 0, Integer.parseInt(arg), 0, 0);
    }
  }
  private static class IntervalSecondLiteralProcessor extends AbstractDayTimeIntervalProcessor {
    
    private static final BigDecimal NANOS_PER_SEC_BD = new BigDecimal(DateUtils.NANOS_PER_SEC);
    
    @Override
    public Integer getKey() {
      return HiveParser.TOK_INTERVAL_SECOND_LITERAL;
    }

    @Override
    protected HiveIntervalDayTime getIntervalDayTime(String arg) {
       BigDecimal bd = new BigDecimal(arg);
       BigDecimal bdSeconds = new BigDecimal(bd.toBigInteger());
       BigDecimal bdNanos = bd.subtract(bdSeconds);
       new HiveIntervalDayTime(0, 0, 0, bdSeconds.intValueExact(),
       bdNanos.multiply(NANOS_PER_SEC_BD).intValue());

      return new HiveIntervalDayTime(0, 0, Integer.parseInt(arg), 0, 0);
    }
  }

  private static class IntervalDayTimeLiteralProcessor extends AbstractDayTimeIntervalProcessor {
    
    @Override
    public Integer getKey() {
      return HiveParser.TOK_INTERVAL_DAY_TIME_LITERAL;
    }

    @Override
    protected HiveIntervalDayTime getIntervalDayTime(String arg) {
      return HiveIntervalDayTime.valueOf(arg);
    }
  }
  
  
  private static class IntervalYearMonthLiteralProcessor extends AbstractYearMonthIntervalProcessor {
    
    @Override
    public Integer getKey() {
      return HiveParser.TOK_INTERVAL_YEAR_MONTH_LITERAL;
    }

    @Override
    protected HiveIntervalYearMonth getIntervalYearMonth(String arg) {
      return HiveIntervalYearMonth.valueOf(arg);
    }
  }
  private static class IntervalYearLiteralProcessor extends AbstractYearMonthIntervalProcessor {
    
    @Override
    public Integer getKey() {
      return HiveParser.TOK_INTERVAL_YEAR_LITERAL;
    }

    @Override
    protected HiveIntervalYearMonth getIntervalYearMonth(String arg) {
      return new HiveIntervalYearMonth(Integer.parseInt(arg),0);
    }
  }

  private static class IntervalMonthLiteralProcessor extends AbstractYearMonthIntervalProcessor {

    @Override
    public Integer getKey() {
      return HiveParser.TOK_INTERVAL_MONTH_LITERAL;
    }

    @Override
    protected HiveIntervalYearMonth getIntervalYearMonth(String arg) {
      return new HiveIntervalYearMonth(0, Integer.parseInt(arg));
    }
  }

  private static Map<Integer, IntervalProcessor> getProcessorMap() {
    
    Map<Integer, IntervalProcessor> ret = new HashMap<>();
    IntervalProcessor ips[]=new IntervalProcessor[]{
        new IntervalDayTimeLiteralProcessor(),
        
        new IntervalDayLiteralProcessor(),
        new IntervalHourLiteralProcessor(),
        new IntervalMinuteLiteralProcessor(),
        new IntervalSecondLiteralProcessor(),
        
        new IntervalYearMonthLiteralProcessor(),
        
        new IntervalYearLiteralProcessor(),
        new IntervalMonthLiteralProcessor(),
    };
    
    for (IntervalProcessor ip : ips) {
      ret.put(ip.getKey(), ip);
    }

    // switch (expr.getType()) {
//    // case HiveParser.TOK_INTERVAL_YEAR_MONTH_LITERAL:
    // return new ExprNodeConstantDesc(TypeInfoFactory.intervalYearMonthTypeInfo,
    // HiveIntervalYearMonth.valueOf(intervalString));
//    // case HiveParser.TOK_INTERVAL_DAY_TIME_LITERAL:
    // return new ExprNodeConstantDesc(TypeInfoFactory.intervalDayTimeTypeInfo,
    // HiveIntervalDayTime.valueOf(intervalString));
  //  // case HiveParser.TOK_INTERVAL_YEAR_LITERAL:
    // return new ExprNodeConstantDesc(TypeInfoFactory.intervalYearMonthTypeInfo,
    // new HiveIntervalYearMonth(Integer.parseInt(intervalString), 0));
//    // case HiveParser.TOK_INTERVAL_MONTH_LITERAL:
    // return new ExprNodeConstantDesc(TypeInfoFactory.intervalYearMonthTypeInfo,
    // new HiveIntervalYearMonth(0, Integer.parseInt(intervalString)));
//    // case HiveParser.TOK_INTERVAL_DAY_LITERAL:
    // return new ExprNodeConstantDesc(TypeInfoFactory.intervalDayTimeTypeInfo,
    // // FIXME HIVE-13557 remove this hoax
    // new HiveIntervalDayTime(Integer.parseInt("42"), 0, 0, 0, 0));
//    // case HiveParser.TOK_INTERVAL_HOUR_LITERAL:
    // return new ExprNodeConstantDesc(TypeInfoFactory.intervalDayTimeTypeInfo,
    // new HiveIntervalDayTime(0, Integer.parseInt(intervalString), 0, 0, 0));
//    // case HiveParser.TOK_INTERVAL_MINUTE_LITERAL:
    // return new ExprNodeConstantDesc(TypeInfoFactory.intervalDayTimeTypeInfo,
    // new HiveIntervalDayTime(0, 0, Integer.parseInt(intervalString), 0, 0));
    // case HiveParser.TOK_INTERVAL_SECOND_LITERAL:
    // BigDecimal bd = new BigDecimal(intervalString);
    // BigDecimal bdSeconds = new BigDecimal(bd.toBigInteger());
    // BigDecimal bdNanos = bd.subtract(bdSeconds);
    // return new ExprNodeConstantDesc(TypeInfoFactory.intervalDayTimeTypeInfo,
    // new HiveIntervalDayTime(0, 0, 0, bdSeconds.intValueExact(),
    // bdNanos.multiply(NANOS_PER_SEC_BD).intValue()));
    // default:
//    // throw new IllegalArgumentException("Invalid time literal type " + expr.getType());
    // }

    return ret;
  }

  @Override
  public String getDisplayString(String[] children) {
    return getStandardDisplayString("INTERNAL_INTERVAL", children, ",");
  }

}
