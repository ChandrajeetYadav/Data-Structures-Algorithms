package com.chandu.dsa.graph;

import java.util.ArrayList;

public class FloydWarshallAlgorithm {
    private static final int INF = 9999;
    private int vertexCount;
    private int[][] path;

    FloydWarshallAlgorithm(int vertexCount){
        this.vertexCount = vertexCount;
        path = new int[vertexCount][vertexCount];
    }

    public static void main(String[] args) {
        FloydWarshallAlgorithm obj = new FloydWarshallAlgorithm(4);
        int[][] adj = {
                    {0,   5,  INF, 10},
                    {INF, 0,   3, INF},
                    {INF, INF, 0,   1},
                    {INF, INF, INF, 0}
                };
        obj.allPairShortestPath(adj);
        System.out.println();

        int[][] adj2 = { {0   , 1   , INF , INF},
            {INF , 0   , -1  , INF},
            {INF , INF , 0   ,  -1},
            {-1  , INF , INF ,   0}};
        obj.allPairShortestPath(adj2);

        int [][] graph = { { 0, 3, INF, 7 },
                { 8, 0, 2, INF },
                { 5, INF, 0, 1 },
                { 2, INF, INF, 0 } };
        obj.allPairShortestPath(graph);
        System.out.print("Shortest path from 1 to 3: ");
        obj.printPath(obj.path, 1, 3);
        System.out.print("Shortest path from 0 to 2: ");
        obj.printPath(obj.path, 0, 2);
        System.out.print("Shortest path from 3 to 2: ");
        obj.printPath(obj.path, 3, 2);
    }

    //Time Complexity : O(V^3)
    //Space Complexity : O(V^2)
    public void allPairShortestPath(int[][] adj){
        int[][] dist = new int[vertexCount][vertexCount];
        for(int i=0; i<vertexCount; i++){
            for (int j=0; j<vertexCount; j++){
                dist[i][j] = adj[i][j];
                if(adj[i][j] == INF)
                    path[i][j] = -1;
                else
                    path[i][j] = j;
            }
        }

        for (int k=0; k<vertexCount; k++){
            for (int i=0; i<vertexCount; i++){
                for (int j=0; j<vertexCount; j++){
                    if(dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[i][k];
                    }
                }
            }
        }
        isNegativeCyclePresent(dist);
        printMatrix(dist);
    }

    private void isNegativeCyclePresent(int[][] arr){
        for (int i=0; i<vertexCount; i++){
            if (arr[i][i] < 0){
                System.out.println("Graph contains negative cycle.");
                return;
            }
        }
        System.out.println("Graph doesn't contain negative cycle.");
    }

    private void printMatrix(int[][] arr){
        for (int i=0; i<vertexCount; i++){
            for (int j=0; j<vertexCount; j++){
                if (arr[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void printPath(int[][] arr, int u, int v){
        if(arr[u][v] == -1){
            System.out.println("No path exists between " + u + " and " + v);
            return;
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(u);
        while (u != v){
            u = arr[u][v];
            list.add(u);
        }
        System.out.println(list);
    }
}
