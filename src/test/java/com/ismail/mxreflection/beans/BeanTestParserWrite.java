package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.Expression;
import com.ismail.mxreflection.annotations.Arg;

import java.math.BigInteger;

public class BeanTestParserWrite {


    @Arg("f1")
    public String field1;

    @Arg("f2")
    public int field2;

    @Expression("f1 - f2")
    public String field3;

    @Expression("f1 * f2")
    public double field5;

    @Expression("sin(f2)")
    public Double field6;

    @Expression("f1 - f2")
    public long field9;

    @Expression("f1 - f2")
    public Long field10;

    @Expression("f1 - f2")
    public BigInteger field15;

}
