package com.chandu.dsa.tree;

public class FindMinMax {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Maximum element in tree is: " + findMax(root));
        System.out.println("Minimum element in tree is: " + findMin(root));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static int findMax(BinaryTreeNode root){
        if(root == null)
            return Integer.MIN_VALUE;
        int res = root.data;
        int lMax = findMax(root.left);
        int rMax = findMax(root.right);

        return Math.max(res, Math.max(lMax, rMax));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static int findMin(BinaryTreeNode root){
        if(root == null)
            return Integer.MAX_VALUE;
        int res = root.data;
        int lMin = findMin(root.left);
        int rMin = findMin(root.right);

        return Math.min(res, Math.min(lMin, rMin));
    }
}
