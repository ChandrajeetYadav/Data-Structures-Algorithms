package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumLevelSumInBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Max level sum, using level order traversal: " + maxLevelSumUsingLevelOrderTraversal(root));
        System.out.println("Max level sum, using recursive approach 1: " + maxLevelSumRecursive1(root));
        System.out.println("Max level sum, using recursive approach 2: " + maxLevelSumRecursive2(root));
    }

    // Time complexity: O(n)
    // Space complexity: O(w), w = maximum width of the tree
    private static int maxLevelSumUsingLevelOrderTraversal(BinaryTreeNode root) {
        if (root == null)
            return 0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = Integer.MIN_VALUE;
        int size, sum;
        while (!queue.isEmpty()) {
            size = queue.size();
            sum = 0;
            while (size-- > 0) {
                root = queue.poll();
                sum += root.data;
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
            result = Math.max(result, sum);
        }
        return result;
    }

    // Time complexity: O(n)
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int maxLevelSumRecursive1(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int height = getHeight(root);
        int[] sum = new int[height];
        maxLevelSumRecursive1Helper(root, 0, sum);
        int result = Integer.MIN_VALUE;
        for (int i : sum)
            result = Math.max(result, i);
        return result;
    }

    private static void maxLevelSumRecursive1Helper(BinaryTreeNode root, int level, int[] sum) {
        if (root == null)
            return;
        sum[level] += root.data;
        maxLevelSumRecursive1Helper(root.left, level + 1, sum);
        maxLevelSumRecursive1Helper(root.right, level + 1, sum);
    }

    // Time complexity: O(n^2)
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int maxLevelSumRecursive2(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int height = getHeight(root);
        int result = Integer.MIN_VALUE, curLevelSum;
        for (int i = 1; i <= height; i++) {
            curLevelSum = maxLevelSumRecursive2Helper(root, i);
            result = Math.max(result, curLevelSum);
        }
        return result;
    }

    private static int maxLevelSumRecursive2Helper(BinaryTreeNode root, int level) {
        if (root == null)
            return 0;
        if (level == 1)
            return root.data;
        else
            return maxLevelSumRecursive2Helper(root.left, level - 1) + maxLevelSumRecursive2Helper(root.right, level - 1);
    }

    private static int getHeight(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
