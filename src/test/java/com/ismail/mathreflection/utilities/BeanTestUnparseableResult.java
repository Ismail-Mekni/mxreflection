package com.ismail.mathreflection.utilities;

import com.ismail.mathreflection.annotations.MXFormula;
import com.ismail.mathreflection.annotations.Variable;

import java.util.Map;

public class BeanTestUnparseableResult {

    @Variable("f1")
    public String field1;

    @Variable("f2")
    public int field2;

    @MXFormula("f1 - f2")
    public int field3;

    @MXFormula("f1 + f2 + field3")
    public Map field4;
}
