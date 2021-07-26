package com.sde.chandu.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class StockSpanProblem {
    public static void main(String[] args) {
        int[] stockPrice = {100, 80, 60, 70, 60, 75, 85};
        System.out.println("Original stock price: ");
        display(stockPrice);
        System.out.println("Stock span, using brute force approach:");
        int[] span = calculateSpanBrute(stockPrice);
        display(span);
        System.out.println("Stock span, using stack:");
        span = calculateSpanUsingStack(stockPrice);
        display(span);
        System.out.println("Stock span, without using stack:");
        span = calculateSpanWithoutExtraSpace(stockPrice);
        display(span);
    }

    // Time complexity : O(n^2)
    // Space complexity : O(1)
    private static int[] calculateSpanBrute(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        int[] span = new int[arr.length];
        span[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            span[i] = 1;
            for (int j = i - 1; j >= 0 && arr[i] >= arr[j]; j--)
                span[i]++;
        }
        return span;
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static int[] calculateSpanUsingStack(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        int[] span = new int[arr.length];
        span[0] = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i])
                stack.pop();
            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        return span;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static int[] calculateSpanWithoutExtraSpace(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        int[] span = new int[arr.length];
        span[0] = 1;
        int counter;
        for (int i = 1; i < arr.length; i++) {
            counter = 1;
            while ((i - counter) >= 0 && arr[i] >= arr[i - counter])
                counter += span[i - counter];
            span[i] = counter;
        }
        return span;
    }

    private static void display(int[] arr) {
        for (int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
