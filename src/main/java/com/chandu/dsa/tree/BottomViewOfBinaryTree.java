package com.chandu.dsa.tree;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BottomViewOfBinaryTree{
    int hd;
    int data;
    BottomViewOfBinaryTree left;
    BottomViewOfBinaryTree right;

    BottomViewOfBinaryTree(int data){
        this.data = data;
    }
    public static void main(String[] args) {
        BottomViewOfBinaryTree root = createDemoBinaryTree();
        inOrderTraversal(root);
        System.out.println();
        System.out.println("Bottom view of Binary tree is:");
        printBottomView(root);
    }

    //Time Complexity: O(n), strictly it's O(n log n) as treemap insertion takes O(log n)
    //Space Complexity: O(n)
    public static void printBottomView(BottomViewOfBinaryTree root){
        if(root == null)
            return;
        Queue<BottomViewOfBinaryTree> queue = new LinkedList<>();
        root.hd = 0;
        queue.add(root);
        Map<Integer, Integer> map = new TreeMap<>();
        int hd;
        while(!queue.isEmpty()){
            root = queue.poll();
            hd = root.hd;
            map.put(hd, root.data);
            if(root.left != null){
                root.left.hd = hd-1;
                queue.add(root.left);
            }
            if(root.right != null){
                root.right.hd = hd+1;
                queue.add(root.right);
            }
        }
        map.forEach((m, n)->System.out.print(n + " "));
        /*for(Map.Entry<Integer, Integer> entry : map.entrySet())
            System.out.print(entry.getValue() + " ");*/
    }

    public static BottomViewOfBinaryTree createDemoBinaryTree(){
        BottomViewOfBinaryTree root = new BottomViewOfBinaryTree(1);
        root.left = new BottomViewOfBinaryTree(2);
        root.right = new BottomViewOfBinaryTree(3);
        root.left.left = new BottomViewOfBinaryTree(4);
        root.left.right = new BottomViewOfBinaryTree(5);
        root.right.left = new BottomViewOfBinaryTree(6);
        root.right.right = new BottomViewOfBinaryTree(7);
        return root;
    }

    public static void inOrderTraversal(BottomViewOfBinaryTree root) {
        if (root == null)
            return;
        inOrderTraversal(root.left);
        System.out.print(root.data + " ");
        inOrderTraversal(root.right);
    }
}
