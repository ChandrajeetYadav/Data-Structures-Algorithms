package com.chandu.dsa.graph;

import java.util.*;

public class MinCostPath {
    private static int size;
    public static void main(String[] args) {
        int[][] arr = {
                {31, 100, 65, 12, 18},
                {10, 13, 47, 157, 6},
                {100, 113, 174, 11, 33},
                {88, 124, 41, 20, 140},
                {99, 32, 111, 41, 20}
        };
        size = arr.length;
        System.out.println("Minimum cost path is: " + getMinimumCost(arr));
    }

    //Time complexity : O(N^2)
    //Space complexity : O(N^2)
    private static int getMinimumCost(int[][] arr){
        if (arr==null || arr.length==0)
            return 0;
        int[][] dist = new int[size][size];
        boolean[][] visited = new boolean[size][size];
        for (int i=0; i<size; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[0][0] = arr[0][0];

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        Node node = new Node(new Cell(0, 0), dist[0][0]);
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                if (a.dist == b.dist){
                    if (a.cell.x != b.cell.x)
                        return a.cell.x - b.cell.x;
                    else
                        return a.cell.y - b.cell.y;
                }
                return a.dist - b.dist;
            }
        });
        pq.add(node);
        visited[0][0] = true;
        Set<Node> set = new HashSet<>();

        while (!pq.isEmpty()){
            node = pq.poll();
            if (node.cell.x == size-1 && node.cell.y==size-1)
                break;
            visited[node.cell.x][node.cell.y] = true;
            for (int i=0; i<dx.length; i++){
                int r = node.cell.x + dx[i];
                int c = node.cell.y + dy[i];

                if (!isValidCell(r, c) || visited[r][c])
                    continue;
                if (dist[r][c] > dist[node.cell.x][node.cell.y] + arr[r][c]){
                    if(dist[r][c] != Integer.MAX_VALUE)
                        pq.remove(new Node(new Cell(r, c) , 0));
                    dist[r][c] = dist[node.cell.x][node.cell.y] + arr[r][c];
                    pq.add(new Node(new Cell(r, c), dist[r][c]));
                }
            }
        }
        return node.dist;
    }

    private static boolean isValidCell(int r, int c){
        if(r<0 || r>=size || c<0 || c>=size)
            return false;
        return true;
    }

    static class Node{
        Cell cell;
        int dist;
        Node(Cell cell, int dist){
            this.cell = cell;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object  obj){
            if(obj == this)
                return true;
            if(!(obj instanceof Node))
                return false;
            Node node = (Node) obj;
            return this.cell.x==node.cell.x && this.cell.y==node.cell.y;
        }
    }

    static class Cell {
        int x;
        int y;
        Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
