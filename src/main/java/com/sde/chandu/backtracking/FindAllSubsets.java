package com.sde.chandu.backtracking;

import java.util.ArrayList;
import java.util.List;

public class FindAllSubsets {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println("All subsets using left shift or power set approach: ");
        findAllSubsetsUsingLeftShiftOperator(arr);
        System.out.println("All subsets using backtracking: ");
        findAllSubsetsUsingLBacktracking(arr);
    }

    // Time complexity : O(n * (2 ^ n))
    // Space complexity : O((2 ^ n) * X), X = Length of each subset.
    private static void findAllSubsetsUsingLeftShiftOperator(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset;
        for (int i = 0; i < (1 << arr.length); i++) {
            subset = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                if ((i & (1 << j)) > 0)
                    subset.add(arr[j]);
            }
            res.add(subset);
        }
        System.out.println(res);
    }

    // Time complexity : O(2 ^ n) For every index i two recursive cases originate, So Time Complexity is O(2^n).
    //If we include the time taken to copy the subset vector into the res vector the time taken will be equal to the
    // size of the subset vector.

    // Space complexity : O((2 ^ n) * X), X = Length of each subset.
    private static void findAllSubsetsUsingLBacktracking(int[] arr) {
        List<List<Integer>> subset = new ArrayList<>();
        findAllSubsetsUsingLBacktrackingUtil(arr, subset, new ArrayList<>(), 0);
        System.out.println(subset);
    }

    private static void findAllSubsetsUsingLBacktrackingUtil(int[] arr, List<List<Integer>> subset, List<Integer> output, int index) {
        if (index == arr.length) {
            subset.add(output);
            return;
        }
        findAllSubsetsUsingLBacktrackingUtil(arr, subset, new ArrayList<>(output), index + 1);
        output.add(arr[index]);
        findAllSubsetsUsingLBacktrackingUtil(arr, subset, new ArrayList<>(output), index + 1);
    }
}
