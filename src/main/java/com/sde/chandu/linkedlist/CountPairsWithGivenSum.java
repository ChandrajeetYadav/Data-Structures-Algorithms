package com.sde.chandu.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class CountPairsWithGivenSum {
    public static void main(String[] args) {
        int[] arr1 = {3, 1, 5, 7};
        int[] arr2 = {8, 2, 5, 3};
        int sum = 10;
        Node head1 = Node.createLinkedList(arr1);
        Node head2 = Node.createLinkedList(arr2);
        System.out.println("List 1: ");
        head1.display();
        System.out.println("List 2: ");
        head2.display();

        System.out.println("Pairs for sum = " + sum + " , brute approach : ");
        printPairsBrute(head1, head2, sum);
        System.out.println();

        System.out.println("Pairs for sum = " + sum + " , using hashing : ");
        printPairsUsingHashing(head1, head2, sum);
        System.out.println();

        System.out.println("Pairs for sum = " + sum + " , using sorting : ");
        printPairsUsingSorting(head1, head2, sum);
    }

    // Time complexity : O(m * n)
    // Space complexity : O(1)
    private static void printPairsBrute(Node head1, Node head2, int sum) {
        Node temp2;
        while (head1 != null) {
            temp2 = head2;
            while (temp2 != null) {
                if (head1.data + temp2.data == sum)
                    System.out.println(head1.data + "," + temp2.data + "\t");
                temp2 = temp2.next;
            }
            head1 = head1.next;
        }
    }

    // Time complexity : O(m + n)
    // Space complexity : O(m)
    private static void printPairsUsingHashing(Node head1, Node head2, int sum) {
        Set<Integer> set = new HashSet<>();
        while (head1 != null) {
            set.add(head1.data);
            head1 = head1.next;
        }

        while (head2 != null) {
            if (set.contains(sum - head2.data))
                System.out.println((sum - head2.data) + "," + head2.data + "\t");
            head2 = head2.next;
        }
    }

    // Time complexity : O(m log m + n log n)
    // Space complexity : O(1), ignoring recursion stack
    private static void printPairsUsingSorting(Node head1, Node head2, int sum) {
        head1 = mergeSortAscending(head1);
        head2 = mergeSortDescending(head2);
        int curSum;
        while (head1 != null && head2 != null) {
            curSum = head1.data + head2.data;
            if (curSum == sum) {
                System.out.println(head1.data + "," + head2.data + "\t");
                head1 = head1.next;
                head2 = head2.next;
            } else if (curSum > sum)
                head2 = head2.next;
            else
                head1 = head1.next;

        }
    }

    private static Node mergeSortDescending(Node head) {
        if (head == null || head.next == null)
            return head;
        Node mid = getMiddle(head);
        Node nextOfMid = mid.next;
        mid.next = null;
        Node left = mergeSortDescending(head);
        Node right = mergeSortDescending(nextOfMid);
        return mergeDescending(left, right);
    }

    private static Node mergeDescending(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        if (head1.data >= head2.data) {
            head1.next = mergeDescending(head1.next, head2);
            return head1;
        } else {
            head2.next = mergeDescending(head1, head2.next);
            return head2;
        }
    }

    private static Node mergeSortAscending(Node head) {
        if (head == null || head.next == null)
            return head;
        Node mid = getMiddle(head);
        Node nextOfMid = mid.next;
        mid.next = null;
        Node left = mergeSortAscending(head);
        Node right = mergeSortAscending(nextOfMid);
        return mergeAscending(left, right);
    }

    private static Node mergeAscending(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        if (head1.data <= head2.data) {
            head1.next = mergeAscending(head1.next, head2);
            return head1;
        } else {
            head2.next = mergeAscending(head1, head2.next);
            return head2;
        }
    }

    private static Node getMiddle(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
