package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxWidthOfTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Max width of tree, using level order traversal: " + getMaxWidthUsingLevelOrderTraversal(root));
        System.out.println("Max width of tree, using pre order traversal: " + getMaxWidthUsingPreOrderTraversal(root));
    }

    // Time complexity: O(n)
    // Space complexity: O(w), w = maximum width of the tree
    private static int getMaxWidthUsingLevelOrderTraversal(BinaryTreeNode root) {
        if (root == null)
            return 0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        int maxSize = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            size = queue.size();
            maxSize = Math.max(size, maxSize);
            while (size-- > 0) {
                root = queue.poll();
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
        }
        return maxSize;
    }

    // Time complexity: O(n)
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int getMaxWidthUsingPreOrderTraversal(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int height = getHeight(root);
        int[] count = new int[height];
        getMaxWidthUsingPreOrderTraversalHelper(root, count, 0);
        return getMaxElement(count);
    }

    private static void getMaxWidthUsingPreOrderTraversalHelper(BinaryTreeNode root, int[] count, int level) {
        if (root != null) {
            count[level]++;
            getMaxWidthUsingPreOrderTraversalHelper(root.left, count, level + 1);
            getMaxWidthUsingPreOrderTraversalHelper(root.right, count, level + 1);
        }
    }

    private static int getHeight(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private static int getMaxElement(int[] count) {
        int maxElement = Integer.MIN_VALUE;
        for (int i : count)
            maxElement = Math.max(i, maxElement);
        return maxElement;
    }
}
