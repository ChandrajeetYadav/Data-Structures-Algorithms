package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsMSTAlgorithm {
    private int vertexCount;
    private List<List<PathNode>> adj;

    PrimsMSTAlgorithm(int vertexCount){
        this.vertexCount = vertexCount;
        adj = new ArrayList<>(vertexCount);
        for (int i=0; i<vertexCount; i++)
            adj.add(new ArrayList<>());
    }
    public static void main(String[] args) {
        PrimsMSTAlgorithm obj = new PrimsMSTAlgorithm(9);
        obj.addEdge(0, 1, 4);
        obj.addEdge(0, 7, 8);
        obj.addEdge(1, 2, 8);
        obj.addEdge(1, 7, 11);
        obj.addEdge(2, 3, 7);
        obj.addEdge(2, 8, 2);
        obj.addEdge(2, 5, 4);
        obj.addEdge(3, 4, 9);
        obj.addEdge(3, 5, 14);
        obj.addEdge(4, 5, 10);
        obj.addEdge(5, 6, 2);
        obj.addEdge(6, 7, 1);
        obj.addEdge(6, 8, 6);
        obj.addEdge(7, 8, 7);

        obj.primsMst();
    }

    private void addEdge(int source, int destination, int weight){
        adj.get(source).add(new PathNode(destination, weight));
        adj.get(destination).add(new PathNode(source, weight));
    }

    //Time Complexity: O((V + E) * log V)
    //Space Complexity: O(V)
    private void primsMst(){
        boolean[] mstSet = new boolean[vertexCount];
        int[] parent = new int[vertexCount];
        Arrays.fill(parent, -1);

        KeyNode[] keyArr = new KeyNode[vertexCount];
        for (int i=0; i<vertexCount; i++)
            keyArr[i] = new KeyNode(i, Integer.MAX_VALUE);
        keyArr[0].key = 0;
        PriorityQueue<KeyNode> pq = new PriorityQueue<>((KeyNode a, KeyNode b) -> a.key - b.key);
        for (KeyNode k : keyArr)
            pq.add(k);
        int minWeight = 0;
        KeyNode node;
        while (!pq.isEmpty()){
            node = pq.poll();
            mstSet[node.vertex] = true;
            minWeight += node.key;
            for(PathNode pathNode : adj.get(node.vertex)){
                if(!mstSet[pathNode.destination]){
                    if(keyArr[pathNode.destination].key > pathNode.weight){
                        pq.remove(keyArr[pathNode.destination]);
                        keyArr[pathNode.destination].key = pathNode.weight;
                        pq.add(keyArr[pathNode.destination]);
                        parent[pathNode.destination] = node.vertex;
                    }
                }
            }
        }
        System.out.println("Sum of weights of edges in the minimum spanning tree: " + minWeight);
        System.out.println("Minimum spanning tree is : ");
        for (int i=1; i<vertexCount; i++)
            System.out.println(parent[i] + " - " + i);
    }

    static class PathNode{
        int destination;
        int weight;
        PathNode(int destination, int weight){
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class KeyNode{
        int vertex;
        int key;

        KeyNode(int vertex, int key){
            this.vertex = vertex;
            this.key = key;
        }
    }
}
