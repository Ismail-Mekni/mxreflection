package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.MXFormula;
import com.ismail.mxreflection.annotations.Variable;

public class BeanTestPrivateVariable {


    @Variable("f1")
    private double field1;

    @Variable("f2")
    private double field2;

    public BeanTestPrivateVariable(double field1, double field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    @MXFormula("f1 - f2")
    public double field3;

    @MXFormula("f1 + f2 + field3")
    public double field4;
}
