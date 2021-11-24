package com.sde.chandu.tree;

public class LongestConsecutiveSequenceInBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Longest consecutive sequence length, recursive approach: " + getLongestConsecutiveSequenceRecursive(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int getLongestConsecutiveSequenceRecursive(BinaryTreeNode root) {
        Result result = new Result();
        getLongestConsecutiveSequenceHelper(root, 0, root.data, result);
        return result.result < 2 ? -1 : result.result;
    }

    private static void getLongestConsecutiveSequenceHelper(BinaryTreeNode root, int curLength, int expected, Result result) {
        if (root == null)
            return;
        if (root.data == expected)
            curLength++;
        else
            curLength = 1;
        result.result = Math.max(result.result, curLength);
        getLongestConsecutiveSequenceHelper(root.left, curLength, root.data + 1, result);
        getLongestConsecutiveSequenceHelper(root.right, curLength, root.data + 1, result);
    }

    private static class Result {
        int result;
    }
}
