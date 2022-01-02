package com.sde.chandu.tree;

public class CheckForBalancedTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Is tree balanced: " + isBalanced(root));
        System.out.println("Is tree balanced, efficient approach: " + isBalancedEfficient(root));
    }

    // Time complexity : O(n^2)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static boolean isBalanced(BinaryTreeNode root) {
        if (root == null)
            return true;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private static int height(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Time complexity : O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static boolean isBalancedEfficient(BinaryTreeNode root) {
        if (root == null)
            return true;
        Height height = new Height();
        return isBalancedEfficientHelper(root, height);
    }

    private static boolean isBalancedEfficientHelper(BinaryTreeNode root, Height height) {
        if (root == null)
            return true;
        Height lHeight = new Height(), rHeight = new Height();
        boolean left = isBalancedEfficientHelper(root.left, lHeight);
        boolean right = isBalancedEfficientHelper(root.right, rHeight);
        height.height = Math.max(lHeight.height, rHeight.height) + 1;
        if (Math.abs(lHeight.height - rHeight.height) > 1)
            return false;
        return left && right;
    }

    static class Height {
        int height;
    }
}
