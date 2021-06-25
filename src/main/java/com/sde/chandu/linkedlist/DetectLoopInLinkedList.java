package com.sde.chandu.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class DetectLoopInLinkedList {
    public static void main(String[] args) {
        int[] arr = {20, 4, 15, 10};
        Node head = Node.createLinkedList(arr);
        head.next.next.next.next = head;
        System.out.println("Does list contain loop, using hashing: " + detectLoopUsingHashing(head));
        System.out.println("Does list contain loop, using floyd cycle detection algorithm: " + detectLoopUsingFloydCycleDetectionAlgorithm(head));
        System.out.println("Does list contain loop, by marking nodes as visited: " + detectLoopByMarkingNodesAsVisited(head));

        head = Node.createLinkedList(arr);
        head.next.next.next.next = head;
        System.out.println("Does list contain loop, by modifying data if range of data is known: " + detectLoopByModifyingDataIfRangeOfDataIsKnown(head));
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static boolean detectLoopUsingHashing(Node head) {
        Set<Node> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head))
                return true;
            set.add(head);
            head = head.next;
        }
        return false;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static boolean detectLoopUsingFloydCycleDetectionAlgorithm(Node head) {
        Node fast = head, slow = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static boolean detectLoopByMarkingNodesAsVisited(Node head) {
        Node temp = new Node(0, null);
        while (head != null) {
            if (head.next == temp)
                return true;
            Node next = head.next;
            head.next = temp;
            head = next;
        }
        return false;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static boolean detectLoopByModifyingDataIfRangeOfDataIsKnown(Node head) {
        while (head != null) {
            if (head.data == -1)
                return true;
            head.data = -1;
            head = head.next;
        }
        return false;
    }
}
