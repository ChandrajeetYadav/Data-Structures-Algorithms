package com.sde.chandu.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class SegregateEvenOddNodes {
    public static void main(String[] args) {
        int[] arr = {17, 15, 8, 12, 10, 5, 4, 1, 7, 6};
        Node head = Node.createLinkedList(arr);
        System.out.println("Linked list before segregating even and odd nodes: ");
        head.display();
        head = segregateBrute(head);
        System.out.println("Brute approach, Linked list after segregating even and odd nodes: ");
        head.display();

        head = Node.createLinkedList(arr);
        System.out.println("Linked list before segregating even and odd nodes: ");
        head.display();
        head = segregate(head);
        System.out.println("Efficient approach, Linked list after segregating even and odd nodes: ");
        head.display();
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static Node segregateBrute(Node head) {
        if (head == null || head.next == null)
            return head;
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        Node temp = head;
        while (temp != null) {
            if (temp.data % 2 == 0)
                even.add(temp.data);
            else
                odd.add(temp.data);
            temp = temp.next;
        }
        temp = head;
        for (int i : even) {
            temp.data = i;
            temp = temp.next;
        }
        for (int i : odd) {
            temp.data = i;
            temp = temp.next;
        }
        return head;
    }

    // Time complexity: O(n)
    // Space complexity: O(1)
    private static Node segregate(Node head) {
        if (head == null || head.next == null)
            return head;
        Node even = new Node(0), odd = new Node(0);
        Node evenEnd = even, oddEnd = odd;
        while (head != null) {
            if (head.data % 2 == 0) {
                evenEnd.next = head;
                evenEnd = evenEnd.next;
            } else {
                oddEnd.next = head;
                oddEnd = oddEnd.next;
            }
            head = head.next;
        }
        oddEnd.next = null;
        evenEnd.next = odd.next;
        head = even.next;
        even.next = null;
        odd.next = null;
        return head;
    }
}
