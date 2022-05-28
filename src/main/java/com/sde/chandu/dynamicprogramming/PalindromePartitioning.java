package com.sde.chandu.dynamicprogramming;

import java.util.Arrays;

public class PalindromePartitioning {
    public static void main(String[] args) {
        String s = "ababbbabbababa";
        System.out.println("Recursive approach, minimum partition: " + palindromePartitionRecursive(s));
        System.out.println("Memoization approach, minimum partition: " + palindromePartitionMemoization(s));
        System.out.println("Memoization efficient approach, minimum partition: " + palindromePartitionMemoizationEfficient(s));
    }

    //Time Complexity: O(n * (2 ^ n ))
    //Auxiliary Space: O(n)
    private static int palindromePartitionRecursive(String s) {
        if (s == null)
            return 0;
        return palindromePartitionRecursive(s, 0, s.length() - 1);
    }

    private static int palindromePartitionRecursive(String s, int i, int j) {
        if (i >= j || isPalindrome(s, i, j))
            return 0;
        int min = Integer.MAX_VALUE;
        int partition;
        for (int k = i; k < j; k++) {
            partition = 1 + palindromePartitionRecursive(s, i, k) + palindromePartitionRecursive(s, k + 1, j);
            min = Math.min(min, partition);
        }
        return min;
    }

    // Time complexity: O(n ^ 3)
    // Space complexity: O(n ^ 2)
    private static int palindromePartitionMemoization(String s) {
        if (s == null)
            return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
        return palindromePartitionMemoization(s, 0, s.length() - 1, dp);
    }

    private static int palindromePartitionMemoization(String s, int i, int j, int[][] dp) {
        if (i >= j || isPalindrome(s, i, j))
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int min = Integer.MAX_VALUE;
        int partition;
        for (int k = i; k < j; k++) {
            partition = 1 + palindromePartitionMemoization(s, i, k, dp) + palindromePartitionMemoization(s, k + 1, j, dp);
            min = Math.min(min, partition);
        }
        return dp[i][j] = min;
    }

    // Time complexity: O(n ^ 2)
    // Space complexity: O(n ^ 2)
    private static int palindromePartitionMemoizationEfficient(String s) {
        if (s == null)
            return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
        return palindromePartitionMemoizationEfficient(s, 0, s.length() - 1, dp);
    }

    private static int palindromePartitionMemoizationEfficient(String s, int i, int j, int[][] dp) {
        if (i >= j)
            return 0;
        if (isPalindrome(s, i, j))
            return dp[i][j] = 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int min = Integer.MAX_VALUE;
        int partition;
        for (int k = i; k < j; k++) {
            // The idea is to check on every prefix of the string, whether the prefix is a palindrome.
            // If it is, consider it a cut and move towards the next substring
            // Calculate all such cuts and return the minimum of all as required.
            if (isPalindrome(s, i, k)) {
                partition = 1 + palindromePartitionMemoizationEfficient(s, k + 1, j, dp);
                min = Math.min(min, partition);
            }
        }
        return dp[i][j] = min;
    }

    private static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
