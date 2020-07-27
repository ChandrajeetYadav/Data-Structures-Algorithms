package com.chandu.dsa.tree;

public class LongestConsecutiveSequenceLength {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of binary tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Length of longest consecutive sequence is: " +
                getLongestConsecutiveSequenceLength(root));
    }

    //Time complexity: O(n)
    //Space complexity: O(H)
    public static int getLongestConsecutiveSequenceLength(BinaryTreeNode root){
        if(root == null)
            return 0;
        Helper helper = new Helper();
        getLongestConsecutiveSequenceLength(root, helper, 0, root.data);
        return helper.len;
    }

    public static void getLongestConsecutiveSequenceLength(BinaryTreeNode root, Helper helper, int curLen, int expectedVal){
        if(root == null)
            return;
        if(root.data == expectedVal)
            curLen++;
        else
            curLen = 1;
        helper.len = Math.max(helper.len, curLen);
        getLongestConsecutiveSequenceLength(root.left, helper, curLen, root.data + 1);
        getLongestConsecutiveSequenceLength(root.right, helper, curLen, root.data + 1);
    }

    static class Helper{
        int len;
    }
}
