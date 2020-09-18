package com.ismail.mathreflection.utilities;

import com.ismail.mathreflection.annotations.MXFormula;
import com.ismail.mathreflection.annotations.Variable;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BeanTestParserWrite {


    @Variable("f1")
    public String field1;

    @Variable("f2")
    public int field2;

    @MXFormula("f1 - f2")
    public String field3;

    @MXFormula("f1 - f2")
    public Integer field4;

    @MXFormula("f1 * field4")
    public double field5;

    @MXFormula("sin(f2)")
    public Double field6;

    @MXFormula("f1 - f2")
    public float field7;

    @MXFormula("f1 - f2")
    public Float field8;

    @MXFormula("f1 - f2")
    public long field9;

    @MXFormula("f1 - f2")
    public Long field10;

    @MXFormula("f1 - f2")
    public short field11;

    @MXFormula("f1 - f2")
    public Short field12;

    @MXFormula("f1 - f2")
    public Byte field14;

    @MXFormula("f1 - f2")
    public BigInteger field15;

    @MXFormula("f1 - f2")
    public BigDecimal field16;
}
