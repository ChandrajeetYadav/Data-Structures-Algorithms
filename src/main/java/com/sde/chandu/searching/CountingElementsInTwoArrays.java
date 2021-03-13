package com.sde.chandu.searching;

import java.util.Arrays;

public class CountingElementsInTwoArrays {
    public static void main(String[] args) {
        int[] arr1 = { 1, 2, 3, 4, 7, 9 };  // O/p: 4, 5, 5, 6, 6, 6
        int[] arr2 = { 0, 1, 2, 1, 1, 4 };

        System.out.println("Elements count, brute: " );
        countElementsLessThanOrEqualBrute(arr1, arr2);

        System.out.println("Elements count, binary search: " );
        countElementsLessThanOrEqualBinarySearch(arr1, arr2);
    }

    //Time complexity: O(m * n)
    //Space complexity: O(1)
    private static void countElementsLessThanOrEqualBrute(int[] arr1, int[] arr2){
        if (arr1==null || arr2==null)
            return;
        int count;
        for (int i : arr1){
            count = 0;
            for (int j : arr2){
                if (i >= j)
                    count++;
            }
            System.out.print(count + "\t");
        }
        System.out.println();
    }

    //Time complexity: O(m log n + n log n) => O((m+n) log n)
    //Space complexity: O(1)
    private static void countElementsLessThanOrEqualBinarySearch(int[] arr1, int[] arr2){
        if (arr1==null || arr2==null)
            return;
        Arrays.sort(arr2);
        int index;
        for (int i : arr1){
            index = binarySearch(arr2, i);
            System.out.print((index+1) + "\t");
        }
        System.out.println();
    }

    private static int binarySearch(int[] arr, int key){
        int low=0, high=arr.length-1, mid;
        while (low <= high){
            mid = (low + high)/2;
            if (arr[mid] <= key)
                low = mid+1;
            else
                high = mid-1;
        }
        return high;
    }
}
