package com.ismailmekni.mxreflection.beans;

import com.ismailmekni.mxreflection.annotations.Expression;
import com.ismailmekni.mxreflection.annotations.Arg;

public class DuplicateNameTestBean {


    @Arg("f1")
    public int field1;

    @Arg("f1")
    public int field2;

    @Expression("f1 + field2")
    public int field3;
}
