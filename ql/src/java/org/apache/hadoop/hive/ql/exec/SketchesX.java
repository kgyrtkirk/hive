package org.apache.hadoop.hive.ql.exec;

import org.apache.datasketches.hive.hll.*;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDAFResolver2;

public class SketchesX {

  private final Registry system;

  public SketchesX(Registry system) {
    this.system = system;
  }

  public static void register(Registry system) {
    new SketchesX(system).registerHll("hll");
  }

  private void registerHll(String prefix) {

    register(new DataToSketchUDAF(), prefix);
    register(SketchToEstimateAndErrorBoundsUDF.class, prefix);
    register(SketchToEstimateUDF.class, prefix);
    register(SketchToStringUDF.class, prefix);
    register(UnionSketchUDF.class, prefix);
    register(new UnionSketchUDAF(), prefix);

  }

  private void register(Class<? extends UDF> udfClass, String prefix) {
    String name = getUDFName(udfClass);
    system.registerUDF(prefix + name, udfClass, false);
  }

  private void register(GenericUDAFResolver2 udaf, String prefix) {
    String name = getUDFName(udaf.getClass());
    system.registerGenericUDAF(prefix + name, new DataToSketchUDAF());
  }

  private String getUDFName(Class<?> clazz) {
    Description desc = getDescription(clazz);
    String name = desc.name().toLowerCase();
    if (name == null || name == "") {
      throw new RuntimeException("The UDF class (" + clazz.getName() + ") doesn't have a valid name");
    }
    return name;
  }

  private Description getDescription(Class<?> clazz) {
    Description desc = clazz.getAnnotation(Description.class);
    if (desc == null) {
      throw new RuntimeException("no Description annotation on class: " + clazz.getName());
    }
    return desc;
  }

}
