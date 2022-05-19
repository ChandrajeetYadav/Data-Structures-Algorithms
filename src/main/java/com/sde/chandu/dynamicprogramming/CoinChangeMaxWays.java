package com.sde.chandu.dynamicprogramming;

public class CoinChangeMaxWays {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int cents = 4;
        System.out.println("Recursive approach, max number of ways to get cents: " + countMaxWaysRecursive(arr, cents));
        System.out.println("Memoization approach, max number of ways to get cents: " + countMaxWaysMemoization(arr, cents));
        System.out.println("Iterative approach, max number of ways to get cents: " + countMaxWaysIterative(arr, cents));
    }

    // Time complexity : O(2^n)
    // Space complexity : O(1), ignoring recursion stack
    private static int countMaxWaysRecursive(int[] arr, int cents) {
        return countMaxWaysRecursive(arr, arr.length, cents);
    }

    private static int countMaxWaysRecursive(int[] arr, int n, int cents) {
        if (cents == 0)
            return 1;
        if (n == 0)
            return 0;
        if (arr[n - 1] <= cents)
            return countMaxWaysRecursive(arr, n, cents - arr[n - 1])
                    + countMaxWaysRecursive(arr, n - 1, cents);
        else
            return countMaxWaysRecursive(arr, n - 1, cents);
    }

    // Time complexity : O(n * cents)
    // Space complexity : O(n * cents)
    private static int countMaxWaysMemoization(int[] arr, int cents) {
        int n = arr.length;
        int[][] dp = new int[n + 1][cents + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= cents; j++)
                dp[i][j] = -1;
        }
        return countMaxWaysMemoization(arr, n, cents, dp);
    }

    private static int countMaxWaysMemoization(int[] arr, int n, int cents, int[][] dp) {
        if (cents == 0)
            return 1;
        if (n == 0)
            return 0;
        if (dp[n][cents] != -1)
            return dp[n][cents];
        if (arr[n - 1] <= cents)
            dp[n][cents] = countMaxWaysMemoization(arr, n, cents - arr[n - 1], dp)
                    + countMaxWaysMemoization(arr, n - 1, cents, dp);
        else
            dp[n][cents] = countMaxWaysMemoization(arr, n - 1, cents, dp);
        return dp[n][cents];
    }

    // Time complexity : O(n * cents)
    // Space complexity : O(n * cents)
    private static int countMaxWaysIterative(int[] arr, int cents) {
        int n = arr.length;
        int[][] dp = new int[n + 1][cents + 1];
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= cents; j++) {
                if (arr[i - 1] <= j)
                    dp[i][j] = dp[i][j - arr[i - 1]] + dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][cents];
    }
}
