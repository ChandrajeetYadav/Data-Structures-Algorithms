package com.sde.chandu.linkedlist;

import java.util.Stack;

public class CheckIfLinkedListIsPalindrome {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3, 2, 1};
        Node head1 = Node.createLinkedList(arr);
        Node head2 = Node.createLinkedList(6);
        System.out.println("List 1: ");
        head1.display();
        System.out.println("List 2");
        head2.display();

        System.out.println("Is list 1 palindrome, using stack: " + isPalindromeUsingStack(head1));
        System.out.println("Is list 2 palindrome, using stack: " + isPalindromeUsingStack(head2));
        System.out.println();

        System.out.println("Is list 1 palindrome, by reversing: " + isPalindromeByReversing(head1));
        System.out.println("Is list 2 palindrome, by reversing: " + isPalindromeByReversing(head2));
        System.out.println();

        System.out.println("Is list 1 palindrome, using recursion: " + isPalindromeUsingRecursion(head1));
        System.out.println("Is list 2 palindrome, using recursion: " + isPalindromeUsingRecursion(head2));
        System.out.println();
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static boolean isPalindromeUsingStack(Node head) {
        if (head == null)
            return false;
        Stack<Integer> stack = new Stack<>();
        Node temp = head;
        while (temp != null) {
            stack.push(temp.data);
            temp = temp.next;
        }
        while (head != null) {
            if (head.data != stack.pop())
                return false;
            head = head.next;
        }
        return true;
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static boolean isPalindromeByReversing(Node head) {
        if (head == null)
            return false;
        if (head.next == null)
            return true;
        boolean res;
        Node fast = head, slow = head, prevSlow = null, mid = null;
        while (fast != null && fast.next != null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            mid = slow;
            slow = slow.next;
        }
        prevSlow.next = null;
        slow = reverse(slow);
        res = compareLists(head, slow);
        slow = reverseRecursive(slow);
        if (mid != null) {
            mid.next = slow;
            prevSlow.next = mid;
        } else
            prevSlow.next = slow;
        return res;
    }

    private static boolean compareLists(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if (head1.data != head2.data)
                return false;
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1 == null && head2 == null;
    }

    private static Node reverse(Node head) {
        Node prev = null, cur = head, next;
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
    // Space complexity : O(n)
    private static boolean isPalindromeUsingRecursion(Node head) {
        Helper helper = new Helper(head);
        return isPalindromeUsingRecursionUtil(head, helper);
    }

    private static boolean isPalindromeUsingRecursionUtil(Node right, Helper helper) {
        if (right == null)
            return true;
        boolean isPalindrome = isPalindromeUsingRecursionUtil(right.next, helper);
        if (!isPalindrome)
            return isPalindrome;
        isPalindrome = helper.left.data == right.data;
        helper.left = helper.left.next;
        return isPalindrome;
    }

    static class Helper {
        Node left;

        Helper(Node left) {
            this.left = left;
        }
    }
}
