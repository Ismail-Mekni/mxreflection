package com.ismailmekni.mxreflection.beans.inheritance;

import com.ismailmekni.mxreflection.annotations.Arg;
import com.ismailmekni.mxreflection.annotations.Expression;

public class ChildAnnotationMixAndDependencyTestBean extends ParentAnnotationMixAndDependencyTestBean {

    @Arg("f1")
    public String field1;

    @Arg("f2")
    public String field2;

    @Expression("f1 + f2 + f8")
    @Arg("f3")
    public String field3;

    @Expression("f1 + f3")
    public String field4;
}
