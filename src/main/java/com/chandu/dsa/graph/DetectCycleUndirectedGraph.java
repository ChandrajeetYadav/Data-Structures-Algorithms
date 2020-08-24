package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleUndirectedGraph {
    private int vertexCount;
    private ArrayList<ArrayList<Integer>> edges;
    DetectCycleUndirectedGraph(int vertexCount){
        this.vertexCount = vertexCount;
        edges = new ArrayList<>();
        for (int i=0; i<vertexCount; i++)
            edges.add(new ArrayList<>());
    }

    public static void main(String[] args) {
        DetectCycleUndirectedGraph obj1 = new DetectCycleUndirectedGraph(5);
        obj1.addEdge(1, 0);
        obj1.addEdge(0, 2);
        obj1.addEdge(2, 1);
        obj1.addEdge(0, 3);
        obj1.addEdge(3, 4);
        System.out.println("Graph1 contains cycle using DFS: " + obj1.detectCycleUsingDfs());
        System.out.println("Graph1 contains cycle using BFS: " + obj1.detectCycleUsingBfs());
        System.out.println();

        DetectCycleUndirectedGraph obj2 = new DetectCycleUndirectedGraph(3);
        obj2.addEdge(0, 1);
        obj2.addEdge(1, 2);
        System.out.println("Graph2 contains cycle using DFS: " + obj2.detectCycleUsingDfs());
        System.out.println("Graph1 contains cycle using BFS: " + obj2.detectCycleUsingBfs());
    }

    private void addEdge(int source, int destination){
        edges.get(source).add(destination);
        edges.get(destination).add(source);
    }

    //Time Complexity : O(V + E)
    //Space Complexity : O(V)
    public boolean detectCycleUsingBfs(){
        boolean[] isVisited = new boolean[vertexCount];
        for (int i=0; i<vertexCount; i++){
            if(!isVisited[i]){
                if(detectCycleUsingBfsUtil(i, isVisited))
                    return true;
            }
        }
        return false;
    }

    public boolean detectCycleUsingBfsUtil(int source, boolean[] isVisited){
        int[] parent = new int[vertexCount];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        isVisited[source] = true;
        queue.add(source);

        int u;
        while (!queue.isEmpty()){
            u = queue.poll();
            for(int v : edges.get(u)){
                if (!isVisited[v]){
                    isVisited[v] = true;
                    parent[v] = u;
                    queue.add(v);
                }
                else if(parent[u] != v)
                    return true;
            }
        }
        return false;
    }

    //Time Complexity : O(V + E)
    //Space Complexity : O(V)
    public boolean detectCycleUsingDfs(){
        boolean[] isVisited = new boolean[vertexCount];
        for (int i=0; i<vertexCount; i++){
            if(!isVisited[i]){
                if(detectCycleUsingDfsUtil(i, isVisited, -1))
                    return true;
            }
        }
        return false;
    }

    public boolean detectCycleUsingDfsUtil(int source, boolean[] isVisited, int parent){
        isVisited[source] = true;
        for(int i : edges.get(source)){
            if(!isVisited[i]){
                if(detectCycleUsingDfsUtil(i, isVisited, source))
                    return true;
            }
            else if(i != parent)
                return true;
        }
        return false;
    }
}
