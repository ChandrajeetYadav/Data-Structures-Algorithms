package com.sde.chandu.dynamicprogramming;

public class MatrixChainMultiplication {

    public static void main(String[] args) {
        int[] arr = {40, 20, 30, 10, 30};
        System.out.println("Recursive approach, minimum number of multiplication: " + matrixMultiplicationRecursive(arr));
        System.out.println("Memoization approach, minimum number of multiplication: " + matrixMultiplicationMemoization(arr));
        System.out.println("Bottom-up / Iterative approach, minimum number of multiplication: " + matrixMultiplicationBottomUp(arr));
    }

    //Time Complexity: O(n * (2 ^ n ))
    //Auxiliary Space: O(n)
    private static int matrixMultiplicationRecursive(int[] arr) {
        if (arr == null || arr.length < 2)
            return 0;
        int n = arr.length;

        return matrixMultiplicationRecursive(arr, 1, n - 1);
    }

    private static int matrixMultiplicationRecursive(int[] arr, int i, int j) {
        if (i >= j)
            return 0;
        int ans = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int count = matrixMultiplicationRecursive(arr, i, k) + matrixMultiplicationRecursive(arr, k + 1, j)
                    + arr[i - 1] * arr[k] * arr[j];
            ans = Math.min(ans, count);
        }
        return ans;
    }

    //Time Complexity: O(n ^ 3 )
    //Auxiliary Space: O(n ^ 2)
    private static int matrixMultiplicationMemoization(int[] arr) {
        if (arr == null || arr.length < 2)
            return 0;
        int n = arr.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++)
                dp[i][j] = -1;
        }
        return matrixMultiplicationMemoization(arr, 1, n - 1, dp);
    }

    private static int matrixMultiplicationMemoization(int[] arr, int i, int j, int[][] dp) {
        if (i >= j)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int count = matrixMultiplicationMemoization(arr, i, k, dp) + matrixMultiplicationMemoization(arr, k + 1, j, dp)
                    + arr[i - 1] * arr[k] * arr[j];
            min = Math.min(min, count);
        }
        return dp[i][j] = min;
    }

    //Time Complexity: O(n ^ 3 )
    //Auxiliary Space: O(n ^ 2)
    private static int matrixMultiplicationBottomUp(int[] arr) {
        if (arr == null || arr.length < 2)
            return 0;
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 1; i < n; i++)
            dp[i][i] = 0;
        int cost;
        for (int l = 2; l < n; l++) {
            for (int i = 1; i < n - l + 1; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    cost = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[1][n - 1];
    }
}
