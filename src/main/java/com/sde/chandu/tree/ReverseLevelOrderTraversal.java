package com.sde.chandu.tree;

import java.util.*;

public class ReverseLevelOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Reverse level order traversal using queue and stack: " + reverseLevelOrderUsingQueueAndStack(root));
        System.out.println("Reverse level order traversal using queue and deque: " + reverseLevelOrderUsingDeque(root));
        System.out.println("Reverse level order traversal using recursion: " + reverseLevelOrderUsingRecursion(root));
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static List<Integer> reverseLevelOrderUsingQueueAndStack(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> result = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        queue.add(root);
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size-- > 0) {
                root = queue.poll();
                stack.push(root.data);
                if (root.right != null)
                    queue.add(root.right);
                if (root.left != null)
                    queue.add(root.left);
            }
        }
        while (!stack.isEmpty())
            result.add(stack.pop());
        return result;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static List<Integer> reverseLevelOrderUsingDeque(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> result = new LinkedList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        Deque<Integer> deque = new LinkedList<>();
        queue.add(root);
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size-- > 0) {
                root = queue.poll();
                deque.addFirst(root.data);
                if (root.right != null)
                    queue.add(root.right);
                if (root.left != null)
                    queue.add(root.left);
            }
        }
        while (!deque.isEmpty())
            result.add(deque.poll());
        return result;
    }

    // Time complexity: O(n ^ 2)
    // Space complexity: O(n)
    private static List<Integer> reverseLevelOrderUsingRecursion(BinaryTreeNode root) {
        if (root == null)
            return null;
        int height = height(root);
        List<Integer> result = new ArrayList<>();
        for (int i = height; i >= 1; i--)
            reverseLevelOrderUsingRecursionHelper(root, i, result);
        return result;
    }

    private static void reverseLevelOrderUsingRecursionHelper(BinaryTreeNode root, int level, List<Integer> result) {
        if (root == null)
            return;
        if (level == 1)
            result.add(root.data);
        if (level > 1) {
            reverseLevelOrderUsingRecursionHelper(root.left, level - 1, result);
            reverseLevelOrderUsingRecursionHelper(root.right, level - 1, result);
        }
    }

    private static int height(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
