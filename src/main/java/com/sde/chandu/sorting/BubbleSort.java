package com.sde.chandu.sorting;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array:");
        printArray(arr);
        bubbleSort(arr);
        System.out.println("Sorted array:");
        printArray(arr);

    }

    //Time Complexity: O(n^2)
    //Best Case Time Complexity: O(n)
    //Space Complexity: O(1)
    private static void bubbleSort(int[] arr){
        if (arr == null)
            return;
        boolean swapped;
        for (int i=0; i<arr.length-1; i++){
            swapped = false;
            for (int j=0; j<(arr.length-i-1); j++){
                if (arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArray(int[] arr){
        if (arr==null)
            return;
        for (int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
