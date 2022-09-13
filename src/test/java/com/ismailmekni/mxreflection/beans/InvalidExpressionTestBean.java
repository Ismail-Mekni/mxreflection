package com.ismailmekni.mxreflection.beans;

import com.ismailmekni.mxreflection.annotations.Expression;

public class InvalidExpressionTestBean {
    public int field1;

    public int field2;

    @Expression("sldkjf + field2")
    public int field3;
}
