package com.chandu.dsa.tree;

public class DiameterOfTree {

    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree:");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Diameter of tree is : " + diameter(root));

        System.out.println();
        System.out.println("Diameter of tree in efficient way is : " + diameterEfficient(root));
    }

    //Time Complexity: O(n^2)
    //Space Complexity: O(n)
    public static int diameter(BinaryTreeNode root){
        if(root == null)
            return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int leftDiameter = diameter(root.left);
        int rightDiameter = diameter(root.right);

        return Math.max(leftHeight + rightHeight + 1, Math.max(leftDiameter, rightDiameter));
    }

    public static int height(BinaryTreeNode node){
        if(node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static int diameterEfficient(BinaryTreeNode root){
        if(root == null)
            return 0;
        Helper helper = new Helper();
        int height = height(root, helper);
        return helper.val;
    }

    public static int height(BinaryTreeNode root, Helper helper){
        if(root == null)
            return 0;
        int leftHeight = height(root.left, helper);
        int rightHeight = height(root.right, helper);

        helper.val = Math.max(helper.val, 1 + leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    static class Helper{
        int val = Integer.MIN_VALUE;
    }
}
