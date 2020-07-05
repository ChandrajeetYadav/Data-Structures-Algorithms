package com.chandu.dsa.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//Symmetric means Mirror Image
public class SymmetricTree {

    public static void main(String[] args) {
        BinaryTreeNode root1 = BinaryTreeNode.createDemoBinaryTree();
        BinaryTreeNode root2 = BinaryTreeNode.createDemoBinaryTree1();

        System.out.println("InOrder traversal of tree 1: ");
        TreeTraversal.inOrderTraversal(root1);
        System.out.println();
        System.out.println("InOrder traversal of tree 2: ");
        TreeTraversal.inOrderTraversal(root2);
        System.out.println();
        System.out.println("Are tree1 and tree2 mirror of each other using recursion: " + isMirrorRecursive(root1, root2));
        System.out.println("Are tree1 and tree2 mirror of each other using iterative: " + isMirrorIterative(root1, root2));
        System.out.println("Is tree1 mirror of itself: " + isMirrorIterative2(root1));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static boolean isMirrorRecursive(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        return root1.data == root2.data &&
                isMirrorRecursive(root1.left, root2.right) &&
                isMirrorRecursive(root1.right, root2.left);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static boolean isMirrorIterative(BinaryTreeNode root1, BinaryTreeNode root2){
        Stack<BinaryTreeNode> stack1 = new Stack<BinaryTreeNode>();
        Stack<BinaryTreeNode> stack2 = new Stack<BinaryTreeNode>();

        while(true){
            while(root1 != null  && root2 != null){
                if(root1.data != root2.data)
                    return false;
                stack1.push(root1);
                stack2.push(root2);
                root1 = root1.left;
                root2 = root2.right;
            }

            if(!(root1==null && root2==null))
                return false;

            if(!stack1.isEmpty() && !stack2.isEmpty()){
                root1 = stack1.pop();
                root2 = stack2.pop();

                root1 = root1.right;
                root2 = root2.left;
            }else
                break;
        }
        return true;
    }

    //Is tree a mirror of itself
    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static boolean isMirrorIterative2(BinaryTreeNode root){
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        queue.add(root);
        BinaryTreeNode temp1, temp2;

        while(!queue.isEmpty()){
            temp1 = queue.poll();
            temp2 = queue.poll();

            if(temp1==null && temp2==null)
                continue;
            if(temp1==null || temp2==null)
                return false;
            if(temp1.data != temp2.data)
                return false;

            queue.add(temp1.left);
            queue.add(temp2.right);
            queue.add(temp1.right);
            queue.add(temp2.left);
        }
         return true;
    }
}
