package com.ismailmekni.mxreflection.beans.inheritance;

import com.ismailmekni.mxreflection.annotations.Expression;

public class ParentInvalidExpressionTestBean {

    @Expression("sldkjf + field2")
    public int field3;
}
