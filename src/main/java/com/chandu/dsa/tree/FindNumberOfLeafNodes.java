package com.chandu.dsa.tree;

import java.util.LinkedList;
import java.util.Queue;

public class FindNumberOfLeafNodes {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of binary tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Number of leaf nodes in tree is: " + getNumberOfLeaveNodes(root));
        System.out.println("Number of leaf nodes in tree using iterative approach is: " + getNumberOfLeaveNodesIterative(root));
    }

    //Time complexity : O(n)
    //Space complexity : O(n)
    public static int getNumberOfLeaveNodes(BinaryTreeNode root){
        if(root == null)
            return 0;
        if(root.left==null && root.right==null)
            return 1;
        return getNumberOfLeaveNodes(root.left) + getNumberOfLeaveNodes(root.right);
    }

    //Time complexity : O(n)
    //Space complexity : O(n)
    public static int getNumberOfLeaveNodesIterative(BinaryTreeNode root){
        if(root == null)
            return 0;
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        int count = 0;
        while(!queue.isEmpty()){
            root = queue.poll();
            if(root.left==null && root.right==null)
                count++;
            else{
                if(root.left != null)
                    queue.add(root.left);
                if(root.right != null)
                    queue.add(root.right);
            }
        }
        return count;
    }
}
