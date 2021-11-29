package com.sde.chandu.tree;

import java.util.*;

public class ConstructTreeFromInorderPreOrder {
    private static int preIndex = 0;

    public static void main(String[] args) {
        int[] inOrder = {3, 1, 4, 0, 5, 2};
        int[] preOrder = {0, 1, 3, 4, 2, 5};
        BinaryTreeNode root = buildTreeRecursive(inOrder, preOrder);
        System.out.println("Inorder traversal of tree created, recursive approach: " + InOrderTraversal.inOrderTraversalIterativeApproach(root));
        root = buildTreeRecursiveEfficient(inOrder, preOrder);
        System.out.println("Inorder traversal of tree created, recursive efficient approach: " + InOrderTraversal.inOrderTraversalIterativeApproach(root));
        root = buildTreeIterative(inOrder, preOrder);
        System.out.println("Inorder traversal of tree created, iterative approach: " + InOrderTraversal.inOrderTraversalIterativeApproach(root));
    }

    // Time complexity: O(n ^ 2)
    // Space complexity: O(n)
    private static BinaryTreeNode buildTreeRecursive(int[] inOrder, int[] preOrder) {
        return buildTreeRecursiveHelper(inOrder, preOrder, 0, preOrder.length - 1);
    }

    private static BinaryTreeNode buildTreeRecursiveHelper(int[] inOrder, int[] preOrder, int inStart, int inEnd) {
        if (inStart > inEnd)
            return null;
        BinaryTreeNode root = new BinaryTreeNode(preOrder[preIndex++]);
        if (inStart == inEnd)
            return root;
        int inIndex = searchInOrder(inOrder, inStart, inEnd, root.data);
        root.left = buildTreeRecursiveHelper(inOrder, preOrder, inStart, inIndex - 1);
        root.right = buildTreeRecursiveHelper(inOrder, preOrder, inIndex + 1, inEnd);
        return root;
    }

    private static int searchInOrder(int[] inOrder, int start, int end, int value) {
        int i;
        for (i = start; i <= end; i++) {
            if (inOrder[i] == value)
                break;
        }
        return i;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static BinaryTreeNode buildTreeRecursiveEfficient(int[] inOrder, int[] preOrder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++)
            inMap.put(inOrder[i], i);
        preIndex = 0;
        return buildTreeRecursiveEfficientHelper(inOrder, preOrder, 0, preOrder.length - 1, inMap);
    }

    private static BinaryTreeNode buildTreeRecursiveEfficientHelper(int[] inOrder, int[] preOrder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (inStart > inEnd)
            return null;
        BinaryTreeNode root = new BinaryTreeNode(preOrder[preIndex++]);
        if (inStart == inEnd)
            return root;
        int inIndex = inMap.get(root.data);
        root.left = buildTreeRecursiveEfficientHelper(inOrder, preOrder, inStart, inIndex - 1, inMap);
        root.right = buildTreeRecursiveEfficientHelper(inOrder, preOrder, inIndex + 1, inEnd, inMap);
        return root;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static BinaryTreeNode buildTreeIterative(int[] inOrder, int[] preOrder) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        Set<BinaryTreeNode> set = new HashSet<>();
        BinaryTreeNode root = null, temp;
        for (int preIndex = 0, inIndex = 0; preIndex < preOrder.length; ) {
            do {
                temp = new BinaryTreeNode(preOrder[preIndex]);
                if (root == null)
                    root = temp;
                if (!stack.isEmpty()) {
                    if (set.contains(stack.peek())) {
                        set.remove(stack.peek());
                        stack.pop().right = temp;
                    } else
                        stack.peek().left = temp;
                }
                stack.push(temp);
            } while (preOrder[preIndex++] != inOrder[inIndex] && preIndex < preOrder.length);
            while (!stack.isEmpty() && inIndex < inOrder.length && stack.peek().data == inOrder[inIndex]) {
                temp = stack.pop();
                inIndex++;
            }
            if (temp != null) {
                stack.push(temp);
                set.add(temp);
            }
        }
        return root;
    }
}
