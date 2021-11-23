package com.sde.chandu.tree;

import java.util.Stack;

public class PreOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Preorder traversal, recursive approach: ");
        preOrderTraversalRecursive(root);
        System.out.println();
        System.out.println("Preorder traversal, iterative approach: ");
        preOrderTraversalIterative(root);
        System.out.println();
        System.out.println("Preorder traversal, iterative optimized approach: ");
        preOrderTraversalIterativeOptimized(root);
        System.out.println();
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static void preOrderTraversalRecursive(BinaryTreeNode root) {
        if (root == null)
            return;
        System.out.print(root.data + " ");
        preOrderTraversalRecursive(root.left);
        preOrderTraversalRecursive(root.right);
    }

    // Time complexity: O(n)
    // Space complexity : O(n)
    private static void preOrderTraversalIterative(BinaryTreeNode root) {
        if (root == null)
            return;
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            System.out.print(root.data + " ");
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }
    }

    // Time complexity: O(n)
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(1) if tree is a left skewed tree
    private static void preOrderTraversalIterativeOptimized(BinaryTreeNode root) {
        if (root == null)
            return;
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (root != null) {
            while (root != null) {
                System.out.print(root.data + " ");
                if (root.right != null)
                    stack.push(root.right);
                root = root.left;
            }
            if (!stack.isEmpty())
                root = stack.pop();
        }
    }
}
