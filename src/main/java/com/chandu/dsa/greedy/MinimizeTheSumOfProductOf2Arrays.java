package com.chandu.dsa.greedy;

import java.util.Arrays;

public class MinimizeTheSumOfProductOf2Arrays {

    public static void main(String[] args) {
        int[] arr1 = { 3, 1, 1 };
        int[] arr2 = { 6, 5, 4 };

        System.out.println("Minimum Sum of Product is: " + getMinimumSumOfProduct(arr1, arr2));
    }

    //Time Complexity: O(n log n)
    //Space Complexity: O(1)
    private static long getMinimumSumOfProduct(int[] arr1, int[] arr2){
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        long sum = 0;
        for (int i=0; i< arr1.length; i++)
            sum += arr1[i] * arr2[arr2.length-i-1];

        return sum;
    }
}
