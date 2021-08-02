package com.sde.chandu.queue;

import java.util.*;

public class GenerateBinaryNumbers {
    public static void main(String[] args) {
        int n = 10;
        List<String> res = generateBinaryNumbersUsingQueue(n);
        System.out.println("Binary numbers from 1 to " + n + ", using queue:");
        System.out.println(res);

        res = generateBinaryNumbersUsingStack(n);
        System.out.println("Binary numbers from 1 to " + n + ", using stack:");
        System.out.println(res);

        res = generateBinaryNumbersUsingInBuiltMethod(n);
        System.out.println("Binary numbers from 1 to " + n + ", using in built method:");
        System.out.println(res);
    }

    // Time complexity : O(n)
    // Space complexity : O(n), for storing result
    private static List<String> generateBinaryNumbersUsingInBuiltMethod(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            list.add(Integer.toBinaryString(i));
        return list;
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static List<String> generateBinaryNumbersUsingQueue(int n) {
        List<String> list = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        String s1, s2;
        while (n-- > 0) {
            s1 = queue.remove();
            list.add(s1);
            queue.add(s1 + "0");
            queue.add(s1 + "1");
        }
        return list;
    }

    // Time complexity : O(n log n)
    // Space complexity : O(n log n)
    private static List<String> generateBinaryNumbersUsingStack(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            list.add(convertIntToBinary(i));
        return list;
    }

    private static String convertIntToBinary(int n) {
        Stack<Integer> stack = new Stack<>();
        while (n > 0) {
            stack.add(n % 2);
            n /= 2;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        return sb.toString();
    }
}
