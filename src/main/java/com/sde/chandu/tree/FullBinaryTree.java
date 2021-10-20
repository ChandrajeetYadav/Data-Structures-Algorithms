package com.sde.chandu.tree;

public class FullBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Is tree full binary tree: " + isFullBinaryTree(root));
    }

    // Time complexity : O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    public static boolean isFullBinaryTree(BinaryTreeNode root) {
        if ((root == null) || (root.left == null && root.right == null))
            return true;
        else if (root.left != null && root.right != null)
            return isFullBinaryTree(root.left) && isFullBinaryTree(root.right);
        return false;
    }
}
