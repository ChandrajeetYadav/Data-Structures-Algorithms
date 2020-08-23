package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TopologicalSort {
    private int vertexCount;
    private ArrayList<Integer>[] edges;

    TopologicalSort(int vertexCount){
        this.vertexCount = vertexCount;
        edges = new ArrayList[vertexCount];
        for(int i=0; i<vertexCount; i++)
            edges[i] = new ArrayList<>();
    }

    private void addEdge(int source, int destination){
        edges[source].add(destination);
    }

    public static void main(String[] args) {
        TopologicalSort obj = new TopologicalSort(6);
        obj.addEdge(5, 2);
        obj.addEdge(5, 0);
        obj.addEdge(4, 0);
        obj.addEdge(4, 1);
        obj.addEdge(2, 3);
        obj.addEdge(3, 1);
        System.out.println("Topological sort of the graph is as below: ");
        //obj.topologicalSort();
        obj.topologicalSortUsingKahnsAlgo();
    }

    //Time Complexity: O(V + E)
    //Space Complexity: O(V)
    public void topologicalSort(){
        boolean[] isVisited = new boolean[vertexCount];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<vertexCount; i++){
            if(!isVisited[i])
                topologicalSortUtil(isVisited, i, stack);
        }
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
        System.out.println();
    }

    public void topologicalSortUtil(boolean[] isVisited, int v, Stack<Integer> stack){
        isVisited[v] = true;
        for(int i : edges[v]){
            if (!isVisited[i])
                topologicalSortUtil(isVisited, i, stack);
        }
        stack.push(v);
    }

    //Time Complexity: O(V + E)
    //Space Complexity: O(V)
    public void topologicalSortUsingKahnsAlgo(){
        int[] indegree = new int[vertexCount];
        for(int i=0; i<vertexCount; i++){
            for (int j : edges[i])
                indegree[j]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<vertexCount; i++){
            if (indegree[i] == 0)
                queue.add(i);
        }
        int count = 0;
        ArrayList<Integer> result = new ArrayList<>();
        int v;
        while (!queue.isEmpty()){
            v = queue.poll();
            result.add(v);
            for (int i : edges[v]){
                if(--indegree[i] == 0)
                    queue.add(i);
            }
            count++;
        }
        if(count != vertexCount){
            System.out.println("Graph has a cycle");
            return;
        }
        for (int i : result)
            System.out.print(i + " ");
    }
}
