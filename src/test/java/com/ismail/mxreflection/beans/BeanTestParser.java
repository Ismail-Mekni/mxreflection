package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.MXFormula;
import com.ismail.mxreflection.annotations.Variable;

public class BeanTestParser {


    @Variable("f1")
    public String field1;

    @Variable("f2")
    public int field2;

    @MXFormula("f1 - f2")
    public double field3;

    @MXFormula("f1 + f2 + field3")
    public Double field4;
}
