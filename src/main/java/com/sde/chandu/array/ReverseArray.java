package com.sde.chandu.array;

public class ReverseArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println("Original array:");
        printArray(arr);
        reverseIterative(arr);
        System.out.println("Array after reversing using iterative approach:");
        printArray(arr);
        reverseRecursive(arr);
        System.out.println("Array after reversing using recursive approach:");
        printArray(arr);
    }

    // Time complexity: O(n)
    // Space complexity: O(1)
    private static void reverseIterative(int[] arr) {
        if (arr == null || arr.length <= 1)
            return;
        int start = 0, end = arr.length - 1;
        int temp;
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Time complexity: O(n)
    // Space complexity: O(n/2) = O(n)
    // Space complexity: O(1) if we ignore recursion stack
    private static void reverseRecursive(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        reverseRecursiveHelper(arr, 0, arr.length - 1);
    }

    private static void reverseRecursiveHelper(int[] arr, int start, int end) {
        if (start >= end)
            return;
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseRecursiveHelper(arr, start + 1, end - 1);
    }

    private static void printArray(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }
}
