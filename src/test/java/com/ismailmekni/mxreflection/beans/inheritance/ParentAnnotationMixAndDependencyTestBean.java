package com.ismailmekni.mxreflection.beans.inheritance;

import com.ismailmekni.mxreflection.annotations.Arg;
import com.ismailmekni.mxreflection.annotations.Expression;

public class ParentAnnotationMixAndDependencyTestBean {

    @Arg("f5")
    public String field5;

    @Arg("f6")
    public String field6;

    @Expression("f5 + field4")
    @Arg("f7")
    public String field7;

    @Expression("f1 + f5")
    @Arg("f8")
    public String field8;
}
