package com.sde.chandu.hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSum {
    public static void main(String[] args) {
        int[] arr1 = {0, -1, 2, -3, 1};
        int[] arr2 = {1, -2, 1, 0, 5};
        int sum1 = -2;
        int sum2 = 0;

        System.out.println("arr1, find pair brute:");
        findPairBrute(arr1, sum1);
        System.out.println("arr2, find pair brute:");
        findPairBrute(arr2, sum2);
        System.out.println();

        System.out.println("arr1, find pair hashing:");
        findPairHashing(arr1, sum1);
        System.out.println("arr2, find pair hashing:");
        findPairHashing(arr2, sum2);
        System.out.println();

        System.out.println("arr1, find pair sorting:");
        findPairSorting(arr1, sum1);
        System.out.println("arr2, find pair sorting:");
        findPairSorting(arr2, sum2);
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static void findPairBrute(int[] arr, int sum){
        if (arr==null || arr.length<2)
            return;
        boolean isPairFound = false;
        for (int i=0; i<arr.length-1; i++){
            for (int j=i+1; j<arr.length; j++){
                if (arr[i]+arr[j] == sum) {
                    System.out.println("Pair with sum " + sum + " is: " + arr[i] + "," + arr[j]);
                    isPairFound = true;
                }
            }
        }
        if (!isPairFound)
            System.out.println("No such pair exist.");
    }

    //Time complexity: O(n log n)
    //Space complexity: O(1)
    private static void findPairSorting(int[] arr, int sum){
        if (arr==null || arr.length<2)
            return;
        Arrays.sort(arr);
        int low=0, high=arr.length-1;
        boolean isPairFound = false;
        while (low < high){
            if (arr[low]+arr[high] == sum){
                System.out.println("Pair with sum " + sum + " is: " + arr[low] + "," + arr[high]);
                low++;
                high--;
                isPairFound = true;
            }else if (arr[low]+arr[high] < sum)
                low++;
            else
                high--;
        }
        if (!isPairFound)
            System.out.println("No such pair exist.");
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static void findPairHashing(int[] arr, int sum){
        if (arr==null || arr.length<2)
            return;
        Set<Integer> set = new HashSet<>();
        boolean isPairFound = false;
        for (int i=0, temp; i< arr.length; i++){
            temp = sum - arr[i];
            if (set.contains(temp)){
                System.out.println("Pair with sum " + sum + " is: " + arr[i] + "," + temp);
                isPairFound = true;
            }else
                set.add(arr[i]);
        }
        if (!isPairFound)
            System.out.println("No such pair exist.");
    }
}
