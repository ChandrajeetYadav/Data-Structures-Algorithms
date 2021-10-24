package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumNodeLevelInBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Max node level is: " + getMaxNodeLevel(root));
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static int getMaxNodeLevel(BinaryTreeNode root) {
        if (root == null)
            return -1;
        int maxNodeLevel = Integer.MIN_VALUE;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size, prevSize = -1, level = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            if (size > prevSize) {
                prevSize = size;
                maxNodeLevel = level;
            }
            while (size > 0) {
                root = queue.poll();
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
                size--;
            }
            level++;
        }
        return maxNodeLevel;
    }
}
