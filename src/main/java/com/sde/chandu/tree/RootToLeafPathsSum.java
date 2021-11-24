package com.sde.chandu.tree;

public class RootToLeafPathsSum {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Root to leaf path sum, recursive approach: " + getRootToLeafPathsSum(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int getRootToLeafPathsSum(BinaryTreeNode root) {
        return getRootToLeafPathsSumHelper(root, 0);
    }

    private static int getRootToLeafPathsSumHelper(BinaryTreeNode root, int val) {
        if (root == null)
            return 0;
        val = val * 10 + root.data;
        if (root.left == null && root.right == null)
            return val;
        return getRootToLeafPathsSumHelper(root.left, val) + getRootToLeafPathsSumHelper(root.right, val);
    }
}
