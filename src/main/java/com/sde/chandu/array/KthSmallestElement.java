package com.sde.chandu.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestElement {
    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 30, 15};
        int k = 3;
        System.out.println("Priority Queue approach, kth smallest: " + kthSmallestUsingPriorityQueue(arr, k));
        System.out.println("Sorting approach, kth smallest: " + kthSmallestUsingSorting(arr, k));
        int[] arr1 = {7, 10, 4, 20, 15};
        k = 4;
        System.out.println("Randomized quick sort approach, kth smallest: " + kthSmallestUsingRandomizedQuickSort(arr1, 0, arr1.length - 1, k));
    }

    // Time complexity: O(n log n)
    // Space complexity: O(1)
    private static int kthSmallestUsingSorting(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k - 1];
    }

    // Time complexity: O(n * log k)
    // Space complexity: O(k)
    private static int kthSmallestUsingPriorityQueue(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < k; i++)
            pq.add(arr[i]);
        for (int i = k; i < arr.length; i++) {
            if (pq.peek() > arr[i]) {
                pq.poll();
                pq.add(arr[i]);
            }
        }
        return pq.peek();
    }

    // Time complexity: O(n * log n)
    // Space complexity: O(1)
    //The worst case time complexity of the above solution is still O(n2). In the worst case, the randomized function
    // may always pick a corner element. The expected time complexity of above randomized QuickSelect is O(n)
    private static int kthSmallestUsingRandomizedQuickSort(int[] arr, int l, int r, int k) {
        if (k > 0 && k <= r - l + 1) {
            int pos = randomPartition(arr, l, r);
            if (pos - l == k - 1)
                return arr[pos];
            if (pos - l > k - 1)
                return kthSmallestUsingRandomizedQuickSort(arr, l, pos - 1, k);
            return kthSmallestUsingRandomizedQuickSort(arr, pos + 1, r, k - pos + l - 1);
        }
        return Integer.MAX_VALUE;
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static int randomPartition(int[] arr, int l, int r) {
        int n = r - l + 1;
        int pivot = (int) (Math.random() * (n - 1));
        swap(arr, l + pivot, r);
        return partition(arr, l, r);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
