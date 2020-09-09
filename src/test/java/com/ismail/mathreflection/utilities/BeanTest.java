package com.ismail.mathreflection.utilities;

import com.ismail.mathreflection.annotations.MXFormula;
import com.ismail.mathreflection.annotations.Variable;

public class BeanTest {

    @Variable("f1")
    public int field1;

    @Variable("f2")
    public int field2;

    @MXFormula("f1 - f2")
    public int field3;

    @MXFormula("f1 + f2 + field3")
    public int field4;
}
