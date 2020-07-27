package com.chandu.dsa.tree;

public class CreateMirrorTreeFromBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("Level order of original binary tree:");
        TreeTraversal.levelOrderTraversal(root);
        System.out.println();
        BinaryTreeNode mirrorTree = createMirrorTree(root);
        System.out.println("Level order traversal of Mirror tree:");
        TreeTraversal.levelOrderTraversal(mirrorTree);
    }

    //Time complexity = O(n)
    //Space complexity = O(H)
    public static BinaryTreeNode createMirrorTree(BinaryTreeNode root){
        if(root == null)
            return null;
        BinaryTreeNode mirrorTree = new BinaryTreeNode(root.data);
        mirrorTree.left = createMirrorTree(root.right);
        mirrorTree.right = createMirrorTree(root.left);

        return mirrorTree;
    }
}
