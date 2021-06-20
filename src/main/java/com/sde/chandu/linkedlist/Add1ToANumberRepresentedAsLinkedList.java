package com.sde.chandu.linkedlist;

public class Add1ToANumberRepresentedAsLinkedList {
    public static void main(String[] args) {
        int[] arr = {1, 9, 9, 9};
        Node head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after adding 1, using reverse approach: ");
        head = addOneByReversing(head);
        head.display();
        System.out.println();

        int[] arr2 = {9, 9, 9};
        head = Node.createLinkedList(arr2);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after adding 1, using recursion: ");
        head = addOneUsingRecursion(head);
        head.display();
        System.out.println();

        head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after adding 1, using non 9 digit: ");
        head = addOneByCheckingNon9digit(head);
        head.display();
        System.out.println();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node addOneByReversing(Node head) {
        if (head == null)
            return new Node(1, null);
        head = reverse(head);
        int carry = 1, sum;
        Node cur = head, prev = null;
        while (cur != null) {
            if (carry == 0)
                break;
            sum = cur.data + carry;
            carry = sum >= 10 ? 1 : 0;
            cur.data = sum % 10;
            prev = cur;
            cur = cur.next;
        }
        if (carry > 0)
            prev.next = new Node(carry, null);
        head = reverseRecursive(head);
        return head;
    }

    private static Node reverse(Node head) {
        Node cur = head, next, prev = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    private static Node reverseRecursive(Node head) {
        if (head == null || head.next == null)
            return head;
        Node rest = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return rest;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node addOneUsingRecursion(Node head) {
        int carry = addWithCarry(head);
        if (carry > 0) {
            Node newNode = new Node(carry, null);
            newNode.next = head;
            head = newNode;
        }
        return head;
    }

    private static int addWithCarry(Node head) {
        if (head == null)
            return 1;
        int carry = addWithCarry(head.next);
        int sum = head.data + carry;
        head.data = sum % 10;
        return sum / 10;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node addOneByCheckingNon9digit(Node head) {
        if (head == null)
            return new Node(1, null);
        Node last = null, cur = head;
        while (cur.next != null) {
            if (cur.data != 9)
                last = cur;
            cur = cur.next;
        }
        if (cur.data != 9) {
            cur.data++;
            return head;
        }
        if (last == null) {
            last = new Node(0, head);
            head = last;
        }
        last.data++;
        last = last.next;
        while (last != null) {
            last.data = 0;
            last = last.next;
        }
        return head;
    }
}
