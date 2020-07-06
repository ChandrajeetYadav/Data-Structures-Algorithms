package com.chandu.dsa.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeABinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("LevelOrder traversal of tree: ");
        TreeTraversal.levelOrderTraversal(root);
        System.out.println();
        ArrayList<Integer> list = serializeTree(root);
        System.out.println("List after serializing tree: ");
        System.out.println(list);
        BinaryTreeNode deserializedTree = deserializeTree(list);
        System.out.println("Level order traversal of deserialized tree:");
        TreeTraversal.levelOrderTraversal(deserializedTree);
        System.out.println();
    }

    //Time Complexity: O(N).
    //Space Complexity: O(N).
    public static ArrayList<Integer> serializeTree(BinaryTreeNode root){
        ArrayList<Integer> list = new ArrayList<Integer>();
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        BinaryTreeNode temp;

        while(!queue.isEmpty()){
            temp = queue.poll();
            if(temp == null)
                list.add(null);
            else{
                list.add(temp.data);
                queue.add(temp.left);
                queue.add(temp.right);
            }
        }
        return list;
    }

    //Time Complexity: O(N).
    //Space Complexity: O(N).
    public static BinaryTreeNode deserializeTree(ArrayList<Integer> list){
        if(list.get(0) == null)
            return null;
        BinaryTreeNode root = new BinaryTreeNode(list.get(0));
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        BinaryTreeNode temp, left, right;
        int i = 1;
        while(!queue.isEmpty()){
            temp = queue.poll();
            if(temp != null){
                left = null;
                if(list.get(i) != null)
                    left = new BinaryTreeNode(list.get(i));
                temp.left = left;
                queue.add(left);
                i++;

                right = null;
                if(list.get(i) != null)
                    right = new BinaryTreeNode(list.get(i));
                temp.right = right;
                queue.add(right);
                i++;
            }
        }
        return root;
    }
}
