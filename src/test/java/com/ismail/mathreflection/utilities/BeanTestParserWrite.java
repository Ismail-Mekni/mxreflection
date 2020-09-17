package com.ismail.mathreflection.utilities;

import com.ismail.mathreflection.annotations.MXFormula;
import com.ismail.mathreflection.annotations.Variable;

public class BeanTestParserWrite {


    @Variable("f1")
    public String field1;

    @Variable("f2")
    public int field2;

    @MXFormula("f1 - f2")
    public Integer field3;

    @MXFormula("f1 + f2 + field3")
    public String field4;

    @MXFormula("f1 * field4")
    public double field5;

    @MXFormula("sin(f2)")
    public Double field6;
}
