package com.sde.chandu.string;

public class Atoi {
    public static void main(String[] args) {
        String s1 = "123"; // O/p : 123
        String s2 = "21a"; // O/p : -1
        String s3 = "-456"; // O/p : -456

        System.out.println(s1 + " numeric value: " + atoi(s1));
        System.out.println(s2 + " numeric value: " + atoi(s2));
        System.out.println(s3 + " numeric value: " + atoi(s3));
    }

    // Time complexity : O( n ) , n = s.length()
    // Space complexity : O(1)
    private static int atoi(String s) {
        if (s == null || s.length() == 0)
            return -1;
        int sign = 1, i = 0, res = 0;
        if (s.charAt(i) == '-') {
            sign = -1;
            i++;
        }

        char ch;
        for (; i < s.length(); i++) {
            ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                res = res * 10 + ch - '0';
            } else
                return -1;
        }
        return res * sign;
    }
}
