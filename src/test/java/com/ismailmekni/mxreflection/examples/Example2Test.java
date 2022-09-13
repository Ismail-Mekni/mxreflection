package com.ismailmekni.mxreflection.examples;

import com.ismailmekni.mxreflection.annotations.Arg;
import com.ismailmekni.mxreflection.annotations.Expression;
import com.ismailmekni.mxreflection.core.Calculator;
import com.ismailmekni.mxreflection.factory.MXFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Example2Test {

    public class Example2 {
        @Arg("f1")
        private String field1;

        @Arg("f2")
        private Long field2;

        @Expression("f2 - f1")
        @Arg("f3")
        private double field3;

        @Expression("f3 - f2")
        @Arg("f4")
        private double field4;

        @Expression("f1 - f2")
        @Arg("f5")
        private Double field5;

        @Expression("f4-f5")
        @Arg("f6")
        private String field6;

        @Expression("f6-f5")
        @Arg("f7")
        private long field7;

        @Expression("f7+5")
        private Long field8;
    }

    @Test
    public void example2Test() {
        Example2 example2 = new Example2();
        example2.field1 = "2.2";
        example2.field2 = 5L;

        Calculator<Example2> calculator = MXFactory.createCalculator(Example2.class);
        calculator.calculate(example2);
        assertEquals("2.2", example2.field1);
        assertEquals(5, (long) example2.field2);
        assertEquals(2.8, example2.field3, 0.001);
        assertEquals(-2.2, example2.field4, 0.001);
        assertEquals(-2.8, example2.field5, 0.001);
        assertEquals("0.6", example2.field6);
        assertEquals(3, example2.field7);
        assertEquals(8, (long) example2.field8);

        System.out.println("Field 3 result: " + example2.field3);
        System.out.println("Field 4 result: " + example2.field4);
        System.out.println("Field 5 result: " + example2.field5);
        System.out.println("Field 6 result: " + example2.field6);
        System.out.println("Field 6 result: " + example2.field7);
        System.out.println("Field 6 result: " + example2.field8);
    }
}
