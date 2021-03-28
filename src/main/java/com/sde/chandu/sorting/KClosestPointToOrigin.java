package com.sde.chandu.sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointToOrigin {
    public static void main(String[] args) {
        int[][] points = { { 3, 3 },
                { 5, -1 },
                { -2, 4 } };
        int k = 2;
        System.out.println(k + " closest point, brute: ");
        findKclosetPointSorting(points, k);
        System.out.println(k + " closest point, priority queue: ");
        findKclosetPointUsingPQ(points, k);
    }

    //Time complexity: O(n log n)
    //Space complexity: O(n)
    private static void findKclosetPointSorting(int[][] points, int k){
        int[] distance = new int[points.length];
        int x,y;
        for (int i=0; i< points.length; i++){
            x = points[i][0];
            y = points[i][1];
            distance[i] = (x*x) + (y*y);
        }
        Arrays.sort(distance);
        int kDist = distance[k-1];
        int tempDist;
        for (int i=0; i< points.length; i++){
            x = points[i][0];
            y = points[i][1];
            tempDist = (x*x) + (y*y);
            if (tempDist <= kDist)
                System.out.print(x+","+y+"\t");
        }
        System.out.println();
    }

    //Time complexity: O(n log n + k log n) => O((n+k) log n)
    //Space complexity: O(n)
    private static void findKclosetPointUsingPQ(int[][] points, int k){
        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a,b)->(a.x*a.x + a.y*a.y) - (b.x*b.x + b.y*b.y));
        for (int i=0; i< points.length; i++)
            pq.add(new Pair(points[i][0], points[i][1]));
        Pair pair;
        for (int i=0; i<k; i++){
            pair = pq.poll();
            System.out.print(pair.x + "," + pair.y + "\t");
        }
        System.out.println();
    }

    private static class Pair{
        int x;
        int y;
        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
