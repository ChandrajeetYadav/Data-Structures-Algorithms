package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOfANodeInBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Level of node 3, iterative approach: " + getLevelOfNodeIterative(root, 3));
        System.out.println("Level of node 8, iterative approach: " + getLevelOfNodeIterative(root, 8));
        System.out.println("Level of node 1, recursive approach: " + getLevelOfNodeRecursive(root, 1));
        System.out.println("Level of node 10, recursive approach: " + getLevelOfNodeRecursive(root, 10));
    }

    // Time complexity: O(n)
    // Space complexity : O(w) = O(n) where w is the max width of the tree
    private static int getLevelOfNodeIterative(BinaryTreeNode root, int target) {
        if (root == null)
            return 0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0, size;
        while (!queue.isEmpty()) {
            size = queue.size();
            level++;
            while (size-- > 0) {
                root = queue.poll();
                if (root.data == target)
                    return level;
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
        }
        return 0;
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int getLevelOfNodeRecursive(BinaryTreeNode root, int target) {
        return getLevelOfNodeRecursiveHelper(root, target, 1);
    }

    private static int getLevelOfNodeRecursiveHelper(BinaryTreeNode root, int target, int level) {
        if (root == null)
            return 0;
        if (root.data == target)
            return level;
        int result = getLevelOfNodeRecursiveHelper(root.left, target, level + 1);
        if (result != 0)
            return result;
        return getLevelOfNodeRecursiveHelper(root.right, target, level + 1);
    }
}
