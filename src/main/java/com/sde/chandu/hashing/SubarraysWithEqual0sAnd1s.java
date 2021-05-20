package com.sde.chandu.hashing;

import java.util.HashMap;
import java.util.Map;

public class SubarraysWithEqual0sAnd1s {
    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 1, 0, 1, 1}; // O/p: 8
        System.out.println("No of subarrays with equal 0s and 1s, brute: " + countSubArrayWithEqual0And1Brute(arr));
        System.out.println("No of subarrays with equal 0s and 1s, hashing 1: " + countSubArrayWithEqual0And1UsingHashing1(arr));
        System.out.println("No of subarrays with equal 0s and 1s, hashing 2: " + countSubArrayWithEqual0And1UsingHashing2(arr));
    }

    //Time complexity: O(n^2)
    //Space complexity: O(1)
    private static int countSubArrayWithEqual0And1Brute(int[] arr) {
        int count = 0, sum;
        for (int i = 0; i < arr.length; i++) {
            sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j] == 0 ? -1 : 1;
                if (sum == 0)
                    count++;
            }
        }
        return count;
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static int countSubArrayWithEqual0And1UsingHashing1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int curSum = 0;
        for (int i : arr) {
            curSum += i == 0 ? -1 : 1;
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1)
                count += (entry.getValue() * (entry.getValue() - 1)) / 2;
        }
        if (map.containsKey(0))
            count += map.get(0);
        return count;
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    private static int countSubArrayWithEqual0And1UsingHashing2(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        for (int i : arr) {
            sum += i == 0 ? -1 : 1;
            if (sum == 0)
                count++;
            if (map.containsKey(sum))
                count += map.get(sum);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
