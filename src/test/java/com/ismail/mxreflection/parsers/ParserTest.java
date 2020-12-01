package com.ismail.mxreflection.parsers;

import com.ismail.mxreflection.exceptions.UnparseableFieldException;
import com.ismail.mxreflection.exceptions.UnparseableResultException;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void parseArgumentsTest() {
        List<Object> testObjects = Arrays.asList("3.5", 4, 4.5, 999999999999999999L,
                2.55555555555555555555, Float.valueOf(4.5F), Integer.valueOf(4)
                , Long.valueOf(9999999999999999L), Double.valueOf(2.55555555555555555555));

        List<Double> values = Parser.parseArguments(testObjects);

        assertEquals(3.5, values.get(0), 0.001);
        assertEquals(4, values.get(1), 0.001);
        assertEquals(4.5, values.get(2), 0.001);
        assertEquals(999999999999999999L, values.get(3), 0.001);
        assertEquals(2.55555555555555555555, values.get(4), 0.001);
        assertEquals(4.5, values.get(5), 0.001);
        assertEquals(4, values.get(6), 0.001);
        assertEquals(9999999999999999L, values.get(7), 0.001);
        assertEquals(2.55555555555555555555, values.get(8), 0.001);

    }

    @Test
    public void parseResultTest() {
        assertEquals(Double.MAX_VALUE, Parser.parseResult(Double.MAX_VALUE, Double.class), 0.00001);
        assertEquals(Double.MAX_VALUE, Parser.parseResult(Double.MAX_VALUE, double.class), 0.00001);
        assertEquals(Long.valueOf(Math.round(Double.MAX_VALUE)), Parser.parseResult(Double.MAX_VALUE, Long.class));
        assertEquals(BigInteger.valueOf(Math.round(Double.MAX_VALUE)), Parser.parseResult(Double.MAX_VALUE, BigInteger.class));
        assertEquals(Long.valueOf(Math.round(Double.MAX_VALUE)), Parser.parseResult(Double.MAX_VALUE, long.class));
        assertEquals(String.valueOf(Double.MAX_VALUE), Parser.parseResult(Double.MAX_VALUE, String.class));
    }

    @Test(expected = UnparseableFieldException.class)
    public void parseArgumentsUnparseableFieldExceptionTest() {
        Parser.parseArguments(Arrays.asList("hello", 2.9));
    }

    @Test(expected = UnparseableResultException.class)
    public void parseResultUnparseableResultExceptionTest() {
        Parser.parseResult(2.8, int.class);
    }
}
