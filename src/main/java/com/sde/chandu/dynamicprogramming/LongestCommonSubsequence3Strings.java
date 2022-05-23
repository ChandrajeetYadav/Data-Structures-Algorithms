package com.sde.chandu.dynamicprogramming;

public class LongestCommonSubsequence3Strings {
    public static void main(String[] args) {
        String s1 = "geeks";
        String s2 = "geeksfor";
        String s3 = "geeksforgeeks";
        System.out.println("Recursive approach, longest common sub sequence: " + lcsRecursive(s1, s2, s3));
        System.out.println("Memoization approach, longest common sub sequence: " + lcsMemoization(s1, s2, s3));
        System.out.println("Iterative / Bottom up approach, longest common sub sequence: " + lcsBottomUp(s1, s2, s3));
    }

    // Time complexity: O(2 ^ max(x, y, z))
    // Space complexity: O(1), ignoring recursion stack
    private static int lcsRecursive(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null)
            return 0;
        return lcsRecursive(s1, s2, s3, s1.length(), s2.length(), s3.length());
    }

    private static int lcsRecursive(String s1, String s2, String s3, int x, int y, int z) {
        if (x == 0 || y == 0 || z == 0)
            return 0;
        if (s1.charAt(x - 1) == s2.charAt(y - 1) && s1.charAt(x - 1) == s3.charAt(z - 1))
            return 1 + lcsRecursive(s1, s2, s3, x - 1, y - 1, z - 1);
        else
            return Math.max(Math.max(lcsRecursive(s1, s2, s3, x - 1, y, z),
                    lcsRecursive(s1, s2, s3, x, y - 1, z)),
                    lcsRecursive(s1, s2, s3, x, y, z - 1));
    }

    // Time complexity: O(x * y * z)
    // Space complexity: O(x * y * z)
    private static int lcsMemoization(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null)
            return 0;
        int x = s1.length();
        int y = s2.length();
        int z = s3.length();
        int[][][] dp = new int[x + 1][y + 1][z + 1];
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                for (int k = 0; k <= z; k++)
                    dp[i][j][k] = -1;
            }
        }
        return lcsMemoization(s1, s2, s3, s1.length(), s2.length(), s3.length(), dp);
    }

    private static int lcsMemoization(String s1, String s2, String s3, int x, int y, int z, int[][][] dp) {
        if (x == 0 || y == 0 || z == 0)
            return 0;
        if (dp[x][y][z] != -1)
            return dp[x][y][z];
        if (s1.charAt(x - 1) == s2.charAt(y - 1) && s1.charAt(x - 1) == s3.charAt(z - 1))
            dp[x][y][z] = 1 + lcsMemoization(s1, s2, s3, x - 1, y - 1, z - 1, dp);
        else
            dp[x][y][z] = Math.max(Math.max(lcsMemoization(s1, s2, s3, x - 1, y, z, dp),
                    lcsMemoization(s1, s2, s3, x, y - 1, z, dp)),
                    lcsMemoization(s1, s2, s3, x, y, z - 1, dp));
        return dp[x][y][z];
    }

    // Time complexity: O(x * y * z)
    // Space complexity: O(x * y * z)
    private static int lcsBottomUp(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null)
            return 0;
        int x = s1.length();
        int y = s2.length();
        int z = s3.length();

        int[][][] dp = new int[x + 1][y + 1][z + 1];
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                for (int k = 1; k <= z; k++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1) && s1.charAt(i - 1) == s3.charAt(k - 1))
                        dp[i][j][k] = 1 + dp[i - 1][j - 1][k - 1];
                    else
                        dp[i][j][k] = Math.max(Math.max(dp[i - 1][j][k], dp[i][j - 1][k]), dp[i][j][k - 1]);
                }
            }
        }
        return dp[x][y][z];
    }
}
