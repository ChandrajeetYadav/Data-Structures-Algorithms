package com.chandu.dsa.tree;

public class MaxPathSumBetweenTwoLeaves {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder Traversal of Binary tree:");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Maximum path sum between two leaves: " +
                getMaxPathSumBetweenTwoLeavesBrute(root));
        System.out.println("Maximum path sum between two leaves in efficient way: " +
                getMaxPathSumBetweenTwoLeavesEfficient(root));
    }

    //Time complexity: O(n)
    //Space complexity: O(H)
    public static int getMaxPathSumBetweenTwoLeavesEfficient(BinaryTreeNode root){
        if(root == null)
            return 0;
        Helper helper = new Helper();
        getMaxPathSumBetweenTwoLeavesEfficient(root, helper);
        return helper.res;
    }

    public static int getMaxPathSumBetweenTwoLeavesEfficient(BinaryTreeNode root, Helper helper){
        if(root == null)
            return 0;
        if(root.left==null && root.right==null)
            return root.data;
        int leftSum = getMaxPathSumBetweenTwoLeavesEfficient(root.left, helper);
        int rightSum = getMaxPathSumBetweenTwoLeavesEfficient(root.right, helper);

        if(root.left!=null && root.right!=null){
            helper.res = Math.max(helper.res, root.data + leftSum + rightSum);
            return Math.max(leftSum, rightSum) + root.data;
        }
        return root.left==null ? rightSum + root.data : leftSum + root.data;
    }

    //Time complexity: O(n^2)
    //Space complexity: O(H)
    public static int getMaxPathSumBetweenTwoLeavesBrute(BinaryTreeNode root){
        if(root == null)
            return 0;
        Helper helper = new Helper();
        getMaxPathSumBetweenTwoLeavesBrute(root, helper);
        return helper.res;
    }
    public static void getMaxPathSumBetweenTwoLeavesBrute(BinaryTreeNode root, Helper helper){
        if(root == null)
            return;
        int leftSum = MaxSumLeafToRootPath.maxSumLeafToRootPath(root.left);
        int rightSum = MaxSumLeafToRootPath.maxSumLeafToRootPath(root.right);
        int currSum = root.data + leftSum + rightSum;

        helper.res = Math.max(helper.res, currSum);
        getMaxPathSumBetweenTwoLeavesBrute(root.left, helper);
        getMaxPathSumBetweenTwoLeavesBrute(root.right, helper);
    }


    static class Helper {
        int res = Integer.MIN_VALUE;
    }
}