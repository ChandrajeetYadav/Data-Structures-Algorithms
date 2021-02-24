package com.sde.chandu.array;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumNegative {
    public static void main(String[] args) {
        int sum = -10;
        int[] arr = {10, 2, -2, -20, 10}; // O/p: 1 and 4
        System.out.println("Sub array sum, brute");
        subArraySumBrute(arr, sum);
        System.out.println("Sub array sum, hashing");
        subArraySumUsingHashing(arr, sum);
        int[] arr2 = { 10, -12, 2, -2, -20, 10 }; //O/p: 2 and 3
        System.out.println("Sub array sum, efficient");
        subArraySumEfficient(arr2, sum);
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static void subArraySumBrute(int[] arr, int targetSum){
        if(arr==null || arr.length==0){
            System.out.println("No sub array found");
            return;
        }
        int currSum;
        for(int i=0; i<arr.length; i++){
            currSum = arr[i];
            for(int j=i+1; j<=arr.length; j++){
                if(currSum == targetSum){
                    System.out.println("Sum found between "+(i+1)+" and "+j+" position");
                    return;
                }
                if(j==arr.length)
                    break;
                currSum += arr[j];
            }
        }
        System.out.println("No sub array found");
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static void subArraySumUsingHashing(int[] arr, int targetSum){
        if(arr==null || arr.length==0){
            System.out.println("No sub array found");
            return;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int curSum=0, start=0, end=-1;
        for(int i=0; i<arr.length; i++){
            curSum += arr[i];
            if(curSum == targetSum) {
                end = i;
                break;
            }
            if(map.containsKey(curSum-targetSum)){
                start = map.get(curSum-targetSum) + 1;
                end = i;
                break;
            }
            map.put(curSum, i);
        }
        if(end == -1)
            System.out.println("No sub array found");
        else
            System.out.println("Sum found between "+(start+1)+" and "+(end+1)+" position");
    }

    //Time complexity: O(n)
    //Space complexity: O(1)
    private static void subArraySumEfficient(int[] arr, int targetSum){
        if(arr==null || arr.length==0){
            System.out.println("No sub array found");
            return;
        }
        int min = Integer.MAX_VALUE;
        for(int i : arr)
            min = Math.min(min, i);
        int absMin = Math.abs(min);
        int curSum = arr[0] + absMin;
        int start = 0;
        for(int i=1; i<=arr.length; i++){
            while ((curSum-(i-start)*absMin > targetSum) && start<i){
                curSum = curSum -arr[start] - absMin;
                start++;
            }
            if(curSum-(i-start)*absMin == targetSum){
                System.out.println("Sum found between "+(start+1)+" and "+i+" position");
                return;
            }
            if(i<arr.length)
                curSum = curSum + arr[i] + absMin;
        }
        System.out.println("No sub array found");
    }
}
