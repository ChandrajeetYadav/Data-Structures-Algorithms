package com.chandu.dsa.tree;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewOfBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of tree:");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("Top view of tree is: ");
        printTopViewOfTree(root);
    }

    //Time Complexity: O(n), strictly it's O(n log n) as tree map insertion takes O(log n)
    //Space Complexity: O(n)
    public static void printTopViewOfTree(BinaryTreeNode root){
        if(root == null)
            return;
        Queue<QueueObj> queue = new LinkedList<>();
        queue.add(new QueueObj(root, 0));
        Map<Integer, Integer> map = new TreeMap<>();
        QueueObj temp;
        while(!queue.isEmpty()){
            temp = queue.poll();
            if(!map.containsKey(temp.hd))
                map.put(temp.hd, temp.node.data);
            if(temp.node.left != null)
                queue.add(new QueueObj(temp.node.left, temp.hd - 1));
            if(temp.node.right != null){
                queue.add(new QueueObj(temp.node.right, temp.hd + 1));
            }
        }
        map.forEach((m,n) -> System.out.print(n + " "));
    }

    static class QueueObj{
        BinaryTreeNode node;
        int hd;
        QueueObj(BinaryTreeNode node, int hd){
            this.node = node;
            this.hd = hd;
        }
    }
}
