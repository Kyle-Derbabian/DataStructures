package com.datastructures.matrices;

public class BooleanMatrix {
    
    private final int numRows;
    private final int numColumns;
    private final boolean[][] matrix;
    
    public BooleanMatrix(int numRows, int numColumns) {
        this.matrix = new boolean[numRows][numColumns];
        this.numRows = numRows;
        this.numColumns = numColumns;
    }
    
    public BooleanMatrix(boolean[][] matrix) {
        this.matrix = matrix;
        this.numRows = matrix.length;
        this.numColumns = matrix[0].length;
    }
    
    public int getNumRows() {
        return numRows;
    }
    
    public int getNumColumns() {
        return numColumns;
    }
    
    public Boolean get(int row, int column) {
        return matrix[row][column];
    }
    
    public void set(int row, int column, Boolean value) {
        matrix[row][column] = value;
    }
    
    public BooleanMatrix complement() {
        BooleanMatrix complement = new BooleanMatrix(numRows, numColumns);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                boolean value = this.get(i, j);
                complement.set(i, j, value);
            }
        }
        return complement;
    }
    
    public BooleanMatrix or(BooleanMatrix other) {
        
        BooleanMatrix logicalOr = new BooleanMatrix(numRows, numColumns);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                boolean value = this.get(i, j) || other.get(i, j);
                logicalOr.set(i, j, value);
            }
        }
        return logicalOr;
        
    }
    
    public BooleanMatrix and(BooleanMatrix other) {
        
        BooleanMatrix logicalAnd = new BooleanMatrix(numRows, numColumns);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                boolean value = this.get(i, j) && other.get(i, j);
                logicalAnd.set(i, j, value);
            }
        }
        return logicalAnd;
        
    }
    
    public BooleanMatrix times(BooleanMatrix other) {

        if (this.numColumns != other.numRows) {
            throw new IllegalArgumentException("Matrices have invalid dimensions");
        }
    
        BooleanMatrix logicalProduct = new BooleanMatrix(this.numRows, other.numColumns);
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < other.numColumns; j++) {
                for (int k = 0; k < other.numRows; k++) {
                    boolean oldValue = logicalProduct.get(i, j);
                    boolean newValue = this.get(i, k) && other.get(k, j);
                    logicalProduct.set(i, j, oldValue || newValue);
                }
            }
        }
        return logicalProduct;
        
    }
    
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                String next = matrix[i][j] + " ";
                string.append(next);
            }
            string.append("\n");
        }
        return string.isEmpty() ? "[]" : string.toString();
    }

}
