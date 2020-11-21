package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.MXFormula;
import com.ismail.mxreflection.annotations.Variable;

public class BeanTest {

    @Variable("f1")
    public double field1;

    @Variable("f2")
    public double field2;

    @MXFormula("f1 - f2")
    public double field3;

    @MXFormula("f1 + f2 + field3")
    public double field4;

    private String field5;

    public BeanTest(double field1, double field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public BeanTest() {
    }

    public double getField1() {
        return field1;
    }

    public void setField1(double field1) {
        this.field1 = field1;
    }

    public double getField2() {
        return field2;
    }

    public void setField2(double field2) {
        this.field2 = field2;
    }

    public double getField3() {
        return field3;
    }

    public void setField3(double field3) {
        this.field3 = field3;
    }

    public double getField4() {
        return field4;
    }

    public void setField4(double field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }
}
