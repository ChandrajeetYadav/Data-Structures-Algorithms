package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSUsingAdjacencyMatrix {
    private int vertexCount;
    private List<GraphNode> vertices;
    private int[][] edges;

    BFSUsingAdjacencyMatrix(int vertexCount){
        this.vertexCount = vertexCount;
        vertices = new ArrayList<>(vertexCount);
        edges = new int[vertexCount][vertexCount];
    }

    private void addNode(int data){
        vertices.add(new GraphNode(data));
    }

    private void addEdge(int source, int destination){
        edges[source][destination] = 1;
        edges[destination][source] = 1;
    }

    public static void main(String[] args) {
        BFSUsingAdjacencyMatrix obj = new BFSUsingAdjacencyMatrix(5);
        for(int i=0; i<5; i++)
            obj.addNode(i);
        obj.addEdge(0, 1);
        obj.addEdge(0, 2);
        obj.addEdge(1, 3);

        System.out.println("BFS traversal for graph is: ");
        obj.bfs(0);
    }

    //Time Complexity: O(V^2)
    //Space Complexity: O(V)
    public void bfs(int s){
        Queue<Integer> queue = new LinkedList<>();
        vertices.get(s).visited = true;
        queue.add(s);
        int i;
        while (!queue.isEmpty()){
            i = queue.poll();
            print(i);
            for(int j=0; j<vertexCount; j++){
                if(edges[i][j]==1 && !vertices.get(j).visited){
                    vertices.get(j).visited = true;
                    queue.add(j);
                }
            }
        }
    }

    public void print(int index){
        System.out.print(vertices.get(index).data + " ");
    }
}
