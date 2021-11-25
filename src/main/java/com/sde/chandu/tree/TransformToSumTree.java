package com.sde.chandu.tree;

public class TransformToSumTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Original tree: " + InOrderTraversal.inOrderTraversalIterativeApproach(root));
        transformToSumTree(root);
        System.out.println("Tree after converting to sum tree: " + InOrderTraversal.inOrderTraversalIterativeApproach(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static void transformToSumTree(BinaryTreeNode root) {
        transformToSumTreeHelper(root);
    }

    private static int transformToSumTreeHelper(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int oldData = root.data;
        root.data = transformToSumTreeHelper(root.left) + transformToSumTreeHelper(root.right);
        return root.data + oldData;
    }
}
