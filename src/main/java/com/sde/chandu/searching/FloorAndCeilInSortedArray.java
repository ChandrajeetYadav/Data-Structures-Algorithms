package com.sde.chandu.searching;

public class FloorAndCeilInSortedArray {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 8, 10, 10, 12, 19};
        int key1 = 5; // O/p: 1

        int[] arr2 = {1,2,8,10,11,12,19};
        int key2 = 0; // O/p : -1

        System.out.println("arr1 floor, brute: " + findFloorBrute(arr1, key1));
        System.out.println("arr2 floor, brute: " + findFloorBrute(arr2, key2));

        System.out.println("arr1 floor, binary search: " + findFloorBinarySearch(arr1, key1));
        System.out.println("arr2 floor, binary search: " + findFloorBinarySearch(arr2, key2));

        key1 = 5; // Ceil = 2;
        key2 = 20; // Ceil = -1;
        int key3 = 0; // Ceil = 0;
        int key4 = 18; // Ceil = 6;

        System.out.println("arr1 ceil, brute: " + findCeilBrute(arr1, key1));
        System.out.println("arr1 ceil, brute: " + findCeilBrute(arr1, key2));
        System.out.println("arr1 ceil, brute: " + findCeilBrute(arr1, key3));
        System.out.println("arr1 ceil, brute: " + findCeilBrute(arr1, key4));

        System.out.println("arr1 ceil, binary search: " + findCeilBinarySearch(arr1, key1));
        System.out.println("arr1 ceil, binary search: " + findCeilBinarySearch(arr1, key2));
        System.out.println("arr1 ceil, binary search: " + findCeilBinarySearch(arr1, key3));
        System.out.println("arr1 ceil, binary search: " + findCeilBinarySearch(arr1, key4));
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    private static int findFloorBrute(int[] arr, int key) {
        if (arr == null || arr.length == 0 || key < arr[0])
            return -1;
        else if (key >= arr[arr.length - 1])
            return arr.length - 1;
        for (int i = 1; i < arr.length-1; i++) {
            if (arr[i] > key)
                return i - 1;
        }
        return -1;
    }

    //Time complexity : O(log n)
    //Space complexity : O(1)
    private static int findFloorBinarySearch(int[] arr, int key){
        if (arr==null || arr.length==0)
            return -1;
        int low=0, high=arr.length-1, mid;
        while (low <= high){
            if (key >= arr[high])
                return high;
            mid = (low + high)/2;
            if (arr[mid] == key)
                return mid;
            else if (mid>0 && arr[mid-1]<=key && key<arr[mid])
                return mid-1;
            else if(key < arr[mid])
                high = mid-1;
            else
                low = mid+1;
        }
        return -1;
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    private static int findCeilBrute(int[] arr, int key){
        if (arr == null || arr.length == 0 || key > arr[arr.length-1])
            return -1;
        else if(key <= arr[0])
            return 0;
        for (int i=1; i<arr.length; i++){
            if (arr[i] >= key)
                return i;
        }
        return -1;
    }

    //Time complexity : O(log n)
    //Space complexity : O(1)
    private static int findCeilBinarySearch(int[] arr, int key){
        if (arr == null || arr.length == 0)
            return -1;
        int low=0, high=arr.length-1, mid;
        while (low <= high){
            if (key <= arr[low])
                return low;
            mid = (low+high)/2;
            if (arr[mid]==key)
                return mid;
            else if(mid<high && arr[mid]<key && key<=arr[mid+1])
                return mid+1;
            else if(key > arr[mid])
                low = mid+1;
            else
                high = mid-1;
        }
        return -1;
    }
}
