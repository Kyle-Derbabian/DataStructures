package com.datastructures.graphs;

public class Main {
    
    public static void main(String[] args) {
        
        test(0);
        test(1);
        test(2);
        test(3);
        test(4);
        
    }
    
    public static void test(int number) {
        
        switch(number) {
            case 0 -> undirectedGraphTesting();
            case 1 -> weightedUndirectedGraphTesting();
            case 2 -> directedGraphTesting();
            case 3 -> weightedDirectedGraphTesting();
            case 4 -> graphsTesting();
        }
        
    }
    
    public static void undirectedGraphTesting() {
        
        UndirectedGraph<Integer> g = new UndirectedGraph<>();
        g.addNode(0);
        g.addNodes(new Integer[] {1, 2, 3, 4, 5});
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
    
        System.out.println(g.getMap());
        System.out.println(g.getNodes());
        System.out.println(g.getEdges());
        System.out.println(g.getNeighbors(0));
        System.out.println(g.getDegree(0));
        System.out.println(g);
        System.out.println(g.bfs(0).getDistances());
        System.out.println(g.bfs(0).getParents());
        System.out.println(g.bfsPath(0, 5));
        
    }

    public static void weightedUndirectedGraphTesting() {
    
        WeightedUndirectedGraph<Integer> g = new WeightedUndirectedGraph<>();
        g.addNode(0);
        g.addNodes(new Integer[] {1, 2, 3, 4, 5});
        g.addEdge(0, 1, 1.0);
        g.addEdge(0, 2, 1.0);
        g.addEdge(0, 3, 1.0);
        g.addEdge(1, 3, 1.0);
        g.addEdge(3, 4, 1.0);
        g.addEdge(4, 5, 1.0);
    
        System.out.println(g.getMap());
        System.out.println(g.getNodes());
        System.out.println(g.getEdges());
        System.out.println(g.getNeighbors(0));
        System.out.println(g.getDegree(0));
        System.out.println(g.getWeight(0, 1));
        System.out.println(g);
        System.out.println(g.bfs(0).getDistances());
        System.out.println(g.bfs(0).getParents());
        System.out.println(g.bfsPath(0, 5));
        System.out.println(g.dijkstra(0).getDistances());
        System.out.println(g.dijkstra(0).getParents());
        System.out.println(g.dijkstraPath(0, 5));
        
    }
    
    public static void directedGraphTesting() {
    
        DirectedGraph<Integer> g = new DirectedGraph<>();
        g.addNode(0);
        g.addNodes(new Integer[] {1, 2, 3, 4, 5});
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
    
        System.out.println(g.getMap());
        System.out.println(g.getNodes());
        System.out.println(g.getEdges());
        System.out.println(g.getNeighbors(0));
        System.out.println(g.getInDegree(0));
        System.out.println(g.getOutDegree(0));
        System.out.println(g);
        System.out.println(g.bfs(0).getDistances());
        System.out.println(g.bfs(0).getParents());
        System.out.println(g.bfsPath(0, 5));
        
    }
    
    public static void weightedDirectedGraphTesting() {
    
        WeightedDirectedGraph<Integer> g = new WeightedDirectedGraph<>();
        g.addNode(0);
        g.addNodes(new Integer[] {1, 2, 3, 4, 5});
        g.addEdge(0, 1, 1.0);
        g.addEdge(0, 2, 1.0);
        g.addEdge(0, 3, 1.0);
        g.addEdge(1, 3, 1.0);
        g.addEdge(3, 4, 1.0);
        g.addEdge(4, 5, 1.0);
    
        System.out.println(g.getMap());
        System.out.println(g.getNodes());
        System.out.println(g.getEdges());
        System.out.println(g.getNeighbors(0));
        System.out.println(g.getInDegree(0));
        System.out.println(g.getOutDegree(0));
        System.out.println(g.getWeight(0, 1));
        System.out.println(g);
        System.out.println(g.bfs(0).getDistances());
        System.out.println(g.bfs(0).getParents());
        System.out.println(g.bfsPath(0, 5));
        System.out.println(g.dijkstra(0).getDistances());
        System.out.println(g.dijkstra(0).getParents());
        System.out.println(g.dijkstraPath(0, 5));
        
    }
    
    public static void graphsTesting() {
    
        UndirectedGraph<Integer> undirected = Graphs.generateCompleteGraph(5);
        UndirectedGraph<Integer> cycle = Graphs.generateCycle(5);
        UndirectedGraph<Integer> completeBipartite = Graphs.generateCompleteBipartiteGraph(3, 4);
        
        System.out.println(undirected);
        System.out.println(cycle);
        System.out.println(completeBipartite);
        
    }
    
}
