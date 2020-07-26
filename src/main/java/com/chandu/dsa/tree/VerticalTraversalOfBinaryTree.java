package com.chandu.dsa.tree;

import java.util.*;

public class VerticalTraversalOfBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of binary tree: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Vertical traversal of tree is: ");
        //verticalTraversal(root);
        //verticalTraversalEfficientUsingPreOrderTraversal(root);
        verticalTraversalEfficientUsingLevelOrderTraversal(root);
    }

    //Time complexity: O(n log n)
    //Space complexity: O(n)
    //We can reduce time complexity to O(n) using unordered_map. To print nodes in desired order,
    // we can have 2 variables denoting min and max horizontal distance. We can simply iterate from min
    // to max horizontal distance and get corresponding values from Map. So it is O(n)
    public static void verticalTraversalEfficientUsingLevelOrderTraversal(BinaryTreeNode root){
        if(root == null)
            return;
        Queue<QueueObj> queue = new LinkedList<>();
        queue.add(new QueueObj(root, 0));
        QueueObj temp;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        while (!queue.isEmpty()){
            temp = queue.poll();
            List<Integer> list = map.get(temp.hd);
            if(list == null)
                list = new ArrayList<>();
            list.add(temp.node.data);
            map.put(temp.hd, list);
            if(temp.node.left != null)
                queue.add(new QueueObj(temp.node.left, temp.hd - 1));
            if(temp.node.right != null)
                queue.add(new QueueObj(temp.node.right, temp.hd + 1));
        }
        map.forEach((m, n) -> {
            n.forEach(i -> System.out.print(i + " "));
            System.out.println();
        });
    }

    static class QueueObj{
        BinaryTreeNode node;
        int hd;
        QueueObj(BinaryTreeNode node, int hd){
            this.node = node;
            this.hd = hd;
        }
    }

    //Time complexity: O(n log n)
    //Space complexity: O(n)
    public static void verticalTraversalEfficientUsingPreOrderTraversal(BinaryTreeNode root){
        if(root == null)
            return;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        verticalTraversalEfficientUsingPreOrderTraversal(root, 0, map);
        map.forEach((m,n)->{
            n.forEach(i-> System.out.print(i + " "));
            System.out.println();
        });
    }

    public static void verticalTraversalEfficientUsingPreOrderTraversal(BinaryTreeNode root, int hd, Map<Integer, List<Integer>> map){
        if(root == null)
            return;
        List<Integer> list = map.get(hd);
        if(list == null)
            list = new ArrayList<>();
        list.add(root.data);
        map.put(hd, list);
        verticalTraversalEfficientUsingPreOrderTraversal(root.left, hd-1, map);
        verticalTraversalEfficientUsingPreOrderTraversal(root.right, hd+1, map);
    }

    //Time complexity: O(n^2)
    //Space complexity: O(n)
    public static void verticalTraversal(BinaryTreeNode root){
        if(root == null)
            return;
        Helper helper = new Helper();
        getMinAndMaxHorizontalDistance(root, helper, 0);
        for(int i=helper.minHd; i<=helper.maxHd; i++){
            printVerticalLine(root, i, 0);
            System.out.println();
        }
    }

    public static void printVerticalLine(BinaryTreeNode root, int lineNo, int hd){
        if(root == null)
            return;
        if(lineNo == hd)
            System.out.print(root.data + " ");
        printVerticalLine(root.left, lineNo, hd-1);
        printVerticalLine(root.right, lineNo, hd+1);
    }

    public static void getMinAndMaxHorizontalDistance(BinaryTreeNode root, Helper helper, int hd){
        if(root == null)
            return;
        if(helper.minHd > hd)
            helper.minHd = hd;
        if(helper.maxHd < hd)
            helper.maxHd = hd;
        getMinAndMaxHorizontalDistance(root.left, helper, hd-1);
        getMinAndMaxHorizontalDistance(root.right, helper, hd+1);
    }

    static class Helper{
        int minHd = Integer.MAX_VALUE;
        int maxHd = Integer.MIN_VALUE;
    }
}
