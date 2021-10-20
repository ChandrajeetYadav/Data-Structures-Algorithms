package com.sde.chandu.tree;

public class SymmetricTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Is tree symmetric: " + isMirror(root));
    }

    // Time complexity : O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static boolean isMirror(BinaryTreeNode root) {
        return isMirror(root, root);
    }

    private static boolean isMirror(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        else if (root1 != null && root2 != null && root1.data == root2.data)
            return isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
        return false;
    }
}
