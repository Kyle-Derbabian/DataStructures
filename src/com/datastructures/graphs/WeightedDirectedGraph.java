package com.datastructures.graphs;

import java.util.Map;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A class representing a weighted, directed graph. Extends the <code>DirectedGraph</code> class.
 * @param <V> the type of nodes stored in the graph
 */
public class WeightedDirectedGraph<V> extends DirectedGraph<V> {
    
    /**
     * Constructs a <code>WeightedDirectedGraph</code> object.
     */
    public WeightedDirectedGraph() {
        super();
    }
    
    /**
     * Adds a weighted edge to this <code>WeightedDirectedGraph</code> instance.
     * @param node1 a node in the graph
     * @param node2 a node in the graph
     * @param weight the weight of the edge between the two nodes
     */
    public void addEdge(V node1, V node2, Double weight) {
        try {
            this.getMap().get(node1).put(node2, weight);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Graph does not contain node");
        }
    }
    
    /**
     * Returns the <code>Double</code> value representing the weight between the two nodes in this <code>WeightedDirectedGraph</code> instance.
     * @param node1 a node in the graph
     * @param node2 a node in the graph
     * @return the weight of the edge between the two nodes
     */
    public Double getWeight(V node1, V node2) {
        return this.getMap().get(node1).get(node2);
    }
    
    /**
     * Performs Dijkstra's algorithm on this <code>WeightedDirectedGraph</code> instance starting at node <code>root</code>.
     * @param root a node in the graph to start Dijkstra's algorithm from
     * @return an object containing the distance and parent mappings from Dijkstra's algorithm
     */
    public GraphSearchOutput<V> dijkstra(V root) {
        
        Map<V, Number> distances = new HashMap<>();
        for (V v : this.getNodes()) {
            distances.put(v, Double.POSITIVE_INFINITY);
        }
        distances.put(root, 0.0);
        
        Map<V, V> parents = new HashMap<>();
        for (V v : this.getNodes()) {
            parents.put(v, null);
        }
        
        PriorityQueue<Map.Entry<V, Double>> pq = new PriorityQueue<>(this.getNodes().size(), Comparator.comparingDouble(Map.Entry::getValue));
        Map.Entry<V, Double> first = new AbstractMap.SimpleEntry<>(root, 0.0);
        pq.add(first);
        
        while (!pq.isEmpty()) {
            Map.Entry<V, Double> next = pq.poll();
            V node = next.getKey();
            Double distance = next.getValue();
            Set<V> neighbors = this.getNeighbors(node).keySet();
            for (V neighbor : neighbors) {
                Double newDistance = distance + this.getMap().get(node).get(neighbor);
                if (newDistance < distances.get(neighbor).doubleValue()) {
                    distances.put(neighbor, newDistance);
                    parents.put(neighbor, node);
                    AbstractMap.SimpleEntry<V, Double> entry = new AbstractMap.SimpleEntry<>(neighbor, newDistance);
                    pq.add(entry);
                }
            }
        }
        
        return new GraphSearchOutput<>(distances, parents);
        
    }
    
    /**
     * Computes, using Dijkstra's algorithm on this <code>WeightedDirectedGraph</code> instance, the least-cost path from node <code>root</code> to node <code>goal</code>.
     * @param root a node in the graph to start Dijkstra's algorithm from
     * @param goal a node in the graph to reach from the root node
     * @return a list representing the path from the root to the goal.
     */
    public List<V> dijkstraPath(V root, V goal) {
        
        GraphSearchOutput<V> graphSearchOutput = dijkstra(root);
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
     * Returns a <code>String</code> view of this <code>WeightedDirectedGraph</code> instance, with each node on a separate line.
     * @return a string representation of the graph
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (V node : this.getNodes()) {
            String next = node + ": " + this.getMap().get(node) + "\n";
            string.append(next);
        }
        return string.isEmpty() ? "{}" : string.toString();
    }

}
