package com.chandu.dsa.greedy;

import java.util.Arrays;

public class MinimizeMaxDifferenceBetweenHeights {
    private static int max;
    private static int min;

    public static void main(String[] args) {
        int[] arr1 = {1, 15, 10};
        int k1 = 6;
        System.out.println("Minimum difference of heights is : " + getMinimumDifferenceOfHeightsUsingSorting(arr1, k1));
        System.out.println("Minimum difference of heights is : " + getMinimumDifferenceOfHeightsWithoutSorting(arr1, k1));

        int[] arr2 = {1, 10, 14, 14, 14, 15};
        int k2 = 6;
        System.out.println("Minimum difference of heights is : " + getMinimumDifferenceOfHeightsUsingSorting(arr2, k2));
        System.out.println("Minimum difference of heights is : " + getMinimumDifferenceOfHeightsWithoutSorting(arr2, k2));

        int[] arr3 = {4, 6};
        int k3 = 10;
        System.out.println("Minimum difference of heights is : " + getMinimumDifferenceOfHeightsUsingSorting(arr3, k3));
        System.out.println("Minimum difference of heights is : " + getMinimumDifferenceOfHeightsWithoutSorting(arr3, k3));
    }

    //Time Complexity : O(n log n)
    //Space Complexity : O(1)
    private static int getMinimumDifferenceOfHeightsUsingSorting(int[] arr, int k){
        if(arr==null || arr.length<2)
            return 0;
        Arrays.sort(arr);
        int ans = arr[arr.length-1] - arr[0];

        int small, big, subtract, add;
        small = arr[0] + k;
        big = arr[arr.length-1] - k;
        if(small > big)
            small = small + big - (big=small);

        for (int i=1; i<arr.length-1; i++){
            subtract = arr[i] - k;
            add = arr[i] + k;
            if(subtract>=small || add<=big)
                continue;

            if (big-subtract <= add-small)
                small = subtract;
            else
                big = add;
        }
        return Math.min(ans, big-small);
    }

    //Time Complexity : O(n)
    //Space Complexity : O(1)
    private static int getMinimumDifferenceOfHeightsWithoutSorting(int[] arr, int k){
        if (arr==null || arr.length<2)
            return  0;
        findMinMax(arr);
        int diff = max - min;
        if (k >= diff)
            return diff;
        int avg = (max + min)/2;

        for (int i=0; i<arr.length; i++){
            if (arr[i] <= avg)
                arr[i] += k;
            else
                arr[i] -= k;
        }

        findMinMax(arr);
        return max - min;
    }

    private static void findMinMax(int[] arr){
        min = arr[0];
        max = arr[0];

        for (int i=1; i<arr.length; i++){
            if (min > arr[i])
                min = arr[i];
            if (max < arr[i])
                max = arr[i];
        }
    }
}
