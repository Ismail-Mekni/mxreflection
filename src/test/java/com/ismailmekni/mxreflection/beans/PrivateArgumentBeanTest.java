package com.ismailmekni.mxreflection.beans;

import com.ismailmekni.mxreflection.annotations.Expression;
import com.ismailmekni.mxreflection.annotations.Arg;

public class PrivateArgumentBeanTest {


    @Arg("f1")
    private double field1;

    @Arg("f2")
    private double field2;

    public PrivateArgumentBeanTest(double field1, double field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    @Expression("f1 - f2")
    public double field3;

    @Expression("f1 + f2 + field3")
    public double field4;
}
