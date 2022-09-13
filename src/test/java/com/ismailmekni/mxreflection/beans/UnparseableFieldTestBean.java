package com.ismailmekni.mxreflection.beans;

import com.ismailmekni.mxreflection.annotations.Expression;
import com.ismailmekni.mxreflection.annotations.Arg;

import java.util.Map;

public class UnparseableFieldTestBean {
    @Arg("f1")
    public Map field1;

    @Arg("f2")
    public int field2;

    @Expression("f1 - f2")
    public double field3;

    @Expression("f1 + f2 + field3")
    public Double field4;
}
