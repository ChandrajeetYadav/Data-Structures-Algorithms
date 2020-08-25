package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleDirectedGraph {
    private static final int WHITE = 0, GRAY = 1, BLACK = 2;
    private int vertexCount;
    private ArrayList<ArrayList<Integer>> edges;
    DetectCycleDirectedGraph(int vertexCount){
        this.vertexCount = vertexCount;
        edges = new ArrayList<>(vertexCount);
        for (int i=0; i<vertexCount; i++)
            edges.add(new ArrayList<>());
    }

    public static void main(String[] args) {
        DetectCycleDirectedGraph obj = new DetectCycleDirectedGraph(4);
        obj.addEdge(0, 1);
        obj.addEdge(0, 2);
        obj.addEdge(1, 2);
        obj.addEdge(2, 0);
        obj.addEdge(2, 3);
        obj.addEdge(3, 3);

        System.out.println("Graph contains cycle: " + obj.detectCycleDfs());
        System.out.println("Graph contains cycle: " + obj.detectCycleBfsKahns());
        System.out.println("Graph contains cycle: " + obj.detectCycleUsingDfsAndColoring());

        System.out.println();
        obj = new DetectCycleDirectedGraph(29);
        obj.addEdge(6, 21);
        obj.addEdge(17, 12);
        obj.addEdge(2, 11);
        obj.addEdge(9, 11);

        System.out.println("Graph contains cycle: " + obj.detectCycleBfsKahns());
    }

    private void addEdge(int source, int destination){
        edges.get(source).add(destination);
    }

    //Time Complexity : O(V + E)
    //Space Complexity : O(V)
    public boolean detectCycleBfsKahns(){
        int[] inDegree = new int[vertexCount];
        for (int i=0; i<vertexCount; i++){
            for (int j : edges.get(i))
                inDegree[j]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<vertexCount; i++){
            if (inDegree[i] == 0)
                queue.add(i);
        }
        int u;
        int count = 0;
        while (!queue.isEmpty()){
            u = queue.poll();
            for (int v : edges.get(u)){
                if(--inDegree[v] == 0)
                    queue.add(v);
            }
            count++;
        }
        return count != vertexCount;
    }

    //Time Complexity : O(V + E)
    //Space Complexity : O(V)
    public boolean detectCycleDfs(){
        boolean[] visited = new boolean[vertexCount];
        boolean[] recurStack = new boolean[vertexCount];
        for (int i=0; i<vertexCount; i++){
            if(detectCycleDfsUtil(i, visited, recurStack))
                return true;
        }
        return false;
    }

    private boolean detectCycleDfsUtil(int source, boolean[] visited, boolean[] recurStack){
        if (recurStack[source])
            return true;
        if(visited[source])
            return false;
        visited[source] = true;
        recurStack[source] = true;
        for (int v : edges.get(source)){
            if (detectCycleDfsUtil(v, visited, recurStack))
                return true;
        }
        recurStack[source] = false;
        return false;
    }

    //Time Complexity : O(V + E)
    //Space Complexity : O(V)
    public boolean detectCycleUsingDfsAndColoring(){
        int[] color = new int[vertexCount];
        for (int i=0; i<vertexCount; i++){
            if(color[i] == WHITE && detectCycleUsingDfsAndColoringUtil(i, color))
                return true;
        }
        return false;
    }

    private boolean detectCycleUsingDfsAndColoringUtil(int source, int[] color){
        color[source] = GRAY;
        for (int i : edges.get(source)){
            if(color[i] == GRAY)
                return true;
            if(color[i]== WHITE && detectCycleUsingDfsAndColoringUtil(i, color))
                return true;
        }
        color[source] = BLACK;
        return false;
    }
}
