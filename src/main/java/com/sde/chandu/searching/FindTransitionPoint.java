package com.sde.chandu.searching;

public class FindTransitionPoint {
    public static void main(String[] args) {
        int[] arr1 = {0,0,0,1,1}; // O/p: 3
        int[] arr2 = {0,0,0,0}; // O/p: -1
        int[] arr3 = {1,1,1,1}; // O/p: 0

        System.out.println("arr1, transition point brute: " + findTransitionPointBrute(arr1));
        System.out.println("arr2, transition point brute: " + findTransitionPointBrute(arr2));
        System.out.println("arr3, transition point brute: " + findTransitionPointBrute(arr3));
        System.out.println();

        System.out.println("arr1, transition point binary search: " + findTransitionPointBinarySearch(arr1));
        System.out.println("arr2, transition point binary search: " + findTransitionPointBinarySearch(arr2));
        System.out.println("arr3, transition point binary search: " + findTransitionPointBinarySearch(arr3));
    }

    //Time complexity : O(n)
    //Space complexity : O(1)
    private static int findTransitionPointBrute(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        else if(arr[0] == 1)
            return 0;
        else if(arr[arr.length-1] == 0)
            return -1;
        for (int i=0; i<arr.length; i++){
            if (arr[i] == 1)
                return i;
        }
        return -1;
    }

    //Time complexity : O(log n)
    //Space complexity : O(1)
    private static int findTransitionPointBinarySearch(int[] arr){
        if(arr==null || arr.length==0)
            return -1;
        else if(arr[0] == 1)
            return 0;
        else if(arr[arr.length-1] == 0)
            return -1;
        int low=0, high=arr.length-1, mid;
        while (low <= high){
            mid = (low+high)/2;
            if(arr[mid] == 0)
                low = mid + 1;
            else {
                if(mid>0 && arr[mid-1]==0)
                    return mid;
                high = mid - 1;
            }
        }
        return -1;
    }
}
