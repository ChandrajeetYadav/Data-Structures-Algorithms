package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class HeightOfATree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Height of the tree using recursion: " + getHeightRecursive(root));
        System.out.println("Height of the tree using level order traversal: " + getHeightUsingLevelOrderTraversal(root));
    }

    // Time complexity : O(n)
    // Space complexity : O(1) if we don't consider stack size for function all.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    public static int getHeightRecursive(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int lHeight = getHeightRecursive(root.left);
        int rHeight = getHeightRecursive(root.right);
        return Math.max(lHeight, rHeight) + 1;
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    public static int getHeightUsingLevelOrderTraversal(BinaryTreeNode root) {
        if (root == null)
            return 0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        int height = 0;
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root == null)
                height++;
            if (root != null) {
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            } else if (!queue.isEmpty())
                queue.add(null);
        }
        return height;
    }
}
