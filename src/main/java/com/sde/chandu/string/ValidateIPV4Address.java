package com.sde.chandu.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateIPV4Address {
    public static void main(String[] args) {
        String s1 = "128.0.0.1";        // O/p: true
        String s2 = "125.16.100.1";     // O/p: true
        String s3 = "125.512.100.1";    // O/p: false
        String s4 = "125.212.100.abc";  // O/p: false
        String s5 = "125.212.100.1.";   // O/p: false
        String s6 = "000.000.000.000";  // O/p: false

        System.out.println(s1 + " is valid IP4 address: " + isValidIp(s1));
        System.out.println(s2 + " is valid IP4 address: " + isValidIp(s2));
        System.out.println(s3 + " is valid IP4 address: " + isValidIp(s3));
        System.out.println(s4 + " is valid IP4 address: " + isValidIp(s4));
        System.out.println(s5 + " is valid IP4 address: " + isValidIp(s5));
        System.out.println(s6 + " is valid IP4 address: " + isValidIp(s6));
        System.out.println();

        System.out.println(s1 + " is valid IP4 address, using regex: " + isValidIpUsingRegex(s1));
        System.out.println(s2 + " is valid IP4 address, using regex: " + isValidIpUsingRegex(s2));
        System.out.println(s3 + " is valid IP4 address, using regex: " + isValidIpUsingRegex(s3));
        System.out.println(s4 + " is valid IP4 address, using regex: " + isValidIpUsingRegex(s4));
        System.out.println(s5 + " is valid IP4 address, using regex: " + isValidIpUsingRegex(s5));
        System.out.println(s6 + " is valid IP4 address, using regex: " + isValidIpUsingRegex(s6));
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static boolean isValidIp(String s) {
        if (s == null || s.charAt(0) == '.' || s.charAt(s.length() - 1) == '.')
            return false;
        String[] arr = s.split("\\.");
        if (arr.length != 4)
            return false;
        for (int i = 0, num; i < arr.length; i++) {
            if (arr[i].length() > 1 && arr[i].charAt(0) == '0')
                return false;
            try {
                num = Integer.parseInt(arr[i]);
            } catch (NumberFormatException e) {
                return false;
            }
            if (num < 0 || num > 255)
                return false;
        }
        return true;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static boolean isValidIpUsingRegex(String s) {
        final String IPV4_PATTERN =
                "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";
        Pattern pattern = Pattern.compile(IPV4_PATTERN);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
