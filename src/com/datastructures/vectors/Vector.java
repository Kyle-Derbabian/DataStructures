package com.datastructures.vectors;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Vector {

    private final List<Double> vector = new ArrayList<>();
    
    public Vector(Double ... values) {
        this.vector.addAll(Arrays.asList(values));
    }
    
    public int size() {
        return vector.size();
    }
    
    public Double get(int i) {
        return vector.get(i);
    }
    
    private void add(Double value) {
        vector.add(value);
    }
    
    public double magnitude() {
        
        double magnitude = 0;
        for (int i = 0; i < this.size(); i++) {
            magnitude += this.get(i) * this.get(i);
        }
        magnitude = Math.sqrt(magnitude);
        return magnitude;
        
    }
    
    public Vector plus(Vector other) {
        
        if (this.size() != other.size()) {
            throw new IllegalArgumentException("Vectors are of different size");
        }
        
        Vector sum = new Vector();
        for (int i = 0; i < this.size(); i++) {
            sum.add(this.get(i) + other.get(i));
        }
        
        return sum;
        
    }
    
    public Vector minus(Vector other) {
        
        if (this.size() != other.size()) {
            throw new IllegalArgumentException("Vectors are of different size");
        }
        
        Vector sum = new Vector();
        for (int i = 0; i < this.size(); i++) {
            sum.add(this.get(i) - other.get(i));
        }
        
        return sum;
        
    }
    
    public Double dot(Vector other) {
    
        if (this.size() != other.size()) {
            throw new IllegalArgumentException("Vectors are of different size");
        }
        
        double dotProduct = 0;
        for (int i = 0; i < this.size(); i++) {
            dotProduct += this.get(i) * other.get(i);
        }
        return dotProduct;
        
    }
    
    public String toString() {
        if (this.size() == 0) {
            return "<>";
        }
        StringBuilder string = new StringBuilder("<");
        for (Double i : vector) {
            String next = i + ", ";
            string.append(next);
        }
        string.append("\b\b>");
        return string.toString();
    }

}
