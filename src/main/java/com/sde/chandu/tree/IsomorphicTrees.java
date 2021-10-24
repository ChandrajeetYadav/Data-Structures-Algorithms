package com.sde.chandu.tree;

public class IsomorphicTrees {
    public static void main(String[] args) {
        BinaryTreeNode root1 = BinaryTreeNode.createBinaryTree();
        BinaryTreeNode root2 = BinaryTreeNode.createBinaryTree();
        System.out.println("Are trees isomorphic: " + isIsomorphic(root1, root2));
    }

    // Time complexity : O(min(n1, n2)*2) => O(min(n1, n2))
    // Space complexity : O(min(h1, h2))
    private static boolean isIsomorphic(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        if (root1.data != root2.data)
            return false;
        return (isIsomorphic(root1.left, root2.left) && isIsomorphic(root1.right, root2.right))
                || (isIsomorphic(root1.left, root2.right) && isIsomorphic(root2.right, root1.left));
    }
}
