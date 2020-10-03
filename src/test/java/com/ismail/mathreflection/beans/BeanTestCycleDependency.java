package com.ismail.mathreflection.beans;

import com.ismail.mathreflection.annotations.MXFormula;

public class BeanTestCycleDependency {


    @MXFormula("field2 + field4")
    public int field1;

    public int field2;

    @MXFormula("field1 + field2")
    public int field3;

    @MXFormula("field5 + field3")
    public int field4;

    public int field5;
}
