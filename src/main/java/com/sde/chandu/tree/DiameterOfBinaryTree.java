package com.sde.chandu.tree;

public class DiameterOfBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Height of the tree, recursive approach: " + diameterRecursive(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int diameterRecursive(BinaryTreeNode root) {
        if (root == null)
            return 0;
        Height h = new Height();
        height(root, h);
        return h.height;
    }

    private static int height(BinaryTreeNode root, Height h) {
        if (root == null)
            return 0;
        int leftHeight = height(root.left, h);
        int rightHeight = height(root.right, h);
        h.height = Math.max(h.height, 1 + leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static class Height {
        int height;
    }
}
