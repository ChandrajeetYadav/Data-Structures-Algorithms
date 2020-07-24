package com.chandu.dsa.tree;

public class MaxPathSum {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Max path sum is: " + getMaxPathSum(root));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(H)
    public static int getMaxPathSum(BinaryTreeNode root){
        Helper helper = new Helper();
        getMaxPathSum(root, helper);
        return helper.res;
    }

    public static int getMaxPathSum(BinaryTreeNode root, Helper helper){
        if(root == null)
            return 0;
        int leftSum = getMaxPathSum(root.left, helper);
        int rightSum = getMaxPathSum(root.right, helper);
        int maxSingle = Math.max(Math.max(leftSum, rightSum)+root.data, root.data);
        int maxTop = Math.max(maxSingle, leftSum + rightSum + root.data);
        helper.res = Math.max(helper.res, maxTop);
        return maxSingle;
    }

    static class Helper{
        int res = Integer.MIN_VALUE;
    }
}
