package com.ismail.mxreflection.beans.inheritance;

import com.ismail.mxreflection.annotations.Arg;

public class BeanTestParent {

    @Arg("f1")
    private double field1;

    public double getField1() {
        return field1;
    }

    public void setField1(double field1) {
        this.field1 = field1;
    }
}
