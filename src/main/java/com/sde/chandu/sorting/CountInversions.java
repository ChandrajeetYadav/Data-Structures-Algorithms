package com.sde.chandu.sorting;

public class CountInversions {
    public static void main(String[] args) {
        int[] arr = {8, 4, 2, 1}; // O/p: 6
        System.out.println("Inversion count, brute: " + getInversionCountBrute(arr));
        System.out.println("Inversion count, merge sort: " + getInversionCountMergeSort(arr));
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static int getInversionCountBrute(int[] arr){
        if (arr==null || arr.length<2)
            return 0;
        int count = 0;
        for (int i=0; i<arr.length-1; i++){
            for (int j=i+1; j<arr.length; j++){
                if (arr[i] > arr[j])
                    count++;
            }
        }
        return count;
    }

    //Time complexity: O(n log n)
    //Space complexity: O(n)
    private static int getInversionCountMergeSort(int[] arr){
        if (arr==null || arr.length<2)
            return 0;
        return mergeSortAndCount(arr, 0, arr.length-1);
    }

    private static int mergeSortAndCount(int[] arr, int start, int end){
        int count = 0;
        if(start < end){
            int mid = (start+end)/2;
            count += mergeSortAndCount(arr, start, mid);
            count += mergeSortAndCount(arr, mid+1, end);
            count += mergeAndCount(arr, start, mid, end);
        }
        return count;
    }

    private static int mergeAndCount(int[] arr, int start, int mid, int end){
        int[] temp = new int[end - start + 1];
        int i = start, j=mid+1, k=0;
        int swaps = 0;
        while (i<=mid && j<=end){
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else{
                temp[k++] = arr[j++];
                swaps = swaps + (mid + 1 - i);
            }
        }
        while (i <= mid)
            temp[k++] = arr[i++];
        while (j <= end)
            temp[k++] = arr[j++];
        for (i=start; i<=end; i++)
            arr[i] = temp[i-start];
        return swaps;
    }
}
