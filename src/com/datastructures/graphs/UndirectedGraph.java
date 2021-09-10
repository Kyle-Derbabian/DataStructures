package com.datastructures.graphs;

import java.util.NoSuchElementException;

/**
 * A class representing an unweighted, undirected graph. Extends the <code>Graph</code> class.
 * @param <V> the type of nodes stored in the graph
 */
public class UndirectedGraph<V> extends Graph<V> {
    
    /**
     * Constructs an <code>UndirectedGraph</code> object.
     */
    public UndirectedGraph() {
        super();
    }
    
    /**
     * Returns the degree of a node in this <code>UndirectedGraph</code> instance.
     * @param node a node in the graph
     * @return the degree of a node in the graph
     */
    public int getDegree(V node) {
        if (this.getMap().containsKey(node)) {
            return this.getMap().get(node).size();
        } else {
            return -1;
        }
    }
    
    /**
     * Adds an unweighted edge to this <code>UndirectedGraph</code> instance.
     * @param node1 a node in the graph
     * @param node2 a node in the graph
     */
    public void addEdge(V node1, V node2) {
        try {
            this.getMap().get(node1).put(node2, null);
            this.getMap().get(node2).put(node1, null);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Graph does not contain node");
        }
    }
    
    /**
     * Returns a <code>String</code> view of this <code>UndirectedGraph</code> instance, with each node on a separate line.
     * @return a string representation of the graph
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (V node : this.getNodes()) {
            String next = node + ": {" + this.getMap().get(node).keySet().toString().substring(1) + "\b}\n";
            string.append(next);
        }
        return string.isEmpty() ? "{}" : string.toString();
    }
    
}
