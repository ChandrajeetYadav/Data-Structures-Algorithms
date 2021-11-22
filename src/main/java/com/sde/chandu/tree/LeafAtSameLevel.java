package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class LeafAtSameLevel {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Are all leaves at same level, recursive approach: " + areLeavesAtSameLevelRecursive(root));
        System.out.println("Are all leaves at same level, iterative approach: " + areLeavesAtSameLevelIterative(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(w) = O(n) where w is the max width of the tree
    private static boolean areLeavesAtSameLevelIterative(BinaryTreeNode root) {
        if (root == null)
            return true;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        int firstLeafLevel = 0, level = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            level++;
            while (size-- > 0) {
                root = queue.poll();
                if (root.left == null && root.right == null) {
                    if (firstLeafLevel == 0)
                        firstLeafLevel = level;
                    else if (firstLeafLevel != level)
                        return false;
                }
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
        }
        return true;
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static boolean areLeavesAtSameLevelRecursive(BinaryTreeNode root) {
        LeafLevel leafLevel = new LeafLevel();
        return areLeavesAtSameLevelHelper(root, 0, leafLevel);
    }

    private static boolean areLeavesAtSameLevelHelper(BinaryTreeNode root, int level, LeafLevel leafLevel) {
        if (root == null)
            return true;
        if (root.left == null && root.right == null) {
            if (leafLevel.level == 0)
                leafLevel.level = level;
            return leafLevel.level == level;
        }
        return areLeavesAtSameLevelHelper(root.left, level + 1, leafLevel)
                && areLeavesAtSameLevelHelper(root.right, level + 1, leafLevel);
    }

    private static class LeafLevel {
        int level;
    }
}
