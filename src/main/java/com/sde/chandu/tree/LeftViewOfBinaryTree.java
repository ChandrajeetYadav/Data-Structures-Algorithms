package com.sde.chandu.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeftViewOfBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Left view of binary tree, recursive approach: " + leftViewRecursive(root));
        System.out.println("Left view of binary tree, iterative approach: " + leftViewIterative(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(w) = O(n) where w is the max width of the tree
    private static List<Integer> leftViewIterative(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> result = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            result.add(queue.peek().data);
            while (size-- > 0) {
                root = queue.poll();
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
        }
        return result;
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static List<Integer> leftViewRecursive(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> result = new ArrayList<>();
        leftViewRecursiveHelper(root, 1, result, new Level());
        return result;
    }

    private static void leftViewRecursiveHelper(BinaryTreeNode root, int curLevel, List<Integer> result, Level level) {
        if (root == null)
            return;
        if (curLevel > level.maxLevel) {
            result.add(root.data);
            level.maxLevel = curLevel;
        }
        leftViewRecursiveHelper(root.left, curLevel + 1, result, level);
        leftViewRecursiveHelper(root.right, curLevel + 1, result, level);
    }

    private static class Level {
        int maxLevel;
    }
}
