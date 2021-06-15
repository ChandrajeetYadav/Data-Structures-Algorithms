package com.sde.chandu.string;

public class ExcelColumnNumberFromColumnTitle {
    public static void main(String[] args) {
        String s = "CDA"; // 2133
        System.out.println("Column number for " + s + " is : " + getColumnNumber(s));
    }

    // Time complexity : O(n) , n = s.length()
    // Space complexity : O(1)
    private static int getColumnNumber(String s) {
        if (s == null || s.length() == 0)
            return -1;
        int result = 0;
        for (int i = 0; i < s.length(); i++)
            result = result * 26 + s.charAt(i) - 'A' + 1;
        return result;
    }
}
