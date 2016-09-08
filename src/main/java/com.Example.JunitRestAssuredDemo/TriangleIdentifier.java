package com.Example.JunitRestAssuredDemo;

/**
 * Created by vishal on 9/8/16.
 */
public class TriangleIdentifier {

    public static TriangleExample triangleSidesParser(final int sideA, final int sideB, final int sideC) throws NumberFormatException {
        try {
            int triangleType;

            // Check that nothing is negative

            if ((sideA <= 0) || (sideB <= 0) || (sideC <= 0)) {
                return TriangleExample.INVALID;
            }
            triangleType = 0;
            if (sideA == sideB) {
                triangleType = triangleType + 1;
            }
            if (sideA == sideC) {
                triangleType = triangleType + 2;
            }
            if (sideB == sideC) {
                triangleType = triangleType + 3;
            }

            // Check for side length

            if (triangleType == 0) {
                if (((sideA + sideB) < sideC) || ((sideA + sideC) < sideB) || ((sideB + sideC) < sideA)) {
                    return TriangleExample.INVALID;
                } else {
                    return TriangleExample.SCALENE;
                }
            }
            if (triangleType > 3) {
                return TriangleExample.EQUILATERAL;
            }
            if ((triangleType == 1) && ((sideA + sideB) > sideC)) {
                return TriangleExample.ISOSCELES;
            } else if ((triangleType == 2) && ((sideA + sideC) > sideB)) {
                return TriangleExample.ISOSCELES;
            } else if ((triangleType == 3) && ((sideB + sideC) > sideA)) {
                return TriangleExample.ISOSCELES;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return TriangleExample.INVALID;
    }

}
