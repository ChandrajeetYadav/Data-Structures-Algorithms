package com.chandu.dsa.tree;

import java.util.LinkedList;
import java.util.Queue;

public class HeightOfTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree:");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Height of Tree is: " + HeightOfTree.height(root));
        System.out.println("Level order traversal: Height of Tree is: " + HeightOfTree.heightUsingLevelOrderTraversal(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    public static int height(BinaryTreeNode root){
        if(root == null)
            return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return leftHeight>rightHeight ? leftHeight+1 : rightHeight+1;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    private static int heightUsingLevelOrderTraversal(BinaryTreeNode root) {
        if (root == null)
            return 0;
        int depth = 0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            depth++;
            while (size-- > 0) {
                root = queue.poll();
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
        }
        return depth;
    }
}
