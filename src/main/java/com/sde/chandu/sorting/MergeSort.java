package com.sde.chandu.sorting;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original array:");
        printArray(arr);

        sort(arr);
        System.out.println("Array after sorting:");
        printArray(arr);
    }

    private static void sort(int[] arr){
        if (arr==null || arr.length<2)
            return;
        mergeSort(arr, 0, arr.length-1);
    }

    //Time complexity: O(n log n)
    //Space complexity: O(n)
    private static void mergeSort(int[] arr, int start, int end){
        if(start < end){
            int mid = (start + end)/2;
            mergeSort(arr, start, mid);
            mergeSort(arr,mid+1, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end){
        int[] temp = new int[end - start + 1];
        int i = start, j=mid+1, k=0;
        while (i<=mid && j<=end){
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        while (i <= mid)
            temp[k++] = arr[i++];
        while (j <= end)
            temp[k++] = arr[j++];
        for (i=start; i<=end; i++)
            arr[i] = temp[i-start];
    }

    private static void printArray(int[] arr){
        if(arr==null || arr.length==0)
            return;
        for (int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
