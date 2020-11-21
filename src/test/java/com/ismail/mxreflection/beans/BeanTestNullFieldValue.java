package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.MXFormula;

public class BeanTestNullFieldValue {

    public Double field1;

    public Double field2;

    @MXFormula("field1 + field2")
    public Double field3;
}
