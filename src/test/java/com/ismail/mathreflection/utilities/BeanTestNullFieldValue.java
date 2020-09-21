package com.ismail.mathreflection.utilities;

import com.ismail.mathreflection.annotations.MXFormula;

public class BeanTestNullFieldValue {

    public Double field1;

    public Double field2;

    @MXFormula("field1 + field2")
    public Double field3;
}
