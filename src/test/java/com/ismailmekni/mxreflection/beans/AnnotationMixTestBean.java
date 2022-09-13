package com.ismailmekni.mxreflection.beans;

import com.ismailmekni.mxreflection.annotations.Expression;
import com.ismailmekni.mxreflection.annotations.Arg;

public class AnnotationMixTestBean {

    @Arg("f1")
    public String field1;

    @Arg("f2")
    public String field2;

    @Expression("f1 + f2")
    @Arg("f3")
    public String field3;

    @Expression("f1 + f3")
    public String field4;
}
