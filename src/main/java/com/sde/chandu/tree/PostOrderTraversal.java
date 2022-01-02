package com.sde.chandu.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Post order traversal, recursive: " + postOrderTraversalRecursive(root));
        System.out.println("Post order traversal, using one stack: " + postOrderTraversalUsingOneStack(root));
        System.out.println("Post order traversal, using one stack approach 2: " + postOrderTraversalUsingOneStackApproach2(root));
        System.out.println("Post order traversal, using two stacks: " + postOrderTraversalUsingTwoStacks(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    private static List<Integer> postOrderTraversalRecursive(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrderTraversalRecursiveHelper(root, result);
        return result;
    }

    private static void postOrderTraversalRecursiveHelper(BinaryTreeNode root, List<Integer> result) {
        if (root == null)
            return;
        postOrderTraversalRecursiveHelper(root.left, result);
        postOrderTraversalRecursiveHelper(root.right, result);
        result.add(root.data);
    }

    // Time complexity: O(n)
    // Space complexity : O(n)
    private static List<Integer> postOrderTraversalUsingOneStack(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> result = new ArrayList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        BinaryTreeNode prev = null;
        while (!stack.isEmpty()) {
            root = stack.peek();
            if (prev == null || prev.left == root || prev.right == root) {
                if (root.left != null)
                    stack.push(root.left);
                else if (root.right != null)
                    stack.push(root.right);
                else
                    result.add(stack.pop().data);
            } else if (root.left == prev) {
                if (root.right != null)
                    stack.push(root.right);
                else
                    result.add(stack.pop().data);
            } else if (root.right == prev)
                result.add(stack.pop().data);
            prev = root;
        }
        return result;
    }

    // Time complexity: O(n)
    // Space complexity : O(n)
    private static List<Integer> postOrderTraversalUsingOneStackApproach2(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> result = new ArrayList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty())
                break;
            root = stack.pop();
            if (!stack.isEmpty() && stack.peek() == root)
                root = root.right;
            else {
                result.add(root.data);
                root = null;
            }
        }
        return result;
    }

    // Time complexity: O(n)
    // Space complexity : O(n)
    private static List<Integer> postOrderTraversalUsingTwoStacks(BinaryTreeNode root) {
        if (root == null)
            return null;
        Stack<BinaryTreeNode> s1 = new Stack<>();
        Stack<BinaryTreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            root = s1.pop();
            s2.push(root);
            if (root.left != null)
                s1.push(root.left);
            if (root.right != null)
                s1.push(root.right);
        }
        List<Integer> result = new ArrayList<>();
        while (!s2.isEmpty())
            result.add(s2.pop().data);
        return result;
    }
}
