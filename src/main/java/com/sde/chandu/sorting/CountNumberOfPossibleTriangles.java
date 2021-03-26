package com.sde.chandu.sorting;

import java.util.Arrays;

public class CountNumberOfPossibleTriangles {
    public static void main(String[] args) {
        int[] arr = {4, 6, 3, 7};
        System.out.println("Number of triangles, brute: " + getNumberOfTrianglesBrute(arr));
        System.out.println("Number of triangles, sorting 1: " + getNumberOfTrianglesSorting1(arr));
        System.out.println("Number of triangles, sorting 2: " + getNumberOfTrianglesSorting2(arr));
    }

    //Time complexity: O(n^3)
    //Space complexity: O(1)
    private static int getNumberOfTrianglesBrute(int[] arr){
        if (arr==null || arr.length<3)
            return 0;
        int count = 0;
        for (int i=0; i<arr.length-2; i++){
            for (int j=i+1; j<arr.length-1; j++){
                for (int k=j+1; k<arr.length; k++){
                    if (arr[i]+arr[j]>arr[k] && arr[i]+arr[k]>arr[j] && arr[j]+arr[k]>arr[i])
                        count++;
                }
            }
        }
        return count;
    }

    //Time complexity: O(n log n) + O(n^2) => O(n^2)
    //Space complexity: O(1)
    private static int getNumberOfTrianglesSorting1(int[] arr){
        if (arr==null || arr.length<3)
            return 0;
        Arrays.sort(arr);
        int count = 0;
        for (int i=0, k; i<arr.length-2; i++){
            k = i+2;
            for (int j=i+1; j<arr.length; j++){
                while (k<arr.length && arr[i]+arr[j]>arr[k])
                    k++;
                if (k > j)
                    count += (k - j - 1);
            }
        }
        return count;
    }

    //Time complexity: O(n log n) + O(n^2) => O(n^2)
    //Space complexity: O(1)
    private static int getNumberOfTrianglesSorting2(int[] arr){
        if (arr==null || arr.length<3)
            return 0;
        Arrays.sort(arr);
        int count = 0;
        for (int i=arr.length-1, left, right; i>=2; i--){
            left=0;
            right=i-1;
            while (left < right){
                if (arr[left]+arr[right] > arr[i]){
                    count += right - left;
                    right--;
                }else
                    left++;
            }
        }
        return count;
    }
}
