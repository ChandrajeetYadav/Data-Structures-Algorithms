package com.sde.chandu.array;

import java.util.Arrays;

public class MaximumIndex {
    public static void main(String[] args) {
        int[] arr = { 9, 2, 3, 4, 5, 6, 7, 8, 18, 0 };
        System.out.println("Max index difference, brute: " + maxIndexDiffBrute(arr));
        System.out.println("Max index difference, brute optimized: " + maxIndexDiffBruteOptimized(arr));
        System.out.println("Max index difference, efficient: " + maxIndexDiffEfficient(arr));
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static int maxIndexDiffBrute(int[] arr){
        if(arr==null || arr.length<=0)
            return 0;
        int res = Integer.MIN_VALUE;
        for(int i=0; i<arr.length-1; i++){
            for(int j=arr.length-1; j>i; j--){
                if(arr[j] > arr[i]){
                    res = Math.max(res, j-i);
                    break;
                }
            }
        }
        return res;
    }

    //Time complexity: O(n log n)
    //Space complexity: O(n)
    private static int maxIndexDiffBruteOptimized(int[] arr){
        if(arr==null || arr.length<=0)
            return 0;
        int[] maxFromEnd = new int[arr.length];
        Arrays.fill(maxFromEnd, Integer.MIN_VALUE);
        maxFromEnd[arr.length-1] = arr[arr.length-1];
        for(int i=arr.length-2; i>=0; i--)
            maxFromEnd[i] = Math.max(arr[i], maxFromEnd[i+1]);
        int low, high, ans, mid, res = Integer.MIN_VALUE;
        for(int i=0; i<arr.length; i++){
            ans = i;
            low = i+1;
            high = arr.length-1;
            while (low<=high){
                mid = (low + high)/2;
                if(arr[i] < maxFromEnd[mid]){
                    low = mid + 1;
                    ans = Math.max(ans, mid);
                }else
                    high = mid - 1;
            }
            res = Math.max(res, ans - i);
        }
        return res;
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static int maxIndexDiffEfficient(int[] arr){
        if(arr==null || arr.length<=0)
            return 0;
        int[] leftMin = new int[arr.length];
        int[] rightMax = new int[arr.length];

        leftMin[0] = arr[0];
        for(int i=1; i<arr.length; i++)
            leftMin[i] = Math.min(arr[i], leftMin[i-1]);

        rightMax[arr.length-1] = arr[arr.length-1];
        for(int i=arr.length-2; i>=0; i--)
            rightMax[i] = Math.max(arr[i], rightMax[i+1]);

        int res = Integer.MIN_VALUE, i=0, j=0;
        while (i<arr.length && j<arr.length){
            if(leftMin[i] < rightMax[j]){
                res = Math.max(res, j-i);
                j++;
            }else
                i++;
        }
        return res;
    }

}
