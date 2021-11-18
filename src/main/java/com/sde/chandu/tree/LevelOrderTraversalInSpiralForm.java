package com.sde.chandu.tree;

import java.util.*;

public class LevelOrderTraversalInSpiralForm {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Level order traversal in spiral form, recursive approach: " + levelOrderTraversalInSpiralRecursive(root));
        System.out.println("Level order traversal in spiral form, using two stack approach: " + levelOrderTraversalInSpiralUsingTwoStack(root));
        System.out.println("Level order traversal in spiral form, using deque approach: " + levelOrderTraversalInSpiralUsingDeque(root));
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static List<Integer> levelOrderTraversalInSpiralUsingTwoStack(BinaryTreeNode root) {
        if (root == null)
            return null;
        Stack<BinaryTreeNode> curLevel = new Stack<>();
        Stack<BinaryTreeNode> nextLevel = new Stack<>();
        Stack<BinaryTreeNode> temp;
        curLevel.push(root);
        List<Integer> list = new ArrayList<>();
        boolean leftToRight = false;
        while (!curLevel.isEmpty()) {
            while (!curLevel.isEmpty()) {
                root = curLevel.pop();
                list.add(root.data);
                if (leftToRight) {
                    if (root.left != null)
                        nextLevel.push(root.left);
                    if (root.right != null)
                        nextLevel.add(root.right);
                } else {
                    if (root.right != null)
                        nextLevel.add(root.right);
                    if (root.left != null)
                        nextLevel.add(root.left);
                }
            }

            leftToRight = !leftToRight;
            temp = curLevel;
            curLevel = nextLevel;
            nextLevel = temp;
        }
        return list;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static List<Integer> levelOrderTraversalInSpiralUsingDeque(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> list = new LinkedList<>();
        Deque<BinaryTreeNode> deque = new LinkedList<>();
        deque.add(root);
        boolean leftToRight = false;
        int size;
        while (!deque.isEmpty()) {
            size = deque.size();
            while (size-- > 0) {
                if (leftToRight) {
                    root = deque.poll();
                    if (root.left != null)
                        deque.add(root.left);
                    if (root.right != null)
                        deque.add(root.right);
                } else {
                    root = deque.pollLast();
                    if (root.right != null)
                        deque.addFirst(root.right);
                    if (root.left != null)
                        deque.addFirst(root.left);
                }
                list.add(root.data);
            }
            leftToRight = !leftToRight;
        }
        return list;
    }

    // Time complexity: O(n^2)
    // Space complexity : O(n)
    private static List<Integer> levelOrderTraversalInSpiralRecursive(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> list = new ArrayList<>();
        int height = height(root);
        boolean leftToRight = false;
        for (int i = 1; i <= height; i++) {
            levelOrderTraversalInSpiralRecursiveHelper(root, leftToRight, i, list);
            leftToRight = !leftToRight;
        }
        return list;
    }

    private static void levelOrderTraversalInSpiralRecursiveHelper(BinaryTreeNode root, boolean leftToRight, int level, List<Integer> list) {
        if (root == null)
            return;
        if (level == 1)
            list.add(root.data);
        else if (level > 1) {
            if (leftToRight) {
                levelOrderTraversalInSpiralRecursiveHelper(root.left, leftToRight, level - 1, list);
                levelOrderTraversalInSpiralRecursiveHelper(root.right, leftToRight, level - 1, list);
            } else {
                levelOrderTraversalInSpiralRecursiveHelper(root.right, leftToRight, level - 1, list);
                levelOrderTraversalInSpiralRecursiveHelper(root.left, leftToRight, level - 1, list);
            }
        }
    }

    private static int height(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
