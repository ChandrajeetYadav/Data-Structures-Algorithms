package com.sde.chandu.string;

public class CompareVersionNumber {
    public static void main(String[] args) {
        String version1 = "1.0.3";
        String version2 = "1.0.7";

        int res = compareVersion(version1, version2);
        displayResult(version1, version2, res);
    }

    // n=v1.length(), m=v2.length()
    // Time complexity : O(n + m)
    // Space complexity : O(1)
    private static int compareVersion(String v1, String v2) {
        int num1 = 0, num2 = 0;
        for (int i = 0, j = 0; i < v1.length() || j < v2.length(); ) {
            while (i < v1.length() && v1.charAt(i) != '.') {
                num1 = num1 * 10 + (v1.charAt(i) - '0');
                i++;
            }
            while (j < v2.length() && v2.charAt(j) != '.') {
                num2 = num2 * 10 + (v2.charAt(j) - '0');
                j++;
            }
            if (num1 > num2)
                return 1;
            else if (num1 < num2)
                return -1;
            else {
                num1 = num2 = 0;
                i++;
                j++;
            }
        }
        return 0;
    }

    private static void displayResult(String v1, String v2, int res) {
        if (res == -1)
            System.out.println(v1 + " is smaller");
        else if (res == 1)
            System.out.println(v2 + " is smaller");
        else
            System.out.println("Both versions are equal");
    }
}
