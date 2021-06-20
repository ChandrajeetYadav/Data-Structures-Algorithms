package com.sde.chandu.linkedlist;

import java.util.Stack;

public class Add2NumbersRepresentedByLinkedList {
    public static void main(String[] args) {
        int[] arr1 = {4, 5};
        int[] arr2 = {3, 4, 5};
        Node head1 = Node.createLinkedList(arr1);
        Node head2 = Node.createLinkedList(arr2);
        System.out.println("Original list 1: ");
        head1.display();
        System.out.println("Original list 2: ");
        head2.display();

        System.out.println("Sum of list 1 and list 2, by reversing: ");
        Node res = addByReversingList(head1, head2);
        res.display();
        System.out.println();

        System.out.println("Original list 1: ");
        head1.display();
        System.out.println("Original list 2: ");
        head2.display();
        System.out.println("Sum of list 1 and list 2, using recursion: ");
        res = addListsRecursively(head1, head2);
        res.display();
        System.out.println("Sum of list 1 and list 2, using stack: ");
        res = addListsUsingStack(head1, head2);
        res.display();
    }

    // Time complexity : O(m + n)
    // Space complexity : O(max(m, n))
    private static Node addByReversingList(Node head1, Node head2) {
        head1 = reverse(head1);
        head2 = reverseRecursion(head2);
        Node first = head1, second = head2, res = null;
        int carry = 0, sum;
        while (first != null || second != null) {
            sum = carry;
            if (first != null) {
                sum += first.data;
                first = first.next;
            }
            if (second != null) {
                sum += second.data;
                second = second.next;
            }
            carry = sum / 10;
            res = addNode(sum % 10, res);
        }
        if (carry > 0)
            addNode(carry, res);
        head1 = reverse(head1);
        head2 = reverseRecursion(head2);
        return res;
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

    private static Node reverseRecursion(Node head) {
        if (head == null || head.next == null)
            return head;
        Node rest = reverseRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return rest;
    }

    // Time complexity : O(m + n)
    // Space complexity : O(max(m, n))
    private static Node addListsRecursively(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        Helper helper = new Helper();
        int size1 = getSize(head1);
        int size2 = getSize(head2);
        if (size1 == size2)
            addSameSizeLists(head1, head2, helper);
        else {
            if (size1 < size2) {
                Node temp = head1;
                head1 = head2;
                head2 = temp;
            }
            int diff = Math.abs(size1 - size2);
            Node cur = head1;
            for (int i = 0; i < diff; i++)
                cur = cur.next;
            addSameSizeLists(cur, head2, helper);
            propogateCarry(head1, cur, helper);
        }
        if (helper.carry > 0)
            addNode(helper.carry, helper);
        return helper.head;
    }

    private static void addSameSizeLists(Node head1, Node head2, Helper helper) {
        if (head1 == null)
            return;
        addSameSizeLists(head1.next, head2.next, helper);
        int sum = head1.data + head2.data + helper.carry;
        helper.carry = sum / 10;
        addNode(sum % 10, helper);
    }

    private static void propogateCarry(Node head, Node cur, Helper helper) {
        if (head != cur) {
            propogateCarry(head.next, cur, helper);
            int sum = head.data + helper.carry;
            helper.carry = sum / 10;
            addNode(sum % 10, helper);
        }
    }

    private static void addNode(int data, Helper helper) {
        helper.head = new Node(data, helper.head);
    }

    private static int getSize(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    // Time complexity : O(m + n)
    // Space complexity : O(m + n)
    private static Node addListsUsingStack(Node head1, Node head2) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        while (head1 != null) {
            stack1.push(head1);
            head1 = head1.next;
        }
        while (head2 != null) {
            stack2.push(head2);
            head2 = head2.next;
        }
        Node res = null;
        int sum, carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            sum = carry;
            if (!stack1.isEmpty())
                sum += stack1.pop().data;
            if (!stack2.isEmpty())
                sum += stack2.pop().data;
            carry = sum / 10;
            res = addNode(sum % 10, res);
        }
        if (carry > 10)
            res = addNode(carry, res);
        return res;
    }

    private static Node addNode(int data, Node res) {
        return new Node(data, res);
    }

    static class Helper {
        Node head;
        int carry;
    }
}
