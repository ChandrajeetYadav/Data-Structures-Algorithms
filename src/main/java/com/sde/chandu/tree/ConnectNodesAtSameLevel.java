package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class ConnectNodesAtSameLevel {
    public static void main(String[] args) {
        Node root = createTree();
        connectNodes(root);
        System.out.println("Next right of nodes are as below after connecting using level order traversal:");
        printNextRight(root);
        root = createTree();
        connectNodesRecursive(root);
        System.out.println("Next right of nodes are as below after connecting, recursive approach:");
        printNextRight(root);
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static void connectNodes(Node root) {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int size;
        Node temp, prev;
        while (!queue.isEmpty()) {
            size = queue.size();
            temp = null;
            while (size-- > 0) {
                prev = temp;
                temp = queue.poll();
                if (prev != null)
                    prev.nextRight = temp;
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
            temp.nextRight = null;
        }
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static void connectNodesRecursive(Node root) {
        if (root == null)
            return;
        if (root.nextRight != null)
            connectNodesRecursive(root.nextRight);
        if (root.left != null) {
            if (root.right != null) {
                root.left.nextRight = root.right;
                root.right.nextRight = getNextRight(root);
            } else
                root.left.nextRight = getNextRight(root);
            connectNodesRecursive(root.left);
        } else if (root.right != null) {
            root.right.nextRight = getNextRight(root);
            connectNodesRecursive(root.right);
        } else
            connectNodesRecursive(getNextRight(root));
    }

    private static Node getNextRight(Node root) {
        root = root.nextRight;
        while (root != null) {
            if (root.left != null)
                return root.left;
            if (root.right != null)
                return root.right;
            root = root.nextRight;
        }
        return null;
    }

    private static void printNextRight(Node root) {
        if (root == null)
            return;
        int nextRight = root.nextRight != null ? root.nextRight.data : -1;
        System.out.println("Next right of " + root.data + " is: " + nextRight);
        printNextRight(root.left);
        printNextRight(root.right);
    }

    private static Node createTree() {
        Node root = new Node(10);
        root.left = new Node(8);
        root.right = new Node(2);
        root.left.left = new Node(3);
        return root;
    }

    private static class Node {
        int data;
        Node left, right, nextRight;

        Node(int data) {
            this.data = data;
        }
    }
}
