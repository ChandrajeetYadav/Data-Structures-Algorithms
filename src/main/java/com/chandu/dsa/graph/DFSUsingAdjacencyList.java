package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DFSUsingAdjacencyList {
    private int vertexCount;
    private int maxSize;
    private List<GraphNode> vertices;
    private LinkedList<Integer>[] edges;

    DFSUsingAdjacencyList(int maxSize){
        this.maxSize = maxSize;
        vertexCount = 0;
        vertices = new ArrayList<>();
        edges = new LinkedList[maxSize];
        for(int i=0; i<maxSize; i++)
            edges[i] = new LinkedList<>();
    }

    public static void main(String[] args) {
        DFSUsingAdjacencyList obj = new DFSUsingAdjacencyList(4);
        for(int i=0; i<4; i++)
            obj.addNode(i);
        obj.addEdge(0, 1);
        obj.addEdge(0, 2);
        obj.addEdge(1, 2);
        obj.addEdge(2, 0);
        obj.addEdge(2, 3);
        obj.addEdge(3, 3);

        System.out.println("Depth first traversal of graph is: ");
        //obj.dfs(2);
        obj.dfsUsingRecursion(2);
    }

    private void addNode(int data){
        vertices.add(new GraphNode(data));
        vertexCount++;
    }

    private void addEdge(int source, int destination){
        edges[source].add(destination);
    }

    private void print(int index){
        System.out.print(vertices.get(index).data + " ");
    }

    //Time Complexity: O(V+E)
    //Space Complexity: O(V)
    public void dfs(int s){
        Stack<Integer> stack = new Stack<>();
        vertices.get(s).visited = true;
        print(s);
        stack.push(s);
        int curr, next;
        while (!stack.isEmpty()){
            curr = stack.peek();
            next = getAdjacentUnvisitedNode(curr);
            if(next == -1)
                stack.pop();
            else {
                vertices.get(next).visited = true;
                print(next);
                stack.push(next);
            }
        }
    }

    //Time Complexity: O(V+E)
    //Space Complexity: O(V)
    private void dfsUsingRecursion(int s){
        vertices.get(s).visited = true;
        print(s);
        int j;
        for(int i : edges[s]){
            if(!vertices.get(i).visited)
                dfsUsingRecursion(i);
        }
    }

    private int getAdjacentUnvisitedNode(int i){
        for(int j : edges[i]){
            if(!vertices.get(j).visited)
                return j;
        }
        return -1;
    }
}
