package com.sde.chandu.sorting;

import java.util.Arrays;

public class SortArrayOf012 {
    public static void main(String[] args) {
        int[] arr1 = {0, 1, 2, 0, 1, 2};
        System.out.println("arr1, Original array: ");
        printArray(arr1);
        sortUsingSorting(arr1);
        System.out.println("Sorted arr1, using sorting");
        printArray(arr1);

        int[] arr2 = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        System.out.println("arr2, Original array: ");
        printArray(arr2);
        sortUsingCounting(arr2);
        System.out.println("Sorted arr2, using counting");
        printArray(arr2);

        int[] arr3 = {0, 2, 1, 2, 0};
        System.out.println("arr3, Original array: ");
        printArray(arr3);
        sortEfficient(arr3);
        System.out.println("Sorted arr3, efficient");
        printArray(arr3);
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static void sortUsingCounting(int[] arr){
        if(arr==null || arr.length==0)
            return;
        int zero=0, one=0, two=0;
        for (int i : arr){
            switch (i){
                case 0 :
                    zero++;
                    break;
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
            }
        }
        int i=0;
        while (zero > 0){
            arr[i++] = 0;
            zero--;
        }
        while (one > 0){
            arr[i++] = 1;
            one--;
        }
        while (two > 0){
            arr[i++] = 2;
            two--;
        }
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static void sortEfficient(int[] arr){
        if(arr==null || arr.length==0)
            return;
        int low=0, mid=0, high=arr.length-1;
        while (mid <= high){
            switch (arr[mid]){
                case 0 :
                    swap(arr, mid, low);
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(arr, mid, high);
                    high--;
                    break;
            }
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //Time complexity: O(n log n)
    //Space complexity: O(1)
    private static void sortUsingSorting(int[] arr){
        if(arr==null || arr.length==0)
            return;
        Arrays.sort(arr);
    }

    private static void printArray(int[] arr){
        if(arr==null || arr.length==0)
            return;
        for (int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
