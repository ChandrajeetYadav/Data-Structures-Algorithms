package com.chandu.dsa.tree.bst;

import com.chandu.dsa.tree.BinaryTreeNode;
import com.chandu.dsa.tree.TreeTraversal;

public class BinarySearchTree {
    public static void main(String[] args) {
        BinaryTreeNode root = createDemoBST();
        System.out.println("InOrder traversal of binary search tree:");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Is 50 present in BST: " + search(root, 50));
        System.out.println("Is 10 present in BST: " + searchIterative(root, 10));
    }

    public static BinaryTreeNode createBST(int[] arr){
        BinaryTreeNode root = null;
        for(int i=0; i<arr.length; i++)
            root = insert(root, arr[i]);
        return root;
    }

    //Time Complexity: O(h), h is height of tree
    //Time Complexity: O(n), if tree is skewed tree
    public static BinaryTreeNode insert(BinaryTreeNode root, int key){
        if(root == null){
            root = new BinaryTreeNode(key);
            return root;
        }
        if(key < root.data)
            root.left = insert(root.left, key);
        else if(key > root.data)
            root.right = insert(root.right, key);
        return root;
    }

    //Time Complexity: O(h), h is height of tree
    //Time Complexity: O(n), if tree is skewed tree
    public static BinaryTreeNode insertIterative(BinaryTreeNode root, int key){
        if(root == null){
            root = new BinaryTreeNode(key);
            return root;
        }
        BinaryTreeNode prev=null, curr=root;
        while(curr != null){
            prev = curr;
            if(key < curr.data)
                curr = curr.left;
            else if(key > curr.data)
                curr = curr.right;
        }

        if(key < prev.data)
            prev.left = new BinaryTreeNode(key);
        else if(key > prev.data)
            prev.right = new BinaryTreeNode(key);
        return root;
    }

    //Time Complexity: O(h), h is height of tree
    //Time Complexity: O(n), if tree is skewed tree
    public static boolean search(BinaryTreeNode root, int key){
        if(root == null)
            return false;
        if(root.data == key)
            return true;
        else if(key < root.data)
            return search(root.left, key);
        else
            return search(root.right, key);
    }

    //Time Complexity: O(h), h is height of tree
    //Time Complexity: O(n), if tree is skewed tree
    public static boolean searchIterative(BinaryTreeNode root, int key){
       while(root != null){
           if(key < root.data)
               root = root.left;
           else if(key > root.data)
               root = root.right;
           else
               return true;
       }
       return false;
    }

    public static BinaryTreeNode createDemoBST(){
        int[] arr = {50, 30, 20, 40, 70, 60, 80};
        BinaryTreeNode root = createBST(arr);
        return root;
    }
}
