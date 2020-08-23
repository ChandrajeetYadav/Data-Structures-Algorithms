package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstrasAlgorithm {
    private int vertexCount;
    private ArrayList<ArrayList<Node>> edges;

    DijkstrasAlgorithm(int vertexCount){
        this.vertexCount = vertexCount;
        edges = new ArrayList<>();
        for (int i=0; i<vertexCount; i++)
            edges.add(new ArrayList<>());
    }

    public static void main(String[] args) {
        DijkstrasAlgorithm obj = new DijkstrasAlgorithm(5);
        obj.addEdge(0,new Node(1, 9));
        obj.addEdge(0,new Node(2, 6));
        obj.addEdge(0,new Node(3, 5));
        obj.addEdge(0,new Node(4, 3));

        obj.addEdge(2,new Node(1, 2));
        obj.addEdge(2,new Node(3, 4));

        obj.dijkstra(0);
    }

    private void addEdge(int source, Node node){
        edges.get(source).add(node);
    }

    //Time complexity : O(V log V + E log V) = O((V + E) log V)
    //Space Complexity : O(V)
    public void dijkstra(int source){
        int[] distance = new int[vertexCount];
        int[] path = new int[vertexCount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        distance[source] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b)-> a.cost - b.cost);
        pq.add(new Node(source, 0));
        int dist;
        int v;
        while (!pq.isEmpty()){
            v = pq.poll().vertex;
            for (Node node : edges.get(v)){
                dist = distance[v] + node.cost;
                if(distance[node.vertex] > dist){
                    distance[node.vertex] = dist;
                    node.cost = dist;
                    pq.add(node);
                    path[node.vertex] = v;
                }
            }
        }

        for(int i=0; i<vertexCount; i++){
            System.out.print("Source= "+source+", Destination= "+i+" : length="+distance[i]+", path=");
            printPath(i, path);
        }
    }

    private void printPath(int destination, int[] path){
        int curr = destination;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(destination);
        while (path[curr] != -1){
            list.add(0, path[curr]);
            curr = path[curr];
        }
        System.out.println(list);
    }

    static class Node{
        int vertex;
        int cost;
        Node(int vertex, int cost){
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}