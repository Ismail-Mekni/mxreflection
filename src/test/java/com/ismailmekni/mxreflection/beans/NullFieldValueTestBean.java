package com.ismailmekni.mxreflection.beans;

import com.ismailmekni.mxreflection.annotations.Expression;

public class NullFieldValueTestBean {

    public Double field1;

    public Double field2;

    @Expression("field1 + field2")
    public Double field3;
}
