package com.ismail.mathreflection.parsers;

import com.ismail.mathreflection.exceptions.UnparseableFieldException;
import com.ismail.mathreflection.exceptions.UnparseableResultException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void parseVariablesTest() {
        List<Object> testObjects = Arrays.asList("3.5", 4, 4.5, 999999999999999999L,
                2.55555555555555555555, Float.valueOf(4.5F), Integer.valueOf(4)
                , Long.valueOf(9999999999999999L), Double.valueOf(2.55555555555555555555));

        List<Double> values = Parser.parseVariables(testObjects);

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

    }

    @Test(expected = UnparseableFieldException.class)
    public void parseVariablesUnparseableFieldExceptionTest() {
        Parser.parseVariables(Arrays.asList("hello", 2.9));
    }

    @Test(expected = UnparseableResultException.class)
    public void parseResultUnparseableResultExceptionTest() {
        Parser.parseResult(2.8, int.class);
    }
}
