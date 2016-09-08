package com.Example.JunitRestAssuredDemo;
import org.junit.Test;
import static com.Example.JunitRestAssuredDemo.TriangleExample.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by vishal on 9/8/16.
 */


public class TriangleIdentifierTest {

    @Test
    public void testScaleneTriangleExample() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(2, 8, 7);
        assertEquals(SCALENE, type);
    }

    @Test
    public void testInvalidScaleneTriangleExample1() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(2, 5, 8);
        assertEquals(INVALID, type);
    }

    @Test
    public void testInvalidScaleneTriangleExample2() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(1, 3, 1);
        assertEquals(INVALID, type);
    }

    @Test
    public void testInvalidScaleneTriangleExample3() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(5, 7, 1);
        assertEquals(INVALID, type);

    }

    @Test
    public void testInvalidScaleneTriangleExampleWithNegativeSides1() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(-10, 6, 7);
        assertEquals(INVALID, type);
    }

    @Test
    public void testInvalidScaleneTriangleExampleWithNegativeSides2() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(10, -6, 7);
        assertEquals(INVALID, type);
    }

    @Test
    public void testInvalidScaleneTriangleExampleWithNegativeSides3() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(10, -6, -1);
        assertEquals(INVALID, type);
    }

    @Test
    public void testEquiliteralTriangleExample() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(5, 5, 5);
        assertEquals(EQUILATERAL, type);
    }

    @Test
    public void testIsoscelesTriangleExample1() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(5, 3, 3);
        assertEquals(ISOSCELES, type);
    }

    @Test
    public void testIsoscelesTriangleExample() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(5, 8, 5);
        assertEquals(ISOSCELES, type);
    }

    @Test
    public void testIsoscelesTriangleExample3() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(5, 3, 3);
        assertEquals(ISOSCELES, type);
    }

    @Test
    public void testTriangle() throws Exception {
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(3, 1, 1);
        assertEquals(INVALID, type);
    }

    @Test(expected = NumberFormatException.class)
    public void testForNumberFormatException() throws Exception {
        String invalidSide = "Test";
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(Integer.parseInt(invalidSide), 1, 1);
        assertEquals(INVALID, type);
    }

    @Test(expected = Exception.class)
    public void testForException() throws Exception {
        String invalidSide = "Test";
        final TriangleExample type = TriangleIdentifier.triangleSidesParser(Integer.parseInt(invalidSide), 1, 1);
        assertEquals(INVALID, type);
    }
}
