package com.chandu.dsa.graph;

import java.util.*;

public class SnakeAndLadder {
    public static void main(String[] args) {
        int N = 30;
        int[] moves = new int[30];
        Arrays.fill(moves, -1);

        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        findMinDiceThrows(moves, 30);
    }

    //Time Complexity: O(n) , n = number of cells
    //Space Complexity: O(n)
    private static void findMinDiceThrows(int[] moves, int n){
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        QueueEntry qe = new QueueEntry();
        qe.v = 0;
        qe.dist = 0;
        visited[0] = true;
        Queue<QueueEntry> queue = new LinkedList<>();
        queue.add(qe);

        int v;
        while (!queue.isEmpty()){
            qe = queue.poll();
            v = qe.v;
            if(v == n-1)
                break;
            for (int i=v+1; i<=v+6 && i<n; i++){
                if(!visited[i]){
                    visited[i] = true;
                    QueueEntry obj = new QueueEntry();
                    obj.dist = qe.dist + 1;
                    parent[i] = v;
                    if(moves[i] != -1)
                        obj.v = moves[i];
                    else
                        obj.v = i;
                    queue.add(obj);
                }
            }
        }
        System.out.println("Minimum dice throws required to reach last cell is : " + qe.dist);
        printMoves(parent, n);
    }

    private static void printMoves(int[] parent, int n){
        int move = n-1;
        List<Integer> list = new ArrayList<>();
        while (parent[move] != -1){
            list.add(0, parent[move] + 1);
            move = parent[move];
        }
        list.add(0, 0);
        System.out.println(list);
    }

    static class QueueEntry{
        int v;
        int dist;
    }
}
