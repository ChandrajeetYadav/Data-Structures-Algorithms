package com.sde.chandu.tree;

import java.util.*;

public class ZigZagTreeTraversal {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Zig Zag Traversal using two stacks: " + zigZagTraversalUsingTwoStacks(root));
        System.out.println("Zig Zag Traversal using deque: " + zigZagTraversalUsingDeque(root));
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static List<Integer> zigZagTraversalUsingTwoStacks(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> list = new ArrayList<>();
        Stack<BinaryTreeNode> curLevel = new Stack<>();
        Stack<BinaryTreeNode> nextLevel = new Stack<>();
        Stack<BinaryTreeNode> temp;
        boolean leftToRight = true;
        curLevel.push(root);
        while (!curLevel.isEmpty()) {
            root = curLevel.pop();
            list.add(root.data);
            if (leftToRight) {
                if (root.left != null)
                    nextLevel.push(root.left);
                if (root.right != null)
                    nextLevel.push(root.right);
            } else {
                if (root.right != null)
                    nextLevel.push(root.right);
                if (root.left != null)
                    nextLevel.push(root.left);
            }
            if (curLevel.isEmpty()) {
                leftToRight = !leftToRight;
                temp = curLevel;
                curLevel = nextLevel;
                nextLevel = temp;
            }
        }
        return list;
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static List<Integer> zigZagTraversalUsingDeque(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> list = new ArrayList<>();
        Deque<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size > 0) {
                if (leftToRight) {
                    root = queue.poll();
                    if (root.left != null)
                        queue.add(root.left);
                    if (root.right != null)
                        queue.add(root.right);
                } else {
                    root = queue.pollLast();
                    if (root.right != null)
                        queue.addFirst(root.right);
                    if (root.left != null)
                        queue.addFirst(root.left);
                }
                list.add(root.data);
                size--;
            }
            leftToRight = !leftToRight;
        }
        return list;
    }
}
