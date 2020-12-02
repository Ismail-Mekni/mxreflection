package com.ismail.mxreflection.beans;

import com.ismail.mxreflection.annotations.Arg;
import com.ismail.mxreflection.annotations.Expression;

public class BeanTestWithFieldDependency {

    @Arg("f1")
    public String field1;

    @Arg("f2")
    public int field2;

    @Expression("f1 - f2")
    @Arg("f3")
    public String field3;

    @Expression("f1 * sin(f3)")
    @Arg("f4")
    public double field4;

    @Expression("log2(-f4) + log10(-f3) + der(sin(f2), f2) * f6")
    public Double field5;

    @Expression("f4 - f2")
    @Arg("f6")
    public long field6;
}
