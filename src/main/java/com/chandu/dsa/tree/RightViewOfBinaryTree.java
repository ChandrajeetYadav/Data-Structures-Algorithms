package com.chandu.dsa.tree;

import java.util.LinkedList;
import java.util.Queue;

public class RightViewOfBinaryTree {
    static int currentLevel = 0;
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree:");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Right view of Binary Tree with iterative approach:");
        rightViewIterative(root);
        System.out.println();
        System.out.println("Right view of Binary Tree with recursive approach:");
        rightViewRecursive(root);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static void rightViewIterative(BinaryTreeNode root){
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
                if(i == size)
                    System.out.print(temp.data + " ");
                if(temp.left != null)
                    queue.add(temp.left);
                if(temp.right != null)
                    queue.add(temp.right);
            }
        }
    }

    public static void rightViewRecursive(BinaryTreeNode root){
        rightViewRecursive(root, 1);
    }

    public static void rightViewRecursive(BinaryTreeNode root, int level){
        if(root == null)
            return;
        if(currentLevel < level){
            System.out.print(root.data + " ");
            currentLevel = level;
        }

        rightViewRecursive(root.right, level + 1);
        rightViewRecursive(root.left, level + 1);
    }
}
