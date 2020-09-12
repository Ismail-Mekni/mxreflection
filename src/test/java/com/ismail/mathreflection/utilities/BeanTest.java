package com.ismail.mathreflection.utilities;

import com.ismail.mathreflection.annotations.MXFormula;
import com.ismail.mathreflection.annotations.Variable;

public class BeanTest {

    @Variable("f1")
    public double field1;

    @Variable("f2")
    public double field2;

    @MXFormula("f1 - f2")
    public double field3;

    @MXFormula("f1 + f2 + field3")
    public double field4;
}
