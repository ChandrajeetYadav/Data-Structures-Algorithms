package com.chandu.dsa.tree;

import java.util.ArrayList;
import java.util.List;

public class LCAinBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("LCA of 2 and 4 is: " + findLcaBrute(root, 2, 4));
        System.out.println("LCA of 4 and 7 is: " + findLcaBrute(root, 4, 7));
        System.out.println("LCA of 4 and 8 is: " + findLcaBrute(root, 4, 8));
        System.out.println("InOrder traversal of tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("LCA of 2 and 4 is: " + findLcaEfficientIfBothNodesArePresent(root, 2, 4).data);
        System.out.println("LCA of 4 and 7 is: " + findLcaEfficientIfBothNodesArePresent(root, 4, 7).data);System.out.println("InOrder traversal of tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("LCA of 2 and 4 is: " + findLcaEfficient(root, 2, 4));
        System.out.println("LCA of 4 and 7 is: " + findLcaEfficient(root, 4, 7));
        System.out.println("LCA of 4 and 7 is: " + findLcaEfficient(root, 9, 8));
    }

    static  class Helper{
        boolean flag1 = false;
        boolean flag2 = false;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(H)
    public static int findLcaEfficient(BinaryTreeNode root, int n1, int n2){
        Helper helper = new Helper();
        BinaryTreeNode res = findLcaEfficient(root, n1, n2, helper);
        if(helper.flag1 && helper.flag2)
            return res.data;
        return -1;
    }

    public static BinaryTreeNode findLcaEfficient(BinaryTreeNode root, int n1, int n2, Helper helper){
        if(root == null)
            return null;
        BinaryTreeNode temp = null;
        if(root.data == n1){
            helper.flag1 = true;
            temp = root;
        }
        if(root.data == n2){
            helper.flag2 = true;
            temp = root;
        }
        BinaryTreeNode leftLca = findLcaEfficient(root.left, n1, n2, helper);
        BinaryTreeNode rightLca = findLcaEfficient(root.right, n1, n2, helper);
        if(temp != null)
            return temp;
        if(leftLca!= null && rightLca!=null)
            return root;
        return leftLca!=null ? leftLca : rightLca;
    }

    //If one key is present and other is absent, then it returns the present key as LCA
    // (Ideally should have returned NULL).
    //Time Complexity: O(n)
    //Space Complexity: O(H)
    public static BinaryTreeNode findLcaEfficientIfBothNodesArePresent(BinaryTreeNode root, int n1, int n2){
        if(root == null)
            return null;
        if(root.data==n1 || root.data==n2)
            return root;
        BinaryTreeNode leftLca = findLcaEfficientIfBothNodesArePresent(root.left, n1, n2);
        BinaryTreeNode rightLca = findLcaEfficientIfBothNodesArePresent(root.right, n1, n2);

        if(leftLca!=null && rightLca!=null)
            return root;
        return leftLca!=null ? leftLca : rightLca;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(H)
    public static int findLcaBrute(BinaryTreeNode root, int n1, int n2){
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        return findLcaBrute(root, n1, n2, list1, list2);
    }

    public static int findLcaBrute(BinaryTreeNode root, int n1, int n2, List<Integer> list1, List<Integer> list2){
        if(!findPath(root, list1, n1) || !findPath(root, list2, n2)){
            return -1;
        }
        int i = 0;
        for( ; i<list1.size() && i<list2.size(); i++){
            if(!list1.get(i).equals(list2.get(i)))
                break;
        }
        return list1.get(i-1);
    }

    public static boolean findPath(BinaryTreeNode root, List<Integer> list, int n){
        if(root == null)
            return false;
        list.add(root.data);
        if(root.data == n)
            return true;
        if(findPath(root.left, list, n))
            return true;
        if(findPath(root.right, list, n))
            return true;
        list.remove(list.size()-1);
        return false;
    }
}
