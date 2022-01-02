package com.sde.chandu.tree;

import java.util.*;

public class ConstructTreeFromPostOrderAndInOrder {
    private static int postIndex;

    public static void main(String[] args) {
        int[] inOrder = {9, 5, 2, 3, 4};
        int[] postOrder = {9, 5, 2, 3, 4};
        BinaryTreeNode root = buildTreeRecursive(inOrder, postOrder);
        System.out.println("Inorder traversal of tree created, recursive approach: " + InOrderTraversal.inOrderTraversalRecursive(root));
        root = buildTreeRecursiveEfficient(inOrder, postOrder);
        System.out.println("Inorder traversal of tree created, recursive efficient approach: " + InOrderTraversal.inOrderTraversalRecursive(root));
        root = buildTreeIterative(inOrder, postOrder);
        System.out.println("Inorder traversal of tree created, iterative approach: " + InOrderTraversal.inOrderTraversalRecursive(root));

    }

    // Time complexity: O(n ^ 2)
    // Space complexity: O(n)
    private static BinaryTreeNode buildTreeRecursive(int[] inOrder, int[] postOrder) {
        postIndex = postOrder.length - 1;
        return buildTreeRecursiveHelper(inOrder, postOrder, 0, postOrder.length - 1);
    }

    private static BinaryTreeNode buildTreeRecursiveHelper(int[] inOrder, int[] postOrder, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;
        BinaryTreeNode root = new BinaryTreeNode(postOrder[postIndex--]);
        if (inStart == inEnd)
            return root;
        int inIndex = search(inOrder, inStart, inEnd, root.data);
        root.right = buildTreeRecursiveHelper(inOrder, postOrder, inIndex + 1, inEnd);
        root.left = buildTreeRecursiveHelper(inOrder, postOrder, inStart, inIndex - 1);
        return root;
    }

    private static int search(int[] inOrder, int start, int end, int data) {
        int i;
        for (i = start; i <= end; i++) {
            if (inOrder[i] == data)
                break;
        }
        return i;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static BinaryTreeNode buildTreeRecursiveEfficient(int[] inOrder, int[] postOrder) {
        postIndex = postOrder.length - 1;
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++)
            inMap.put(inOrder[i], i);
        return buildTreeRecursiveEfficientHelper(inOrder, postOrder, 0, postOrder.length - 1, inMap);
    }

    private static BinaryTreeNode buildTreeRecursiveEfficientHelper(int[] inOrder, int[] postOrder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (inStart > inEnd)
            return null;
        BinaryTreeNode root = new BinaryTreeNode(postOrder[postIndex--]);
        if (inStart == inEnd)
            return root;
        int inIndex = inMap.get(root.data);
        root.right = buildTreeRecursiveEfficientHelper(inOrder, postOrder, inIndex + 1, inEnd, inMap);
        root.left = buildTreeRecursiveEfficientHelper(inOrder, postOrder, inStart, inIndex - 1, inMap);
        return root;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static BinaryTreeNode buildTreeIterative(int[] inOrder, int[] postOrder) {
        BinaryTreeNode root = null, temp;
        Stack<BinaryTreeNode> stack = new Stack<>();
        Set<BinaryTreeNode> set = new HashSet<>();
        for (int postIndex = postOrder.length - 1, inIndex = inOrder.length - 1; postIndex >= 0; ) {
            do {
                temp = new BinaryTreeNode(postOrder[postIndex]);
                if (root == null)
                    root = temp;
                if (!stack.isEmpty()) {
                    if (set.contains(stack.peek())) {
                        set.remove(stack.peek());
                        stack.pop().left = temp;
                    } else
                        stack.peek().right = temp;
                }
                stack.push(temp);
            } while (postOrder[postIndex--] != inOrder[inIndex] && postIndex >= 0);
            while (!stack.isEmpty() && inIndex >= 0 && stack.peek().data == inOrder[inIndex]) {
                temp = stack.pop();
                inIndex--;
            }
            if (temp != null) {
                set.add(temp);
                stack.push(temp);
            }
        }
        return root;
    }
}
