package com.ismailmekni.mxreflection.beans.inheritance;

import com.ismailmekni.mxreflection.annotations.Arg;
import com.ismailmekni.mxreflection.annotations.Expression;

public class ChildUnparseableFieldTestBean extends ParentUnparseableFieldTestBean{

    @Arg("f2")
    public int field2;

    @Expression("f1 - f2")
    public double field3;

    @Expression("f1 + f2 + field3")
    public Double field4;
}
