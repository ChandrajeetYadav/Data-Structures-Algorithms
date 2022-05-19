package com.sde.chandu.dynamicprogramming;

public class UnboundedKnapsack {
    public static void main(String[] args) {
        int[] val = {1, 4, 5, 7};
        int[] wt = {1, 3, 4, 5};
        int W = 8;
        System.out.println("Recursive approach, max profit: " + maxProfitRecursive(val, wt, W));
        System.out.println("Memoization approach, max profit: " + maxProfitMemoization(val, wt, W));
        System.out.println("Iterative approach, max profit: " + maxProfitIterative(val, wt, W));
    }

    // Time complexity : O(2^n)
    // Space complexity : O(1), ignoring recursion stack
    private static int maxProfitRecursive(int[] val, int[] wt, int W) {
        return maxProfitRecursive(val, wt, W, val.length);
    }

    private static int maxProfitRecursive(int[] val, int[] wt, int W, int n) {
        if (n == 0 || W == 0)
            return 0;
        if (wt[n - 1] <= W)
            return Math.max(val[n - 1] + maxProfitRecursive(val, wt, W - wt[n - 1], n),
                    maxProfitRecursive(val, wt, W, n - 1));
        else
            return maxProfitRecursive(val, wt, W, n - 1);
    }

    // Time complexity : O(n * W)
    // Space complexity : O(n * W)
    private static int maxProfitMemoization(int[] val, int[] wt, int W) {
        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++)
                dp[i][j] = -1;
        }
        return maxProfitMemoization(val, wt, W, n, dp);
    }

    private static int maxProfitMemoization(int[] val, int[] wt, int W, int n, int[][] dp) {
        if (n == 0 || W == 0)
            return 0;
        if (dp[n][W] != -1)
            return dp[n][W];
        if (wt[n - 1] <= W)
            dp[n][W] = Math.max(val[n - 1] + maxProfitMemoization(val, wt, W - wt[n - 1], n, dp),
                    maxProfitMemoization(val, wt, W, n - 1, dp));
        else
            dp[n][W] = maxProfitMemoization(val, wt, W, n - 1, dp);
        return dp[n][W];
    }

    // Time complexity : O(n * W)
    // Space complexity : O(n * W)
    private static int maxProfitIterative(int[] val, int[] wt, int W) {
        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (wt[i - 1] <= j)
                    dp[i][j] = Math.max(val[i - 1] + dp[i][j - wt[i - 1]], dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][W];
    }
}
