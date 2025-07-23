package RayTracer;

import java.util.LinkedList;

public class Matrix {
    private Float[][] matrix;

    public Matrix(int rowSize, int columnSize){
        matrix = new Float[rowSize][columnSize];
    }

    public void set(int row, int column, float item){
        matrix[row][column] = item;
    }

    public float get(int row, int column){
        return matrix[row][column];
    }

}
