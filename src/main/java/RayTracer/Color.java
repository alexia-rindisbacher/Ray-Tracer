package RayTracer;

public class Color {
    private double red;
    private double green;
    private double blue;
    private final static double EPSILON = 0.0001;

    //Constructor: Initalizes a new color with values.
    public Color(double red, double green, double z){
        this.red = red;
        this.green = green;
        this.blue = z;
    }

    //Get methods
    public double getRed(){return this.red;}
    public double getGreen(){return this.green;}
    public double getBlue(){return this.blue;}


    public Color add(Color t) {

        double redValue = this.getRed() + t.getRed();
        double greenValue = this.getGreen() + t.getGreen();
        double blueValue = this.getBlue() + t.getBlue();

        return new Color(redValue, greenValue, blueValue);
    }


    public Color subtract(Color c){

        double redValue = this.getRed() - c.getRed();
        double greenValue = this.getGreen() - c.getGreen();
        double blueValue = this.getBlue() - c.getBlue();

        return new Color(redValue, greenValue, blueValue);
    }

    public void negate(){
        this.red = -this.red;
        this.green = -this.green;
        this.blue = -this.blue;
    }

    public void multiply(double d){
        this.red = this.red*d;
        this.green = this.green *d;
        this.blue = this.blue*d;
    }

    public double magnitude(){
        double redSq = Math.pow(this.getRed(), 2);
        double greenSq = Math.pow(this.getGreen(), 2);
        double blueSq = Math.pow(this.getBlue(), 2);
        return Math.sqrt((redSq+ greenSq + blueSq));
    }

    public void normalize(){
        double magnitude = this.magnitude();
        double newRed = this.getRed() / magnitude;
        double newGreen = this.getGreen() / magnitude;
        double newBlue = this.getBlue() / magnitude;
        this.red = newRed;
        this.green = newGreen;
        this.blue = newBlue;
    }

    public Color hadamard(Color t){
        return (new Color (this.getRed()*t.getRed() , this.getGreen() * t.getGreen() , this.getBlue() * t.getBlue()));
    }

    public void replace(Color c){
        this.red = c.red;
        this.green = c.green;
        this.blue = c.blue;
    }


    //Overridden Methods
    @Override
    public boolean equals(Object a){
        if (a instanceof Color m) {
            boolean redSame = equals(this.red, m.red);
            boolean greenSame = equals(this.green, m.green);
            boolean blueSame = equals(this.blue, m.blue);

            return (redSame && greenSame && blueSame);
        }
        return false;
    }

    @Override
    public String toString(){
        return ("(" + this.getRed() + ", " + this.getGreen() + ", " + this.getBlue()+")");
    }


    public boolean equals(double a, double b) {
        return (Math.abs(a - b) < EPSILON);
    }

}
