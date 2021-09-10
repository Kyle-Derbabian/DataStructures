package com.datastructures.tuples;

public class Triple<R, S, T> {
    
    private final R first;
    private final S second;
    private final T third;
    
    public Triple(R first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
    
    public R getFirst() {
        return first;
    }
    
    public S getSecond() {
        return second;
    }
    
    public T getThird() {
        return third;
    }
    
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }
    
    public boolean equals(Triple<R, S, T> other) {
        return first.equals(other.first) && second.equals(other.second) && third.equals(other.third);
    }
    
}
