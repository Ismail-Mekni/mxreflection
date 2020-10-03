package com.ismail.mathreflection.beans;

import com.ismail.mathreflection.annotations.MXFormula;
import com.ismail.mathreflection.annotations.Variable;

public class BeanTestPrivateFormula {


    @Variable("f1")
    private double field1;

    @Variable("f2")
    private double field2;

    public BeanTestPrivateFormula(double field1, double field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    @MXFormula("f1 - f2")
    private double field3;

    @MXFormula("f1 + f2 + field3")
    private double field4;

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
}
