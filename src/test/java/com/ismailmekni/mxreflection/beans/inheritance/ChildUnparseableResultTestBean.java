package com.ismailmekni.mxreflection.beans.inheritance;

import com.ismailmekni.mxreflection.annotations.Arg;

public class ChildUnparseableResultTestBean extends ParentUnparseableResultTestBean {

    @Arg("f1")
    public String field1;

    @Arg("f2")
    public String field2;
}
