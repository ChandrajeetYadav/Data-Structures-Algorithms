package com.chandu.dsa.tree;

public class BinaryTreeNode {
    public int data;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(){}

    public BinaryTreeNode(int data){
        this.data = data;
        left = null;
        right = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public static BinaryTreeNode createDemoBinaryTree(){
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(3);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(6);
        root.right.right = new BinaryTreeNode(7);
        return root;
    }

    //Creating to check for mirror
    public static BinaryTreeNode createDemoBinaryTree1(){
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.left = new BinaryTreeNode(3);
        root.right = new BinaryTreeNode(2);
        root.left.left = new BinaryTreeNode(7);
        root.left.right = new BinaryTreeNode(6);
        root.right.left = new BinaryTreeNode(5);
        root.right.right = new BinaryTreeNode(4);
        return root;
    }
}
