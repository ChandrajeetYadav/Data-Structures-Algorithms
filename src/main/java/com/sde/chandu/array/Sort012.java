package com.sde.chandu.array;

public class Sort012 {
    public static void main(String[] args) {
        int[] arr = {0, 2, 1, 2, 0};
        System.out.println("Original array: ");
        printArray(arr);
        sortUsingCount(arr);
        System.out.println("Counting approach, Sorted array:");
        printArray(arr);
        int[] arr1 = {1, 2, 0};
        System.out.println("Original array: ");
        printArray(arr1);
        sortEfficient(arr1);
        System.out.println("Efficient approach, Sorted array:");
        printArray(arr1);
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static void sortUsingCount(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        int zeroCount = 0, oneCount = 0;
        for (int i : arr) {
            if (i == 0)
                zeroCount++;
            else if (i == 1)
                oneCount++;
        }
        int i = 0;
        while (zeroCount-- > 0)
            arr[i++] = 0;
        while (oneCount-- > 0)
            arr[i++] = 1;
        while (i < arr.length)
            arr[i++] = 2;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static void sortEfficient(int[] arr) {
        int low = 0, high = arr.length - 1, mid = 0;
        while (mid <= high) {
            switch (arr[mid]) {
                case 0:
                    swap(arr, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(arr, mid, high);
                    high--;
                    break;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArray(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }
}
