package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathUnweighted {
    private int vertexCount;
    private ArrayList<ArrayList<Integer>> edges;

    ShortestPathUnweighted(int vertexCount){
        this.vertexCount = vertexCount;
        edges = new ArrayList<>();
        for (int i=0; i<vertexCount; i++)
            edges.add(new ArrayList<>());
    }

    public static void main(String[] args) {
        ShortestPathUnweighted obj = new ShortestPathUnweighted(8);
        obj.addEdge( 0, 1);
        obj.addEdge( 0, 3);
        obj.addEdge( 1, 2);
        obj.addEdge( 3, 4);
        obj.addEdge( 3, 7);
        obj.addEdge( 4, 5);
        obj.addEdge( 4, 6);
        obj.addEdge( 4, 7);
        obj.addEdge( 5, 6);
        obj.addEdge( 6, 7);

        obj.unweightedShortestPath(0);
        obj.unweightedShortestPath(2);
    }

    private void addEdge(int source, int destination){
        edges.get(source).add(destination);
        edges.get(destination).add(source);
    }

    //Time Complexity: O(V + E)
    //Space Complexity: O(V)
    private void unweightedShortestPath(int source){
        int[] dist = new int[vertexCount];
        int[] path = new int[vertexCount];
        Arrays.fill(dist, -1);
        Arrays.fill(path, -1);
        dist[source] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        int v;
        while (!queue.isEmpty()){
            v = queue.poll();
            for (int j : edges.get(v)){
                if(dist[j] == -1){
                    dist[j] = dist[v] + 1;
                    path[j] = v;
                    queue.add(j);
                }
            }
        }
        System.out.println("Shortest path from vertex " + source + " to all vertices are: ");
        for (int i=0; i<vertexCount; i++){
            System.out.print("Vertex " + i +" : length= " + dist[i]);
            System.out.print(" Path= ");
            getShortestPath(path, i);
        }
        System.out.println();
    }

    private void getShortestPath(int[] path, int destination){
        ArrayList<Integer> list = new ArrayList<>();
        int curr = destination;
        list.add(destination);
        while (path[curr] != -1){
            list.add(0, path[curr]);
            curr = path[curr];
        }
        System.out.println(list);
    }
}
