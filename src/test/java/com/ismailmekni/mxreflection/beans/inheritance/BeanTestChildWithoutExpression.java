package com.ismailmekni.mxreflection.beans.inheritance;

import com.ismailmekni.mxreflection.annotations.Arg;

public class BeanTestChildWithoutExpression extends ParentWithExpressionTestBean {

    @Arg("f1")
    private double field1;

    @Arg("f2")
    private double field2;

    public double getField2() {
        return field2;
    }

    public void setField2(double field2) {
        this.field2 = field2;
    }

    public double getField1() {
        return field1;
    }

    public void setField1(double field1) {
        this.field1 = field1;
    }
}
