package com.sde.chandu.array;

import java.util.Arrays;

public class WaveArray {
    public static void main(String[] args) {
        int[] arr = {3, 6, 5, 10, 7, 20};
        System.out.println("arr, Original array:");
        printArray(arr);
        System.out.println("arr, Array after sorting in wave using sorting:");
        sortInWaveUsingSorting(arr);
        printArray(arr);

        int[] arr1 = {10, 90, 49, 2, 1, 5, 23};
        System.out.println("arr1, Original array:");
        printArray(arr1);
        System.out.println("arr1, Array after sorting in wave efficient:");
        sortInWaveEfficient(arr1);
        printArray(arr1);
    }

    //Time Complexity : O(n log n)
    //Space Complexity : O(1)
    private static void sortInWaveUsingSorting(int[] arr){
        if(arr==null || arr.length<=1)
            return;
        Arrays.sort(arr);
        for(int i=0; i<arr.length-1; i+=2)
            arr[i] = arr[i] + arr[i+1] - (arr[i+1]=arr[i]);
    }

    //Time Complexity : O(n)
    //Space Complexity : O(1)
    private static void sortInWaveEfficient(int[] arr){
        if(arr==null || arr.length<=1)
            return;
        for(int i=0; i<arr.length; i+=2){
            if(i>0 && arr[i] < arr[i-1])
                swap(arr, i, i-1);
            if(i<arr.length-1 && arr[i]<arr[i+1])
                swap(arr, i, i+1);
        }
    }

    private static void printArray(int[] arr){
        if(arr==null || arr.length==0)
            return;
        for(int i : arr)
            System.out.print(i+"\t");
        System.out.println();
    }
    
    private static void swap (int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
