package com.sde.chandu.queue;

import java.util.*;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        //int[] arr = {1909,1209,1758,1221,1588,1422,1946,1506,1030,1413,1168,1900,1591,1762,1655,1410,1359,1624,1537,1548,1483,1595,1041,1602,1350,1291,1836,1374,1020,1596,1021,1348,1199,1668,1484,1281,1734,1053,1999,1418,1938,1900,1788,1127,1467,1728,1893,1648,1483,1807,1421,1310,1617,1813,1514,1309,1616,1935,1451,1600,1249,1519,1556,1798,1303,1224,1008,1844,1609,1989,1702,1195,1485,1093,1343,1523,1587,1314,1503,1448,1200,1458,1618,1580,1796,1798,1281,1589,1798,1009,1157,1472,1622,1538,1292,1038,1179,1190};
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
            while (!queue.isEmpty() && arr[i] >= arr[queue.peekLast()])
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
