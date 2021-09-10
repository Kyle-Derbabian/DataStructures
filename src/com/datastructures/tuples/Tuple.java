package com.datastructures.tuples;

public class Tuple<S, T> {

    private final S first;
    private final T second;
    
    public Tuple(S first, T second) {
        this.first = first;
        this.second = second;
    }
    
    public S getFirst() {
        return first;
    }
    
    public T getSecond() {
        return second;
    }
    
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
    
    public boolean equals(Tuple<S, T> other) {
        return first.equals(other.first) && second.equals(other.second);
    }

}
