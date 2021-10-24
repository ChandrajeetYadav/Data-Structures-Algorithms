package com.sde.chandu.tree;

import java.util.TreeMap;

public class VerticalSumInBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        printVerticalSumUsingHashing(root);
        printVerticalSumUsingLinkedList(root);
    }

    // Time complexity: O(n log n)
    // Space complexity: O(n)
    private static void printVerticalSumUsingHashing(BinaryTreeNode root) {
        if (root == null)
            return;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        printVerticalSumUsingHashingHelper(root, 0, treeMap);
        System.out.println("Vertical sum of the binary tree, using hashing:");
        treeMap.forEach((k, v) -> System.out.print(v + " "));
        System.out.println();
    }

    private static void printVerticalSumUsingHashingHelper(BinaryTreeNode root, int horizontalDistance, TreeMap<Integer, Integer> treeMap) {
        if (root == null)
            return;
        printVerticalSumUsingHashingHelper(root.left, horizontalDistance - 1, treeMap);
        treeMap.put(horizontalDistance, treeMap.getOrDefault(horizontalDistance, 0) + root.data);
        printVerticalSumUsingHashingHelper(root.right, horizontalDistance + 1, treeMap);
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static void printVerticalSumUsingLinkedList(BinaryTreeNode root) {
        if (root == null)
            return;
        LLNode head = new LLNode(0);
        printVerticalSumUsingLinkedListHelper(root, head);
        while (head.prev != null)
            head = head.prev;
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    private static void printVerticalSumUsingLinkedListHelper(BinaryTreeNode root, LLNode head) {
        head.data = head.data + root.data;
        if (root.left != null) {
            if (head.prev == null) {
                head.prev = new LLNode(0);
                head.prev.next = head;
            }
            printVerticalSumUsingLinkedListHelper(root.left, head.prev);
        }
        if (root.right != null) {
            if (head.next == null) {
                head.next = new LLNode(0);
                head.next.prev = head;
            }
            printVerticalSumUsingLinkedListHelper(root.right, head.next);
        }
    }

    private static class LLNode {
        int data;
        LLNode next;
        LLNode prev;

        LLNode(int data) {
            this.data = data;
        }
    }
}
