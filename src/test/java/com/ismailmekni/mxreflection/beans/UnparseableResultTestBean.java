package com.ismailmekni.mxreflection.beans;

import com.ismailmekni.mxreflection.annotations.Expression;
import com.ismailmekni.mxreflection.annotations.Arg;

import java.util.Map;

public class UnparseableResultTestBean {

    @Arg("f1")
    public String field1;

    @Arg("f2")
    public int field2;

    @Expression("f1 - f2")
    public int field3;

    @Expression("f1 + f2 + field3")
    public Map field4;
}
