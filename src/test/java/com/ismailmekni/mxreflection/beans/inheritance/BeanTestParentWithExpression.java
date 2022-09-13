package com.ismailmekni.mxreflection.beans.inheritance;

import com.ismailmekni.mxreflection.annotations.Expression;

public class BeanTestParentWithExpression {

    @Expression("f1 - f2")
    private double field3;

    @Expression("f1 + f2 + field3")
    private double field4;

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
