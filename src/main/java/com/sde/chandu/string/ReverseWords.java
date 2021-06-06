package com.sde.chandu.string;

import java.util.Stack;

public class ReverseWords {
    public static void main(String[] args) {
        String s = "i.like.this.program.very.much"; // O/p: much.very.program.this.like.i
        System.out.println("Approach 1, string after reversing words:  " + reverseWordsApproach1(s));
        System.out.println("Using split method, string after reversing words:  " + reverseWordsUsingSplit(s));
        System.out.println("Using stack, string after reversing words:  " + reverseWordsUsingStack(s));
        System.out.println("Without extra space, string after reversing words:  " + reverseWordsWithoutExtraSpace(s));
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static String reverseWordsApproach1(String s) {
        if (s == null || s.length() <= 1)
            return s;
        char[] arr = s.toCharArray();
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            if (arr[end] == '.') {
                reverse(arr, start, end - 1);
                start = end + 1;
            }
        }
        reverse(arr, start, s.length() - 1);
        reverse(arr, 0, s.length() - 1);
        return new String(arr);
    }

    private static void reverse(char[] arr, int start, int end) {
        char temp;
        while (start <= end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static String reverseWordsUsingSplit(String s) {
        if (s == null || s.length() <= 1)
            return s;
        String[] arr = s.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--)
            sb.append(arr[i]).append(".");
        return sb.substring(0, sb.length() - 1);
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static String reverseWordsUsingStack(String s) {
        if (s == null || s.length() <= 1)
            return s;
        String[] arr = s.split("\\.");
        Stack<String> stack = new Stack<>();
        for (String obj : arr)
            stack.push(obj);
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(".");
        return sb.substring(0, s.length() - 1);
    }

    // Time complexity : O(n)
    // Space complexity : O(n) // for splitting
    private static String reverseWordsWithoutExtraSpace(String s) {
        if (s == null || s.length() <= 1)
            return s;
        String[] arr = s.split("\\.");

        int mid = arr.length % 2 == 0 ? arr.length / 2 : arr.length / 2 + 1;
        String temp;
        for (int j = mid; j <= arr.length - 1; j++) {
            temp = arr[arr.length - j - 1];
            arr[arr.length - j - 1] = arr[j];
            arr[j] = temp;
        }
        return String.join(".", arr);
    }
}
