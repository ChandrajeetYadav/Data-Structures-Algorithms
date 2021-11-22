package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepthOfABinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = create();
        System.out.println("Minimum depth of tree, recursive approach: " + getMinimumDepthRecursive(root));
        System.out.println("Minimum depth of tree, iterative approach: " + getMinimumDepthIterative(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int getMinimumDepthRecursive(BinaryTreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        if (root.left == null)
            return getMinimumDepthRecursive(root.right) + 1;
        if (root.right == null)
            return getMinimumDepthRecursive(root.left) + 1;
        return 1 + Math.min(getMinimumDepthRecursive(root.left), getMinimumDepthRecursive(root.right));
    }

    // Time complexity: O(n)
    // Space complexity : O(w) = O(n) where w is the max width of the tree
    private static int getMinimumDepthIterative(BinaryTreeNode root) {
        if (root == null)
            return 0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            level++;
            while (size-- > 0) {
                root = queue.poll();
                if (root.left == null && root.right == null)
                    return level;
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
        }
        return level;
    }

    private static BinaryTreeNode create() {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right = new BinaryTreeNode(3);
        return root;
    }
}
