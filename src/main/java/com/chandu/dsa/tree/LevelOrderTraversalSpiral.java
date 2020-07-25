package com.chandu.dsa.tree;

import java.util.*;

public class LevelOrderTraversalSpiral {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("Level order traversal of tree: ");
        TreeTraversal.levelOrderTraversal(root);
        System.out.println();
        System.out.println("Level Order traversal in spiral form using stack and queue: ");
        levelOrderTraversalSpiralUsingQueueAndStack(root);
        System.out.println();
        System.out.println("Level Order traversal in spiral form using deque: ");
        levelOrderTraversalSpiralUsingDeque(root);
        System.out.println();
        System.out.println("Level Order traversal in spiral form using two stacks: ");
        levelOrderTraversalSpiralUsingTwoSTacks(root);
        System.out.println();
        System.out.println("Level Order traversal in spiral form using recursion: ");
        levelOrderTraversalSpiralUsingRecursion(root);
    }

    //Time Complexity: O(N)
    //Space Complexity: O(N)
    public static void levelOrderTraversalSpiralUsingDeque(BinaryTreeNode root){
        if(root == null)
            return;
        Deque<BinaryTreeNode> deque = new LinkedList<BinaryTreeNode>();
        deque.addLast(root);
        int size;
        boolean leftToRight = false;
        while(!deque.isEmpty()){
            size = deque.size();
            for(int i=1; i<=size; i++){
                if(leftToRight){
                    root = deque.pollFirst();
                    if(root.left != null)
                        deque.addLast(root.left);
                    if(root.right != null)
                        deque.addLast(root.right);
                }else{
                    root = deque.pollLast();
                    if(root.right != null)
                        deque.addFirst(root.right);
                    if(root.left != null)
                        deque.addFirst(root.left);
                }
                System.out.print(root.data + " ");
            }
            leftToRight = !leftToRight;
        }
    }

    //Time Complexity : O(n)
    //Auxiliary Space : O(n)
    public static void levelOrderTraversalSpiralUsingQueueAndStack(BinaryTreeNode root){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null)
            return;
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        Stack<Integer> stack = new Stack<Integer>();
        queue.add(root);
        int size;
        boolean leftToRight = false;
        while (!queue.isEmpty()){
            size = queue.size();
            for(int i=1; i<=size; i++){
                root = queue.poll();
                if(root.left != null)
                    queue.add(root.left);
                if(root.right != null)
                    queue.add(root.right);
                if(!leftToRight)
                    stack.push(root.data);
                else
                    System.out.print(root.data + " ");
            }
            if(!leftToRight)
                printStack(stack);
            leftToRight = !leftToRight;
        }
    }

    public static void printStack(Stack<Integer> stack){
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
    }

    //Time Complexity : O(n)
    //Auxiliary Space : O(n)
    public static void levelOrderTraversalSpiralUsingTwoSTacks(BinaryTreeNode root){
        if(root == null)
            return;
        Stack<BinaryTreeNode> s1 = new Stack<BinaryTreeNode>();
        Stack<BinaryTreeNode> s2 = new Stack<BinaryTreeNode>();

        s1.push(root);
        while (!s1.isEmpty() || !s2.isEmpty()){
            while (!s1.isEmpty()){
                root = s1.pop();
                if(root.right != null)
                    s2.push(root.right);
                if(root.left != null)
                    s2.push(root.left);
                System.out.print(root.data + " ");
            }
            while(!s2.isEmpty()){
                root = s2.pop();
                if(root.left != null)
                    s1.push(root.left);
                if(root.right != null)
                    s1.push(root.right);
                System.out.print(root.data + " ");
            }
        }
    }

    //Time Complexity : O(n^2)
    //Auxiliary Space : O(n)
    public static void levelOrderTraversalSpiralUsingRecursion(BinaryTreeNode root){
        int height = height(root);
        boolean leftToRight = false;
        for(int i=1; i<=height; i++){
            printGivenLevel(root, i, leftToRight);
            leftToRight = !leftToRight;
        }
    }

    public static void printGivenLevel(BinaryTreeNode root, int level, boolean leftToRight){
        if(root == null)
            return;
        if(level == 1)
            System.out.print(root.data + " ");
        else if(level > 1){
            if(leftToRight){
                printGivenLevel(root.left, level-1, leftToRight);
                printGivenLevel(root.right, level-1, leftToRight);
            }else{
                printGivenLevel(root.right, level-1, leftToRight);
                printGivenLevel(root.left, level-1, leftToRight);
            }
        }
    }

    public static int height(BinaryTreeNode root){
        if(root == null)
            return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
