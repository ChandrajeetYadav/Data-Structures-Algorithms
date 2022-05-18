package com.sde.chandu.dynamicprogramming;

public class CountSubsetsWithAGivenDifference {
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3};
        int diff = 1;
        System.out.println("Number of subsets with given difference: " + countSubset(arr, diff));
    }

    // Time complexity: O(n * sum)
    // Space complexity: O(n * sum)
    private static int countSubset(int[] arr, int diff) {
        if (arr == null || arr.length == 0)
            return 0;
        int sum = 0;
        for (int i : arr)
            sum += i;
        if (sum < Math.abs(diff) || (diff + sum) % 2 != 0)
            return 0;
        int reqSum = (sum + diff) / 2;
        // This problem is now reduced to count of subsets with given sum.
        return CountOfSubsetsWithGivenSum.findNumberOfSubsetsIterative(arr, reqSum);
    }
}
