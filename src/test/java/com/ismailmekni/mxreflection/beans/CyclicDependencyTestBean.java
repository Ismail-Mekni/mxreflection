package com.ismailmekni.mxreflection.beans;

import com.ismailmekni.mxreflection.annotations.Expression;

public class CyclicDependencyTestBean {


    @Expression("field2 + field4")
    public int field1;

    public int field2;

    @Expression("field1 + field2")
    public int field3;

    @Expression("field5 + field3")
    public int field4;

    public int field5;
}
