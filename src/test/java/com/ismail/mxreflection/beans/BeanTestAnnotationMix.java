package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.MXFormula;
import com.ismail.mxreflection.annotations.Variable;

public class BeanTestAnnotationMix {

    @Variable("f1")
    public String field1;

    @Variable("f2")
    public String field2;

    @MXFormula("f1 + f2")
    @Variable("f3")
    public String field3;

    @MXFormula("f1 + f3")
    public String field4;
}
