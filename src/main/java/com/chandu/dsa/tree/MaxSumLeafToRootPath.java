package com.chandu.dsa.tree;

public class MaxSumLeafToRootPath {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Maximum sum from leaf to root: " + maxSumLeafToRootPath(root));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(H)
    public static int maxSumLeafToRootPath(BinaryTreeNode root){
        if(root == null)
            return 0;
        Helper helper = new Helper();
        maxSumLeafToRootPath(root, helper, 0);
        System.out.println("Path for maximum sum from leaf to root: ");
        printPath(root, helper.targetLeaf);
        System.out.println();
        return helper.res;
    }

    public static void maxSumLeafToRootPath(BinaryTreeNode root, Helper helper, int currSum){
        if(root == null)
            return;
        currSum = currSum + root.data;
        if(root.left==null && root.right==null){
            if(currSum > helper.res){
                helper.res = currSum;
                helper.targetLeaf = root;
            }
        }
        maxSumLeafToRootPath(root.left, helper, currSum);
        maxSumLeafToRootPath(root.right, helper, currSum);
    }

    public static boolean printPath(BinaryTreeNode root, BinaryTreeNode targetLeaf){
        if(root == null)
            return false;
        if(root==targetLeaf || printPath(root.left, targetLeaf) || printPath(root.right, targetLeaf)){
            System.out.print(root.data + " ");
            return true;
        }
        return false;
    }

    static class Helper{
        int res = Integer.MIN_VALUE;
        BinaryTreeNode targetLeaf;
    }
}
