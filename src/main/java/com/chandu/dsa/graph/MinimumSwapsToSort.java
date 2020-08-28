package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.List;

public class MinimumSwapsToSort {
    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 3, 2};
        System.out.println("Minimum number of steps required is: " + getMinimumNumberOfSwaps(arr));
    }

    //Time Complexity : O(n log n)
    //Space Complexity : O(n)
    private static int getMinimumNumberOfSwaps(int[] arr){
        if(arr==null || arr.length==0)
            return 0;
        List<Pair> list = new ArrayList<>();
        for (int i=0; i<arr.length; i++)
            list.add(new Pair(arr[i], i));
        list.sort((Pair a, Pair b)-> a.key - b.key);

        boolean[] visited = new boolean[arr.length];
        int cycle;
        int result = 0;
        for (int i=0,j; i<arr.length; i++){
            if(visited[i] || list.get(i).value==i)
                continue;
            cycle =0;
            j = i;
            while (!visited[j]){
                visited[j] = true;
                j = list.get(j).value;
                cycle++;
            }
            if (cycle > 0)
                result += cycle-1;
        }
        return result;
    }

    static class Pair{
        int key;
        int value;

        Pair(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
}
