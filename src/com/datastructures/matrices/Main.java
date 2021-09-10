package com.datastructures.matrices;

public class Main {
    
    public static void main(String[] args) {
        
        double[][] matrixArray = new double[][] {{1, 2, 3}, {1, 4, 5}, {6, 4, 3}};
        Matrix matrix = new Matrix(matrixArray);

        boolean[][] booleanMatrixArray1 = new boolean[][] {{true, false, false}, {true, true, false}, {false, true, false}};
        boolean[][] booleanMatrixArray2 = new boolean[][] {{true, false, false}, {true, true, false}, {true, false, false}};

        BooleanMatrix booleanMatrix1 = new BooleanMatrix(booleanMatrixArray1);
        BooleanMatrix booleanMatrix2 = new BooleanMatrix(booleanMatrixArray2);

        System.out.println(booleanMatrix1);
        System.out.println(booleanMatrix2);

        System.out.println(booleanMatrix1.times(booleanMatrix2));
        
    }
    
}
