package com.datastructures.graphs;

/**
 * This class contains static methods for generating complete graphs, cycles, and complete bipartite graphs. All generated graphs are given in the form of <code>UndirectedGraph</code> objects.
 */
public class Graphs {
    
    /**
     * Generates a complete graph.
     * @param numNodes the number of nodes to include in the graph
     * @return a complete graph with a specified number of nodes
     */
    public static UndirectedGraph<Integer> generateCompleteGraph(int numNodes) {
        
        UndirectedGraph<Integer> g = new UndirectedGraph<>();
        for (int i = 0; i < numNodes; i++) {
            g.addNode(i);
        }
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (i != j) {
                    g.addEdge(i, j);
                }
            }
        }
        return g;
        
    }
    
    /**
     * Generates a cyclic graph.
     * @param numNodes the number of nodes to include in the graph
     * @return a cycle with a specified number of nodes
     */
    public static UndirectedGraph<Integer> generateCycle(int numNodes) {
        UndirectedGraph<Integer> g = new UndirectedGraph<>();
        for (int i = 0; i < numNodes; i++) {
            g.addNode(i);
        }
        for (int i = 0; i < numNodes; i++) {
            g.addEdge(i, (i + 1) % numNodes);
        }
        return g;
        
    }
    
    /**
     * Generates a complete bipartite graph.
     * @param m the number of nodes in the first partition of the graph
     * @param n the number of nodes in the second partition of the graph
     * @return a complete bipartite graph with a specified number of nodes in each partition
     */
    public static UndirectedGraph<Integer> generateCompleteBipartiteGraph(int m, int n) {
        
        UndirectedGraph<Integer> g = new UndirectedGraph<>();
        for (int i = 0; i < m + n; i++) {
            g.addNode(i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = m; j < m + n; j++) {
                g.addEdge(i, j);
            }
        }
        return g;
        
    }
    
}
