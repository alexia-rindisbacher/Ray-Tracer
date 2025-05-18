package RayTracer;
import java.math.*;
import org.junit.jupiter.api.*;

import static RayTracer.Tuple.point;
import static RayTracer.Tuple.vector;
import static org.junit.jupiter.api.Assertions.*;

public class Tester {
    @Test
    @DisplayName("1. A Tuple is a point (4)")
    void testTuplePoint() throws NoSuchFieldException, IllegalAccessException {
        Tuple tuple = new Tuple(4.3, -4.2, 3.1 ,1.0);
        double x = tuple.getX();
        double y = tuple.getY();
        double z = tuple.getZ();
        boolean isVector = tuple.isVector();
        boolean isPoint = tuple.isPoint();

        assertEquals(4.3, x);
        assertEquals(-4.2, y);
        assertEquals(3.1, z);
        assertFalse(isVector);
        assertTrue(isPoint);
    }

    @Test
    @DisplayName("2. A Tuple is a vector (4)")
    void testTupleVector() {
        Tuple tuple = new Tuple(4.3, -4.2, 3.1 ,0.0);
        double x = tuple.getX();
        double y = tuple.getY();
        double z = tuple.getZ();
        boolean isVector = tuple.isVector();
        boolean isPoint = tuple.isPoint();

        assertEquals(4.3, x);
        assertEquals(-4.2, y);
        assertEquals(3.1, z);
        assertFalse(isPoint);
        assertTrue(isVector);
    }

    @Test
    @DisplayName("3. point() creates a point (4)")
    void testPointMaker() throws NoSuchFieldException, IllegalAccessException {
        Tuple tuple = point(4.3, -4.2, 3.1);
        double x = tuple.getX();
        double y = tuple.getY();
        double z = tuple.getZ();
        boolean isVector = tuple.isVector();
        boolean isPoint = tuple.isPoint();

        assertEquals(4.3, x);
        assertEquals(-4.2, y);
        assertEquals(3.1, z);
        assertFalse(isVector);
        assertTrue(isPoint);
    }

    @Test
    @DisplayName("4. vector() creates a vector (4)")
    void testVectorMaker() {
        Tuple tuple = vector(4.3, -4.2, 3.1 );
        double x = tuple.getX();
        double y = tuple.getY();
        double z = tuple.getZ();
        boolean isVector = tuple.isVector();
        boolean isPoint = tuple.isPoint();

        assertEquals(4.3, x);
        assertEquals(-4.2, y);
        assertEquals(3.1, z);
        assertFalse(isPoint);
        assertTrue(isVector);
    }

    @Test
    @DisplayName("5. add() Adding a point and a vector (6)")
    void testAdditionPV() {
        Tuple a1 = point(3, -2, 5);
        Tuple a2 = vector(2, 3, 1);
        Tuple result = a1.add(a2);
        assertTrue(result.equals(point(5, 1, 6)));

        Tuple resultReversed = a2.add(a1);
        assertTrue(resultReversed.equals(point(5, 1, 6)));
    }

    @Test
    @DisplayName("6. add() Adding two vectors (6)")
    void testAdditionVV() {
        Tuple a1 = vector(3, -2, 5);
        Tuple a2 = vector(2, 3, 1);
        Tuple result = a1.add(a2);
        assertTrue(result.equals(vector(5, 1, 6)));
    }

    @Test
    @DisplayName("7. add() Adding two points throws an IllegalArgumentException (6)")
    void testAdditionPP() {
        Tuple a1 = point(3, -2, 5);
        Tuple a2 = point(2, 3, 1);
        assertThrows(IllegalArgumentException.class,
                () -> {a1.add(a2);});
    }


    @Test
    @DisplayName("8. subtract() Subtracting two points (6)")
    void testSubtractPP(){
        Tuple a1 = point(3, 2, 1);
        Tuple a2 = point(5, 6, 7);
        assertTrue(a1.subtract(a2).equals(vector(-2,-4,-6)));
    }

    @Test
    @DisplayName("9. subtract() Subtracting a vector from a point (6)")
    void testSubtractPV(){
        Tuple a1 = point(3, 2, 1);
        Tuple a2 = vector(5, 6, 7);
        assertTrue(a1.subtract(a2).equals(point(-2,-4,-6)));
    }

    @Test
    @DisplayName("10. subtract() Subtracting two vectors (6)")
    void testSubtractVV(){
        Tuple a1 = vector(3, 2, 1);
        Tuple a2 = vector(5, 6, 7);
        assertTrue(a1.subtract(a2).equals(vector(-2,-4,-6)));
    }

    @Test
    @DisplayName("11. subtract() Subtracting a point from a vector (7)")
    void testSubtractVP(){
        Tuple a1 = vector(3, 2, 1);
        Tuple a2 = point(5, 6, 7);
        assertThrows(IllegalArgumentException.class,
                () -> {a1.subtract(a2);});
    }

    @Test
    @DisplayName("12. subtract() Subtracting a vector from the 0 vector (7)")
    void testNegationBySubtraction(){
        Tuple zero = vector(0, 0, 0);
        Tuple a2 = vector(1, -2, 3);
        assertTrue(zero.subtract(a2).equals(vector(-1,2,-3)));
    }

    @Test
    @DisplayName("13. negate() Test for negation (7)")
    void testNegate(){
        Tuple a = vector(1,-2,3);
        a.negate();
        assertTrue(a.equals(vector(-1,2,-3)));
    }

    @Test
    @DisplayName("14. multiplication() Multiplying a tuple by a scalar (8)")
    void testScalarMult(){
        Tuple a = vector(1,-2,3);
        a.multiply(3.5);
        assertTrue(a.equals(vector(3.5,-7,10.5)));
    }

    @Test
    @DisplayName("15. multiplication() Multiplying a tuple by a faction (8)")
    void testFractMult(){
        Tuple a = vector(1,-2,3);
        a.multiply(0.5);
        assertTrue(a.equals(vector(0.5,-1,1.5)));
    }

    @Test
    @DisplayName("16. magnitude() Computing the magnitude of different vectors (8-9)")
    void testMagnitdude(){
        double a = vector(1,0,0).magnitude();
        double b = vector(0,1,0).magnitude();
        double c = vector(0,0,1).magnitude();
        double d = vector(1,2,3).magnitude();
        double e = vector(-1,-2,-3).magnitude();
        assertEquals(1,a);
        assertEquals(1,b);
        assertEquals(1,c);
        assertEquals(Math.sqrt(14), d);
        assertEquals(Math.sqrt(14), e);
    }

    @Test
    @DisplayName("17. normalize() Normalizing Simple Vectors (10)")
    void testNormalEasy(){
        Tuple v = vector(4,0,0);
        v.normalize();
        assertTrue(vector(1,0,0).equals(v));
        assertEquals(1, v.magnitude() );
    }

    @Test
    @DisplayName("18. normalize() Normalizing more complicated Vectors (10)")
    void testNormalHard(){
        Tuple v = vector(1,2,3);
        v.normalize();
        assertTrue(v.equals(vector(0.26736, 0.53452, 0.80178)));
        assertEquals(1, v.magnitude() );
    }

    @Test
    @DisplayName("19. dot() Dot product of two vectors (10)")
    void testDot(){
        Tuple a = vector(1,2,3);
        Tuple b = vector(2,3,4);
        assertEquals(20, a.dot(b));
    }

    @Test
    @DisplayName("20. cross() Cross Product of two vectors (11) ")
    void testCross(){
        Tuple a = vector(1,2,3);
        Tuple b = vector(2,3,4);
        assertTrue(a.cross(b).equals(vector(-1,2,-1)));
        assertTrue(b.cross(a).equals(vector(1,-2,1)));
    }

    @Test
    @DisplayName("21. Color constructor (16)")
    void testColor(){
        Color c = new Color(-0.5, 0.4, 1.7);
        assertEquals(-0.5, c.getRed());
        assertEquals(0.4, c.getGreen());
        assertEquals(1.7, c.getBlue());
    }

    @Test
    @DisplayName("22. Adding Colors (17)")
    void testAddColor(){
        Color c1 = new Color(0.9,0.6, 0.75);
        Color c2 = new Color(0.7,0.1, 0.25);
        assertTrue(new Color(1.6,0.7, 1.0).equals(c1.add(c2)));
    }


    @Test
    @DisplayName("23. Subtracting Colors (17)")
    void testSubtractColor(){
        Color c1 = new Color(0.9,0.6, 0.75);
        Color c2 = new Color(0.7,0.1, 0.25);
        assertTrue(new Color(0.2,0.5, 0.5).equals(c1.subtract(c2)));
    }


    @Test
    @DisplayName("24. Multiplying Colors by a Scalar (17)")
    void testScalarMultColor(){
        Color c = new Color(0.2,0.3,0.4);
        c.multiply(2);
        assertTrue(new Color(0.4,0.6,0.8).equals(c));
    }


    @Test
    @DisplayName("25. Multiplying Colors by Hadamard/Schur Porduct (17)")
    void testHadamardColor(){
        Color c1 = new Color(1,0.2, 0.4);
        Color c2 = new Color(0.9,1, 0.1);
        assertTrue(new Color(0.9,0.2, 0.04).equals(c1.hadamard(c2)));
    }

    @Test
    @DisplayName("26. Creating a new canvas (19)")
    void testBlankCanvas(){
        //Fill this in
    }

    @Test
    @DisplayName("27. Write a pixel to a canvas (19)")
    void testWritePixel(){
        //Fill this in
    }

    @Test
    @DisplayName("28. Get a pixel from the canvas (19)")
    void testGetPixel(){
        //Fill this in
    }
}
