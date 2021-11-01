package com.sde.chandu.tree;

import java.util.Stack;

public class TwoMirrorTrees {
    public static void main(String[] args) {
        BinaryTreeNode root1 = BinaryTreeNode.createBinaryTree();
        BinaryTreeNode root2 = BinaryTreeNode.createBinaryTree();
        System.out.println("Are tree1 and tree2 mirror of each other, recursive approach: " + areMirrorRecursive(root1, root2));
        System.out.println("Are tree1 and tree2 mirror of each other, iterative approach: " + areMirrorIterative(root1, root2));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static boolean areMirrorRecursive(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        return (root1.data == root2.data) && areMirrorRecursive(root1.left, root2.right)
                && areMirrorRecursive(root1.right, root2.left);
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static boolean areMirrorIterative(BinaryTreeNode root1, BinaryTreeNode root2) {
        Stack<BinaryTreeNode> stack1 = new Stack<>();
        Stack<BinaryTreeNode> stack2 = new Stack<>();
        while (true) {
            while (root1 != null && root2 != null) {
                if (root1.data != root2.data)
                    return false;
                stack1.push(root1);
                stack2.push(root2);
                root1 = root1.left;
                root2 = root2.right;
            }
            if (!(root1 == null && root2 == null))
                return false;
            if (!stack1.isEmpty() && !stack2.isEmpty()) {
                root1 = stack1.pop();
                root2 = stack2.pop();
                root1 = root1.right;
                root2 = root2.left;
            } else
                break;
        }
        return true;
    }
}
