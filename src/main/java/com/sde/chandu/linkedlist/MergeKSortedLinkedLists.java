package com.sde.chandu.linkedlist;

import java.util.PriorityQueue;

public class MergeKSortedLinkedLists {
    public static void main(String[] args) {
        Node[] arr = getSortedLists();
        Node res;
        res = mergeKSortedListsUsingMergeSort(arr);
        System.out.println("Sorted lists, using merge sort: ");
        res.display();
        System.out.println();

        arr = getSortedLists();
        System.out.println("Sorted lists, merge one by one: ");
        res = mergeKSortedListsOneByOne(arr);
        res.display();
        System.out.println();

        arr = getSortedLists();
        System.out.println("Sorted lists, using divide and conquer approach: ");
        res = mergeKSortedListsByDivideAndConquer(arr);
        res.display();
        System.out.println();

        arr = getSortedLists();
        System.out.println("Sorted lists, using min heap: ");
        res = mergeKSortedListsUsingMinHeap(arr);
        res.display();
        System.out.println();
    }

    // Time complexity : O(N log N), where N=kn
    // Space complexity : O(log N)
    private static Node mergeKSortedListsUsingMergeSort(Node[] arr) {
        Node dummy = new Node(0);
        Node temp = dummy, node;
        for (int i = 0; i < arr.length; i++) {
            node = arr[i];
            while (node != null) {
                temp.next = node;
                temp = temp.next;
                node = node.next;
            }
        }
        return mergeSort(dummy.next);
    }

    // Time complexity : O(N*k), where N=kn
    // Space complexity : O(1)
    private static Node mergeKSortedListsOneByOne(Node[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        Node res = arr[0];
        for (int i = 1; i < arr.length; i++)
            res = sortedMerge(res, arr[i]);
        return res;
    }

    // Time complexity : O(N log k), where N=kn
    // Space complexity : O(1)
    private static Node mergeKSortedListsByDivideAndConquer(Node[] arr) {
        int last = arr.length - 1;
        int i, j;
        while (last != 0) {
            i = 0;
            j = last;
            while (i < j) {
                arr[i] = sortedMerge(arr[i], arr[j]);
                i++;
                j--;
                if (i >= j)
                    last = j;
            }
        }
        return arr[0];
    }

    // Time complexity : O(N log k), where N=kn
    // Space complexity : O(k)
    private static Node mergeKSortedListsUsingMinHeap(Node[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        Node head = null, last = null;
        PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> a.data - b.data);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null)
                pq.add(arr[i]);
        }
        if (!pq.isEmpty()) {
            head = pq.poll();
            last = head;
            if (head.next != null)
                pq.add(head.next);
        }
        Node temp;
        while (!pq.isEmpty()) {
            temp = pq.poll();
            if (temp.next != null)
                pq.add(temp.next);
            last.next = temp;
            last = temp;
        }
        return head;
    }

    private static Node sortedMerge(Node head1, Node head2) {
        Node dummy = new Node(0);
        Node temp = dummy;
        while (true) {
            if (head1 == null) {
                temp.next = head2;
                break;
            }
            if (head2 == null) {
                temp.next = head1;
                break;
            }
            if (head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next;
            } else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }
        return dummy.next;
    }

    private static Node mergeSort(Node head) {
        if (head == null || head.next == null)
            return head;
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);
        return sortedMerge(left, right);
    }

    private static Node getMiddle(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static Node[] getSortedLists() {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int[] arr3 = {0, 9, 10, 11};
        Node[] list = new Node[3];
        list[0] = Node.createLinkedList(arr1);
        list[1] = Node.createLinkedList(arr2);
        list[2] = Node.createLinkedList(arr3);

        return list;
    }
}
