package com.sde.chandu.string;

public class RomanNumberToInteger {
    public static void main(String[] args) {
        String s = "MCMIV"; // O/p: 1904
        System.out.println(s + " integer representation: " + romanToInteger(s));
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static int romanToInteger(String s) {
        int res = 0, num1, num2;
        for (int i = 0; i < s.length(); i++) {
            num1 = getNumberValue(s.charAt(i));
            if (i + 1 < s.length()) {
                num2 = getNumberValue(s.charAt(i + 1));
                if (num1 >= num2)
                    res += num1;
                else {
                    res = res + num2 - num1;
                    i++;
                }
            } else
                res += num1;
        }
        return res;
    }

    private static int getNumberValue(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return -1;
        }
    }
}
