package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.MXFormula;
import com.ismail.mxreflection.annotations.Variable;

import java.math.BigInteger;

public class BeanTestParserWrite {


    @Variable("f1")
    public String field1;

    @Variable("f2")
    public int field2;

    @MXFormula("f1 - f2")
    public String field3;

    @MXFormula("f1 * f2")
    public double field5;

    @MXFormula("sin(f2)")
    public Double field6;

    @MXFormula("f1 - f2")
    public long field9;

    @MXFormula("f1 - f2")
    public Long field10;

    @MXFormula("f1 - f2")
    public BigInteger field15;

}