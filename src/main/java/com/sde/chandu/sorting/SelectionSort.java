package com.sde.chandu.sorting;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 9, 7};
        System.out.println("Original Array");
        printArray(arr);
        selectionSort(arr);
        System.out.println("Sorted array");
        printArray(arr);
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static void selectionSort(int[] arr){
        if (arr == null)
            return;
        int minIndex;
        for (int i=0; i<arr.length-1; i++){
            minIndex = i;
            for (int j=i+1; j<arr.length; j++){
                if (arr[minIndex] > arr[j])
                    minIndex = j;
            }
            if (minIndex != i)
                swap(arr, i, minIndex);
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
