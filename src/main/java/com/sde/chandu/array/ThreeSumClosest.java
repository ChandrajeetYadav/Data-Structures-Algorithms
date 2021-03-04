package com.sde.chandu.array;

import java.util.Arrays;

public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] arr1 = {5,2,7,5};
        int x1 = 13; // For arr1 and x1 o/p: 14

        int[] arr2 = {-7,9,8,3,1,1};
        int x2 = 2; // For arr2 and x2 o/p: 2

        System.out.println("arr1, closest sum brute: " + threeSumClosestBrute(arr1, x1));
        System.out.println("arr2, closest sum brute: " + threeSumClosestBrute(arr2, x2));
        System.out.println();

        System.out.println("arr1, closest sum 2 pointer: " + threeSumClosest2Pointer(arr1, x1));
        System.out.println("arr2, closest sum 2 pointer: " + threeSumClosest2Pointer(arr2, x2));
    }

    //Time complexity : O(n^3)
    //Space complexity : O(1)
    private static int threeSumClosestBrute(int[] arr, int x){
        if(arr==null || arr.length<3)
            return Integer.MIN_VALUE;
        int closestSum = Integer.MAX_VALUE, curSum;
        for (int i=0; i<arr.length-2; i++){
            for (int j=i+1; j<arr.length-1; j++){
                for (int k=j+1; k<arr.length; k++){
                    curSum = arr[i] + arr[j] + arr[k];
                    if(Math.abs(x - closestSum) > Math.abs(x - curSum))
                        closestSum = curSum;
                }
            }
        }
        return closestSum;
    }

    //Time complexity : O(n^2)
    //Space complexity : O(1)
    private static int threeSumClosest2Pointer(int[] arr, int x){
        if(arr==null || arr.length<3)
            return Integer.MIN_VALUE;
        Arrays.sort(arr);
        int closestSum = Integer.MAX_VALUE, curSum;
        for (int i=0, low, high; i<arr.length-2; i++){
            low = i+1;
            high = arr.length-1;
            while (low < high){
                curSum = arr[i] + arr[low] + arr[high];
                if(Math.abs(x-closestSum) > Math.abs(x-curSum))
                    closestSum = curSum;
                else if(Math.abs(x-closestSum) == Math.abs(x-curSum)) {
                    if(curSum > closestSum)
                        closestSum = curSum;
                }
                if (curSum > x)
                    high--;
                else
                    low++;
            }
        }
        return closestSum;
    }
}
