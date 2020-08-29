package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CircleOfStrings {
    private final int ALPHABETS_SIZE = 26;
    private int vertexCount;
    private int[] indegree;
    private List<List<Integer>> adj;

    CircleOfStrings(){
        this.vertexCount =  ALPHABETS_SIZE;
        indegree = new int[ALPHABETS_SIZE];
        adj = new ArrayList<>(ALPHABETS_SIZE);
        for (int i=0; i<ALPHABETS_SIZE; i++)
            adj.add(new ArrayList<>());
    }

    public static void main(String[] args) {
        String[] arr1 = { "for", "geek", "rig", "kaf" };
        System.out.println("arr1 can be chained: " + canBeChained(arr1));

        String[] arr2 = { "aab", "abb" };
        System.out.println("arr2 can be chained: " + canBeChained(arr2));

        String[] arr3 = { "abc", "bcd", "cdf" };
        System.out.println("arr3 can be chained: " + canBeChained(arr3));

        String[] arr4 = { "ab", "bc", "cd", "da" };
        System.out.println("arr4 can be chained: " + canBeChained(arr4));
    }

    private void addEdge(int source, int destination){
        adj.get(source).add(destination);
        indegree[destination]++;
    }

    //Time Complexity: O(V + E), here v
    //Space Complexity: O(V)
    private static boolean canBeChained(String[] arr){
        CircleOfStrings obj = new CircleOfStrings();
        for (int i=0; i<arr.length; i++)
            obj.addEdge(arr[i].charAt(0)-'a', arr[i].charAt(arr[i].length() - 1) - 'a');
        return obj.isEulerianCycle();
    }

    private boolean isEulerianCycle(){
        if(!isStronglyConnected())
            return false;
        for (int i=0; i<vertexCount; i++){
            if(adj.get(i).size() != indegree[i])
                return false;
        }
        return true;
    }

    private boolean isStronglyConnected(){
        boolean[] visited = new boolean[vertexCount];
        int n;
        for (n=0 ; n<vertexCount; n++){
            if (adj.get(n).size() > 0)
                break;
        }

        dfsUtil(visited, n);
        if(isUnvisitedNodesPresent(visited))
            return false;

        CircleOfStrings transposedGraph = getTransposedGraph();
        Arrays.fill(visited, false);
        transposedGraph.dfsUtil(visited, n);
        if(transposedGraph.isUnvisitedNodesPresent(visited))
            return false;

        return true;
    }

    private boolean isUnvisitedNodesPresent(boolean[] visited){
        for (int i=0; i<vertexCount; i++){
            if (adj.get(i).size()>0 && !visited[i])
                return true;
        }
        return false;
    }

    private void dfsUtil(boolean[] visited, int v){
        visited[v] = true;
        for (int j : adj.get(v)){
            if(!visited[j])
                dfsUtil(visited, j);
        }
    }

    private CircleOfStrings getTransposedGraph(){
        CircleOfStrings transposedGraph = new CircleOfStrings();
        for (int i=0; i<vertexCount; i++){
            for (int j : adj.get(i))
                transposedGraph.addEdge(j, i);
        }
        return transposedGraph;
    }
}
