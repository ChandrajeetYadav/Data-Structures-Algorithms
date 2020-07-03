package com.chandu.dsa.tree;

public class HeightOfTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree:");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Height of Tree is: " + HeightOfTree.height(root));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static int height(BinaryTreeNode root){
        if(root == null)
            return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return leftHeight>rightHeight ? leftHeight+1 : rightHeight+1;
    }
}
