package com.sde.chandu.hashing;

import java.util.HashMap;
import java.util.Map;

public class LargestSubarrayWithEqual0And1 {
    public static void main(String[] args) {
        int[] arr1 = {1, 0, 1, 1, 1, 0, 0};
        int[] arr2 = {1, 1, 1, 1};

        System.out.println("arr1 max subarray length, brute: " + findLargestSubarrayBrute(arr1));
        System.out.println("arr1 max subarray length, brute: " + findLargestSubarrayBrute(arr2));
        System.out.println();

        System.out.println("arr1, max subarray length, hashing: " + findLargestSubarrayHashing(arr1));
        System.out.println("arr1, max subarray length, hashing: " + findLargestSubarrayHashing(arr2));
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static int findLargestSubarrayBrute(int[] arr){
        if (arr==null || arr.length==0)
            return 0;
        int sum, maxSize = 0, startIndex = 0, endIndex;
        for (int i=0; i<arr.length-1; i++){
            sum = (arr[i]==0) ? -1 : 1;
            for (int j=i+1; j<arr.length; j++){
                sum += (arr[j]==0) ? -1 : 1;
                if (sum==0 && maxSize<j-i+1){
                    maxSize = j-i+1;
                    startIndex = i;
                }
            }
        }
        endIndex = startIndex + maxSize -1;
        if (maxSize == 0)
            System.out.println("No such subarray found");
        else
            System.out.println("Max subarray lie between "+ startIndex + " and " + endIndex);
        return maxSize;
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static int findLargestSubarrayHashing(int[] arr){
        if (arr==null || arr.length==0)
            return -1;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLen = 0, endIndex = -1;
        for (int i=0; i<arr.length; i++){
            sum += (arr[i]==0) ? -1 : 1;
            if (sum == 0){
                maxLen = i+1;
                endIndex = i;
            }else if (map.containsKey(sum) && maxLen<i-map.get(sum)){
                maxLen = i - map.get(sum);
                endIndex = i;
            }
            if (!map.containsKey(sum))
                map.put(sum, i);
        }
        if (endIndex == -1)
            System.out.println("No such subarray found");
        else
            System.out.println("Max subarray lie between "+ (endIndex - maxLen + 1) + " and " + endIndex);
        return maxLen;
    }
}
