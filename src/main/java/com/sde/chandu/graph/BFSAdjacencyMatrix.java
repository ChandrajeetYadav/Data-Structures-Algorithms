package com.sde.chandu.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSAdjacencyMatrix {
    private final int V;
    private final int[][] adj;

    BFSAdjacencyMatrix(int V) {
        this.V = V;
        adj = new int[V][V];
    }

    public static void main(String[] args) {
        BFSAdjacencyMatrix graph = new BFSAdjacencyMatrix(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        int start = 2;
        List<Integer> result = graph.bfsIterative(start);
        System.out.println("Iterative approach, Breadth first traversal of graph starting from vertex " + start + ": " + result);

        result = graph.bfsRecursive(start);
        System.out.println("Recursive approach, Breadth first traversal of graph starting from vertex " + start + ": " + result);
    }

    private void addEdge(int src, int dest) {
        adj[src][dest] = 1;
    }

    // Time complexity: O(V + E)
    // Space complexity: O(V)
    private List<Integer> bfsIterative(int start) {
        if (start < 0 || start >= V)
            return null;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[V];
        List<Integer> result = new ArrayList<>();
        queue.add(start);
        visited[start] = true;
        int cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            result.add(cur);
            for (int i = 0; i < V; i++) {
                if (adj[cur][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        return result;
    }

    // Time complexity: O(V + E)
    // Space complexity: O(V)
    private List<Integer> bfsRecursive(int start) {
        if (start < 0 || start >= V)
            return null;
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[V];
        queue.add(start);
        visited[start] = true;
        bfsRecursiveUtil(queue, visited, result);
        return result;
    }

    private void bfsRecursiveUtil(Queue<Integer> queue, boolean[] visited, List<Integer> result) {
        if (queue.isEmpty())
            return;
        int cur = queue.poll();
        result.add(cur);
        for (int i = 0; i < V; i++) {
            if (adj[cur][i] == 1 && !visited[i]) {
                queue.add(i);
                visited[i] = true;
            }
        }
        bfsRecursiveUtil(queue, visited, result);
    }
}
