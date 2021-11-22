package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class OddEvenLevelDifference {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Difference between odd and even level sum, iterative: " + oddEvenLevelDifferenceIterative(root));
        System.out.println("Difference between odd and even level sum, recursive: " + oddEvenLevelDifferenceRecursive(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(w) = O(n) where w is the max width of the tree
    private static int oddEvenLevelDifferenceIterative(BinaryTreeNode root) {
        if (root == null)
            return 0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size, level = 0, odd = 0, even = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            level++;
            while (size-- > 0) {
                root = queue.poll();
                if (level % 2 == 0)
                    even += root.data;
                else
                    odd += root.data;
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
        }
        return odd - even;
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int oddEvenLevelDifferenceRecursive(BinaryTreeNode root) {
        if (root == null)
            return 0;
        return root.data - oddEvenLevelDifferenceRecursive(root.left) - oddEvenLevelDifferenceRecursive(root.right);
    }
}
