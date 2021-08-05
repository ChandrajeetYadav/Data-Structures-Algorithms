package com.sde.chandu.queue;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int k = 3;
        System.out.println("Max when window size is: " + k + ", using brute approach");
        maxBrute(arr, k);
        System.out.println("Max when window size is: " + k + ", using max heap");
        maxUsingMaxHeap(arr, k);
        System.out.println("Max when window size is: " + k + ", using queue");
        maxUsingQueue(arr, k);
    }

    // Time complexity : O((n-k+1)k) = O(n * k)
    // Space complexity : O(1)
    private static void maxBrute(int[] arr, int k) {
        if (arr == null || arr.length == 0)
            return;
        int max;
        for (int i = 0; i <= arr.length - k; i++) {
            max = arr[i];
            for (int j = 1; j < k; j++)
                max = Math.max(max, arr[i + j]);
            System.out.print(max + "\t");
        }
        System.out.println();
    }

    // Time complexity : O(n log k)
    // Space complexity : O(k)
    private static void maxUsingMaxHeap(int[] arr, int k) {
        if (arr == null || arr.length == 0)
            return;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int i;
        for (i = 0; i < k; i++)
            pq.add(arr[i]);
        System.out.print(pq.peek() + "\t");
        pq.remove(arr[0]);
        for (; i < arr.length; i++) {
            pq.add(arr[i]);
            System.out.print(pq.peek() + "\t");
            pq.remove(arr[i - k + 1]);
        }
        System.out.println();
    }

    // Time complexity : O(n), It seems more than O(n) at first look. It can be observed that
    // every element of array is added and removed at most once. So there are total 2n operations.
    // Space complexity : O(k)
    private static void maxUsingQueue(int[] arr, int k) {
        if (arr == null || arr.length == 0)
            return;
        Deque<Integer> queue = new LinkedList<>();
        int i;
        for (i = 0; i < k; i++) {
            while (!queue.isEmpty() && arr[i] >= queue.peekLast())
                queue.removeLast();
            queue.addLast(i);
        }
        for (; i < arr.length; i++) {
            System.out.print(arr[queue.peek()] + "\t");
            while (!queue.isEmpty() && queue.peek() <= i - k)
                queue.poll();
            while (!queue.isEmpty() && arr[i] >= arr[queue.peekLast()])
                queue.removeLast();
            queue.addLast(i);
        }
        System.out.println(arr[queue.peek()] + "\t");
    }
}
