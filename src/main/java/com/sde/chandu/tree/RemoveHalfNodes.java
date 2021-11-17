package com.sde.chandu.tree;

import java.util.List;

public class RemoveHalfNodes {
    public static void main(String[] args) {
        BinaryTreeNode root = createHalfNodeBinaryTree();
        List<Integer> list = InOrderTraversal.inOrderTraversalRecursive(root);
        System.out.println("In order traversal of original tree: " + list);
        root = removeHalfNodesRecursive(root);
        list = InOrderTraversal.inOrderTraversalRecursive(root);
        System.out.println("In order traversal of tree after removing half nodes: " + list);
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static BinaryTreeNode removeHalfNodesRecursive(BinaryTreeNode root) {
        if (root == null)
            return null;
        root.left = removeHalfNodesRecursive(root.left);
        root.right = removeHalfNodesRecursive(root.right);
        if (root.left == null && root.right == null)
            return root;
        if (root.left == null)
            return root.right;
        if (root.right == null)
            return root.left;
        return root;
    }

    private static BinaryTreeNode createHalfNodeBinaryTree() {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.left.right = new BinaryTreeNode(7);

        root.right = new BinaryTreeNode(3);
        root.right.left = new BinaryTreeNode(5);
        root.right.right = new BinaryTreeNode(6);
        root.right.left.left = new BinaryTreeNode(8);
        root.right.left.right = new BinaryTreeNode(9);

        return root;
    }
}
