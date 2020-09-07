package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetectCycleUnionSize {
    private int vertexCount;
    private List<List<Integer>> adj;

    DetectCycleUnionSize(int vertexCount){
        this.vertexCount = vertexCount;
        adj = new ArrayList<>(vertexCount);
        for (int i=0; i<vertexCount; i++)
            adj.add(new ArrayList<>());
    }
    public static void main(String[] args) {
        DetectCycleUnionSize obj = new DetectCycleUnionSize(3);
        obj.addEdge(0, 1);
        obj.addEdge(1, 2);
        obj.addEdge(0, 2);

        System.out.println("Graph contains cycle: " + obj.isCyclePresent());
    }

    private void addEdge(int source, int destination){
        adj.get(source).add(destination);
    }

    //Time Complexity: O(E * log V)
    //Space Complexity: O(V)
    private boolean isCyclePresent(){
        int[] parent= new int[vertexCount];
        Arrays.fill(parent, -1);
        int x, y;
        for (int u=0; u<vertexCount; u++){
            for (int v : adj.get(u)){
                x = find(parent, u);
                y = find(parent, v);
                if(x == y)
                    return true;
                union(parent, x, y);
            }
        }
        return false;
    }

    private void union(int[] parent, int x, int y){
        int xSet = find(parent, x);
        int ySet = find(parent, y);

        if (parent[xSet] <= parent[ySet]){
            parent[xSet] = parent[xSet] + parent[ySet];
            parent[ySet] = xSet;
        }else{
            parent[ySet] = parent[xSet] + parent[ySet];
            parent[xSet] = ySet;
        }
        for(int i : parent)
            System.out.print(i + " ");
        System.out.println();
    }

    private int find(int[] parent, int i){
        if(parent[i] < 0)
            return i;
        return find(parent, parent[i]);
    }
}
