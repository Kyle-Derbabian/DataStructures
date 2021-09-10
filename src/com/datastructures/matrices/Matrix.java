package com.datastructures.matrices;

public class Matrix {
    
    private final int numRows;
    private final int numColumns;
    private final double[][] matrix;
    
    public Matrix(int numRows, int numColumns) {
        this.matrix = new double[numRows][numColumns];
        this.numRows = numRows;
        this.numColumns = numColumns;
    }
    
    public Matrix(double[][] matrix) {
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
    
    public Double get(int row, int column) {
        return matrix[row][column];
    }
    
    public void set(int row, int column, Double value) {
        matrix[row][column] = value;
    }
    
    public Matrix withoutRow(int row) {
        System.out.println(this);
        Matrix copy = new Matrix(this.numRows - 1, this.numColumns);
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                if (i < row) {
                    System.out.println(i + " " + j + " " + this.get(i, j));
                    copy.set(i, j, this.get(i, j));
                } else if (i > row) {
                    System.out.println(i + " " + j + " " + this.get(i, j));
                    copy.set(i - 1, j, this.get(i, j));
                }
            }
        }
        return copy;
    }
    
    public Matrix withoutColumn(int column) {
        Matrix copy = new Matrix(this.numRows, this.numColumns - 1);
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                if (j < column) {
                    copy.set(i, j, this.get(i, j));
                } else if (j > column) {
                    copy.set(i, j - 1, this.get(i, j));
                }
            }
        }
        return copy;
    }
    
    public double getDeterminant() {
        
        if (numRows != numColumns) {
            throw new IllegalArgumentException("Matrix has invalid dimensions");
        }
        
        if (this.numRows == 1) {
            return this.get(0, 0);
        }
        if (this.numRows == 2) {
            return this.get(0, 0) * this.get(1, 1) - this.get(0, 1) * this.get(1, 0);
        }
    
        Matrix copy = new Matrix(this.numRows, this.numColumns);
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numRows; j++) {
                copy.set(i, j, this.get(i, j));
            }
        }
        
        double determinant = 0.0;
        double[] topRow = matrix[0];
        Matrix removedRowMatrix = this.withoutRow(0);
        for (int i = 0; i < topRow.length; i++) {
            Matrix removedColumnMatrix = removedRowMatrix.withoutColumn(i);
            System.out.println(removedColumnMatrix);
            determinant += Math.pow(-1, i) * topRow[i] * removedColumnMatrix.getDeterminant();
        }
        
        return determinant;
        
    }
    
    public Matrix plus(Matrix other) {
        
        if (numRows != other.numRows || numColumns != other.numColumns) {
            throw new IllegalArgumentException("Matrices have invalid dimensions");
        }
    
        Matrix sum = new Matrix(numRows, numColumns);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                Double newValue = this.get(i, j) + other.get(i, j);
                sum.set(i, j, newValue);
            }
        }
        return sum;
        
    }
    
    public Matrix minus(Matrix other) {
        
        if (numRows != other.numRows || numColumns != other.numColumns) {
            throw new IllegalArgumentException("Matrices have invalid dimensions");
        }
    
        Matrix difference = new Matrix(numRows, numColumns);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                Double newValue = this.get(i, j) - other.get(i, j);
                difference.set(i, j, newValue);
            }
        }
        return difference;
        
    }
    
    public Matrix scaledBy(double scalar) {
    
        Matrix scaled = new Matrix(numRows, numColumns);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                Double newValue = this.get(i, j) * scalar;
                scaled.set(i, j, newValue);
            }
        }
        return scaled;
        
    }
    
    public Matrix times(Matrix other) {
        
        if (this.numColumns != other.numRows) {
            throw new IllegalArgumentException("Matrices have invalid dimensions");
        }
    
        Matrix product = new Matrix(this.numRows, other.numColumns);
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < other.numColumns; j++) {
                for (int k = 0; k < other.numRows; k++) {
                    double oldValue = product.get(i, j);
                    double newValue = this.get(i, k) * other.get(k, j);
                    product.set(i, j, oldValue + newValue);
                }
            }
        }
        return product;
        
    }
    
    public Matrix getTranspose() {
        Matrix transpose = new Matrix(numColumns, numRows);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                Double newValue = this.get(i, j);
                transpose.set(j, i, newValue);
            }
        }
        return transpose;
        
    }
    
    public Matrix augmentWith(Matrix other) {
        
        if (this.numRows != other.numRows) {
            throw new IllegalArgumentException("Matrices have invalid dimensions");
        }
    
        Matrix augmented = new Matrix(this.numRows, this.numColumns + other.numColumns);
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns + other.numColumns; j++) {
                Double newValue = j < this.numColumns ? this.get(i, j) : other.get(i, j - other.numColumns);
                augmented.set(i, j, newValue);
            }
        }
        return augmented;
        
    }
    
    private void swapRows(int row1, int row2) {
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }
    
    private void scaleRow(int row, double scalar) {
        double[] currentRow = matrix[row];
        for (int i = 0; i < currentRow.length; i++) {
            currentRow[i] *= scalar;
        }
        matrix[row] = currentRow;
    }
    
    private void combineRows(int row1, int row2, double scalar) {
        for (int i = 0; i < numColumns; i++) {
            matrix[row1][i] += scalar * matrix[row2][i];
        }
    }

    public Matrix getRREF() {

        Matrix rref = new Matrix(numRows, numColumns);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                double value = matrix[i][j];
                rref.set(i, j, value);
            }
        }
        
        int lead = 0;
        int rowCount = numRows;
        int columnCount = numColumns;

        for (int r = 0; r < rowCount; r++) {
            
            if (columnCount <= lead) {
                break;
            }
            int i = r;
            while (rref.get(i, lead) == 0) {
                i += 1;
                if (rowCount == i) {
                    i = r;
                    lead += 1;
                    if (columnCount == lead) {
                        return rref;
                    }
                }
            }
            if (i != r) {
                rref.swapRows(i, r);
            }
            rref.scaleRow(r, 1 / rref.get(r, lead));
            for (int j = 0; j < rowCount; j++) {
                if (j != r) {
                    rref.combineRows(j, r, -rref.get(j, lead));
                }
            }
            lead += 1;
        }

        return rref;

    }
    
    public Matrix getInverse() {
        
        if (this.getDeterminant() == 0.0) {
            throw new IllegalArgumentException("Matrix is not invertible (determinant is 0)");
        }
        
        Matrix identity = new Matrix(numRows, numColumns);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                double value = i == j ? 1.0 : 0.0;
                identity.set(i, j, value);
            }
        }
        
        Matrix augmented = this.augmentWith(identity);
        Matrix rrefAugmented = augmented.getRREF();
        
        Matrix inverse = new Matrix(numRows, numColumns);
        for (int i = 0; i < numRows; i++) {
            for (int j = numColumns; j < numColumns * 2; j++) {
                Double value = rrefAugmented.get(i, j);
                inverse.set(i, j - numColumns, value);
            }
        }
        
        return inverse;
        
    }
    
    public void fill(Double value) {
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                this.set(i, j, value);
            }
        }
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
