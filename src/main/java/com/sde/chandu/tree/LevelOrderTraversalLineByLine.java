package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversalLineByLine {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Level order traversal line by line, recursive approach: ");
        printLevelOrderTraversalLineByLineRecursive(root);
        System.out.println("Level order traversal line by line, iterative approach: ");
        printLevelOrderTraversalLineByLineIterative(root);
        System.out.println("Level order traversal line by line, using two queue approach: ");
        printLevelOrderTraversalLineByLineUsingTwoQueue(root);
    }

    // Time complexity: O(n)
    // Space complexity : O(w) where w is the width of the tree
    private static void printLevelOrderTraversalLineByLineIterative(BinaryTreeNode root) {
        if (root == null)
            return;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size-- > 0) {
                root = queue.poll();
                System.out.print(root.data + " ");
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
            System.out.println();
        }
    }

    // Time complexity: O(n)
    // Space complexity : O(n)
    private static void printLevelOrderTraversalLineByLineUsingTwoQueue(BinaryTreeNode root) {
        if (root == null)
            return;
        Queue<BinaryTreeNode> queue1 = new LinkedList<>();
        Queue<BinaryTreeNode> queue2 = new LinkedList<>();
        queue1.add(root);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            while (!queue1.isEmpty()) {
                root = queue1.poll();
                System.out.print(root.data + " ");
                if (root.left != null)
                    queue2.add(root.left);
                if (root.right != null)
                    queue2.add(root.right);
            }
            System.out.println();
            if (!queue2.isEmpty()) {
                while (!queue2.isEmpty()) {
                    root = queue2.poll();
                    System.out.print(root.data + " ");
                    if (root.left != null)
                        queue1.add(root.left);
                    if (root.right != null)
                        queue1.add(root.right);
                }
                System.out.println();
            }
        }
    }

    // Time complexity: O(n ^ 2)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static void printLevelOrderTraversalLineByLineRecursive(BinaryTreeNode root) {
        if (root == null)
            return;
        int height = getHeight(root);
        for (int i = 1; i <= height; i++) {
            printLevelOrderTraversalLineByLineRecursiveHelper(root, i);
            System.out.println();
        }
    }

    private static void printLevelOrderTraversalLineByLineRecursiveHelper(BinaryTreeNode root, int level) {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.data + " ");
        else if (level > 1) {
            printLevelOrderTraversalLineByLineRecursiveHelper(root.left, level - 1);
            printLevelOrderTraversalLineByLineRecursiveHelper(root.right, level - 1);
        }
    }

    private static int getHeight(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
