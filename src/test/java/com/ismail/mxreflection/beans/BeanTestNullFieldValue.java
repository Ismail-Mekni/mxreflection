package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.Expression;

public class BeanTestNullFieldValue {

    public Double field1;

    public Double field2;

    @Expression("field1 + field2")
    public Double field3;
}
