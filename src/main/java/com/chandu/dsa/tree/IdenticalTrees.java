package com.chandu.dsa.tree;

import java.util.LinkedList;
import java.util.Queue;

public class IdenticalTrees {
    public static void main(String[] args) {
        BinaryTreeNode root1 = BinaryTreeNode.createDemoBinaryTree();
        BinaryTreeNode root2 = BinaryTreeNode.createDemoBinaryTree();

        System.out.println("InOrder traversal of tree 1: ");
        TreeTraversal.inOrderTraversal(root1);
        System.out.println();
        System.out.println("InOrder traversal of tree 2: ");
        TreeTraversal.inOrderTraversal(root2);
        System.out.println();
        System.out.println("Are tree1 and tree2 identical recursive: " + isIdenticalRecursive(root1, root2));
        System.out.println();
        System.out.println("Are tree1 and tree2 identical iterative: " + isIdenticalIterative(root1, root2));
    }

    //Time Complexity: O(m) where m<n
    //Space Complexity: O(m+n)
    public static boolean isIdenticalRecursive(BinaryTreeNode root1, BinaryTreeNode root2){
        if(root1==null && root2==null)
            return true;
        if(root1==null || root2==null)
            return false;
        return root1.data==root2.data
                && isIdenticalRecursive(root1.left, root2.left)
                && isIdenticalRecursive(root1.right, root2.right);
    }

    //Time Complexity: O(m) where m<n
    //Space Complexity: O(m+n)
    public static boolean isIdenticalIterative(BinaryTreeNode root1, BinaryTreeNode root2){
        if(root1 == null && root2 == null)
            return true;
        if(root1==null || root2==null)
            return false;
        Queue<BinaryTreeNode> queue1 = new LinkedList<BinaryTreeNode>();
        Queue<BinaryTreeNode> queue2 = new LinkedList<BinaryTreeNode>();
        queue1.add(root1);
        queue2.add(root2);
        BinaryTreeNode temp1, temp2;
        while(!queue1.isEmpty() && !queue2.isEmpty()){
            temp1 = queue1.poll();
            temp2 = queue2.poll();
            if(temp1.data != temp2.data)
                return false;
            if(temp1.left!=null && temp2.left!=null){
                queue1.add(temp1.left);
                queue2.add(temp2.left);
            }else if (temp1.left!=null || temp2.left!=null)
                return false;
            if (temp1.right!=null && temp2.right!=null){
                queue1.add(temp1.right);
                queue2.add(temp2.right);
            }else if (temp1.right!=null || temp2.right!=null)
                return false;
        }
        return true;
    }
}
