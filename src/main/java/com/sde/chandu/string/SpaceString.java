package com.sde.chandu.string;

import java.util.ArrayList;
import java.util.List;

public class SpaceString {
    public static void main(String[] args) {
        String s = "abc";
        List<String> list1 = spaceStringRecursion(s);
        System.out.println("List of possible Strings, recursion:");
        list1.forEach(n -> System.out.println(n));

        List<String> list2 = spaceStringRecursion2(s);
        System.out.println("List of possible Strings, recursion 2:");
        list2.forEach(n -> System.out.println(n));
    }

    // Time complexity : O(n * (2^n))
    // Space complexity : O(n)
    private static List<String> spaceStringRecursion2(String s) {
        List<String> list = new ArrayList<>();
        if (s.length() == 1) {
            list.add(s);
            return list;
        }
        List<String> temp = spaceStringRecursion2(s.substring(1));
        for (int i = 0; i < temp.size(); i++) {
            list.add(s.charAt(0) + temp.get(i));
            list.add(s.charAt(0) + " " + temp.get(i));
        }
        return list;
    }

    // Time complexity : O(n * (2^n))
    // Space complexity : O(n)
    private static List<String> spaceStringRecursion(String str) {
        ArrayList<String> list = new ArrayList<>();
        int length = str.length();
        String buf = "";
        buf = buf + str.charAt(0);
        spaceStringRecursion(str, buf, 1, 1, length, list);
        return list;
    }

    private static void spaceStringRecursion(String str, String buf, int i, int j, int length, List<String> list) {
        if (i == length) {
            list.add(buf);
            return;
        }
        buf = buf + str.charAt(i);
        spaceStringRecursion(str, buf, i + 1, j + 1, length, list);

        buf = buf.substring(0, j) + " " + str.charAt(i);
        spaceStringRecursion(str, buf, i + 1, j + 2, length, list);
    }
}
