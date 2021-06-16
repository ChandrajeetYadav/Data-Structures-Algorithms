package com.sde.chandu.string;

public class InterleavedStrings {
    public static void main(String[] args) {
        String a = "XXY", b = "XXZ", c = "XXZXXXY";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingRecursion(a, b, c));
        a = "XY";
        b = "WZ";
        c = "WZXY";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingRecursion(a, b, c));
        a = "XY";
        b = "X";
        c = "XXY";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingRecursion(a, b, c));
        a = "YX";
        b = "X";
        c = "XXY";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingRecursion(a, b, c));
        a = "XXY";
        b = "XXZ";
        c = "XXXXZY";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingRecursion(a, b, c));
        a = "XYYY";
        b = "XXYYXY";
        c = "XYYXXXXXYX";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingRecursion(a, b, c));

        System.out.println();
        a = "XXY";
        b = "XXZ";
        c = "XXZXXXY";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingDP(a, b, c));
        a = "XY";
        b = "WZ";
        c = "WZXY";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingDP(a, b, c));
        a = "XY";
        b = "X";
        c = "XXY";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingDP(a, b, c));
        a = "YX";
        b = "X";
        c = "XXY";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingDP(a, b, c));
        a = "XXY";
        b = "XXZ";
        c = "XXXXZY";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingDP(a, b, c));
        a = "XYYY";
        b = "XXYYXY";
        c = "XYYXXXXXYX";
        System.out.println(c + " is interleaved of " + a + " and " + b + " : " + isInterleavedUsingDP(a, b, c));

    }

    // Note: This method might not work if A and B have some characters in common
    // Time complexity : O(m + n) , m=a.length(), n=b.length()
    // Space complexity : O(1)
    private static boolean isInterleavedBrute(String a, String b, String c) {
        if (a.length() + b.length() != c.length())
            return false;
        int i = 0, j = 0, k = 0;
        while (k < c.length()) {
            if (i < a.length() && a.charAt(i) == c.charAt(k))
                i++;
            else if (j < b.length() && b.charAt(j) == c.charAt(k))
                j++;
            else
                return false;
            k++;
        }
        return true;
    }

    // Time complexity : O(m * n), m=a.length(), n=b.length()
    // Space complexity : O(m * n)
    private static boolean isInterleavedUsingDP(String a, String b, String c) {
        if (a.length() + b.length() != c.length())
            return false;
        boolean[][] il = new boolean[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0 && j == 0)
                    il[i][j] = true;
                else if (i == 0) {
                    if (b.charAt(j - 1) == c.charAt(j - 1))
                        il[i][j] = il[i][j - 1];
                } else if (j == 0) {
                    if (a.charAt(i - 1) == c.charAt(i - 1))
                        il[i][j] = il[i - 1][j];
                } else if (a.charAt(i - 1) == c.charAt(i + j - 1) && b.charAt(j - 1) != c.charAt(i + j - 1))
                    il[i][j] = il[i - 1][j];
                else if (a.charAt(i - 1) != c.charAt(i + j - 1) && b.charAt(j - 1) == c.charAt(i + j - 1))
                    il[i][j] = il[i][j - 1];
                else if (a.charAt(i - 1) == c.charAt(i + j - 1) && b.charAt(j - 1) == c.charAt(i + j - 1))
                    il[i][j] = il[i - 1][j] || il[i][j - 1];
            }
        }
        return il[a.length()][b.length()];
    }

    // Time complexity : O(m + n) , m=a.length(), n=b.length()
    // Space complexity : O(1)
    private static boolean isInterleavedUsingRecursion(String a, String b, String c) {
        if (a.length() + b.length() != c.length())
            return false;
        return isInterleavedUsingRecursionUtil(a, b, c, 0, 0, 0);
    }

    private static boolean isInterleavedUsingRecursionUtil(String a, String b, String c, int i, int j, int k) {
        if (i == a.length() && j == b.length() && k == c.length())
            return true;
        /*if (c.isEmpty())
            return false;*/
        return ((i < a.length() && a.charAt(i) == c.charAt(k) && isInterleavedUsingRecursionUtil(a, b, c, i + 1, j, k + 1))
                || (j < b.length() && b.charAt(j) == c.charAt(k) && isInterleavedUsingRecursionUtil(a, b, c, i, j + 1, k + 1)));
    }
}
