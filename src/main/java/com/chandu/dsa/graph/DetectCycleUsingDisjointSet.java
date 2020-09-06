package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetectCycleUsingDisjointSet {
    private  int vertexCount;
    private List<List<Integer>> adj;

    DetectCycleUsingDisjointSet(int vertexCount){
        this.vertexCount = vertexCount;
        adj = new ArrayList<>(vertexCount);
        for (int i=0; i<vertexCount; i++)
            adj.add(new ArrayList<>());
    }

    public static void main(String[] args) {
        DetectCycleUsingDisjointSet obj = new DetectCycleUsingDisjointSet(3);
        obj.addEdge(0, 1);
        obj.addEdge(1, 2);
        obj.addEdge(0, 2);

        System.out.println("Is cycle present in graph: " + obj.isCyclePresent());
    }

    //Time Complexity: O(v * E)
    //Space Complexity: O(v)
    private boolean isCyclePresent(){
        int[] parent = new int[vertexCount];
        Arrays.fill(parent, -1);
        int x, y;
        for (int u=0; u<vertexCount; u++){
            for (int v : adj.get(u)){
                x = find(parent, u);
                y = find(parent, v);
                if (x == y)
                    return true;
                union(parent, x, y);
            }
        }
        return false;
    }

    private void union(int[] parent, int x, int y){
        int xSet = find(parent, x);
        int ySet = find(parent, y);
        parent[xSet] = ySet;
    }

    private int find(int[] parent, int i){
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    private void addEdge(int source, int destination){
        adj.get(source).add(destination);
    }
}
