package com.sde.chandu.linkedlist;

import java.util.PriorityQueue;

public class FlatteningALinkedList {
    public static void main(String[] args) {
        FlattenedList head = createFlattenedList();
        head = flattenList(head);
        System.out.println("Flattened list is, using merging 2 sorted lists approach: ");
        displayFlattenedList(head);
        System.out.println();

        head = createFlattenedList();
        head = flattenListUsingPriorityQueue(head);
        System.out.println("Flattened list is, using priority queue: ");
        displayFlattenedList(head);
        System.out.println();
    }

    // Time complexity : O(m * n), m = number of sublists, n=number of nodes in each sublist
    // Space commplexity : O(1)
    private static FlattenedList flattenList(FlattenedList head) {
        if (head == null || head.next == null)
            return head;
        return merge(head, flattenList(head.next));
        //return mergeRecursive(head, flattenList(head.next));
    }

    // Time complexity : O(m * n * log m)
    // Space complexity : O(m)
    private static FlattenedList flattenListUsingPriorityQueue(FlattenedList head) {
        if (head == null || head.next == null)
            return head;
        PriorityQueue<FlattenedList> pq = new PriorityQueue<>((FlattenedList a, FlattenedList b) -> a.data - b.data);
        while (head != null) {
            pq.add(head);
            head = head.next;
        }
        FlattenedList result = null, tail = null;
        if (!pq.isEmpty()) {
            result = pq.poll();
            tail = result;
            if (result.bottom != null)
                pq.add(result.bottom);
            result.next = null;
        }
        while (!pq.isEmpty()) {
            tail.bottom = pq.poll();
            tail = tail.bottom;
            if (tail.bottom != null)
                pq.add(tail.bottom);
        }
        return result;
    }

    private static FlattenedList merge(FlattenedList a, FlattenedList b) {
        FlattenedList dummy = new FlattenedList(0);
        FlattenedList tail = dummy;
        while (true) {
            if (a == null) {
                tail.bottom = b;
                break;
            }
            if (b == null) {
                tail.bottom = a;
                break;
            }
            if (a.data < b.data) {
                tail.bottom = a;
                a = a.bottom;
            } else {
                tail.bottom = b;
                b = b.bottom;
            }
            tail = tail.bottom;
        }
        if (dummy.bottom != null)
            dummy.bottom.next = null;
        return dummy.bottom;
    }

    private static FlattenedList mergeRecursive(FlattenedList a, FlattenedList b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        FlattenedList result;
        if (a.data < b.data) {
            result = a;
            result.bottom = mergeRecursive(a.bottom, b);
        } else {
            result = b;
            result.bottom = mergeRecursive(a, b.bottom);
        }
        result.next = null;
        return result;
    }

    private static FlattenedList createFlattenedList() {
        int[] arr1 = {5, 7, 8, 30};
        int[] arr2 = {10, 20};
        int[] arr3 = {19, 22, 50};
        int[] arr4 = {28, 35, 40, 45};

        FlattenedList head = createSubList(arr1);
        head.next = createSubList(arr2);
        head.next.next = createSubList(arr3);
        head.next.next.next = createSubList(arr4);
        return head;
    }

    private static FlattenedList createSubList(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        FlattenedList head = new FlattenedList(arr[0]);
        FlattenedList temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.bottom = new FlattenedList(arr[i]);
            temp = temp.bottom;
        }
        return head;
    }

    private static void displayFlattenedList(FlattenedList head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.bottom;
        }
        System.out.println();
    }

    static class FlattenedList {
        int data;
        FlattenedList next;
        FlattenedList bottom;

        FlattenedList(int data) {
            this.data = data;
        }
    }
}
