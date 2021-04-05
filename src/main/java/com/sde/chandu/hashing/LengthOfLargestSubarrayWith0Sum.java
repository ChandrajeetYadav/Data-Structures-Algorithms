package com.sde.chandu.hashing;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLargestSubarrayWith0Sum {
    public static void main(String[] args) {
        int[] arr1 = {15, -2, 2, -8, 1, 7, 10, 23}; // O/p: 5
        int[] arr2 = {1, 2, 3}; // O/p: 0

        System.out.println("arr1, max subarray length brute: " + maxLengthBrute(arr1));
        System.out.println("arr2, max subarray length brute: " + maxLengthBrute(arr2));
        System.out.println();

        System.out.println("arr1, max subarray length hashing: " + maxLengthHashing(arr1));
        System.out.println("arr2, max subarray length hashing: " + maxLengthHashing(arr2));
    }

    // Time complexity: O(n^2)
    // Space complexity: O(1)
    private static int maxLengthBrute(int[] arr){
        if (arr==null || arr.length==0)
            return 0;
        int sum, maxLen=0, startingIndex=0;
        for(int i=0; i<arr.length; i++){
            sum = 0;
            for (int j=i; j<arr.length; j++){
                sum += arr[j];
                if(sum==0 && maxLen<j-i+1){
                    maxLen = j-i+1;
                    startingIndex = i;
                }
            }
        }
        if (maxLen == 0)
            System.out.println("No such subarray found");
        else
            System.out.println("Max subarray found between "+ startingIndex + " and " + (startingIndex+maxLen-1));
        return maxLen;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static int maxLengthHashing(int[] arr){
        if(arr==null || arr.length==0)
            return 0;
        int maxLen=0, sum=0, endingIndex=0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<arr.length; i++){
            sum += arr[i];
            if (sum == 0){
                maxLen = i+1;
                endingIndex = i;
            }else if (map.containsKey(sum) && maxLen<i-map.get(sum)){
                maxLen = i - map.get(sum);
                endingIndex = i;
            }
            if (!map.containsKey(sum))
                map.put(sum, i);
        }
        if (maxLen == 0)
            System.out.println("No such subarray found");
        else
            System.out.println("Max subarray found between "+ (endingIndex-maxLen+1) + " and " + endingIndex);
        return maxLen;
    }
}
