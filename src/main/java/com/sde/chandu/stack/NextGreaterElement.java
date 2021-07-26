package com.sde.chandu.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class NextGreaterElement {
    public static void main(String[] args) {
        int[] arr = {11, 13, 21, 3};
        System.out.println("Next greater element, brute force approach: ");
        nextGreaterBrute(arr);
        System.out.println();
        System.out.println("Next greater element, using stack: ");
        nextGreaterUsingStack(arr);
    }

    // Time complexity : O(n^2)
    // Space complexity : O(1)
    private static void nextGreaterBrute(int[] arr) {
        int nextGreater;
        for (int i = 0; i < arr.length; i++) {
            nextGreater = -1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    nextGreater = arr[j];
                    break;
                }
            }
            System.out.println("Next greater of " + arr[i] + ": " + nextGreater);
        }
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static void nextGreaterUsingStack(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] nextGreaterElement = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i])
                stack.pop();
            nextGreaterElement[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        for (int i = 0; i < arr.length; i++)
            System.out.println("Next greater of " + arr[i] + ": " + nextGreaterElement[i]);
    }
}
