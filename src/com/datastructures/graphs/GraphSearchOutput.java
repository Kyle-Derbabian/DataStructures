package com.datastructures.graphs;

import java.util.Map;

/**
 * A class containing the output of graph-search algorithms such as breadth-first search and Dijkstra's algorithm. Contains a <code>distances</code> mapping and a <code>parents</code> mapping.
 * @param <V> type of nodes stored in the graph
 */
public class GraphSearchOutput<V> {
    
    private final Map<V, Number> distances;
    private final Map<V, V> parents;
    
    /**
     * Constructs a <code>GraphSearchOutput</code> object.
     * @param distances a mapping of nodes to their distances from a root
     * @param parents a mapping of nodes to their parents in a computed path
     */
    public GraphSearchOutput(Map<V, Number> distances, Map<V, V> parents) {
        this.distances = distances;
        this.parents = parents;
    }
    
    /**
     * Returns the distances mapping of this <code>GraphSearchOutput</code> instance
     * @return the distances mapping of the graph search
     */
    public Map<V, Number> getDistances() {
        return distances;
    }
    
    /**
     * Returns the parents mapping of this <code>GraphSearchOutput</code> instance
     * @return the parents mapping of the graph search
     */
    public Map<V, V> getParents() {
        return parents;
    }

}
