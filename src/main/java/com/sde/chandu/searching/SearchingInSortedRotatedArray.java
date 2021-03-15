package com.sde.chandu.searching;

public class SearchingInSortedRotatedArray {
    public static void main(String[] args) {
        int[] arr1 = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int key1 = 10; // O/p: 5

        int[] arr2 = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        int key2 = 30; // O/p: -1

        System.out.println("arr1 index present at, brute: " + searchBrute(arr1, key1));
        System.out.println("arr2 index present at, brute: " + searchBrute(arr2, key2));

        System.out.println("arr1 index present at, pivoted binary search: " + searchPivotedBinarySearch(arr1, key1));
        System.out.println("arr2 index present at, pivoted binary search: " + searchPivotedBinarySearch(arr2, key2));

        System.out.println("arr1 index present at, modified binary search: " + searchBinarySearchModified(arr1, key1));
        System.out.println("arr2 index present at, modified binary search: " + searchBinarySearchModified(arr2, key2));
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int searchBrute(int[] arr, int key){
        if(arr==null || arr.length==0)
            return -1;
        for (int i=0; i<arr.length; i++){
            if (arr[i] == key)
                return i;
        }
        return -1;
    }

    //Time complexity: O(log n)
    //Space complexity: O(1)
    private static int searchBinarySearchModified(int[] arr, int key){
        if(arr==null || arr.length==0)
            return -1;
        int low=0, high=arr.length-1, mid;
        while (low <= high){
            mid = (low + high)/2;
            if(arr[mid] == key)
                return mid;
            else if(arr[low] <= arr[mid]){
                if(key<=arr[low] && key<arr[mid])
                    high = mid-1;
                else
                    low = mid+1;
            }else{
                if (key>arr[mid] && key<=arr[high])
                    low = mid+1;
                else
                    high = mid-1;
            }
        }
        return -1;
    }

    //Time complexity: O(log n)
    //Space complexity: O(1)
    private static int searchPivotedBinarySearch(int[] arr, int key){
        if(arr==null || arr.length==0)
            return -1;
        int pivot = getPivot(arr);
        if (pivot == -1)
            return binarySearch(arr, 0, arr.length-1, key);
        else if(arr[pivot] == key)
            return pivot;
        else if(arr[0] <= key)
            return binarySearch(arr, 0, pivot-1, key);
        else
            return binarySearch(arr, pivot+1, arr.length-1, key);
    }

    private static int getPivot(int[] arr){
        int low=0, high=arr.length-1, mid;
        while (low <= high){
            if (low == high)
                return low;
            mid = (low+high)/2;
            if (mid>0 && arr[mid] < arr[mid-1])
                return mid-1;
            if (mid<arr.length-1 && arr[mid]>arr[mid+1])
                return mid;
            if (arr[low] >= arr[mid])
                high = mid-1;
            else
                low = mid+1;
        }
        return -1;
    }

    private static int binarySearch(int[] arr, int low, int high, int key){
        int mid;
        while (low <= high){
            mid = (low + high)/2;
            if(arr[mid] == key)
                return mid;
            else if(arr[mid] > key)
                high = mid-1;
            else
                low = mid+1;
        }
        return -1;
    }
}
