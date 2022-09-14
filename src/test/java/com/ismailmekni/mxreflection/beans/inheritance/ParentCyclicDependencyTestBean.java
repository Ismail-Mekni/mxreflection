package com.ismailmekni.mxreflection.beans.inheritance;

import com.ismailmekni.mxreflection.annotations.Arg;
import com.ismailmekni.mxreflection.annotations.Expression;

public class ParentCyclicDependencyTestBean {

    @Arg("f3")
    @Expression("f1 + sin(f2)")
    public String field3;
}
