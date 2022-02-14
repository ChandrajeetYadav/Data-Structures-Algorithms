package com.sde.chandu.graph;

import java.util.*;

public class TopologicalSort {
    private static int index;
    private final int V;
    private final List<Integer>[] adj;

    private TopologicalSort(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adj[i] = new ArrayList<>();
    }

    public static void main(String[] args) {
        TopologicalSort graph = new TopologicalSort(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.println("Topological sort using DFS: " + graph.topologicalSortDFS());
        System.out.println("Topological sort using DFS without stack: " + graph.topologicalSortDFSWithoutStack());
        System.out.println("Topological sort using Kahn's algorithm: " + graph.topologicalSortUsingKahnsAlgorithm());
    }

    private void addEdge(int src, int dest) {
        adj[src].add(dest);
    }

    // Time complexity: O(V + E)
    // Space complexity: O(V)
    private List<Integer> topologicalSortDFS() {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                topologicalSortDFSUtil(i, visited, stack);
        }
        while (!stack.isEmpty())
            result.add(stack.pop());
        return result;
    }

    private void topologicalSortDFSUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int i : adj[v]) {
            if (!visited[i])
                topologicalSortDFSUtil(i, visited, stack);
        }
        stack.push(v);
    }

    // Time complexity: O(V + E)
    // Space complexity: O(V)
    private List<Integer> topologicalSortDFSWithoutStack() {
        index = V;
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                topologicalSortDFSWithoutStackUtil(i, visited, result);
        }
        return result;
    }

    private void topologicalSortDFSWithoutStackUtil(int V, boolean[] visited, List<Integer> result) {
        visited[V] = true;
        for (int i : adj[V]) {
            if (!visited[i])
                topologicalSortDFSWithoutStackUtil(i, visited, result);
        }
        result.add(0, V);
    }

    // Time complexity: O(V + E)
    // Space complexity: O(V)
    private List<Integer> topologicalSortUsingKahnsAlgorithm() {
        int[] indegree = new int[V];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int j : adj[i])
                indegree[j]++;
        }
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }
        int count = 0;
        int cur;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            result.add(cur);
            for (int i : adj[cur]) {
                if (--indegree[i] == 0)
                    queue.add(i);
            }
            count++;
        }
        if (count != V)
            System.out.println("Topological ordering is not possible as there is a cycle in the graph");
        return result;
    }
}
