package com.sde.chandu.searching;

import java.util.Arrays;

public class SmallestPositiveMissingNumber {
    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 6, 8, -1, -10, 15};
        //int[] arr = {-25,38,24,-17,7,31,43,8,20,49,-33,-11,19,13,-28,44,23,-3,-19,12,32,40,42,41,7,-35,-29,7,24,41,-3,1,-19,-29,-13,-10,4,-20,48};
        System.out.println("Smallest positive missing number, brute: " + findSmallestPositiveMissingBrute(arr));
        //System.out.println("Smallest positive missing number, sorting: " + findSmallestPositiveMissingSorting(arr));
        //System.out.println("Smallest positive missing number, extra space: " + findSmallestPositiveMissingExtraSpace(arr));
        System.out.println("Smallest positive missing number, swapping efficient: " + findSmallestPositiveMissingSwappingEfficient(arr));
        //System.out.println("Smallest positive missing number, efficient 2: " + findSmallestPositiveMissingEfficient2(arr));
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static int findSmallestPositiveMissingBrute(int[] arr){
        boolean isPresent;
        for (int i=1; i<=arr.length; i++){
            isPresent = false;
            for (int j : arr){
                if (i == j){
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent)
                return i;
        }
        return arr.length+1;
    }

    //Time complexity: O(n log n + n) => O(n log n)
    //Space complexity: O(1)
    private static int findSmallestPositiveMissingSorting(int[] arr){
        Arrays.sort(arr);
        int i;
        for (i=0; i<arr.length; i++){
            if (arr[i] > 0)
                break;
        }
        for (int j=1; j<=arr.length; j++, i++){
            while (i<arr.length-1 && arr[i]==arr[i+1])
                i++;
            if (arr[i] != j)
                return j;
        }
        return arr.length+1;
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static int findSmallestPositiveMissingExtraSpace(int[] arr){
        boolean[] temp = new boolean[arr.length+1];
        for (int i : arr){
            if (i>0 && i<=arr.length)
                temp[i] = true;
        }
        for (int i=1; i<=arr.length; i++){
            if (!temp[i])
                return i;
        }
        return arr.length+1;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int findSmallestPositiveMissingEfficient2(int[] arr){
        boolean isOnePresent = false;
        for (int i : arr){
            if (i == 1){
                isOnePresent = true;
                break;
            }
        }
        if (!isOnePresent)
            return 1;
        for (int i=0; i<arr.length; i++){
            if (arr[i]<=0 || arr[i]>arr.length)
                arr[i] = 1;
        }
        for (int i=0; i<arr.length; i++)
            arr[(arr[i]-1)%arr.length] += arr.length;
        for (int i=0; i<arr.length; i++){
            if (arr[i] <= arr.length)
                return i+1;
        }
        return arr.length+1;
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static int findSmallestPositiveMissingSwappingEfficient(int[] arr){
        for (int i=0; i<arr.length; i++){
            while (arr[i]>0 && arr[i]<=arr.length && arr[i] != arr[arr[i]-1])
                swap(i, arr[i] -1, arr);
        }
        for (int i=0; i<arr.length; i++){
            if (arr[i] != i+1)
                return i+1;
        }
        return arr.length+1;
    }

    private static void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;


    }
}
