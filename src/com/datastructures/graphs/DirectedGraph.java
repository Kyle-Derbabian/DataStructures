package com.datastructures.graphs;

import java.util.NoSuchElementException;

/**
 * A class representing an unweighted, undirected graph. Extends the <code>Graph</code> class.
 * @param <V> the type of nodes stored in the graph
 */
public class DirectedGraph<V> extends Graph<V> {
    
    /**
     * Constructs a <code>DirectedGraph</code> object.
     */
    public DirectedGraph() {
        super();
    }
    
    /**
     * Returns the indegree of a node in this <code>DirectedGraph</code> instance.
     * @param node a node in the graph
     * @return the indegree of a node in the graph
     */
    public int getInDegree(V node) {
        if (this.getMap().containsKey(node)) {
            int count = 0;
            for (V v : this.getNodes()) {
                if (this.getNeighbors(v).containsKey(node)) {
                    count += 1;
                }
            }
            return count;
        } else {
            return -1;
        }
    }
    
    /**
     * Returns the outdegree of a node in this <code>UndirectedGraph</code> instance.
     * @param node a node in the graph
     * @return the outdegree of a node in the graph
     */
    public int getOutDegree(V node) {
        if (this.getMap().containsKey(node)) {
            return this.getMap().get(node).size();
        } else {
            return -1;
        }
    }
    
    /**
     * Adds an unweighted edge to this <code>DirectedGraph</code> instance.
     * @param node1 a node in the graph
     * @param node2 a node in the graph
     */
    public void addEdge(V node1, V node2) {
        try {
            this.getMap().get(node1).put(node2, null);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Graph does not contain node");
        }
    }
    
    /**
     * Returns a <code>String</code> view of this <code>DirectedGraph</code> instance, with each node on a separate line.
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
