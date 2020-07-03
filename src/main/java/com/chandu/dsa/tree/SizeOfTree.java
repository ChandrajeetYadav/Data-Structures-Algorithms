package com.chandu.dsa.tree;

public class SizeOfTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree:");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Size of Tree is: " + SizeOfTree.getSize(root));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static int getSize(BinaryTreeNode root){
        if (root == null)
            return 0;
        return getSize(root.left) + getSize(root.right) + 1;
    }
}
