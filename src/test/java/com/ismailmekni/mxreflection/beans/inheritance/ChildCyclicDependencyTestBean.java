package com.ismailmekni.mxreflection.beans.inheritance;

import com.ismailmekni.mxreflection.annotations.Arg;
import com.ismailmekni.mxreflection.annotations.Expression;

public class ChildCyclicDependencyTestBean extends ParentCyclicDependencyTestBean{

    @Arg("f1")
    public double field1;

    @Arg("f2")
    @Expression("f1 + f3")
    public String field2;
}
