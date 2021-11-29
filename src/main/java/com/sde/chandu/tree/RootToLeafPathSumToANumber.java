package com.sde.chandu.tree;

public class RootToLeafPathSumToANumber {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Is there a root to leaf path with sum 10: " + hasPathSum(root, 10));
        System.out.println("Is there a root to leaf path with sum 7: " + hasPathSum(root, 7));
        System.out.println("Is there a root to leaf path with sum 9: " + hasPathSum(root, 9));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static boolean hasPathSum(BinaryTreeNode root, int sum) {
        return hasPathSumHelper(root, sum, 0);
    }

    private static boolean hasPathSumHelper(BinaryTreeNode root, int sum, int curSum) {
        if (root == null)
            return false;
        curSum += root.data;
        if (root.left == null && root.right == null && curSum == sum)
            return true;
        return hasPathSumHelper(root.left, sum, curSum) || hasPathSumHelper(root.right, sum, curSum);
    }
}
