package com.chandu.dsa.tree;

import java.util.LinkedList;
import java.util.Queue;

public class LeftViewOfBinaryTree {
    static int currentLevel;
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree:");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Left view of Binary Tree is:");
        //leftViewIterative(root);
        leftViewRecursive(root);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static void leftViewIterative(BinaryTreeNode root){
        if(root == null)
            return;
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        int size, i;
        BinaryTreeNode temp;

        while(!queue.isEmpty()){
            size = queue.size();
            i = 0;

            while(i++ < size){
                temp = queue.poll();
                if(i == 1)
                    System.out.print(temp.data + " ");
                if(temp.left != null)
                    queue.add(temp.left);
                if(temp.right != null)
                    queue.add(temp.right);
            }
        }
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static void leftViewRecursive(BinaryTreeNode root){
        leftViewRecursive(root, 1);
    }

    public static void leftViewRecursive(BinaryTreeNode root, int level){
        if(root == null)
            return;
        if(currentLevel < level){
            System.out.print(root.data + " ");
            currentLevel = level;
        }

        leftViewRecursive(root.left, level+1);
        leftViewRecursive(root.right, level+1);
    }
}
