package com.sde.chandu.dynamicprogramming;

public class CountOfSubsetsWithGivenSum {
    public static void main(String[] args) {
        int[] arr = {9, 7, 0, 3, 9, 8, 6, 5, 7, 6};//{1, 2, 3, 3};
        int sum = 31;
        System.out.println("Recursive approach, number of subsets: " + findNumberOfSubsetsRecursive(arr, sum));
        System.out.println("Memoization approach, number of subsets: " + findNumberOfSubsetsMemoization(arr, sum));
        System.out.println("Iterative approach, number of subsets: " + findNumberOfSubsetsIterative(arr, sum));
    }

    // Time complexity: O(2^n)
    // Space complexity: O(1), ignoring recursion stack
    private static int findNumberOfSubsetsRecursive(int[] arr, int sum) {
        int n = arr.length;
        return findNumberOfSubsetsRecursive(arr, sum, n);
    }

    private static int findNumberOfSubsetsRecursive(int[] arr, int sum, int n) {
        if (sum == 0 && n == 0)
            return 1;
        if (n == 0)
            return 0;
        if (arr[n - 1] <= sum)
            return findNumberOfSubsetsRecursive(arr, sum - arr[n - 1], n - 1)
                    + findNumberOfSubsetsRecursive(arr, sum, n - 1);
        else
            return findNumberOfSubsetsRecursive(arr, sum, n - 1);
    }

    // Time complexity: O(n * sum)
    // Space complexity: O(n * sum)
    private static int findNumberOfSubsetsMemoization(int[] arr, int sum) {
        if (arr == null || arr.length == 0)
            return 0;
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++)
                dp[i][j] = -1;
        }
        return findNumberOfSubsetsMemoization(arr, sum, n, dp);
    }

    private static int findNumberOfSubsetsMemoization(int[] arr, int sum, int n, int[][] dp) {
        if (n == 0 && sum == 0)
            return 1;
        if (n == 0)
            return 0;
        if (dp[n][sum] != -1)
            return dp[n][sum];
        if (arr[n - 1] <= sum)
            dp[n][sum] = findNumberOfSubsetsMemoization(arr, sum - arr[n - 1], n - 1, dp)
                    + findNumberOfSubsetsMemoization(arr, sum, n - 1, dp);
        else
            dp[n][sum] = findNumberOfSubsetsMemoization(arr, sum, n - 1, dp);
        return dp[n][sum];
    }

    // Time complexity: O(n * sum)
    // Space complexity: O(n * sum)
    private static int findNumberOfSubsetsIterative(int[] arr, int sum) {
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];

        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (arr[i - 1] <= j)
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][sum];
    }
}
