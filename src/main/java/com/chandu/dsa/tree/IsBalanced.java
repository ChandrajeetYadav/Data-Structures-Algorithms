package com.chandu.dsa.tree;

public class IsBalanced {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree is: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Is Tree balanced: " + isBalancedBruteForce(root));
        System.out.println("Is Tree balanced using efficient approach: " + isBalancedEfficient(root));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(H), H is height of tree
    public static boolean isBalancedEfficient(BinaryTreeNode root){
        return checkHeight(root)!=-1;
    }

    public static int checkHeight(BinaryTreeNode root){
        if(root == null)
            return 0;
        int leftHeight = checkHeight(root.left);
        if(leftHeight == -1)
            return -1;
        int rightHeight = checkHeight(root.right);
        if(rightHeight == -1)
            return -1;
        if(Math.abs(leftHeight-rightHeight) > 1)
            return -1;
        else
            return 1 + Math.max(leftHeight, rightHeight);
    }

    //Time Complexity: O(n^2)
    //Space Complexity: O(h)
    public static boolean isBalancedBruteForce(BinaryTreeNode root){
        if(root == null)
            return true;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if(Math.abs(leftHeight-rightHeight) > 1)
            return false;
        return isBalancedBruteForce(root.left) && isBalancedBruteForce(root.right);
    }

    public static int getHeight(BinaryTreeNode root){
        if(root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}
