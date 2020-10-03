package com.ismail.mathreflection.beans;

import com.ismail.mathreflection.annotations.MXFormula;

public class BeanTestNullFieldValue {

    public Double field1;

    public Double field2;

    @MXFormula("field1 + field2")
    public Double field3;
}
