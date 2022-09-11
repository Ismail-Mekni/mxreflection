package com.ismail.mxreflection.beans.inheritance;

import com.ismail.mxreflection.annotations.Arg;
import com.ismail.mxreflection.annotations.Expression;

public class BeanTestChild extends BeanTestParent{

    @Arg("f2")
    private double field2;

    @Expression("f1 - f2")
    private double field3;

    @Expression("f1 + f2 + field3")
    private double field4;

    public double getField2() {
        return field2;
    }

    public void setField2(double field2) {
        this.field2 = field2;
    }

    public double getField3() {
        return field3;
    }

    public void setField3(double field3) {
        this.field3 = field3;
    }

    public double getField4() {
        return field4;
    }

    public void setField4(double field4) {
        this.field4 = field4;
    }
}
