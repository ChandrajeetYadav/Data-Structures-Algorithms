package com.sde.chandu.dynamicprogramming;

public class BooleanParenthesization {
    public static void main(String[] args) {
        String s = "T^F&T";
        System.out.println("Recursive approach, number of ways to evaluate true: " + countWaysRecursive(s));
        System.out.println("Memoization approach, number of ways to evaluate true: " + countWaysMemoization(s));
    }

    //Time Complexity: O(n * (4 ^ n ))
    //Auxiliary Space: O(n)
    private static int countWaysRecursive(String s) {
        if (s == null)
            return 0;
        return countWaysRecursive(s, 0, s.length() - 1, true);
    }

    private static int countWaysRecursive(String s, int i, int j, boolean isTrue) {
        if (i > j)
            return 0;
        if (i == j) {
            if (isTrue)
                return s.charAt(i) == 'T' ? 1 : 0;
            else
                return s.charAt(i) == 'F' ? 1 : 0;
        }
        int ans = 0;
        for (int k = i + 1; k < j; k = k + 2) {
            int leftTrue = countWaysRecursive(s, i, k - 1, true);
            int leftFalse = countWaysRecursive(s, i, k - 1, false);
            int rightTrue = countWaysRecursive(s, k + 1, j, true);
            int rightFalse = countWaysRecursive(s, k + 1, j, false);

            if (s.charAt(k) == '^') {
                if (isTrue)
                    ans += leftTrue * rightFalse + leftFalse * rightTrue;
                else
                    ans += leftTrue * rightTrue + leftFalse * rightFalse;
            } else if (s.charAt(k) == '&') {
                if (isTrue)
                    ans += leftTrue * rightTrue;
                else
                    ans += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
            } else if (s.charAt(k) == '|') {
                if (isTrue)
                    ans += leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
                else
                    ans += leftFalse * rightFalse;
            }
        }
        return ans;
    }

    // Time Complexity: O(n ^ 3)
    // Space Complexity: O(n ^ 2)
    private static int countWaysMemoization(String s) {
        if (s == null)
            return 0;
        int n = s.length();
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return countWaysMemoization(s, 0, n - 1, true, dp);
    }

    private static int countWaysMemoization(String s, int i, int j, boolean isTrue, int[][][] dp) {
        if (i > j)
            return 0;
        if (i == j) {
            if (isTrue)
                return s.charAt(i) == 'T' ? 1 : 0;
            else
                return s.charAt(i) == 'F' ? 1 : 0;
        }
        int k = isTrue ? 1 : 0;
        if (dp[i][j][k] != -1)
            return dp[i][j][k];
        int ans = 0;
        int leftTrue, leftFalse, rightTrue, rightFalse;
        for (k = i + 1; k < j; k = k + 2) {
            leftTrue = dp[i][k - 1][1] != -1 ? dp[i][k - 1][1] : countWaysMemoization(s, i, k - 1, true, dp);
            leftFalse = dp[i][k - 1][0] != -1 ? dp[i][k - 1][0] : countWaysMemoization(s, i, k - 1, false, dp);
            rightTrue = dp[k + 1][j][1] != -1 ? dp[k + 1][j][1] : countWaysMemoization(s, k + 1, j, true, dp);
            rightFalse = dp[k + 1][j][0] != -1 ? dp[k + 1][j][0] : countWaysMemoization(s, k + 1, j, false, dp);

            if (s.charAt(k) == '^') {
                if (isTrue)
                    ans += leftTrue * rightFalse + leftFalse * rightTrue;
                else
                    ans += leftTrue * rightTrue + leftFalse * rightFalse;
            } else if (s.charAt(k) == '&') {
                if (isTrue)
                    ans += leftTrue * rightTrue;
                else
                    ans += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
            } else if (s.charAt(k) == '|') {
                if (isTrue)
                    ans += leftTrue * rightFalse + leftFalse * rightTrue + leftTrue * rightTrue;
                else
                    ans += leftFalse * rightFalse;
            }
        }
        k = isTrue ? 1 : 0;
        return dp[i][j][k] = ans % 1003; // ans % 1003 as result might get big
    }
}
