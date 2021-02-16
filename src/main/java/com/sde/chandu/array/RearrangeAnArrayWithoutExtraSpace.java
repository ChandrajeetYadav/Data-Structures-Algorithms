package com.sde.chandu.array;

/*Given an array arr[] of size N where every element is in the range from 0 to n-1.
        Rearrange the given array so that arr[i] becomes arr[arr[i]].*/

public class RearrangeAnArrayWithoutExtraSpace {
    public static void main(String[] args) {
        int[] arr = {3, 2, 0, 1}; // O/p: {1, 0, 3, 2}
        System.out.println("Original array");
        printArray(arr);
        arrange1(arr);
        System.out.println("Array after arranging");
        printArray(arr);
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static void arrange1(int[] arr){
        if(arr==null || arr.length==0)
            return;
        for(int i=0; i<arr.length; i++)
            arr[i] += (arr[arr[i]] % arr.length) * arr.length;
        for(int i=0; i<arr.length; i++)
            arr[i] /= arr.length;
    }

    private static void printArray(int[] arr){
        if(arr==null || arr.length==0)
            return;
        for(int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
