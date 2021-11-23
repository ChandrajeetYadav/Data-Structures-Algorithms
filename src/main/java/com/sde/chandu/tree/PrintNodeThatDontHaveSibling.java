package com.sde.chandu.tree;

import java.util.*;

public class PrintNodeThatDontHaveSibling {
    public static void main(String[] args) {
        BinaryTreeNode root = create();
        System.out.println("Nodes that don't have sibling, recursive approach: " + noSiblingRecursive(root));
        System.out.println("Nodes that don't have sibling, iterative approach: " + noSiblingIterative(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(w) = O(n) where w is the max width of the tree
    private static List<Integer> noSiblingIterative(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> result = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null && root.right == null)
                result.add(root.left.data);
            if (root.left == null && root.right != null)
                result.add(root.right.data);
            if (root.left != null)
                queue.add(root.left);
            if (root.right != null)
                queue.add(root.right);
        }
        Collections.sort(result);
        return result;
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static List<Integer> noSiblingRecursive(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        noSiblingRecursiveHelper(root, result);
        Collections.sort(result);
        return result;
    }

    private static void noSiblingRecursiveHelper(BinaryTreeNode root, List<Integer> result) {
        if (root == null)
            return;
        if (root.left != null && root.right != null) {
            noSiblingRecursiveHelper(root.left, result);
            noSiblingRecursiveHelper(root.right, result);
        } else if (root.left != null) {
            result.add(root.left.data);
            noSiblingRecursiveHelper(root.left, result);
        } else if (root.right != null) {
            result.add(root.right.data);
            noSiblingRecursiveHelper(root.right, result);
        }
    }

    private static BinaryTreeNode create() {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.right = new BinaryTreeNode(4);
        root.right.left = new BinaryTreeNode(5);
        root.right.left.left = new BinaryTreeNode(6);
        return root;
    }
}
