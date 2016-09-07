package com.Example.JunitRestAssuredDemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vishal on 9/7/16.
 */
public class SimpleCalculatorTest {
    @Test
    public void testMultiply() {

        double a = 5.0;
        double b = -4.0;

        // can change value here
        double expected = -20.0;
        double result = SimpleCalculator.multiply(a, b);

        // check if expected is equals to result
        assertEquals(expected, result, 0.00001);
    }

    @Test
    public void testAdd() {
        int c = 1;
        int d = 1;

        // test to insert expected to 3 and it shall fail since method should
        // generate 2
        int expected = 2;
        int result = SimpleCalculator.add(c, d);

        // Check if expected is equals to result
        assertEquals(expected, result);
    }

    @Test
    public void testSub() {
        int a = 10;
        int b = -20;
        int expected = 30;
        int actual = SimpleCalculator.subtraction(a, b);
        assertEquals(expected, actual);

    }

    @Test
    public void testDivide() {
        double a = 10;
        double b = 5;
        double expected = 2;
        double actual = SimpleCalculator.divide(a, b);
        assertEquals(expected, actual, 0.00001);

    }

}
