package com.sde.chandu.string;

public class CheckIfStringIsRotatedBy2Places {
    public static void main(String[] args) {
        String s1 = "amazon";
        String s2 = "azonam";

        String s3 = "geeksforgeeks";
        String s4 = "geeksgeeksfor";

        System.out.println(s1 + " and " + s2 + " are rotated: " + isRotated(s1, s2));
        System.out.println(s3 + " and " + s4 + " are rotated: " + isRotated(s3, s4));
    }

    private static boolean isRotated(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        if (s1.length() < 2)
            return s1.equals(s2);

        int len = s2.length();
        String antiClockRot = s2.substring(len - 2) + s2.substring(0, len - 2);
        String clockRot = s2.substring(2) + s2.substring(0, 2);
        return s1.equals(antiClockRot) || s1.equals(clockRot);
    }
}
