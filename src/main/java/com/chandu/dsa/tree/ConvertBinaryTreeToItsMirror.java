package com.chandu.dsa.tree;

import java.util.LinkedList;
import java.util.Queue;

public class ConvertBinaryTreeToItsMirror {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("Level Order traversal of tree: ");
        TreeTraversal.levelOrderTraversal(root);
        System.out.println();
        //convertToMirrorRecursive(root);
        convertToMirrorIterative(root);
        System.out.println("Level Order traversal of tree after converting to its mirror:");
        TreeTraversal.levelOrderTraversal(root);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static BinaryTreeNode convertToMirrorIterative(BinaryTreeNode root){
        if(root == null)
            return null;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        BinaryTreeNode temp;
        while(!queue.isEmpty()){
            root = queue.poll();
            temp = root.left;
            root.left = root.right;
            root.right = temp;
            if(root.left != null)
                queue.add(root.left);
            if(root.right != null)
                queue.add(root.right);
        }
        return root;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static BinaryTreeNode convertToMirrorRecursive(BinaryTreeNode root){
        if (root == null)
            return  null;
        BinaryTreeNode left = convertToMirrorRecursive(root.left);
        BinaryTreeNode right = convertToMirrorRecursive(root.right);

        root.left = right;
        root.right = left;
        return root;
    }
}
