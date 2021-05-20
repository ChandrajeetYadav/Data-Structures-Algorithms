package com.sde.chandu.hashing;

import java.util.*;

public class LongestConsecutiveSubsequence {
    public static void main(String[] args) {
        int[] arr1 = {1, 9, 3, 10, 4, 20, 2}; // O/p: 4
        System.out.println("Longest consecutive sub sequence in arr1, brute: " + findLongestConsecutiveSubsequenceBrute(arr1));

        int[] arr2 = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42}; // O/p: 5
        System.out.println("Longest consecutive sub sequence in arr2, hashing: " + findLongestConsecutiveSubsequenceHahing(arr2));
        System.out.println("Longest consecutive sub sequence in arr2, Priority Queue: " + findLongestConsecutiveSubsequenceUsingPQ(arr2));
    }

    //Time complexity: O(n log n)
    //Space complexity: O(n)
    private static int findLongestConsecutiveSubsequenceBrute(int[] arr) {
        Arrays.sort(arr);
        List<Integer> list = new ArrayList<>();
        list.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1])
                list.add(arr[i]);
        }

        int ans = 1, count = 1;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == list.get(i - 1) + 1)
                count++;
            else
                count = 1;
            ans = Math.max(ans, count);
        }
        return ans;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static int findLongestConsecutiveSubsequenceHahing(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i : arr)
            set.add(i);
        int ans = 0, j;
        for (int i : set) {
            if (!set.contains(i - 1)) {
                j = i;
                while (set.contains(j))
                    j++;
                ans = Math.max(ans, j - i);
            }
        }
        return ans;
    }

    // Time complexity: O(n log n)
    // Space complexity:  O(n)
    private static int findLongestConsecutiveSubsequenceUsingPQ(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : arr)
            pq.add(i);
        int prev = pq.poll();
        int ans = 1, count = 1;
        while (!pq.isEmpty()) {
            if (pq.peek() - prev > 1) {
                count = 1;
                prev = pq.poll();
            } else if (pq.peek() - prev == 0) {
                prev = pq.poll();
            } else {
                count++;
                prev = pq.poll();
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
