package com.sde.chandu.tree;

import java.util.Stack;

public class ReverseAlternateLevelNodesPerfectBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        //BinaryTreeNode root = create();
        System.out.println("Level order traversal of tree: " + LevelOrderTraversal.levelOrderTraversalIterative(root));
        reverseAlternateLevelNodesTwoTraversal(root);
        System.out.println("Level order traversal of tree after reversing alternate level nodes, two traversals: " + LevelOrderTraversal.levelOrderTraversalIterative(root));

        root = BinaryTreeNode.createBinaryTree();
        System.out.println("Level order traversal of tree: " + LevelOrderTraversal.levelOrderTraversalIterative(root));
        reverseAlternateLevelNodesSingleTraversal(root);
        System.out.println("Level order traversal of tree after reversing alternate level nodes, single traversal: " + LevelOrderTraversal.levelOrderTraversalIterative(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static void reverseAlternateLevelNodesSingleTraversal(BinaryTreeNode root) {
        if (root == null)
            return;
        reverseAlternateLevelNodesSingleTraversalHelper(root.left, root.right, 0);
    }

    private static void reverseAlternateLevelNodesSingleTraversalHelper(BinaryTreeNode root1, BinaryTreeNode root2, int level) {
        if (root1 == null || root2 == null)
            return;
        if (level % 2 == 0) {
            int temp = root1.data;
            root1.data = root2.data;
            root2.data = temp;
        }
        reverseAlternateLevelNodesSingleTraversalHelper(root1.left, root2.right, level + 1);
        reverseAlternateLevelNodesSingleTraversalHelper(root1.right, root2.left, level + 1);
    }

    // Time complexity: O(n)
    // Space complexity : O(n)
    private static void reverseAlternateLevelNodesTwoTraversal(BinaryTreeNode root) {
        if (root == null)
            return;
        Stack<Integer> stack = new Stack<>();
        storeAlternate(root, stack, 0);
        modifyTree(root, stack, 0);
    }

    private static void modifyTree(BinaryTreeNode root, Stack<Integer> stack, int level) {
        if (root == null)
            return;
        modifyTree(root.left, stack, level + 1);
        if (level % 2 != 0)
            root.data = stack.pop();
        modifyTree(root.right, stack, level + 1);
    }

    private static void storeAlternate(BinaryTreeNode root, Stack<Integer> stack, int level) {
        if (root == null)
            return;
        storeAlternate(root.left, stack, level + 1);
        if (level % 2 != 0)
            stack.push(root.data);
        storeAlternate(root.right, stack, level + 1);
    }

    private static BinaryTreeNode create() {
        BinaryTreeNode root = new BinaryTreeNode(9);
        root.left = new BinaryTreeNode(8);
        root.right = new BinaryTreeNode(4);
        root.left.left = new BinaryTreeNode(4);
        root.left.right = new BinaryTreeNode(4);
        root.right.left = new BinaryTreeNode(4);
        root.right.right = new BinaryTreeNode(9);
        root.left.left.left = new BinaryTreeNode(5);
        root.left.left.right = new BinaryTreeNode(6);
        root.left.right.left = new BinaryTreeNode(6);
        root.left.right.right = new BinaryTreeNode(10);
        root.right.left.left = new BinaryTreeNode(6);
        root.right.left.right = new BinaryTreeNode(8);
        root.right.right.left = new BinaryTreeNode(6);
        root.right.right.right = new BinaryTreeNode(6);
        return root;
    }
}
