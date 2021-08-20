package com.sde.chandu.backtracking;

import java.util.*;

public class UniqueSubsets {
    public static void main(String[] args) {
        int[] arr = {2, 1, 2};
        System.out.println("All unique subsets using power set: ");
        findUniqueSubsetsUsingPowerSet(arr);
        int[] arr2 = {1, 2, 3, 3};
        System.out.println("All unique subsets using backtracking: ");
        findUniqueSubsetsUsingBacktracking(arr2);
    }

    // Time complexity : O(n * (2 ^ n)) + O((2^n) log (2^n))
    // Space complexity : O(2 ^ n * X), X = Length of each subset.
    private static List<List<Integer>> findUniqueSubsetsUsingPowerSet(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        if (arr == null)
            return res;
        int n = arr.length;
        String temp;
        Arrays.sort(arr);
        TreeSet<String> set = new TreeSet<>();
        for (int i = 0; i < (1 << n); i++) {
            temp = "";
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0)
                    temp += arr[j] + " ";
            }
            set.add(temp.trim());
        }
        for (String s : set)
            res.add(parseFromStringToInteger(s));
        System.out.println(res);
        return res;
    }

    // Time complexity : O(2 ^ n) + O((2^n) log (2^n))
    // For every index i two recursive cases originate, So Time Complexity is O(2^n).
    //If we include the time taken to copy the subset vector into the res vector the time taken will be equal to the
    // size of the subset vector.

    // Space complexity : O((2 ^ n) * X), X = Length of each subset.
    private static List<List<Integer>> findUniqueSubsetsUsingBacktracking(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        if (arr == null)
            return res;
        Set<String> set = new HashSet<>();
        findUniqueSubsetsUsingBacktrackingUtil(arr, set, "", 0);
        for (String s : set)
            res.add(parseFromStringToInteger(s));
        sort(res);
        System.out.println(res);
        return res;
    }

    private static void findUniqueSubsetsUsingBacktrackingUtil(int[] arr, Set<String> set, String s, int index) {
        if (arr.length == index) {
            set.add(s.trim());
            return;
        }
        findUniqueSubsetsUsingBacktrackingUtil(arr, set, s, index + 1);
        s += arr[index] + " ";
        findUniqueSubsetsUsingBacktrackingUtil(arr, set, s, index + 1);
    }

    private static List<Integer> parseFromStringToInteger(String s) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.isEmpty())
            return list;
        String[] arr = s.split(" ");
        for (String i : arr)
            list.add(Integer.parseInt(i));
        return list;
    }

    private static void sort(List<List<Integer>> list) {
        if (list == null || list.isEmpty())
            return;
        list.sort((l1, l2) -> {
            for (int i = 0; i < Math.min(l1.size(), l2.size()); i++) {
                if (l1.get(i) != l2.get(i))
                    return l1.get(i) - l2.get(i);
            }
            return l1.size() - l2.size();
        });
    }
}
