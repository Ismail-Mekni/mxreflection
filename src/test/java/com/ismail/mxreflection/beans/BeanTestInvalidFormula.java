package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.MXFormula;

public class BeanTestInvalidFormula {
    public int field1;

    public int field2;

    @MXFormula("sldkjf + field2")
    public int field3;
}
