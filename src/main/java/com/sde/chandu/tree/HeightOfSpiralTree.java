package com.sde.chandu.tree;

public class HeightOfSpiralTree {
    public static void main(String[] args) {
        BinaryTreeNode root = createSpiralTree();
        System.out.println("Height of spiral tree is: " + getHeightOfSpiralTree(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int getHeightOfSpiralTree(BinaryTreeNode root) {
        if (root == null)
            return 0;
        if (isLeaf(root))
            return 1;
        return 1 + Math.max(getHeightOfSpiralTree(root.left), getHeightOfSpiralTree(root.right));
    }

    private static boolean isLeaf(BinaryTreeNode root) {
        return root.right != null && root == root.right.left
                && root.left != null && root == root.left.right;
    }

    private static BinaryTreeNode createSpiralTree() {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.left.left.left = new BinaryTreeNode(6);

        BinaryTreeNode l1 = root.left.left.left;
        BinaryTreeNode l2 = root.left.right;
        BinaryTreeNode l3 = root.right;

        l1.right = l2;
        l2.right = l3;
        l3.right = l1;

        l3.left = l2;
        l2.left = l1;
        l1.left = l3;

        return root;
    }
}
