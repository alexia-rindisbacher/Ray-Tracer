package RayTracer;

import java.util.LinkedList;

public class Matrix {
    private final static double EPSILON = 0.0001;
    private Float[][] matrix;
    private int rowSize;
    private int columnSize;

    public static void main(String args[]){
        Matrix m = new Matrix(2,2);
        m.set(0,0,1);
        m.set(0,1, 2);
        m.set(1,0, 3);
        m.set(1,1,4);

        Matrix n = new Matrix(2,2);
        n.set(0,0,1);
        n.set(0,1, 2);
        n.set(1,0, 3);
        n.set(1,1,4);

        Matrix l = m.multiply(n);
    }

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

    public Matrix multiply(Matrix other){
        //Step 1: Check that they are the compatible
        if (this.columnSize != other.rowSize){
            throw new IllegalArgumentException("Matrix Sizes are incompatible ");
        }

        //Step 2: Create resulting matrix:
        Matrix result = new Matrix(this.rowSize, other.columnSize);

        //Step 3: Determine each result and enter it
        float n = 0;

        for (int row = 0 ; row < result.rowSize ; row++){
            float[] currentRow = selectRow(row);
            for (int col = 0 ; col < result.columnSize; col++){
                float[] currentCol = selectCol(col);
                result.set(row, col, multiplyTuple(currentCol, currentRow));
            }
        }

        //Step 4: Return the result
        return result;
    }

    private float[] selectRow(int x){
        float[] row = new float[this.rowSize];
        for (int i = 0 ; i < this.rowSize ; i ++){
            row[i] = this.matrix[x][i];
        }
        return row;
    }

    private float[] selectCol(int x){
        float[] col = new float[columnSize];
        for (int i = 0 ; i < this.columnSize ; i++){
            col[i] = matrix[i][x];
        }
        return col;
    }

    private static float multiplyTuple(float[] a, float[] b){
        float result = 0;
        if (a.length != b.length){
            throw new IllegalArgumentException("Tuples must be the same length");
        }
        for (int i = 0 ; i < a.length ; i++){
            result += a[i]*b[i];
        }
        return result;
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
