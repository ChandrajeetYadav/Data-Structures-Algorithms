package com.sde.chandu.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSAdjacencyMatrix {
    private final int V;
    private final int[][] adj;

    DFSAdjacencyMatrix(int V) {
        this.V = V;
        adj = new int[V][V];
    }

    public static void main(String[] args) {
        DFSAdjacencyMatrix obj = new DFSAdjacencyMatrix(4);
        obj.addEdge(0, 1);
        obj.addEdge(0, 2);
        obj.addEdge(1, 2);
        obj.addEdge(2, 0);
        obj.addEdge(2, 3);
        obj.addEdge(3, 3);

        int start = 2;
        List<Integer> result = obj.dfsRecursive(start);
        System.out.println("Recursive approach, Depth first traversal of graph starting from vertex " + start + ": " + result);

        result = obj.dfsIterative(start);
        System.out.println("Iterative approach, Depth first traversal of graph starting from vertex " + start + ": " + result);
    }

    private void addEdge(int v, int w) {
        adj[v][w] = 1;
    }

    // Time complexity: O(V^2)
    // Space complexity: O(V)
    private List<Integer> dfsRecursive(int start) {
        if (start < 0 || start >= V)
            return null;
        boolean[] visited = new boolean[V];
        List<Integer> result = new ArrayList<>();
        dfsRecursiveUtil(start, visited, result);
        return result;
    }

    private void dfsRecursiveUtil(int v, boolean[] visited, List<Integer> result) {
        visited[v] = true;
        result.add(v);
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1 && !visited[i])
                dfsRecursiveUtil(i, visited, result);
        }
    }

    // Time complexity: O(V^2)
    // Space complexity: O(V)
    private List<Integer> dfsIterative(int start) {
        if (start < 0 || start >= V)
            return null;
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        List<Integer> result = new ArrayList<>();
        stack.push(start);
        visited[start] = true;
        result.add(start);
        int cur, next;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            next = getNextAdjacent(cur, visited);
            if (next == -1)
                stack.pop();
            else {
                visited[next] = true;
                stack.push(next);
                result.add(next);
            }
        }
        return result;
    }

    private int getNextAdjacent(int v, boolean[] visited) {
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1 && !visited[i])
                return i;
        }
        return -1;
    }
}
