package com.ismail.mathreflection.beans;

import com.ismail.mathreflection.annotations.MXFormula;
import com.ismail.mathreflection.annotations.Variable;

import java.util.Map;

public class BeanTestUnparseableField {
    @Variable("f1")
    public Map field1;

    @Variable("f2")
    public int field2;

    @MXFormula("f1 - f2")
    public double field3;

    @MXFormula("f1 + f2 + field3")
    public Double field4;
}
