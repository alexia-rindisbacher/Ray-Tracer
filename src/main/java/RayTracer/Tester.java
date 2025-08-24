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
        Canvas c = new Canvas(5,4);
        for (int i = 0 ; i < c.getHeight() ; i++ ){
            for ( int j = 0 ; j < c.getWidth() ; j++){
                assertEquals(new Color(0,0,0), c.getPixel(j,i));
            }
        }
    }

    @Test
    @DisplayName("27. Write a pixel to a canvas & Test getPixel at the same time (19)")
    void testWriteGetPixel(){
        Canvas c = new Canvas(5,4);
        c.writePixel(4,3, new Color(1,1,1));
        //System.out.println(c);
        assertEquals(new Color(1,1,1), c.getPixel(4,3));

    }


    @Test
    @DisplayName("28. canvasToPPM(), Constructs a PPM data using pixel data (20)")
    void testcanvasToPPM(){
        Canvas c = new Canvas(5,3);
        Color c1 = new Color(1.5, 0, 0 );
        Color c2 = new Color(0,0.5,0);
        Color c3 = new Color(-0.5,0,1);

        c.writePixel(0,0,c1);
        c.writePixel(2,1,c2);
        c.writePixel(4,2,c3);
        String n = c.canvasToPPM();

        assertEquals("P3\n5 3\n225\n225 0 0 0 0 0 0 0 0 0 0 0 0 0 0 \n0 0 0 0 0 0 0 128 0 0 0 0 0 0 0 \n0 0 0 0 0 0 0 0 0 0 0 0 0 0 255 \n", n);
    }

    @Test
    @DisplayName("29. canvasToPPM(), Splits long lines in PPM File (22)")
    void testLongLinesInPPM(){
        Canvas c = new Canvas(10, 2, new Color(1,0.8, 0.6));
        String s = c.canvasToPPM();
        assertEquals("P3\n10 2\n225\n255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204 \n153 255 204 153 255 204 153 255 204 153 255 204 153 \n255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204 \n153 255 204 153 255 204 153 255 204 153 255 204 153 \n", s );
    }

    @Test
    @DisplayName("30. canvasToPPM(), PPM files are terminated by a newline character (22)")
    void testEndNewLine(){
        Canvas c = new Canvas(5,3);
        String s = c.canvasToPPM();
        String n = "" + s.charAt(s.length()-1);
        assertEquals("\n", n);
    }

    @Test
    @DisplayName("31. PPM file")
    void testPPMFileCreation(){
        Canvas c = new Canvas(10, 2, new Color(1,0.8, 0.6));
        String s = c.canvasToPPM();

        //c.stringToPPM("06102025-testfile-3");
    }

    @Test
    @DisplayName("32. Matrix Creation (26)")
    void testMatrixCreator(){
        Matrix m = new Matrix(4,4);
        m.set(0,0,1);
        m.set(0,1, 2);
        m.set(0,2, 3);
        m.set(0,3, 4);

        m.set(1,0, 5.5F);
        m.set(1,1, 6.5F);
        m.set(1,2, 7.5F);
        m.set(1,3, 8.5F);

        m.set(2,0, 9);
        m.set(2,1, 10);
        m.set(2,2, 11);
        m.set(2,3, 12);

        m.set(3,0, 13.5F);
        m.set(3,1, 14.5F);
        m.set(3,2, 15.5F);
        m.set(3,3, 16.5F);

        assertEquals(1, m.get(0,0));
        assertEquals(5.5, m.get(1,0));
        assertEquals(4, m.get(0,3));
        assertEquals(7.5, m.get(1,2));
        assertEquals(11, m.get(2,2));
        assertEquals(13.5, m.get(3,0));
        assertEquals(15.5, m.get(3,2));
    }

    @Test
    @DisplayName("33. 2x2 Matrix Creation")
    void matrix2x2(){
        Matrix m = new Matrix(2,2);
        m.set(0,0,-3);
        m.set(0,1, 5);
        m.set(1,0, 1);
        m.set(1,1,-2);

        assertEquals(-3, m.get(0,0));
        assertEquals(5, m.get(0,1));
        assertEquals(1, m.get(1,0));
        assertEquals(-2, m.get(1,1));
    }

    @Test
    @DisplayName("34. 3x3 Matrix Creation")
    void matrix3x3(){
        Matrix m = new Matrix(3,3);
        m.set(0,0,-3);
        m.set(0,1, 5);
        m.set(1,0, 1);
        m.set(1,1,-2);

        m.set(2,0, 0);
        m.set(2,1,1);
        m.set(2,2, 1);
        m.set(0,2,0);
        m.set(1,2,-7);

        assertEquals(-3, m.get(0,0));
        assertEquals(1, m.get(2,2));
        assertEquals(-2, m.get(1,1));
    }

    @Test
    @DisplayName("35. Identical Matricies (27)")
    void testEqualMatricies(){
        Matrix m = new Matrix(4,4);
        Matrix n = new Matrix(4,4);

        //Set m:
        int y = 1;
        for (int r = 0 ; r < 4 ; r++){
            for (int c = 0 ; c < 4 ; c++){
                m.set(r,c,y);
                n.set(r,c,y);
                y++;
            }
        }

        boolean worked = m.equals(n);
        assertTrue(worked);
    }

    @Test
    @DisplayName("36. Unequal Matricies (27)")
    void testUnequalMatricies(){
        Matrix m = new Matrix(4,4);
        Matrix n = new Matrix(4,4);

        //Set m:
        int y = 1;
        for (int r = 0 ; r < 4 ; r++){
            for (int c = 0 ; c < 4 ; c++){
                m.set(r,c,y);
                y++;
            }
        }

        int l = 2;
        for (int r = 0 ; r < 4 ; r++){
            for (int c = 0 ; c < 4 ; c++){
                n.set(r,c,l);
                l++;
            }
        }

        boolean worked = m.equals(n);
        assertFalse(worked);
    }

    @Test
    @DisplayName("37. Multiplying two matricies (28)")
    void multiplyMatrix(){
        Matrix m = new Matrix(4,4);
        m.set(0,0,1);
        m.set(0,1, 2);
        m.set(0,2,3);
        m.set(0,3,4);

        m.set(1,0,5);
        m.set(1,1, 6);
        m.set(1,2,7);
        m.set(1,3,8);

        m.set(2,0,9);
        m.set(2,1, 8);
        m.set(2,2,7);
        m.set(2,3,6);

        m.set(3,0,5);
        m.set(3,1, 4);
        m.set(3,2,3);
        m.set(3,3,2);

        Matrix n = new Matrix(4,4);
        n.set(0,0,-2);
        n.set(0,1, 1);
        n.set(0,2,2);
        n.set(0,3,3);

        n.set(1,0,3);
        n.set(1,1, 2);
        n.set(1,2,1);
        n.set(1,3,-1);

        n.set(2,0,4);
        n.set(2,1, 3);
        n.set(2,2,6);
        n.set(2,3,5);

        n.set(3,0,1);
        n.set(3,1, 2);
        n.set(3,2,7);
        n.set(3,3,8);

        Matrix l = m.multiply(n);

        Matrix correctResult = new Matrix(4,4);
        correctResult.set(0,0,20);
        correctResult.set(0,1, 22);
        correctResult.set(0,2,50);
        correctResult.set(0,3,48);

        correctResult.set(1,0,44);
        correctResult.set(1,1, 54);
        correctResult.set(1,2,114);
        correctResult.set(1,3,108);

        correctResult.set(2,0,40);
        correctResult.set(2,1, 58);
        correctResult.set(2,2,110);
        correctResult.set(2,3,102);

        correctResult.set(3,0,16);
        correctResult.set(3,1, 26);
        correctResult.set(3,2,46);
        correctResult.set(3,3,42);

        boolean worked = correctResult.equals(l);
        assertTrue(worked);
    }

    @Test
    @DisplayName("38. Multiplying Matrix and Tuple (30)")
    void multiplyMatrixTuple(){
        Matrix m = new Matrix(4,4);
        m.set(0,0,1);
        m.set(0,1, 2);
        m.set(0,2,3);
        m.set(0,3,4);

        m.set(1,0,2);
        m.set(1,1, 4);
        m.set(1,2,4);
        m.set(1,3,2);

        m.set(2,0,8);
        m.set(2,1, 6);
        m.set(2,2,4);
        m.set(2,3,1);

        m.set(3,0,0);
        m.set(3,1, 0);
        m.set(3,2,0);
        m.set(3,3,1);

        Tuple b = new Tuple(1,2,3,1);

        Matrix result = m.multiply(b);
        Matrix correctResult = new Matrix(4,1);
        correctResult.set(0,0, 18);
        correctResult.set(1,0, 24);
        correctResult.set(2,0, 33);
        correctResult.set(3,0, 1);

        boolean worked = correctResult.equals(result);
        assertTrue(worked);

    }
}
