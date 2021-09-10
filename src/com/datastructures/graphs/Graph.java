package com.datastructures.graphs;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * An abstract class representing a graph. Subclasses include <code>UndirectedGraph</code>, <code>WeightedUndirectedGraph</code>, <code>DirectedGraph</code>, and <code>WeightedDirectedGraph</code>.
 * @param <V> the type of nodes stored in the graph
 */
public abstract class Graph<V> {
    
    private final Map<V, Map<V, Double>> map;
    
    /**
     * Constructs a <code>Graph</code> object.
     */
    public Graph() {
        this.map = new HashMap<>();
    }
    
    /**
     * Returns a <code>Set</code> view of nodes in this <code>Graph</code> instance.
     * @return a set of nodes in the graph
     */
    public Set<V> getNodes() {
        return this.getMap().keySet();
    }
    
    /**
     * Returns a <code>Map</code> view of neighbors and their weights of a given in this <code>Graph</code> instance.
     * @param node a node in the graph
     * @return a map of neighbors and their weights of a given node in the graph
     */
    public Map<V, Double> getNeighbors(V node) {
        return this.map.get(node);
    }
    
    /**
     * Returns a <code>Map</code> view of this <code>Graph</code> instance.
     * @return a map view of the graph
     */
    public Map<V, Map<V, Double>> getMap() {
        return this.map;
    }
    
    /**
     * Returns a <code>Set</code> view of the edges of this <code>Graph</code> instance.
     * @return a set of edges in the graph
     */
    public Set<List<V>> getEdges() {
        Set<List<V>> edges = new HashSet<>();
        for (V v : this.getMap().keySet()) {
            Set<V> neighbors = this.getNeighbors(v).keySet();
            for (V n : neighbors) {
                edges.add(Arrays.asList(v, n));
            }
        }
        return edges;
    }
    
    /**
     * Adds a node to this <code>Graph</code> instance.
     * @param node a node to add to the graph
     */
    public void addNode(V node) {
        this.getMap().putIfAbsent(node, new HashMap<>());
    }
    
    /**
     * Adds multiple nodes to this <code>Graph</code> instance.
     * @param nodes an array of nodes to add to the graph
     */
    public void addNodes(V[] nodes) {
        for (V node : nodes) {
            this.addNode(node);
        }
    }
    
    /**
     * Performs a breadth-first search on this <code>Graph</code> instance starting at node <code>root</code>.
     * @param root a node in the graph to start the breadth-first search from
     * @return an object containing the distance and parent mappings from the breadth-first search
     */
    public GraphSearchOutput<V> bfs(V root) {
        
        if (!this.getNodes().contains(root)) {
            throw new NoSuchElementException("Root node not found");
        }
        
        Queue<V> q = new LinkedList<>();
        q.add(root);
        
        Map<V, Number> distances = new HashMap<>();
        Set<V> nodes = this.getNodes();
        for (V node : nodes) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        distances.put(root, 0.0);
        
        Map<V, V> parents = new HashMap<>();
        for (V node : nodes) {
            parents.put(node, null);
        }
        
        while (!q.isEmpty()) {
            
            V node = q.poll();
            Set<V> neighbors = this.getNeighbors(node).keySet();
            double newDistance = distances.get(node).intValue() + 1;
            
            for (V neighbor : neighbors) {
                if (newDistance < distances.get(neighbor).intValue()) {
                    distances.put(neighbor, newDistance);
                    parents.put(neighbor, node);
                    q.add(neighbor);
                }
            }
            
        }
        
        return new GraphSearchOutput<>(distances, parents);
        
    }
    
    /**
     * Computes, using breadth-first search on this <code>Graph</code> instance, the shortest path from node <code>root</code> to node <code>goal</code>.
     * @param root a node in the graph to start the breadth-first search from
     * @param goal a node in the graph to reach from the root node
     * @return a list representing the path from the root to the goal
     */
    public List<V> bfsPath(V root, V goal) {
        
        GraphSearchOutput<V> graphSearchOutput = bfs(root);
        Map<V, V> parents = graphSearchOutput.getParents();
        
        if (!parents.containsKey(root)) {
            throw new NoSuchElementException("Root node not found");
        }
        if (!parents.containsKey(goal)) {
            throw new NoSuchElementException("Goal node not found");
        }
        
        List<V> path = new ArrayList<>();
        path.add(goal);
        
        V currentNode = goal;
        while (currentNode != root) {
            currentNode = parents.get(currentNode);
            if (currentNode == null) {
                throw new IllegalArgumentException("Goal node is unreachable");
            }
            path.add(currentNode);
        }
        
        Collections.reverse(path);
        
        return path;
        
    }
    
    /**
     * Returns a <code>String</code> view of this <code>Graph</code> instance, with each node on a separate line.
     * @return a string representation of the graph
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (V node : this.getNodes()) {
            String next = node + ": " + this.getMap().get(node) + "\n";
            string.append(next);
        }
        return string.toString().isEmpty() ? "{}" : string.toString();
    }
    
}
