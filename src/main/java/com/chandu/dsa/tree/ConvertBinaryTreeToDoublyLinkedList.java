package com.chandu.dsa.tree;

import java.util.ArrayList;
import java.util.List;

public class ConvertBinaryTreeToDoublyLinkedList {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder Traversal of tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        root = convertBinaryTreeToDLLBrute(root);
        printDll(root);

        root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder Traversal of tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        root = convertBinaryTreeToDLLUsingInOrder(root);
        printDll(root);

        root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder Traversal of tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        root = convertBinaryTreeToDLLUsingInOrder2(root);
        printDll(root);

        root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder Traversal of tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        root = convertBinaryTreeToDLLUsingReverseInOrder(root);
        printDll(root);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(H)
    public static BinaryTreeNode convertBinaryTreeToDLLUsingReverseInOrder(BinaryTreeNode root) {
        Helper helper = new Helper();
        convertBinaryTreeToDLLUsingReverseInOrder(root, helper);
        return helper.head;
    }

    public static void convertBinaryTreeToDLLUsingReverseInOrder(BinaryTreeNode root, Helper helper) {
        if(root == null)
            return;
        convertBinaryTreeToDLLUsingReverseInOrder(root.right, helper);
        root.right = helper.head;
        if(helper.head != null)
            helper.head.left = root;
        helper.head = root;
        convertBinaryTreeToDLLUsingReverseInOrder(root.left, helper);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(H)
    //The solution does single traversal of all Binary Tree nodes.
    public static BinaryTreeNode convertBinaryTreeToDLLUsingInOrder2(BinaryTreeNode root){
        Helper helper = new Helper();
        convertBinaryTreeToDLLUsingInOrder2Helper(root, helper);
        return helper.head;
    }

    public static void convertBinaryTreeToDLLUsingInOrder2Helper(BinaryTreeNode root, Helper helper){
        if(root == null)
            return;
        convertBinaryTreeToDLLUsingInOrder2Helper(root.left, helper);
        if(helper.prev == null)
            helper.head = root;
        else{
            root.left = helper.prev;
            helper.prev.right = root;
        }
        helper.prev = root;
        convertBinaryTreeToDLLUsingInOrder2Helper(root.right, helper);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(H)
    //The solution simply does two traversals of all Binary Tree nodes.
    public static BinaryTreeNode convertBinaryTreeToDLLUsingInOrder(BinaryTreeNode root){
        Helper helper = new Helper();
        fixPrev(root, helper);
        return fixNext(root);
    }

    public static void fixPrev(BinaryTreeNode root, Helper helper){
        if(root == null)
            return;
        fixPrev(root.left, helper);
        root.left = helper.prev;
        helper.prev = root;
        fixPrev(root.right, helper);
    }

    static class Helper{
        BinaryTreeNode prev;
        BinaryTreeNode head;
    }

    public static BinaryTreeNode fixNext(BinaryTreeNode root){
        if(root == null)
            return null;
        while(root.right != null)
            root = root.right;
        BinaryTreeNode left;
        while(root.left!=null){
            left = root.left;
            left.right = root;
            root = root.left;
        }
        return root;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static BinaryTreeNode convertBinaryTreeToDLLBrute(BinaryTreeNode root){
        if(root == null)
            return null;
        List<Integer> list = new ArrayList<Integer>();
        list = inOrderTraversal(root, list);
        if(list.size() == 1)
            return root;
        BinaryTreeNode prev = null, next, temp;
        root = new BinaryTreeNode(list.get(0));
        temp = root;
        for(int i=1; i<list.size(); i++){
            next = new BinaryTreeNode(list.get(i));
            root.left = prev;
            root.right = next;
            prev = root;
            root = next;
        }
        root.left = prev;
        root.right = null;
        return temp;
    }

    public static List<Integer> inOrderTraversal(BinaryTreeNode root, List<Integer> list){
        if(root == null)
            return list;
        inOrderTraversal(root.left, list);
        list.add(root.data);
        inOrderTraversal(root.right, list);
        return list;
    }

    public static void printDll(BinaryTreeNode root){
        if(root == null)
            return;
        System.out.println("Printing list in forward direction:");
        while(root.right != null){
            System.out.print(root.data + " ");
            root = root.right;
        }
        System.out.println(root.data + " ");

        System.out.println("Printing list in backward direction:");
        while(root.left != null){
            System.out.print(root.data + " ");
            root = root.left;
        }
        System.out.println(root.data + " ");
    }
}
