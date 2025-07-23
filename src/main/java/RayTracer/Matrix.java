package RayTracer;

import java.util.LinkedList;

public class Matrix {
    private final static double EPSILON = 0.0001;
    private Float[][] matrix;
    private int rowSize;
    private int columnSize;

    public Matrix(int rowSize, int columnSize){
        this.matrix = new Float[rowSize][columnSize];
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public void set(int row, int column, float item){
        matrix[row][column] = item;
    }

    public float get(int row, int column){
        return matrix[row][column];
    }

    //Overridden Methods
    @Override
    public boolean equals(Object a){
        if (a instanceof Matrix) {
            if (this.rowSize != ((Matrix) a).rowSize || this.columnSize != ((Matrix) a).columnSize){
                return false;
            }

            for (int i = 0; i < rowSize ; i++){
                for (int n = 0 ; n < columnSize ; n++){
                    float x = ((Matrix) a).get(i, n);
                    float y = this.get(i,n);
                    if (!equals(x,y)){
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }

    public boolean equals(double a, double b) {
        return (Math.abs(a - b) < EPSILON);
    }
}
