package com.sde.chandu.dynamicprogramming;

public class MinimumSubsetSumDifference {
    public static void main(String[] args) {
        int[] arr = {1, 2, 7};
        System.out.println("Recursive approach, minimum subset sum difference: " + minimumSubsetDifferenceRecursive(arr));
        System.out.println("Memoization approach, minimum subset sum difference: " + minimumSubsetDifferenceMemoization(arr));
        System.out.println("Iterative approach, minimum subset sum difference: " + minimumSubsetDifferenceIterative(arr));

    }

    // Time complexity : O(n * sum), sum = sum of array elements, n = array size
    // Space complexity : O(n * sum)
    private static int minimumSubsetDifferenceIterative(int[] arr) {
        int diff = Integer.MAX_VALUE;
        if (arr == null || arr.length == 0)
            return diff;
        int range = 0, n = arr.length;
        for (int i : arr)
            range += i;
        boolean[][] dp = new boolean[n + 1][range + 1];
        subsetSum(arr, dp, n, range);
        for (int j = range / 2; j >= 0; j--) {
            if (dp[n][j]) {
                return range - 2 * j;
            }
        }
        return diff;
    }

    private static void subsetSum(int[] arr, boolean[][] dp, int n, int sum) {
        for (int j = 0; j <= sum; j++)
            dp[0][j] = false;
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i - 1] <= j)
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] || dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
    }

    // Time complexity : O(2^n)
    // Space complexity : O(1), ignoring recursion stack
    private static int minimumSubsetDifferenceRecursive(int[] arr) {
        if (arr == null || arr.length == 0)
            return Integer.MAX_VALUE;
        int sum = 0;
        for (int i : arr)
            sum += i;
        return minimumSubsetDifferenceRecursive(arr, arr.length, sum, 0);
    }

    private static int minimumSubsetDifferenceRecursive(int[] arr, int n, int sum, int curSum) {
        if (n == 0)
            return Math.abs((sum - curSum) - curSum);
        return Math.min(minimumSubsetDifferenceRecursive(arr, n - 1, sum, curSum + arr[n - 1]),
                minimumSubsetDifferenceRecursive(arr, n - 1, sum, curSum));
    }

    // Time complexity : O(n * sum), sum = sum of array elements, n = array size
    // Space complexity : O(n * sum)
    private static int minimumSubsetDifferenceMemoization(int[] arr) {
        if (arr == null || arr.length == 0)
            return Integer.MAX_VALUE;
        int sum = 0, n = arr.length;
        for (int i : arr)
            sum += i;
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++)
                dp[i][j] = -1;
        }
        return minimumSubsetDifferenceMemoization(arr, n, sum, 0, dp);
    }

    private static int minimumSubsetDifferenceMemoization(int[] arr, int n, int sum, int curSum, int[][] dp) {
        if (n == 0)
            return Math.abs((sum - curSum) - curSum);
        if (dp[n][curSum] != -1)
            return dp[n][curSum];
        dp[n][curSum] = Math.min(minimumSubsetDifferenceMemoization(arr, n - 1, sum, curSum + arr[n - 1], dp),
                minimumSubsetDifferenceMemoization(arr, n - 1, sum, curSum, dp));
        return dp[n][curSum];
    }
}
