package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class CheckIfTwoNodesAreCousins {
    private static BinaryTreeNode parentA;
    private static BinaryTreeNode parentB;
    private static int levelA;
    private static int levelB;

    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Are 2 and 3 cousins, recursive approach: " + isCousinRecursive(root, 2, 3));
        System.out.println("Are 5 and 7 cousins, recursive approach: " + isCousinRecursive(root, 5, 7));
        System.out.println("Are 2 and 3 cousins, iterative approach: " + isCousinIterative(root, 2, 3));
        System.out.println("Are 5 and 7 cousins, iterative approach: " + isCousinIterative(root, 5, 7));
    }

    // Time complexity: O(n)
    // Time complexity: O(w), w = max width of the tree
    private static boolean isCousinIterative(BinaryTreeNode root, int a, int b) {
        if (root == null || root.data == a || root.data == b)
            return false;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        BinaryTreeNode parentA = null, parentB = null;
        queue.add(root);
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size-- > 0) {
                root = queue.poll();
                if (root.left != null) {
                    queue.add(root.left);
                    if (root.left.data == a)
                        parentA = root;
                    if (root.left.data == b)
                        parentB = root;
                }
                if (root.right != null) {
                    queue.add(root.right);
                    if (root.right.data == a)
                        parentA = root;
                    if (root.right.data == b)
                        parentB = root;
                }
            }
            if (parentA != null && parentB != null)
                return parentA != parentB;
            if ((parentA != null && parentB == null) || (parentA == null && parentB != null))
                return false;
        }
        return false;
    }

    // Time complexity : O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static boolean isCousinRecursive(BinaryTreeNode root, int a, int b) {
        if (root == null)
            return false;
        parentA = parentB = null;
        levelA = levelB = -1;
        isCousinRecursiveHelper(root, a, b, 1, null);
        if (levelA == -1 || levelB == -1 || parentA == null | parentB == null)
            return false;
        return (levelA == levelB) && (parentA != parentB);
    }

    private static void isCousinRecursiveHelper(BinaryTreeNode root, int a, int b, int level, BinaryTreeNode parent) {
        if (root == null)
            return;
        if (root.data == a) {
            parentA = parent;
            levelA = level;
        }
        if (root.data == b) {
            parentB = parent;
            levelB = level;
        }
        isCousinRecursiveHelper(root.left, a, b, level + 1, root);
        isCousinRecursiveHelper(root.right, a, b, level + 1, root);
    }
}
