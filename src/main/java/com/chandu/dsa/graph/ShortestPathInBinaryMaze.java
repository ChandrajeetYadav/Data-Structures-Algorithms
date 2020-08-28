package com.chandu.dsa.graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMaze {
    static int row;
    static int col;
    public static void main(String[] args) {
        int[][] arr = {
                        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                        { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                        { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                        { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                        { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                        { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                        { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }
                    };
        row = arr.length;
        col = arr[0].length;
        Point source = new Point(0, 0);
        Point destination = new Point(3, 4);

        System.out.println("Minimum no. of steps needed to reach destination is: " +
                findShortestPathUsingDfs(arr, source, destination));
    }

    //Time complexity : O(row * col)
    //Space complexity : O(min(row , col))
    private static int findShortestPathUsingDfs(int[][] arr, Point source, Point destination){
        if (!isValidCell(source.x, source.y, arr) || !isValidCell(destination.x, destination.y, arr))
            return -1;
        int[] rowIndex = {-1, 0, 0, 1};
        int[] colIndex = {0, -1, 1, 0};

        Queue<QueueNode> queue = new LinkedList<>();
        QueueNode node = new QueueNode(source, 0);
        queue.add(node);
        arr[source.x][source.y] += 1;
        int r, c;
        while (!queue.isEmpty()){
            node = queue.poll();
            if(node.p.x==destination.x && node.p.y== destination.y)
                return node.dist;
            for (int i=0; i<4; i++){
                r = node.p.x + rowIndex[i];
                c = node.p.y + colIndex[i];
                if (isValidCell(r, c, arr)){
                    arr[r][c] += 1;
                    queue.add(new QueueNode(new Point(r, c), node.dist+1));
                }
            }
        }
        return -1;
    }

    private static boolean isValidCell(int r, int c, int[][] arr){
        if(r<0 || r>=row || c<0 || c>=col || arr[r][c]!=1)
            return false;
        return true;
    }

    static class QueueNode{
        Point p;
        int dist;
        QueueNode(Point p, int dist){
            this.p = p;
            this.dist = dist;
        }
    }

    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
