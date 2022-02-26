package com.sde.chandu.graph;

import java.util.*;

public class DetectCycleUndirectedGraph {
    private final int V;
    private final List<List<Integer>> adj;

    private DetectCycleUndirectedGraph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    public static void main(String[] args) {
        /*DetectCycleUndirectedGraph graph = new DetectCycleUndirectedGraph(5);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);*/

        DetectCycleUndirectedGraph graph = new DetectCycleUndirectedGraph(4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        System.out.println("Is graph cyclic, DFS approach: " + graph.isCyclicDFS());
        System.out.println("Is graph cyclic, BFS approach: " + graph.isCyclicBFS());
    }

    private void addEdge(int src, int dest) {
        adj.get(src).add(dest);
        adj.get(dest).add(src);
    }

    // Time complexity: O(V + E)
    // Space complexity: O(V)
    private boolean isCyclicDFS() {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (isCyclicDFSUtil(i, visited, -1))
                    return true;
            }
        }
        return false;
    }

    private boolean isCyclicDFSUtil(int n, boolean[] visited, int parent) {
        visited[n] = true;
        for (int i : adj.get(n)) {
            if (!visited[i]) {
                if (isCyclicDFSUtil(i, visited, n))
                    return true;
            } else if (i != parent)
                return true;
        }
        return false;
    }

    // Time complexity: O(V + E)
    // Space complexity: O(V)
    private boolean isCyclicBFS() {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i] && isCyclicBFSUtil(i, visited))
                return true;
        }
        return false;
    }

    private boolean isCyclicBFSUtil(int n, boolean[] visited) {
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<>();
        visited[n] = true;
        queue.add(n);
        int cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            for (int i : adj.get(cur)) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                    parent[i] = cur;
                } else if (parent[cur] != i)
                    return true;
            }
        }
        return false;
    }
}
