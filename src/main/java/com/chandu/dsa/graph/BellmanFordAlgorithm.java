package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFordAlgorithm {
    private int vertexCount;
    private ArrayList<Edge> edges;

    BellmanFordAlgorithm(int vertexCount){
        this.vertexCount = vertexCount;
        edges = new ArrayList<>();
    }

    private void addEdge(Edge edge){
        edges.add(edge);
    }

    public static void main(String[] args) {
        BellmanFordAlgorithm obj = new BellmanFordAlgorithm(5);
        obj.addEdge(new Edge(0, 1, -1));
        obj.addEdge(new Edge(0, 2, 4));
        obj.addEdge(new Edge(1, 2, 3));
        obj.addEdge(new Edge(1, 3, 2));
        obj.addEdge(new Edge(1, 4, 2));
        obj.addEdge(new Edge(3, 2, 5));
        obj.addEdge(new Edge(3, 1, 1));
        obj.addEdge(new Edge(4, 3, -3));

        obj.bellmanFordShortestPath(0);
    }

    //Time complexity : O(V * E)
    //Space Complexity : O(V)
    public void bellmanFordShortestPath(int source){
        int[] distance = new int[vertexCount];
        int[] path = new int[vertexCount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        distance[source] = 0;
        Edge temp;
        int u, v, dist;
        boolean isNegativeCycle = false;
        for (int i=1; i<vertexCount; i++){
            for (int j=0; j<edges.size(); j++){
                temp = edges.get(j);
                u = temp.source;
                v = temp.destination;
                if(distance[u]!=Integer.MAX_VALUE && distance[v] > distance[u]+temp.weight){
                    distance[v] = distance[u] + temp.weight;
                    path[v] = u;
                }
            }
        }
        for (Edge edge : edges){
            u = edge.source;
            v = edge.destination;
            if(distance[u]!=Integer.MAX_VALUE && distance[v]>distance[u]+edge.weight){
                isNegativeCycle = true;
                break;
            }
        }
        if (isNegativeCycle){
            System.out.println("Graph contains negative weight cycle");
            return;
        }

        System.out.println("Shortest path is as below: ");
        for (int i=0; i<vertexCount; i++){
            System.out.print("Source= "+source+", Destination= "+i+" : length="+distance[i]+", path=");
            printPath(path, i);
        }
    }

    private void printPath(int[] path, int destination){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(destination);
        while (path[destination] != -1){
            list.add(0, path[destination]);
            destination = path[destination];
        }
        System.out.println(list);
    }

    static class Edge{
        int source;
        int destination;
        int weight;
        Edge(int source, int destination, int weight){
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
}