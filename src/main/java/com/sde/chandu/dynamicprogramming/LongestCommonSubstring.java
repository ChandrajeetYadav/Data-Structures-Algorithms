package com.sde.chandu.dynamicprogramming;

public class LongestCommonSubstring {
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        String s1 = "ABCDGH";
        String s2 = "ACDGHR";
        System.out.println("Recursive approach, length of longest common substring: " + longestCommonSubstringRecursive(s1, s2));
        System.out.println("Memoization approach, length of longest common substring: " + longestCommonSubstringMemoization(s1, s2));
        System.out.println("Iterative / Bottom up approach, length of longest common substring: " + longestCommonSubstringBottomUp(s1, s2));
    }

    // Time complexity: O(2 ^ (m + n))
    // Space complexity: O(1), ignoring recursion stack
    private static int longestCommonSubstringRecursive(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
        max = Integer.MIN_VALUE;
        longestCommonSubstringRecursive(s1, s2, s1.length(), s2.length());
        return max;
    }

    private static int longestCommonSubstringRecursive(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        int len = 0;
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            len = 1 + longestCommonSubstringRecursive(s1, s2, m - 1, n - 1);
        max = Math.max(max, len);
        longestCommonSubstringRecursive(s1, s2, m - 1, n);
        longestCommonSubstringRecursive(s1, s2, m, n - 1);
        return len;
    }

    // Time complexity: O(m * n)
    // Space complexity: O(m * n)
    private static int longestCommonSubstringMemoization(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++)
                dp[i][j] = -1;
        }
        max = Integer.MIN_VALUE;
        longestCommonSubstringMemoization(s1, s2, s1.length(), s2.length(), dp);
        return max;
    }

    private static int longestCommonSubstringMemoization(String s1, String s2, int m, int n, int[][] dp) {
        if (m == 0 || n == 0)
            return dp[m][n] = 0;
        if (dp[m][n] != -1)
            return dp[m][n];
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            dp[m][n] = 1 + longestCommonSubstringMemoization(s1, s2, m - 1, n - 1, dp);
        else
            dp[m][n] = 0;
        max = Math.max(max, dp[m][n]);
        longestCommonSubstringMemoization(s1, s2, m - 1, n, dp);
        longestCommonSubstringMemoization(s1, s2, m, n - 1, dp);
        return dp[m][n];
    }

    // Time complexity: O(m * n)
    // Space complexity: O(m * n)
    private static int longestCommonSubstringBottomUp(String s1, String s2) {
        if (s1 == null || s2 == null)
            return 0;
        int m = s1.length();
        int n = s2.length();
        int ans = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
