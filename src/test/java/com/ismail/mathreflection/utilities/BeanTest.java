package com.ismail.mathreflection.utilities;

import com.ismail.mathreflection.annotations.MXFormula;

public class BeanTest {

    public int field1;

    public int field2;

    @MXFormula("field1 + field2")
    public int field3;
}
