package RayTracer;
import java.math.*;

public class Tuple {
    private double x;
    private double y;
    private double z;
    private double w;
    private static double EPSILON = 0.0001;

    //Constructor: Intializes a new tuple with coordinates. Checks that w is 0 or 1.
    public Tuple(double x, double y, double z, double w){
        if (w != 1 && w != 0) {
            throw new IllegalArgumentException("W must be either 0 or 1");
        }

        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    //Create a point
    public static Tuple point(double x, double y, double z){
        return new Tuple(x,y,z,1);
    }

    //Create a vector
    public static Tuple vector(double x, double y, double z){
        return new Tuple(x,y,z,0);
    }

    //Get methods
    public double getX(){return this.x;}
    public double getY(){return this.y;}
    public double getZ(){return this.z;}

    public boolean isVector(){
        return this.w == 0;
    }

    public boolean isPoint(){
        return this.w == 1;
    }

    public boolean isSameType(Tuple m){
        if (this.isVector() && m.isVector()){
            return true;
        }

        if (this.isPoint() && m.isPoint()){
            return true;
        }

        return false;
    }

    public boolean equals(Tuple m){
        boolean xSame = equals(this.x, m.x);
        boolean ySame = equals(this.y, m.y);
        boolean zSame = equals(this.z, m.z);

        if (this.isPoint() && m.isPoint()){
            return (xSame && ySame && zSame);
        }

        if (this.isVector() && m.isVector()){
            return (xSame && ySame && zSame);
        }

        return false;
    }

    public boolean equals(double a, double b) {
        return (Math.abs(a - b) < EPSILON);
    }


    public Tuple add(Tuple t) {
        if (this.isPoint() && t.isPoint()) {
            throw new IllegalArgumentException("Cannot add a point to a point");
        }

        double xValue = this.getX() + t.getX();
        double yValue = this.getY() + t.getY();
        double zValue = this.getZ() + t.getZ();

        if ((this.isPoint() && t.isVector()) || (this.isVector() && t.isPoint())) {
            //Normal, return new point
            return point(xValue, yValue, zValue);
        }

        if (this.isVector() && t.isVector()) {
            //Normal, return new vector
            return vector(xValue, yValue, zValue);
        }

        return null;
    }


    public Tuple subtract(Tuple t){

        if (this.isVector() && t.isPoint()) {
            throw new IllegalArgumentException("Cannot subtract a point from a vector");
        }

        double xValue = this.getX() - t.getX();
        double yValue = this.getY() - t.getY();
        double zValue = this.getZ() - t.getZ();

       if (this.isSameType(t)){
           return vector(xValue, yValue, zValue);
       }

       if (this.isPoint() && t.isVector()){
           return point(xValue, yValue, zValue);
       }
        return null;
    }

    public void negate(){
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    public void multiply(double d){
        this.x = this.x*d;
        this.y = this.y*d;
        this.z = this.z*d;
    }

}
