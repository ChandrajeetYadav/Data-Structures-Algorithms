package com.chandu.dsa.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static void inOrderTraversal(BinaryTreeNode root){
        if(root == null)
            return;
        inOrderTraversal(root.left);
        System.out.print(root.data +" ");
        inOrderTraversal(root.right);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static void preOrderTraversal(BinaryTreeNode root){
        if(root == null)
            return;
        System.out.print(root.data + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static void postOrderTraversal(BinaryTreeNode root){
        if(root == null)
            return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data + " ");
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static void preOrderWithoutRecursion(BinaryTreeNode root){
        if(root == null)
            return;
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            BinaryTreeNode temp = stack.pop();
            System.out.print(temp.data + " ");
            if(temp.right != null)
                stack.push(temp.right);
            if(temp.left != null)
                stack.push(temp.left);
        }
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static void inOrderWithoutRecursion(BinaryTreeNode root){
        if(root == null)
            return;
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        BinaryTreeNode current = root;
        boolean done = false;

        while(!done){
            if(current != null){
                stack.push(current);
                current = current.left;
            }else{
                if(stack.isEmpty())
                    done = true;
                else{
                    current = stack.pop();
                    System.out.print(current.data + " ");
                    current = current.right;
                }
            }
        }
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static void postOrderWithoutRecursion(BinaryTreeNode root){
        if(root == null)
            return;
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        stack.push(root);
        BinaryTreeNode current, prev=null;

        while(!stack.isEmpty()){
            current = stack.peek();
            if(prev==null || prev.left==current || prev.right==current){
                if(current.left != null)
                    stack.push(current.left);
                else if(current.right != null)
                    stack.push(current.right);
            }else if(current.left == prev){
                if(current.right != null)
                    stack.push(current.right);
            }else {
                System.out.print(current.data + " ");
                stack.pop();
            }
            prev = current;
        }
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static void levelOrderTraversal(BinaryTreeNode root){
        if(root == null)
            return;
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        BinaryTreeNode temp;

        while(!queue.isEmpty()){
            temp = queue.poll();
            System.out.print(temp.data + " ");
            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
        }
    }
}
