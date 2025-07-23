package RayTracer;
import java.math.*;

public class Tuple {
    private double x;
    private double y;
    private double z;
    private double w;
    private final static double EPSILON = 0.0001;

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

    public void setX(double x){this.x = x;}
    public void setY(double y){this.y = y;}
    public void setZ(double z){this.z = z;}

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

        return this.isPoint() && m.isPoint();
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

    public double magnitude(){
        double xSq = Math.pow(this.getX(), 2);
        double ySq = Math.pow(this.getY(), 2);
        double zSq = Math.pow(this.getZ(), 2);
        return Math.sqrt((xSq+ ySq + zSq));
    }

    public void normalize(){
        double magnitude = this.magnitude();
        double newX = this.getX() / magnitude;
        double newY = this.getY() / magnitude;
        double newZ = this.getZ() / magnitude;
        this.x = newX;
        this.y = newY;
        this.z = newZ;
    }

    public double dot(Tuple t){
        if (! this.isVector() || ! t.isVector()){
            throw new IllegalArgumentException("Dot product can only be computed with vectors.");
        }

        return (this.getX()*t.getX() + this.getY() * t.getY() + this.getZ() * t.getZ());
    }

    public Tuple cross(Tuple t){
        if (! this.isVector() || ! t.isVector()){
            throw new IllegalArgumentException("Cross product can only be computed with vectors.");
        }
        double newX = (this.y*t.z) - (this.z * t.y);
        double newY = (this.z*t.x) - (this.x*t.z);
        double newZ = (this.x*t.y) - (this.y*t.x);

        return vector(newX, newY, newZ);
    }



    //Overridden Methods
    @Override
    public boolean equals(Object a){
        if (a instanceof Tuple m) {
            boolean xSame = equals(this.x, m.x);
            boolean ySame = equals(this.y, m.y);
            boolean zSame = equals(this.z, m.z);

            if (this.isPoint() && m.isPoint()) {
                return (xSame && ySame && zSame);
            }

            if (this.isVector() && m.isVector()) {
                return (xSame && ySame && zSame);
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return ("(" + this.getX() + ", " + this.getY() + ", " + this.getZ() + ", " + this.w + ")");
    }


    public boolean equals(double a, double b) {
        return (Math.abs(a - b) < EPSILON);
    }


}
