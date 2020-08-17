package com.chandu.dsa.heap;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class MaximumSlidingWindow {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int k1 = 3;
        int[] arr2 = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
        int k2 = 4;
        /*printKMaximumBrute(arr1, k1);
        printKMaximumBrute(arr2, k2);*/

        /*printKMaximumUsingDeque(arr1, k1);
        printKMaximumUsingDeque(arr2, k2);*/

        printKMaximumUsingMaxHeap(arr1, k1);
        printKMaximumUsingMaxHeap(arr2, k2);
    }

    //Time Complexity: O(n * k)
    //Space Complexity: O(1)
    public static void printKMaximumBrute(int[] arr, int k){
        int max;
        for (int i=0; i<=arr.length-k; i++){
            max = arr[i];
            for(int j=1; j<k; j++){
                if(arr[i+j] > max)
                    max = arr[i+j];
            }
            System.out.print(max + " ");
        }
        System.out.println();
    }

    //Time Complexity: O(n)
    //Space Complexity: O(k)
    public static void printKMaximumUsingDeque(int[] arr, int k){
        Deque<Integer> deque = new LinkedList<>();
        int i = 0;
        for (; i<k; i++){
            while (!deque.isEmpty() && arr[i]>=arr[deque.peekLast()])
                deque.removeLast();
            deque.addLast(i);
        }
        for(; i<arr.length; i++){
            System.out.print(arr[deque.peekFirst()] + " ");
            while (!deque.isEmpty() && deque.peek()<=i-k)
                deque.removeFirst();
            while (!deque.isEmpty() && arr[i]>=arr[deque.peekLast()])
                deque.removeLast();
            deque.addLast(i);
        }
        System.out.println(arr[deque.peek()]);
    }

    //Time Complexity: O(n log k)
    //Space Complexity: O(k)
    public static void printKMaximumUsingMaxHeap(int[] arr, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Collections.reverseOrder());
        int i;
        for(i=0; i<k; i++)
            pq.add(arr[i]);
        for(; i<arr.length; i++){
            System.out.print(pq.peek() + " ");
            pq.remove(arr[i-k]);
            pq.add(arr[i]);
        }
        System.out.println(pq.peek() + " ");
    }
}
