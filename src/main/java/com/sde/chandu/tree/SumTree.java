package com.sde.chandu.tree;

public class SumTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Is tree sum tree, recursive brute approach: " + isSumTreeRecursiveBrute(root));
        System.out.println("Is tree sum tree, recursive efficient approach: " + isSumTreeRecursiveEfficient(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static boolean isSumTreeRecursiveEfficient(BinaryTreeNode root) {
        return isSumTreeRecursiveHelper(root) != Integer.MIN_VALUE;
    }

    private static int isSumTreeRecursiveHelper(BinaryTreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return root.data;
        int leftSum = isSumTreeRecursiveHelper(root.left);
        int rightSum = isSumTreeRecursiveHelper(root.right);
        if (leftSum != Integer.MIN_VALUE && rightSum != Integer.MIN_VALUE
                && root.data == leftSum + rightSum)
            return 2 * root.data;
        return Integer.MIN_VALUE;
    }

    // Time complexity: O(n ^ 2)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static boolean isSumTreeRecursiveBrute(BinaryTreeNode root) {
        if (root == null || isLeaf(root))
            return true;
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);
        return (root.data == leftSum + rightSum) && isSumTreeRecursiveBrute(root.left)
                && isSumTreeRecursiveBrute(root.right);
    }

    private static int sum(BinaryTreeNode root) {
        if (root == null)
            return 0;
        return root.data + sum(root.left) + sum(root.right);
    }

    private static boolean isLeaf(BinaryTreeNode root) {
        if (root == null)
            return false;
        return root.left == null && root.right == null;
    }
}
