package com.sde.chandu.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Level Order Traversal of the tree, iterative approach:" + levelOrderTraversalIterative(root));
        System.out.println("Level Order Traversal of the tree, recursive approach:" + levelOrderTraversalRecursive(root));
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static List<Integer> levelOrderTraversalIterative(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        if (root == null)
            return result;
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            result.add(root.data);
            if (root.left != null)
                queue.add(root.left);
            if (root.right != null)
                queue.add(root.right);
        }
        return result;
    }

    // Time complexity: O(n ^ 2)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static List<Integer> levelOrderTraversalRecursive(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> result = new ArrayList<>();
        int height = getHeight(root);
        for (int i = 1; i <= height; i++)
            addCurrentLevelNodes(root, i, result);
        return result;
    }

    private static void addCurrentLevelNodes(BinaryTreeNode root, int level, List<Integer> result) {
        if (root == null)
            return;
        if (level == 1)
            result.add(root.data);
        else if (level > 1) {
            addCurrentLevelNodes(root.left, level - 1, result);
            addCurrentLevelNodes(root.right, level - 1, result);
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
