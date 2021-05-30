package com.sde.chandu.string;

public class ImplementStrStr {
    public static void main(String[] args) {
        String s = "GeeksForGeeks";
        String x1 = "Fr"; // O/p: -1
        String x2 = "For"; // O/p: 5

        System.out.println("Brute, " + x1 + " present in " + s + " at: " + isSubstringBrute(s, x1));
        System.out.println("Brute, " + x2 + " present in " + s + " at: " + isSubstringBrute(s, x2));
        System.out.println();

        System.out.println("Efficient, " + x1 + " present in " + s + " at: " + isSubstringEfficient(s, x1));
        System.out.println("Efficient, " + x2 + " present in " + s + " at: " + isSubstringEfficient(s, x2));
    }

    // Time complexity : O( n * m) , n = s.length(), m = pat.length()
    // Space complexity : O(1)
    private static int isSubstringBrute(String s, String pat) {
        if (s == null || pat == null || s.length() < pat.length())
            return -1;
        for (int i = 0, j; i <= s.length() - pat.length(); i++) {
            for (j = 0; j < pat.length(); j++) {
                if (s.charAt(i + j) != pat.charAt(j))
                    break;
            }
            if (j == pat.length())
                return i;
        }
        return -1;
    }

    // Time complexity : O( n ) , n = s.length()
    // Space complexity : O(1)
    private static int isSubstringEfficient(String s, String pat) {
        if (s == null || pat == null || s.length() < pat.length())
            return -1;
        int counter = 0, i;
        for (i = 0; i < s.length(); i++) {
            if (counter == pat.length())
                break;
            if (s.charAt(i) == pat.charAt(counter))
                counter++;
            else
                counter = 0;
        }
        return counter == pat.length() ? i - counter : -1;
    }
}
