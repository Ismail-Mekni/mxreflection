package com.ismail.mathreflection.utilities;

import com.ismail.mathreflection.annotations.MXFormula;
import com.ismail.mathreflection.annotations.Variable;

public class BeanTestDuplicateName {


    @Variable("f1")
    public int field1;

    @Variable("f1")
    public int field2;

    @MXFormula("f1 + field2")
    public int field3;
}
