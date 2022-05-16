package com.sde.chandu.dynamicprogramming;

public class KnapSack01 {
    public static void main(String[] args) {
        int[] wt = {10, 20, 30};
        int[] val = {60, 100, 120};
        int W = 50;
        System.out.println("Maximum profit using recursive approach: " + knapSackRecurisve(wt, val, W));
        System.out.println("Maximum profit using memoization approach: " + knapSackMemoization(wt, val, W));
        System.out.println("Maximum profit using iterative/bottom-up approach: " + knapSackIterative(wt, val, W));
    }

    // Time complexity: O(2^n)
    // Space complexity: O(1), ignoring recursion stack
    private static int knapSackRecurisve(int[] wt, int[] val, int W) {
        return knapSackRecurisve(wt, val, W, wt.length);
    }

    private static int knapSackRecurisve(int[] wt, int[] val, int W, int n) {
        if (n == 0 || W == 0)
            return 0;
        if (wt[n - 1] <= W)
            return Math.max(val[n - 1] + knapSackRecurisve(wt, val, W - wt[n - 1], n - 1), knapSackRecurisve(wt, val, W, n - 1));
        else
            return knapSackRecurisve(wt, val, W, n - 1);
    }

    // Time complexity: O(n * W)
    // Space complexity: O(n * W)
    private static int knapSackMemoization(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++)
                dp[i][j] = -1;
        }
        return knapSackMemoization(wt, val, W, n, dp);
    }

    private static int knapSackMemoization(int[] wt, int[] val, int W, int n, int[][] dp) {
        if (n == 0 || W == 0)
            return 0;
        if (dp[n][W] != -1)
            return dp[n][W];
        if (wt[n - 1] <= W)
            dp[n][W] = Math.max(val[n - 1] + knapSackMemoization(wt, val, W - wt[n - 1], n - 1, dp), knapSackMemoization(wt, val, W, n - 1, dp));
        else
            dp[n][W] = knapSackMemoization(wt, val, W, n - 1, dp);
        return dp[n][W];
    }

    // Time complexity: O(n * W)
    // Space complexity: O(n * W)
    private static int knapSackIterative(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                else if (wt[i - 1] <= j)
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][W];
    }
}
