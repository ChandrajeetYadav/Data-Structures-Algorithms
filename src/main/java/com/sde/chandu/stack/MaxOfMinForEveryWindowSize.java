package com.sde.chandu.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxOfMinForEveryWindowSize {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 50, 10, 70, 30};
        System.out.println("Original array: ");
        display(arr);
        System.out.println("Max of min for every window size, brute approach:");
        findMaxOfMinBrute(arr);
        System.out.println();

        System.out.println("Max of min for every window size, efficient approach:");
        findMaxOfMinEfficient(arr);
    }

    // Time complexity : O(n^3)
    // Space complexity : O(1)
    private static void findMaxOfMinBrute(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        int maxOfMin, min;
        for (int k = 1; k <= arr.length; k++) {
            maxOfMin = Integer.MIN_VALUE;
            for (int i = 0; i <= arr.length - k; i++) {
                min = arr[i];
                for (int j = 1; j < k; j++) {
                    if (arr[i + j] < min)
                        min = arr[i + j];
                }
                maxOfMin = Math.max(maxOfMin, min);
            }
            System.out.print(maxOfMin + "\t");
        }
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static void findMaxOfMinEfficient(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        int len;
        int[] left = new int[arr.length + 1];
        int[] right = new int[arr.length + 1];
        int[] ans = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            left[i] = -1;
            right[i] = arr.length;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i])
                stack.pop();
            if (!stack.isEmpty())
                left[i] = stack.peek();
            stack.push(i);
        }
        stack.clear();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i])
                stack.pop();
            if (!stack.isEmpty())
                right[i] = stack.peek();
            stack.push(i);
        }
        for (int i = 0; i < arr.length; i++) {
            len = right[i] - left[i] - 1;
            ans[len] = Math.max(ans[len], arr[i]);
        }
        for (int i = arr.length - 1; i >= 1; i--)
            ans[i] = Math.max(ans[i], ans[i + 1]);
        for (int i = 1; i <= arr.length; i++)
            System.out.print(ans[i] + "\t");
    }

    private static void display(int[] arr) {
        for (int i : arr)
            System.out.print(i + "\t");
        System.out.println();
    }
}
