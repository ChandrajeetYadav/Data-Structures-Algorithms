package com.sde.chandu.dynamicprogramming;

public class PrintingShortestCommonSupersequence {
    public static void main(String[] args) {
        String s1 = "abac";
        String s2 = "cab";
        System.out.println("Memoization approach, shortest common supersequence: " + printShortestCommonSupersequenceMemoization(s1, s2));
        System.out.println("Bottom-up / Iterative approach, shortest common supersequence: " + printShortestCommonSupersequenceBottomUp(s1, s2));
    }

    // Time complexity: O(m * n)
    // Space complexity: O(m * n)
    private static String printShortestCommonSupersequenceMemoization(String s1, String s2) {
        if (s1 == null || s2 == null)
            return "";
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        lcsMemoization(s1, s2, m, n, dp);
        return getShortestCommonSupersequenceFromDPTable(s1, s2, dp);
    }

    private static int lcsMemoization(String s1, String s2, int m, int n, int[][] dp) {
        if (m == 0 || n == 0)
            return 0;
        if (dp[m][n] != -1)
            return dp[m][n];
        if (s1.charAt(m - 1) == s2.charAt(n - 1))
            return dp[m][n] = 1 + lcsMemoization(s1, s2, m - 1, n - 1, dp);
        else
            return dp[m][n] = Math.max(lcsMemoization(s1, s2, m - 1, n, dp), lcsMemoization(s1, s2, m, n - 1, dp));
    }

    // Time complexity: O(m * n)
    // Space complexity: O(m * n)
    private static String printShortestCommonSupersequenceBottomUp(String s1, String s2) {
        if (s1 == null || s2 == null)
            return "";
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return getShortestCommonSupersequenceFromDPTable(s1, s2, dp);
    }

    private static String getShortestCommonSupersequenceFromDPTable(String s1, String s2, int[][] dp) {
        int i = s1.length(), j = s2.length();
        int k = i + j - dp[i][j];
        char[] scs = new char[k];
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                scs[--k] = s1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                scs[--k] = s1.charAt(i - 1);
                i--;
            } else {
                scs[--k] = s2.charAt(j - 1);
                j--;
            }
        }
        while (i > 0)
            scs[--k] = s1.charAt(--i);
        while (j > 0)
            scs[--k] = s2.charAt(--j);
        return new String(scs);
    }
}
