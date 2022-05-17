package com.sde.chandu.dynamicprogramming;

public class SubsetSum {
    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int sum1 = 9, sum2 = 13;
        System.out.println("Recursive approach, sum=" + sum1 + " is possible: " + isSubsetSumRecursive(arr, sum1));
        System.out.println("Recursive approach, sum=" + sum2 + " is possible: " + isSubsetSumRecursive(arr, sum2));
        System.out.println("Memoization approach, sum=" + sum1 + " is possible: " + isSubsetSumMemoization(arr, sum1));
        System.out.println("Memoization approach, sum=" + sum2 + " is possible: " + isSubsetSumMemoization(arr, sum2));
        System.out.println("Iterative/bottom-up approach, sum=" + sum1 + " is possible: " + isSubsetSumIterative(arr, sum1));
        System.out.println("Iterative/bottom-up approach, sum=" + sum2 + " is possible: " + isSubsetSumIterative(arr, sum2));
    }

    // Time complexity: O(2^n)
    // Space complexity: O(1), ignoring recursion stack
    private static boolean isSubsetSumRecursive(int[] arr, int sum) {
        return isSubsetSumRecursive(arr, sum, arr.length);
    }

    private static boolean isSubsetSumRecursive(int[] arr, int sum, int n) {
        if (n == 0)
            return false;
        if (sum == 0)
            return true;
        if (arr[n - 1] <= sum)
            return isSubsetSumRecursive(arr, sum - arr[n - 1], n - 1) || isSubsetSumRecursive(arr, sum, n - 1);
        else
            return isSubsetSumRecursive(arr, sum, n - 1);
    }

    // Time complexity: O(n * sum)
    // Space complexity: O(n * sum)
    private static boolean isSubsetSumMemoization(int[] arr, int sum) {
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++)
                dp[i][j] = -1;
        }
        return isSubsetSumMemoization(arr, sum, n, dp) != 0;
    }

    private static int isSubsetSumMemoization(int[] arr, int sum, int n, int[][] dp) {
        if (sum == 0)
            return 1;
        if (n <= 0)
            return 0;
        if (dp[n][sum] != -1)
            return dp[n][sum];
        if (arr[n - 1] <= sum)
            dp[n][sum] = (isSubsetSumMemoization(arr, sum - arr[n - 1], n - 1, dp)
                    + isSubsetSumMemoization(arr, sum, n - 1, dp)) >= 1 ? 1 : 0;
        else
            dp[n][sum] = isSubsetSumMemoization(arr, sum, n - 1, dp);
        return dp[n][sum];
    }

    // Time complexity: O(n * sum)
    // Space complexity: O(n * sum)
    static boolean isSubsetSumIterative(int[] arr, int sum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        // Initialization
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
        return dp[n][sum];
    }
}
