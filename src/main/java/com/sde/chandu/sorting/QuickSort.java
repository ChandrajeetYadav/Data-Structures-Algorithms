package com.sde.chandu.sorting;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        System.out.println("Original array: ");
        printArray(arr);

        sort(arr);
        System.out.println("Sorted Array: ");
        printArray(arr);
    }

    private static void sort(int[] arr){
        if(arr==null || arr.length<2)
            return;
        quickSort(arr, 0, arr.length-1);
    }

    //Time complexity: O(n log n)
    //Space complexity: O(1)
    private static void quickSort(int[] arr, int start, int end){
        if (start < end){
            int pi = partition(arr, start, end);
            quickSort(arr, start, pi-1);
            quickSort(arr, pi+1, end);
        }
    }

    private static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low-1;
        for (int j=low; j<high; j++){
            if (arr[j] < pivot){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, high);
        return i+1;
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArray(int[] arr){
        if(arr==null || arr.length==0)
            return;
        for (int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
