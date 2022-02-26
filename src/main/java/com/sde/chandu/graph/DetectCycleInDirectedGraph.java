package com.sde.chandu.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DetectCycleInDirectedGraph {
    private final int V;
    private final List<List<Integer>> adj;

    private DetectCycleInDirectedGraph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    public static void main(String[] args) {
        DetectCycleInDirectedGraph graph = new DetectCycleInDirectedGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("Is graph cyclic, using DFS approach: " + graph.isCyclicDFS());
        System.out.println("Is graph cyclic, using BFS/Kahn's approach: " + graph.isCyclicBFS());
    }

    private void addEdge(int src, int dest) {
        adj.get(src).add(dest);
    }

    // Time complexity: O(V + E)
    // Space complexity: O(V)
    private boolean isCyclicDFS() {
        boolean[] visted = new boolean[V];
        boolean[] recStack = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (isCyclicDFSUtil(i, visted, recStack))
                return true;
        }
        return false;
    }

    private boolean isCyclicDFSUtil(int n, boolean[] visited, boolean[] recStack) {
        if (recStack[n])
            return true;
        if (visited[n])
            return false;
        visited[n] = true;
        recStack[n] = true;
        for (int i : adj.get(n)) {
            if (isCyclicDFSUtil(i, visited, recStack))
                return true;
        }
        recStack[n] = false;
        return false;
    }

    // Time complexity: O(V + E)
    // Space complexity: O(V)
    // Using Kahn's algorithm
    private boolean isCyclicBFS() {
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int j : adj.get(i))
                inDegree[j]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0)
                queue.add(i);
        }
        int count = 0;
        int cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            for (int i : adj.get(cur)) {
                if (--inDegree[i] == 0)
                    queue.add(i);
            }
            count++;
        }
        return count != V;
    }
}
