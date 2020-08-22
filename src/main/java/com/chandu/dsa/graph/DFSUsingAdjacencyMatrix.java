package com.chandu.dsa.graph;

import java.util.LinkedList;
import java.util.Stack;

public class DFSUsingAdjacencyMatrix {
    private int vertexCount;
    private GraphNode[] vertices;
    private int[][] edges;
    private int maxSize;

    DFSUsingAdjacencyMatrix(int maxSize){
        this.maxSize = maxSize;
        vertices = new GraphNode[maxSize];
        edges = new int[maxSize][maxSize];
    }

    public static void main(String[] args) {
        DFSUsingAdjacencyMatrix obj = new DFSUsingAdjacencyMatrix(5);
        for (int i=0; i< obj.maxSize; i++)
            obj.addNode(i);
        obj.addEdge(0, 1);
        obj.addEdge(0, 2);
        obj.addEdge(0, 3);
        obj.addEdge(0, 4);

        System.out.println("DFS traversal using adjacency matrix is: ");
        //obj.dfs(0);
        obj.dfsUsingRecursion(0);
    }

    private void addNode(int data){
        vertices[vertexCount++] = new GraphNode(data);
    }

    private void addEdge(int source, int destination){
        edges[source][destination] = 1;
    }

    private void print(int index){
        System.out.print(vertices[index].data + " ");
    }

    //Time Complexity: O(V^2)
    //Space Complexity: O(V)
    public void dfs(int s){
        Stack<Integer> stack = new Stack<>();
        vertices[s].visited = true;
        print(s);
        stack.push(s);
        int i;
        while (!stack.isEmpty()){
            i = getAdjacentUnvisitedNode(stack.peek());
            if(i == -1)
                stack.pop();
            else {
                vertices[i].visited = true;
                print(i);
                stack.push(i);
            }
        }
    }

    //Time Complexity: O(V^2)
    //Space Complexity: O(V)
    public void dfsUsingRecursion(int s){
        vertices[s].visited = true;
        print(s);
        for(int i=0; i<vertexCount; i++){
            if(edges[s][i]==1 && !vertices[i].visited)
                dfsUsingRecursion(i);
        }
    }

    private int getAdjacentUnvisitedNode(int i){
        for(int j=0; j<vertexCount; j++){
            if(edges[i][j]==1 && !vertices[j].visited)
                return j;
        }
        return -1;
    }
}
