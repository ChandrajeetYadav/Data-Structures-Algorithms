package com.sde.chandu.tree;

public class ChildrenSumParent {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Does binary tree holds children sum property: " + isChildrenSumParentProperty(root));
    }

    // Time complexity : O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static boolean isChildrenSumParentProperty(BinaryTreeNode root) {
        return isChildrenSumParentPropertyHelper(root) == 1;
    }

    private static int isChildrenSumParentPropertyHelper(BinaryTreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return 1;
        int leftData = (root.left != null) ? root.left.data : 0;
        int rightData = (root.right != null) ? root.right.data : 0;
        if ((root.data == leftData + rightData)
                && (isChildrenSumParentPropertyHelper(root.left) != 0)
                && (isChildrenSumParentPropertyHelper(root.right) != 0))
            return 1;
        return 0;
    }
}
