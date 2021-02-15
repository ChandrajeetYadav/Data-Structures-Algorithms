package com.sde.chandu.array;

import java.util.*;

public class UnionIntersectionOf2UnsortedArrays {
    public static void main(String[] args) {
        int[] arr1 = {7, 1, 5, 2, 3, 3, 6};
        int[] arr2 = {3, 8, 6, 20, 7, 7};

        System.out.println("Union using Brute: ");
        printUnionBrute(arr1, arr2);
        System.out.println("Intersection using Brute: ");
        printIntersectionBrute(arr1, arr2);

        System.out.println("Union using Hashing: ");
        printUnionUsingHashing(arr1, arr2);
        System.out.println("Intersection using Hashing: ");
        printIntersectionUsingHashing(arr1, arr2);

        System.out.println("Union using Sorting and Searching: ");
        printUnionUsingSortingSearching(arr1, arr2);
        System.out.println("Intersection using Sorting and Searching: ");
        printIntersectionUsingSortingSearching(arr1, arr2);
    }

    //Time complexity: O(m+n)
    //Space complexity: O(m+n)
    private static void printUnionUsingHashing(int[] arr1, int[] arr2){
        if(arr1==null || arr2==null)
            return;
        Set<Integer> set = new HashSet<>();
        for(int i : arr1)
            set.add(i);
        for(int i : arr2)
            set.add(i);
        set.forEach(n-> System.out.print(n + "\t"));
        System.out.println();
    }

    //Time complexity: O(m+n)
    //Space complexity: O(min(m,n))
    private static void printIntersectionUsingHashing(int[] arr1, int[] arr2){
        if(arr1==null || arr2==null)
            return;
        if(arr1.length > arr2.length){
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }
        Set<Integer> set;
        set = new HashSet<>();
        for (int i : arr1)
            set.add(i);

        for(int i : arr2){
            if(set.contains(i)) {
                System.out.print(i + "\t");
                set.remove(i);
            }
        }
        System.out.println();
    }

    //Time complexity: O(m*n)
    //Space complexity: O(m+n)
    private static void printUnionBrute(int[] arr1, int[] arr2){
        List<Integer> list = new ArrayList<>();
        for (int i : arr1){
            if(!list.contains(i))
                list.add(i);
        }
        boolean flag;
        for(int i=0; i<arr2.length; i++){
            flag = false;
            for (int j=0; j<arr1.length; j++){
                if(arr2[i] == arr1[j]) {
                    flag = true;
                    break;
                }
            }
            if(!flag)
                list.add(arr2[i]);
        }
        list.forEach(n -> System.out.print(n + "\t"));
        System.out.println();
    }

    //Time complexity: O(m*n)
    //Space complexity: O(min(m, n))
    private static void printIntersectionBrute(int[] arr1, int[] arr2){
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<arr1.length; i++){
            for (int j=0; j<arr2.length; j++){
                if (arr1[i] == arr2[j]){
                    if(!list.contains(arr1[i]))
                        list.add(arr1[i]);
                }
            }
        }
        list.forEach(n -> System.out.print(n + "\t"));
        System.out.println();
    }

    //Time complexity: O(min(mlogm + nlogm, mlogn + nlogn))=> O(min((m+n)logm, (m+n)logn))
    //Space complexity: O(1)
    private static void printUnionUsingSortingSearching(int[] arr1, int[] arr2){
        if(arr1==null || arr2==null || arr1.length==0 || arr2.length==0)
            return;
        if(arr1.length > arr2.length){
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }
        Arrays.sort(arr1);
        System.out.print(arr1[0] + "\t");
        for (int i=1; i<arr1.length; i++){
            if(arr1[i-1] != arr1[i])
                System.out.print(arr1[i] + "\t");
        }
        for (int i=0; i<arr2.length; i++){
            if(binarySearch(arr1, 0, arr1.length-1, arr2[i]) == -1)
                System.out.print(arr2[i] + "\t");
        }
        System.out.println();
    }

    //This will ot handle duplicates
    //Time complexity: O(min(mlogm + nlogm, mlogn + nlogn))=> O(min((m+n)logm, (m+n)logn))
    //Space complexity: O(1)
    private static void printIntersectionUsingSortingSearching(int[] arr1, int[] arr2){
        if(arr1==null || arr2==null || arr1.length==0 || arr2.length==0)
            return;
        if(arr1.length > arr2.length){
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }
        Arrays.sort(arr1);
        for (int i=0; i<arr2.length; i++){
            if(binarySearch(arr1, 0, arr1.length-1, arr2[i]) != -1)
                System.out.print(arr2[i] + "\t");
        }
        System.out.println();
    }

    private static int binarySearch(int[] arr, int start, int end, int num){
        if(start <= end){
            int mid = (start + end) / 2;
            if(arr[mid] == num)
                return mid;
            else if(arr[mid] > num)
                return binarySearch(arr, start, mid-1, num);
            else
                return binarySearch(arr, mid+1, end, num);
        }
        return -1;
    }
}
