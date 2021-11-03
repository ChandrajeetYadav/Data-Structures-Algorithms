package com.sde.chandu.tree;

public class CheckIfTwoNodesAreSiblings {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Are 2 and 3 sibling: " + isSibling(root, 2, 3));
        System.out.println("Are 5 and 7 sibling: " + isSibling(root, 5, 7));
    }

    // Time complexity : O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static boolean isSibling(BinaryTreeNode root, int a, int b) {
        if (root == null)
            return false;
        if (root.left != null && root.right != null) {
            if ((root.left.data == a && root.right.data == b)
                    || (root.left.data == b && root.right.data == a))
                return true;
        }
        return isSibling(root.left, a, b) || isSibling(root.left, a, b);
    }
}
