package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.MXFormula;
import com.ismail.mxreflection.annotations.Variable;

public class BeanTestDuplicateName {


    @Variable("f1")
    public int field1;

    @Variable("f1")
    public int field2;

    @MXFormula("f1 + field2")
    public int field3;
}
