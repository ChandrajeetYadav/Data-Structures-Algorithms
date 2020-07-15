package com.chandu.dsa.tree.bst;

import com.chandu.dsa.tree.BinaryTreeNode;
import com.chandu.dsa.tree.TreeTraversal;

public class LcaInBST {
    public static void main(String[] args) {
        BinaryTreeNode root = BinarySearchTree.createDemoBST();
        System.out.println("InOrder traversal of tree is: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("LCA of 20 and 40 is: " + lcaRecursive(root, 20, 40).data);
        System.out.println("LCA of 50 and 40 is: " + lcaRecursive(root, 50, 40).data);
        System.out.println("LCA of 30 and 80 is: " + lcaRecursive(root, 30, 80).data);
        System.out.println("LCA of 60 and 80 is: " + lcaRecursive(root, 60, 80).data);
        System.out.println("LCA of 45 and 80 is: " + lcaRecursive(root, 45, 80).data);
        System.out.println("LCA of 45 and 90 is: " + lcaRecursive(root, 45, 90).data);

        System.out.println("InOrder traversal of tree is: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("LCA of 20 and 40 is: " + lcaIterative(root, 20, 40).data);
        System.out.println("LCA of 50 and 40 is: " + lcaIterative(root, 50, 40).data);
        System.out.println("LCA of 30 and 80 is: " + lcaIterative(root, 30, 80).data);
        System.out.println("LCA of 60 and 80 is: " + lcaIterative(root, 60, 80).data);
        System.out.println("LCA of 45 and 80 is: " + lcaIterative(root, 45, 80));
        System.out.println("LCA of 45 and 90 is: " + lcaIterative(root, 45, 90));
    }

    //Time Complexity: O(H)
    //Space Complexity: O(H)
    //This method assumes that both n1 and n2 are present in BST
    public static BinaryTreeNode lcaRecursive(BinaryTreeNode root, int n1, int n2){
        if(root == null)
            return null;
        if(root.data>n1 && root.data>n2)
            return lcaRecursive(root.left, n1, n2);
        else if(root.data<n1 && root.data<n2)
            return lcaRecursive(root.right, n1, n2);
        return root;
    }

    //Time Complexity: O(H)
    //Space Complexity: O(1)
    public static  BinaryTreeNode lcaIterative(BinaryTreeNode root, int n1, int n2){
        while(root != null){
            if(root.data>n1 && root.data>n2)
                root = root.left;
            else if(root.data<n1 && root.data<n2)
                root = root.right;
            else
                break;
        }
        if(isPresent(root, n1) && isPresent(root, n2))
            return root;
        return null;
    }

    public static boolean isPresent(BinaryTreeNode root, int key){
        while(root != null){
            if(root.data > key)
                root = root.left;
            else if(root.data < key)
                root = root.right;
            else
                return true;
        }
        return false;
    }
}
