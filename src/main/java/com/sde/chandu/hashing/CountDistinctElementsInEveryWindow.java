package com.sde.chandu.hashing;

import java.util.HashMap;
import java.util.Map;

public class CountDistinctElementsInEveryWindow {
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 3, 4, 2, 3};
        int k = 4;

        System.out.println("Distinct elements in window of size " + k + ", brute :");
        countDistinctBrute(arr, k);
        System.out.println();

        System.out.println("Distinct elements in window of size " + k + ", hashing :");
        countDistinctHashing(arr, k);
    }

    //Time complexity: O(n * k^2)
    //Space complexity: O(1)
    private static void countDistinctBrute(int[] arr, int k){
        if (arr==null || arr.length<k)
            return;
        for (int i=0; i<=arr.length-k; i++)
            System.out.print(countDistinctInWindow(arr, i, k) + "\t");
    }

    private static int countDistinctInWindow(int[] arr, int start, int k){
        int count = 0;
        for (int i=start, j; i<start+k; i++){
            for (j=start; j<i; j++){
                if (arr[i] == arr[j])
                    break;
            }
            if (i == j)
                count++;
        }
        return count;
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static void countDistinctHashing(int[] arr, int k){
        if (arr==null || arr.length<k)
            return;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<k; i++)
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        System.out.print(map.size() + "\t");

        for (int i=k; i<arr.length; i++){
            if (map.get(arr[i-k]) == 1)
                map.remove(arr[i-k]);
            else
                map.put(arr[i-k], map.get(arr[i-k])-1);
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
            System.out.print(map.size() + "\t");
        }
    }
}
