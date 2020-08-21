package com.chandu.dsa.graph;

import com.chandu.dsa.linked.list.Node;

import java.util.*;

public class BFSUsingAdjacencyList {
    private int vertexCount;
    private List<GraphNode> vertices;
    private LinkedList<Integer>[] edges;

    BFSUsingAdjacencyList(int vertexCount){
        this.vertexCount = vertexCount;
        vertices = new ArrayList<>(vertexCount);
        edges = new LinkedList[vertexCount];
        for(int i=0; i<vertexCount; i++){
            vertices.add(new GraphNode(i));
            edges[i] = new LinkedList<>();
        }
    }

    public static void main(String[] args) {
        BFSUsingAdjacencyList obj = new BFSUsingAdjacencyList(4);
        obj.addEdge(0, 1);
        obj.addEdge(0, 2);
        obj.addEdge(1, 2);
        obj.addEdge(2, 0);
        obj.addEdge(2, 3);
        obj.addEdge(3, 3);

        System.out.println("BFS traversal is as below: ");
        obj.bfs(2);
    }

    void addEdge(int source, int destination){
        edges[source].add(destination);
    }

    //Time Complexity: O(V+E)
    //Space Complexity: O(V)
    void bfs(int s){
        Queue<Integer> queue = new LinkedList<>();
        vertices.get(s).visited = true;
        queue.add(s);
        int temp;
        while (!queue.isEmpty()){
            temp = queue.poll();
            print(temp);
            for(int i : edges[temp]){
                if(!vertices.get(i).visited){
                    vertices.get(i).visited = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    private void print(int index){
        System.out.print(vertices.get(index).data + " ");
    }
}
