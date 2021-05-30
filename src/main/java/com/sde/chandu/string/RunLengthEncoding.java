package com.sde.chandu.string;

public class RunLengthEncoding {
    public static void main(String[] args) {
        String s = "wwwwaaadexxxxxx"; // O/p: w4a3d1e1x6
        System.out.println(s + " after run length encoding: " + encode(s));
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static String encode(String s) {
        StringBuilder sb = new StringBuilder();
        int count;
        for (int i = 0; i < s.length(); i++) {
            count = 1;
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                count++;
                i++;
            }
            sb.append(s.charAt(i)).append(count);
        }
        return sb.toString();
    }
}
