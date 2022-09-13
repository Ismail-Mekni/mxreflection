package com.ismailmekni.mxreflection.examples;

import com.ismailmekni.mxreflection.annotations.Arg;
import com.ismailmekni.mxreflection.annotations.Expression;
import com.ismailmekni.mxreflection.core.Calculator;
import com.ismailmekni.mxreflection.factory.MXFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Example1Test {

    class Example1 {

        @Arg("f1")
        String field1;

        @Arg("f2")
        int field2;

        @Expression("f1 * sin(f2) * log2(f1 + f2) + der(cos(f1), f1) * pi + int(tan(f2), f2, 0, e)")
        double field3;

    }

    @Test
    public void example1Test() {
        Example1 example1 = new Example1();
        example1.field1 = "2.2";
        example1.field2 = 5;

        Calculator<Example1> calculator = MXFactory.createCalculator(Example1.class);
        calculator.calculate(example1);
        assertEquals(-34.328, example1.field3, 0.001);

        System.out.println("Field 3 result: " + example1.field3);
    }

}
