package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConvertToMirrorTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        List<Integer> list = InOrderTraversal.inOrderTraversalRecursive(root);
        System.out.println("Binary tree before converting to it's mirror: ");
        System.out.println(list);
        System.out.println("Binary tree after converting to it's mirror, recursive approach:");
        convertToMirrorRecursive(root);
        list = InOrderTraversal.inOrderTraversalRecursive(root);
        System.out.println(list);
        System.out.println();

        list = InOrderTraversal.inOrderTraversalRecursive(root);
        System.out.println("Binary tree before converting to it's mirror: ");
        System.out.println(list);
        System.out.println("Binary tree after converting to it's mirror, iterative approach:");
        convertToMirrorIterative(root);
        list = InOrderTraversal.inOrderTraversalRecursive(root);
        System.out.println(list);
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static BinaryTreeNode convertToMirrorRecursive(BinaryTreeNode root) {
        if (root == null)
            return root;
        BinaryTreeNode left = convertToMirrorRecursive(root.left);
        BinaryTreeNode right = convertToMirrorRecursive(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static void convertToMirrorIterative(BinaryTreeNode root) {
        if (root == null)
            return;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        BinaryTreeNode temp;
        while (!queue.isEmpty()) {
            root = queue.poll();
            temp = root.left;
            root.left = root.right;
            root.right = temp;
            if (root.left != null)
                queue.add(root.left);
            if (root.right != null)
                queue.add(root.right);
        }
    }
}
