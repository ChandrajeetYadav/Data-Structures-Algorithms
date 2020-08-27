package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class StronglyConnectedComponentsKosarajuAlgorithm {
    private int vertexCount;
    private ArrayList<ArrayList<Integer>> adj;

    StronglyConnectedComponentsKosarajuAlgorithm(int vertexCount){
        this.vertexCount = vertexCount;
        adj = new ArrayList<>(vertexCount);
        for (int i=0; i<vertexCount; i++)
            adj.add(new ArrayList<>());
    }

    public static void main(String[] args) {
        StronglyConnectedComponentsKosarajuAlgorithm obj = new StronglyConnectedComponentsKosarajuAlgorithm(5);
        obj.addEdge(1, 0);
        obj.addEdge(0, 2);
        obj.addEdge(2, 1);
        obj.addEdge(0, 3);
        obj.addEdge(3, 4);

        System.out.println("Strongly connected components in the graph are");
        obj.printStronglyConnectedComponent();
    }

    private void addEdge(int source, int destination){
        adj.get(source).add(destination);
    }

    //Time Complexity: O(V + E)
    //Space Complexity: O(V)
    private void printStronglyConnectedComponent(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertexCount];

        for (int i=0; i<vertexCount; i++){
            if(!visited[i])
                fillOrder(i, visited, stack);
        }

        StronglyConnectedComponentsKosarajuAlgorithm transposedGraph = getTransposedGraph();
        Arrays.fill(visited, false);
        int v;
        while (!stack.isEmpty()){
            v = stack.pop();
            if(!visited[v]){
                transposedGraph.dfsUtil(v, visited);
                System.out.println();
            }
        }
    }

    private void fillOrder(int v, boolean[] visited, Stack<Integer> stack){
        visited[v] = true;
        for (int i : adj.get(v)){
            if(!visited[i])
                fillOrder(i, visited, stack);
        }
        stack.push(v);
    }

    private StronglyConnectedComponentsKosarajuAlgorithm getTransposedGraph(){
        StronglyConnectedComponentsKosarajuAlgorithm obj = new StronglyConnectedComponentsKosarajuAlgorithm(vertexCount);
        for (int i=0; i<vertexCount; i++){
            for (int j : adj.get(i))
                obj.addEdge(j, i);
        }
        return obj;
    }

    private void dfsUtil(int v, boolean[] visited){
        visited[v] = true;
        System.out.print(v + " ");
        for (int i : adj.get(v)){
            if(!visited[i])
                dfsUtil(i, visited);
        }
    }
}
