package com.sde.chandu.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        List<Integer> listR = inOrderTraversalRecursive(root);
        System.out.println("InOrder traversal of the tree, recursive approach: ");
        listR.forEach(n -> System.out.print(n + " "));
        System.out.println();

        List<Integer> listI = inOrderTraversalIterativeApproach(root);
        System.out.println("InOrder traversal of the tree, iterative approach: ");
        listI.forEach(n -> System.out.print(n + " "));
    }

    // Time complexity : O(n)
    // Space complexity : O(1) if we don't consider stack size for function all.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    public static List<Integer> inOrderTraversalRecursive(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrderTraversalRecursiveHelper(root, result);
        return result;
    }

    private static void inOrderTraversalRecursiveHelper(BinaryTreeNode root, List<Integer> result) {
        if (root == null)
            return;
        inOrderTraversalRecursiveHelper(root.left, result);
        result.add(root.data);
        inOrderTraversalRecursiveHelper(root.right, result);
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    public static List<Integer> inOrderTraversalIterativeApproach(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (true) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                if (stack.isEmpty())
                    break;
                root = stack.pop();
                result.add(root.data);
                root = root.right;
            }
        }
        return result;
    }
}
