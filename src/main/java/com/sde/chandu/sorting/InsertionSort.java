package com.sde.chandu.sorting;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original Array");
        printArray(arr);
        insertionSort(arr);
        System.out.println("Sorted array");
        printArray(arr);
    }

    //Time Complexity: O(n^2)
    //Best Case Time Complexity: O(n)
    //Space Complexity: O(1)
    private static void insertionSort(int[] arr){
        if(arr == null)
            return;
        for (int i=1, j, key; i<arr.length; i++){
            key = arr[i];
            j = i-1;
            while (j>=0 && arr[j] > key){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    private static void printArray(int[] arr){
        if (arr==null)
            return;
        for (int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
