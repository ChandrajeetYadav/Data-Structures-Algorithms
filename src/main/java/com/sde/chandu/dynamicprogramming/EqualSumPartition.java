package com.sde.chandu.dynamicprogramming;

public class EqualSumPartition {
    public static void main(String[] args) {
        int[] arr1 = {1, 5, 11, 5};
        int[] arr2 = {1, 3, 5};
        System.out.println("Equal sum partition possible for arr1: " + equalSumPartition(arr1));
        System.out.println("Equal sum partition possible for arr2: " + equalSumPartition(arr2));
    }

    // Time complexity: O(n * sum)
    // Space complexity: O(n * sum), sum = sum of array elements
    private static boolean equalSumPartition(int[] arr) {
        if (arr == null || arr.length == 0)
            return false;
        int sum = 0;
        for (int i : arr)
            sum += i;
        if (sum % 2 != 0)
            return false;
        else
            return SubsetSum.isSubsetSumIterative(arr, sum / 2);
    }
}
