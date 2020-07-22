package com.chandu.dsa.tree;

import java.util.LinkedList;
import java.util.Queue;

public class ConnectNodesAtSameLevel{
    public int data;
    public ConnectNodesAtSameLevel left;
    public ConnectNodesAtSameLevel right;
    public ConnectNodesAtSameLevel nextRight;

    ConnectNodesAtSameLevel(int data){
        this.data = data;
    }

    public static void main(String[] args) {
        ConnectNodesAtSameLevel root = createDemoBinaryTree();
        inOrderTraversal(root);
        System.out.println();
        //connectUsingLevelOrderTraversal(root);
        connectUsingIterative(root);
        printNextRight(root);
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public static void connectUsingIterative(ConnectNodesAtSameLevel root){
        if(root == null)
            return;
        ConnectNodesAtSameLevel temp;

        while(root != null){
            temp = root;
            while (temp != null){
                if(temp.left != null){
                    if(temp.right != null)
                        temp.left.nextRight = temp.right;
                    else
                        temp.left.nextRight = getNextRight(temp);
                }
                if (temp.right != null)
                    temp.right.nextRight = getNextRight(temp);
                temp = temp.nextRight;
            }

            if(root.left != null)
                root = root.left;
            else if(root.right != null)
                root = root.right;
            else
                root = getNextRight(root);
        }
    }

    public static ConnectNodesAtSameLevel getNextRight(ConnectNodesAtSameLevel root){
        ConnectNodesAtSameLevel temp = root.nextRight;
        while (temp != null){
            if(temp.left != null)
                return temp.left;
            if(temp.right != null)
                return temp.right;
            temp = temp.nextRight;
        }
        return null;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static void connectUsingLevelOrderTraversal(ConnectNodesAtSameLevel root){
        if(root == null)
            return;
        Queue<ConnectNodesAtSameLevel> queue = new LinkedList<ConnectNodesAtSameLevel>();
        queue.add(root);
        int size;
        ConnectNodesAtSameLevel prev = null, temp;
        while(!queue.isEmpty()){
            size = queue.size();
            for(int i=1; i<=size; i++){
                temp = queue.poll();
                if(i != 1)
                    prev.nextRight = temp;
                if(temp.left != null)
                    queue.add(temp.left);
                if(temp.right != null)
                    queue.add(temp.right);
                prev = temp;
            }
            prev.nextRight = null;
        }
    }

    public static ConnectNodesAtSameLevel createDemoBinaryTree(){
        ConnectNodesAtSameLevel root = new ConnectNodesAtSameLevel(1);
        root.left = new ConnectNodesAtSameLevel(2);
        root.right = new ConnectNodesAtSameLevel(3);
        root.left.left = new ConnectNodesAtSameLevel(4);
        root.left.right = new ConnectNodesAtSameLevel(5);
        root.right.left = new ConnectNodesAtSameLevel(6);
        root.right.right = new ConnectNodesAtSameLevel(7);
        return root;
    }

    public static void printNextRight(ConnectNodesAtSameLevel root){
        if(root == null)
            return;
        System.out.println("Next Right of " + root.data + " is: " + getNextRightData(root));
        printNextRight(root.left);
        printNextRight(root.right);
    }

    public static Integer getNextRightData(ConnectNodesAtSameLevel root){
        return root.nextRight==null ? null : root.nextRight.data;
    }

    public static void inOrderTraversal(ConnectNodesAtSameLevel root) {
        if (root == null)
            return;
        inOrderTraversal(root.left);
        System.out.print(root.data + " ");
        inOrderTraversal(root.right);
    }
}
