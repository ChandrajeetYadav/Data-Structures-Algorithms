package com.sde.chandu.linkedlist;

public class ReorderList {
    private static Node left;

    public static void main(String[] args) {
        Node head1 = Node.createLinkedList(5);
        Node head2 = Node.createLinkedList(6);
        System.out.println("Original list 1: ");
        head1.display();
        System.out.println("List 1 after rearranging, brute approach: ");
        head1 = rearrangeBrute(head1);
        head1.display();
        System.out.println();
        System.out.println("Original list 2: ");
        head2.display();
        System.out.println("List 2 after rearranging, brute approach: ");
        head2 = rearrangeBrute(head2);
        head2.display();
        System.out.println();

        head1 = Node.createLinkedList(5);
        head2 = Node.createLinkedList(6);
        System.out.println("Original list 1: ");
        head1.display();
        System.out.println("List 1 after rearranging, efficient approach: ");
        head1 = rearrangeEfficient(head1);
        head1.display();
        System.out.println();
        System.out.println("Original list 2: ");
        head2.display();
        System.out.println("List 2 after rearranging, efficient approach: ");
        head2 = rearrangeEfficient(head2);
        head2.display();
        System.out.println();

        head1 = Node.createLinkedList(5);
        head2 = Node.createLinkedList(6);
        System.out.println("Original list 1: ");
        head1.display();
        System.out.println("List 1 after rearranging, recursive approach: ");
        head1 = rearrangeRecursive(head1);
        head1.display();
        System.out.println();
        System.out.println("Original list 2: ");
        head2.display();
        System.out.println("List 2 after rearranging, recursive approach: ");
        head2 = rearrangeRecursive(head2);
        head2.display();
        System.out.println();
    }

    // Time complexity : O(n^2)
    // Space complexity : O(1)
    private static Node rearrangeBrute(Node head) {
        Node cur = head, temp, prev;
        while (cur != null && cur.next != null) {
            prev = null;
            temp = cur;
            while (temp.next != null) {
                prev = temp;
                temp = temp.next;
            }
            if (prev != cur) {
                temp.next = cur.next;
                cur.next = temp;
                prev.next = null;
            }
            cur = cur.next.next;
        }
        return head;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node rearrangeEfficient(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node temp1 = head;
        Node temp2 = slow.next;
        slow.next = null;

        temp2 = reverse(temp2);
        Node dummy = new Node(0);
        Node last = dummy;
        while (temp1 != null || temp2 != null) {
            if (temp1 != null) {
                last.next = temp1;
                temp1 = temp1.next;
                last = last.next;
            }
            if (temp2 != null) {
                last.next = temp2;
                temp2 = temp2.next;
                last = last.next;
            }
        }
        return dummy.next;
    }

    private static Node reverse(Node head) {
        Node cur = head, prev = null, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // Time complexity : O(n)
    // Space complexity : O(n), Used for method stack
    private static Node rearrangeRecursive(Node head) {
        left = head;
        if (head != null)
            rearrangeRecursiveUtil(left);
        return head;
    }

    private static void rearrangeRecursiveUtil(Node right) {
        if (right == null)
            return;
        rearrangeRecursiveUtil(right.next);
        if (left == null)
            return;
        if (left != right && left.next != right) {
            Node temp = left.next;
            left.next = right;
            right.next = temp;
            left = temp;
        } else {
            if (left.next == right)
                left.next.next = null;
            else
                left.next = null;
            left = null;
        }
    }
}
