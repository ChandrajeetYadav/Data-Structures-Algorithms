package com.chandu.dsa.tree.bst;

import com.chandu.dsa.tree.BinaryTreeNode;
import com.chandu.dsa.tree.FindMinMax;
import com.chandu.dsa.tree.TreeTraversal;

public class CheckBST {
    public static void main(String[] args) {
        BinaryTreeNode root = BinarySearchTree.createDemoBST();
        System.out.println("InOrder traversal of BST: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        //System.out.println("Is tree BST: " + isBstIncorrectApproach(root));
        System.out.println("Is tree BST: " + isBst(root));
        System.out.println("Is tree BST using efficient approach: " + isBstEfficient(root));
        System.out.println("Is tree BST using InOrder approach: " + isBstUsingInOrder(root));
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    public static boolean isBstEfficient(BinaryTreeNode root){
        return isBstEfficient(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static  boolean isBstEfficient(BinaryTreeNode root, int min, int max){
        if(root == null)
            return true;
        return root.data>min && root.data<max
                && isBstEfficient(root.left, min, root.data)
                && isBstEfficient(root.right, root.data, max);
    }

    //Time complexity: O(n)
    //Space complexity: O(n)
    public static boolean isBstUsingInOrder(BinaryTreeNode root){
        Helper helper = new Helper();
        return isBstUsingInOrder(root, helper);
    }

    public static boolean isBstUsingInOrder(BinaryTreeNode root, Helper helper){
        if(root == null)
            return true;
        if(!isBstUsingInOrder(root.left, helper))
            return false;
        if(root.data < helper.prev)
            return false;
        helper.prev = root.data;
        return isBstUsingInOrder(root.right, helper);
    }

    //Time complexity: O(n^2)
    //Space complexity: O(n)
    public static boolean isBst(BinaryTreeNode root){
        if(root == null)
            return true;
        if(root.left!=null && root.data<FindMinMax.findMax(root.left))
            return false;
        if(root.right!=null && root.data>FindMinMax.findMin(root.right))
            return false;
        if(!isBst(root.left) || !isBst(root.right))
            return false;
        return true;
    }

    //This approach is wrong. Checking only at current node is not enough
    public static boolean isBstIncorrectApproach(BinaryTreeNode root){
        if(root == null)
            return true;
        if(root.left!=null && root.data < root.left.data)
            return false;
        if(root.right!=null && root.data > root.right.data)
            return false;
        if(!isBstIncorrectApproach(root.left) || !isBstIncorrectApproach(root.right))
            return false;
        return true;
    }

    static class Helper{
        int prev = Integer.MIN_VALUE;
    }
}
