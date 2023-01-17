package com.ismailmekni.mxreflection.examples;

import com.ismailmekni.mxreflection.annotations.Arg;
import com.ismailmekni.mxreflection.annotations.Expression;
import com.ismailmekni.mxreflection.core.Calculator;
import com.ismailmekni.mxreflection.factory.MXFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InheritanceExampleTest {

    @Test
    public void inheritanceExampleTest() {
        Child child = new Child();
        child.field1 = "2.2";
        child.field2 = 5;
        child.field4 = "6";
        child.field5 = 3;

        Calculator<Child> calculator = MXFactory.createCalculator(Child.class);
        calculator.calculate(child);
        assertEquals(32.2, child.field3, 0.001);
        assertEquals(96.6, child.field6, 0.001);

        System.out.println("Field 3 result: " + child.field3);
        System.out.println("Field 6 result: " + child.field6);
    }
}

class Parent {

    @Arg("f1")
    String field1;

    @Arg("f2")
    int field2;

    @Expression("f1 + f4 * f2")
    @Arg("f3")
    double field3;
}

class Child extends Parent {

    @Arg("f4")
    String field4;

    @Arg("f5")
    int field5;

    @Expression("f5 * f3")
    double field6;
}
