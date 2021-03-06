package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.Expression;
import com.ismail.mxreflection.annotations.Arg;

public class BeanTestDuplicateName {


    @Arg("f1")
    public int field1;

    @Arg("f1")
    public int field2;

    @Expression("f1 + field2")
    public int field3;
}
