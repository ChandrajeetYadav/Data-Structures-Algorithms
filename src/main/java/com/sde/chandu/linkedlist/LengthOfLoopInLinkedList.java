package com.sde.chandu.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLoopInLinkedList {
    public static void main(String[] args) {
        Node head = Node.createLinkedList(5);
        head.next.next.next.next.next = head;
        System.out.println("Length of loop in lnked list, using hashing: " + findLengthOfLoopUsingHashing(head));
        System.out.println("Length of loop in lnked list, using floyd cycle detection algorithm: " + findLengthOfLoopUsingFloydCycleDetectionAlgorithm(head));
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static int findLengthOfLoopUsingHashing(Node head) {
        int pos = 0;
        Map<Node, Integer> map = new HashMap<>();
        while (head != null) {
            if (map.containsKey(head))
                return pos - map.get(head);
            map.put(head, pos++);
            head = head.next;
        }
        return 0;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static int findLengthOfLoopUsingFloydCycleDetectionAlgorithm(Node head) {
        Node slow = head, fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return getCountOfNodesInLoop(slow);
        }
        return 0;
    }

    private static int getCountOfNodesInLoop(Node node) {
        int count = 1;
        Node temp = node;
        while (temp.next != node) {
            count++;
            temp = temp.next;
        }
        return count;
    }
}
