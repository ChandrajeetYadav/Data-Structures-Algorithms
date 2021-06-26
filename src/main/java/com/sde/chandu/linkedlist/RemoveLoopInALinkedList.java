package com.sde.chandu.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class RemoveLoopInALinkedList {
    public static void main(String[] args) {
        Node head = createLinkedListWithLoop();
        System.out.println("Linked list after removing loop, using floyd cycle detection algorithm approach 1: ");
        head = removeLoopUsingFloydCycleDetection1(head);
        head.display();

        head = createLinkedListWithLoop();
        System.out.println("Linked list after removing loop, using floyd cycle detection algorithm approach 2: ");
        head = removeLoopUsingFloydCycleDetection2(head);
        head.display();

        head = createLinkedListWithLoop();
        System.out.println("Linked list after removing loop, using floyd cycle detection algorithm approach 3: ");
        head = removeLoopUsingFloydCycleDetection3(head);
        head.display();

        head = createLinkedListWithLoop();
        System.out.println("Linked list after removing loop, using hashing : ");
        head = removeLoopUsingHashing(head);
        head.display();
    }

    // Time complexity : O(n * (n-k)) , k = length of loop
    // Space complexity : O(1)
    private static Node removeLoopUsingFloydCycleDetection1(Node head) {
        Node slow = head, fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                removeLoop1(head, slow);
                return head;
            }
        }
        return head;
    }

    private static void removeLoop1(Node head, Node loop) {
        Node temp1 = head, temp2;
        while (true) {
            temp2 = loop;
            while (temp2.next != loop && temp2.next != temp1)
                temp2 = temp2.next;
            if (temp1 == temp2.next)
                break;
            temp1 = temp1.next;
        }
        temp2.next = null;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node removeLoopUsingFloydCycleDetection2(Node head) {
        Node slow = head, fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                removeLoop2(head, slow);
                return head;
            }
        }
        return head;
    }

    private static void removeLoop2(Node head, Node loop) {
        int loopLength = findNoOfNodesInLoop(loop);
        Node temp1 = head, temp2 = head;
        for (int i = 0; i < loopLength; i++)
            temp2 = temp2.next;
        while (temp1 != temp2) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        while (temp2.next != temp1)
            temp2 = temp2.next;
        temp2.next = null;
    }

    private static int findNoOfNodesInLoop(Node loop) {
        Node temp = loop;
        int count = 1;
        while (temp.next != loop) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node removeLoopUsingFloydCycleDetection3(Node head) {
        Node slow = head, fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                removeLoop3(head, slow);
                return head;
            }
        }
        return head;
    }

    private static void removeLoop3(Node head, Node loop) {
        if (head == loop) {
            while (loop.next != head)
                loop = loop.next;
            loop.next = null;
        } else {
            while (head.next != loop.next) {
                head = head.next;
                loop = loop.next;
            }
            loop.next = null;
        }
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static Node removeLoopUsingHashing(Node head) {
        Set<Node> set = new HashSet<>();
        Node prev = null, temp = head;
        while (temp != null) {
            if (set.contains(temp)) {
                prev.next = null;
                return head;
            }
            set.add(temp);
            prev = temp;
            temp = temp.next;
        }
        return head;
    }

    private static Node createLinkedListWithLoop() {
        Node head = Node.createLinkedList(5);
        head.next.next.next.next.next = head;
        return head;
    }
}
