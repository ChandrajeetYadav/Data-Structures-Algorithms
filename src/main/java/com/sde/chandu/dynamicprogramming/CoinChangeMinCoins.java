package com.sde.chandu.dynamicprogramming;

public class CoinChangeMinCoins {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int value = 11;
        System.out.println("Recursive approach, minimum number of coins: " + minimumNoOfCoinsRecursive(coins, value));
        System.out.println("Memoization approach, minimum number of coins: " + minimumNoOfCoinsMemoization(coins, value));
        System.out.println("Iterative approach, minimum number of coins: " + minimumNoOfCoinsIterative(coins, value));
    }

    // Time complexity : O(2^n)
    // Space complexity : O(1), ignoring recursion stack
    private static int minimumNoOfCoinsRecursive(int[] coins, int value) {
        int minCoins = minimumNoOfCoinsRecursive(coins, value, coins.length);
        return minCoins == Integer.MAX_VALUE - 1 ? -1 : minCoins;
    }

    private static int minimumNoOfCoinsRecursive(int[] coins, int value, int n) {
        if (value == 0)
            return 0;
        if (n == 0)
            return Integer.MAX_VALUE - 1;
        if (coins[n - 1] <= value)
            return Math.min(1 + minimumNoOfCoinsRecursive(coins, value - coins[n - 1], n),
                    minimumNoOfCoinsRecursive(coins, value, n - 1));
        else
            return minimumNoOfCoinsRecursive(coins, value, n - 1);
    }

    // Time complexity : O(n * value)
    // Space complexity : O(n * value)
    private static int minimumNoOfCoinsMemoization(int[] coins, int value) {
        int n = coins.length;
        int[][] dp = new int[n + 1][value + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= value; j++)
                dp[i][j] = -1;
        }
        int minCoins = minimumNoOfCoinsMemoization(coins, value, n, dp);
        return minCoins == Integer.MAX_VALUE - 1 ? -1 : minCoins;
    }

    private static int minimumNoOfCoinsMemoization(int[] coins, int value, int n, int[][] dp) {
        if (value == 0)
            return 0;
        if (n == 0)
            return Integer.MAX_VALUE - 1;
        if (dp[n][value] != -1)
            return dp[n][value];
        if (coins[n - 1] <= value)
            dp[n][value] = Math.min(1 + minimumNoOfCoinsMemoization(coins, value - coins[n - 1], n, dp),
                    minimumNoOfCoinsMemoization(coins, value, n - 1, dp));
        else
            dp[n][value] = minimumNoOfCoinsMemoization(coins, value, n - 1, dp);
        return dp[n][value];
    }

    // Time complexity : O(n * value)
    // Space complexity : O(n * value)
    private static int minimumNoOfCoinsIterative(int[] coins, int value) {
        int n = coins.length;
        int[][] dp = new int[n + 1][value + 1];
        for (int j = 1; j <= value; j++) {
            dp[0][j] = Integer.MAX_VALUE - 1;
            // Note: If we remove below condition and initialize i with 1 in the next for loop then also code will work
            if (j % coins[0] == 0)
                dp[1][j] = j / coins[0];
            else
                dp[1][j] = Integer.MAX_VALUE - 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= value; j++) {
                if (coins[i - 1] <= j)
                    dp[i][j] = Math.min(1 + dp[i][j - coins[i - 1]], dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][value] == Integer.MAX_VALUE - 1 ? -1 : dp[n][value];
    }
}
