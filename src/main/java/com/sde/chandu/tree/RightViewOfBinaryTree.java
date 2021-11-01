package com.sde.chandu.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightViewOfBinaryTree {
    private static int maxLevel;

    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Right view of binary tree, using level order traversal: " + getRightViewUsingLevelOrderTraversal(root));
        System.out.println("Right view of binary tree, using recusrive pre order traversal: " + getRightViewRecursivePreOrderTraversal(root));
    }

    // Time complexity: O(n)
    // Space complexity: (O(h) + O(w)), h = Height of binary tree, w = max width of binary tree
    private static List<Integer> getRightViewUsingLevelOrderTraversal(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> result = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size > 0) {
                root = queue.poll();
                if (size == 1)
                    result.add(root.data);
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
                size--;
            }
        }
        return result;
    }

    // Time complexity: O(n)
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static List<Integer> getRightViewRecursivePreOrderTraversal(BinaryTreeNode root) {
        if (root == null)
            return null;
        List<Integer> result = new ArrayList<>();
        maxLevel = Integer.MIN_VALUE;
        getRightViewRecursivePreOrderTraversalHelper(root, result, 0);
        return result;
    }

    private static void getRightViewRecursivePreOrderTraversalHelper(BinaryTreeNode root, List<Integer> result, int level) {
        if (root == null)
            return;
        if (level > maxLevel) {
            maxLevel = level;
            result.add(root.data);
        }
        getRightViewRecursivePreOrderTraversalHelper(root.right, result, level + 1);
        getRightViewRecursivePreOrderTraversalHelper(root.left, result, level + 1);
    }
}
