package com.sde.chandu.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesFromArrayOfSmallPrimes {
    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 2, 2, 5, 7, 7};
        int[] arr1 = {3, 5, 7, 3, 3, 13, 5, 13, 29, 13};

        System.out.println("arr after removing duplicates using brute: ");
        printArray(removeDuplicatesBrute(arr));
        System.out.println("arr1 after removing duplicates using brute: ");
        printArray(removeDuplicatesBrute(arr1));

        System.out.println("arr after removing duplicates using hashing: ");
        printArray(removeDuplicatesUsingHashing(arr));
        System.out.println("arr1 after removing duplicates using hashing: ");
        printArray(removeDuplicatesUsingHashing(arr1));

        System.out.println("arr after removing duplicates efficient: ");
        printArray(removeDuplicatesEfficient(arr));
        System.out.println("arr1 after removing duplicates using efficient: ");
        printArray(removeDuplicatesEfficient(arr1));

        System.out.println("arr after removing duplicates using sorting: ");
        printArray(removeDuplicatesUsingSorting(arr));
        System.out.println("arr1 after removing duplicates using sorting: ");
        printArray(removeDuplicatesUsingSorting(arr1));
    }

    //Time complexity: O(n^2)
    //Space complexity: O(n)
    private static int[] removeDuplicatesBrute(int[] arr){
        if(arr==null || arr.length<2)
            return arr;
        int index = 1;
        for(int i=1; i< arr.length; i++){
            int j;
            for(j=0; j<i; j++){
                if(arr[i] == arr[j])
                    break;
            }
            if(i == j)
                arr[index++] = arr[i];
        }
        int[] res = new int[index];
        for (int i=0; i<index; i++)
            res[i] = arr[i];
        return res;
    }
    //Time complexity: O(n log n)
    //Space complexity: O(1)
    private static int[] removeDuplicatesUsingSorting(int[] arr){
        if(arr==null || arr.length<2)
            return arr;
        Arrays.sort(arr);
        int index = 1;
        for(int i=1; i<arr.length; i++){
            if(arr[i] != arr[i-1])
                arr[index++] = arr[i];
        }
        for (int i=index; i< arr.length; i++)
            arr[i] = Integer.MAX_VALUE;
        return arr;
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static int[] removeDuplicatesUsingHashing(int[] arr){
        if(arr==null || arr.length<2)
            return arr;
        Set<Integer> set = new HashSet<Integer>();
        for (int i : arr)
            set.add(i);
        return set.stream().mapToInt(i->i).toArray();
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int[] removeDuplicatesEfficient(int[] arr){
        if(arr==null || arr.length<2)
            return arr;
        int product = arr[0];
        int index = 1;
        for(int i=1; i< arr.length; i++){
            if (product % arr[i] != 0) {
                arr[index++] = arr[i];
                product *= arr[i];
            }
        }
        return Arrays.copyOf(arr, index);
    }

    private static void printArray(int[] arr){
        if(arr == null)
            return;
        for(int i : arr){
            if (i == Integer.MAX_VALUE)
                break;
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
