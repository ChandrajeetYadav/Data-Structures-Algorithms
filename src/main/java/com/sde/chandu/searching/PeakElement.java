package com.sde.chandu.searching;

public class PeakElement {
    public static void main(String[] args) {
        int[] arr = {5, 10, 20, 15}; // O/p: 2
        System.out.println("peak element, brute: " + findPeakElementIndexBrute(arr));
        System.out.println("peak element, binary search: " + findPeakElementIndexBinarySearch(arr));
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    private static int findPeakElementIndexBrute(int[] arr){
        if (arr==null || arr.length==0)
            return -1;
        if(arr.length == 1 || arr[0] >= arr[1])
            return 0;
        else if(arr[arr.length-1] >= arr[arr.length-2])
            return arr.length-1;
        for (int i=1; i<arr.length-1; i++){
            if (arr[i]>=arr[i-1] && arr[i]>=arr[i+1])
                return i;
        }
        return 0;
    }

    //Time complexity : O(log n)
    //Space complexity : O(1)
    private static int findPeakElementIndexBinarySearch(int[] arr){
        if (arr==null || arr.length==0)
            return -1;
        int low=0, high=arr.length-1, mid;
        while (low <= high){
            mid = (low + high)/2;
            if ((mid==0 || arr[mid]>=arr[mid-1])
                    && (mid==arr.length-1 || arr[mid]>=arr[mid+1]))
                return mid;
            else if(mid>0 && arr[mid-1]>arr[mid])
                high = mid-1;
            else
                low = mid+1;
        }
        return 0;
    }
}
