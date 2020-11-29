package com.sde.chandu.array;

import java.util.Arrays;

public class SecondLargest {
    public static void main(String[] args) {
        int[] arr1 = {12, 35, 1, 10, 34, 1};
        int[] arr2 = {10, 5, 10};
        int[] arr3 = {10, 10, 10};

        System.out.println("Second largest in arr1: " + getSecondLargestEfficient(arr1));
        System.out.println("Second largest in arr2: " + getSecondLargestEfficient(arr2));
        System.out.println("Second largest in arr3: " + getSecondLargestEfficient(arr3));

        System.out.println("\nSecond largest using brute in arr1: " + getSecondLargestBrute(arr1));
        System.out.println("Second largest using brute in arr2: " + getSecondLargestBrute(arr2));
        System.out.println("Second largest using brute in arr3: " + getSecondLargestBrute(arr3));

        System.out.println("\nSecond largest using sorting in arr1: " + getSecondLargestUsingSorting(arr1));
        System.out.println("Second largest using sorting in arr2: " + getSecondLargestUsingSorting(arr2));
        System.out.println("Second largest using sorting in arr3: " + getSecondLargestUsingSorting(arr3));
    }

    //Time complexity: O(n log n)
    //Space complexity: O(1)
    private static int getSecondLargestUsingSorting(int[] arr){
        if (arr==null || arr.length<2)
            return Integer.MIN_VALUE;
        Arrays.sort(arr);
        int largest = arr[arr.length-1];
        int secondLargest = Integer.MIN_VALUE;
        for (int i=arr.length-2; i>=0; i--){
            if (arr[i] != largest)
                return arr[i];
        }
        return secondLargest;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int getSecondLargestBrute(int[] arr){
        if(arr==null || arr.length<2)
            return Integer.MIN_VALUE;
        int largest = arr[0];
        for (int i=1; i<arr.length; i++)
            largest = Math.max(largest, arr[i]);

        int secondLargest = Integer.MIN_VALUE;
        for (int i=0; i<arr.length; i++){
            if (arr[i]>secondLargest && arr[i]<largest)
                secondLargest = arr[i];
        }
        return secondLargest;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int getSecondLargestEfficient(int[] arr){
        if(arr==null || arr.length<2)
            return Integer.MIN_VALUE;
        int largest = arr[0];
        int secondLargest = Integer.MIN_VALUE;

        for (int i=1; i<arr.length; i++){
            if (arr[i] > largest){
                //secondLargest = largest;
                largest = arr[i];
            } else if (arr[i]>secondLargest && arr[i]<largest)
                secondLargest = arr[i];
        }
        return secondLargest;
    }
}
