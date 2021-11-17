package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class SumOfLeftLeaveNodes {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Sum of left leaves nodes, recursive approach: " + getSumOfLeftLeaveNodesRecursive(root));
        System.out.println("Sum of left leaves nodes, iterative approach: " + getSumOfLeftLeaveNodesIterative(root));
    }

    // Time complexity: O(n)
    // Space complexity: O(w), w = max width of the tree = O(n)
    private static int getSumOfLeftLeaveNodesIterative(BinaryTreeNode root) {
        if (root == null)
            return 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, false));
        int sum = 0;
        Pair temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp.isLeft && temp.node.left == null && temp.node.right == null)
                sum += temp.node.data;
            if (temp.node.left != null)
                queue.add(new Pair(temp.node.left, true));
            if (temp.node.right != null)
                queue.add(new Pair(temp.node.right, false));
        }
        return sum;
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int getSumOfLeftLeaveNodesRecursive(BinaryTreeNode root) {
        if (root == null)
            return 0;
        Sum sum = new Sum();
        getSumOfLeftLeaveNodesRecursiveHelper(root, false, sum);
        return sum.sum;
    }

    private static void getSumOfLeftLeaveNodesRecursiveHelper(BinaryTreeNode root, boolean isLeft, Sum sum) {
        if (root == null)
            return;
        if (isLeft && root.left == null && root.right == null)
            sum.sum += root.data;
        getSumOfLeftLeaveNodesRecursiveHelper(root.left, true, sum);
        getSumOfLeftLeaveNodesRecursiveHelper(root.right, false, sum);
    }

    private static class Pair {
        BinaryTreeNode node;
        boolean isLeft;

        Pair(BinaryTreeNode node, boolean isLeft) {
            this.node = node;
            this.isLeft = isLeft;
        }
    }

    private static class Sum {
        int sum;
    }
}
