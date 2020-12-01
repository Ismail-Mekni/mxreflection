package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.Expression;

public class BeanTestInvalidExpression {
    public int field1;

    public int field2;

    @Expression("sldkjf + field2")
    public int field3;
}
