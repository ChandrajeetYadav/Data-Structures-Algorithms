package com.sde.chandu.tree;

public class ConstructBinaryTreeFromParentArray {
    private static BinaryTreeNode root = null;

    public static void main(String[] args) {
        int[] parent = {-1, 0, 0, 1, 1, 3, 5};
        BinaryTreeNode root = createTree(parent);
        System.out.println("In order traversal of created tree: " + InOrderTraversal.inOrderTraversalRecursive(root));
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static BinaryTreeNode createTree(int[] parent) {
        if (parent == null || parent.length == 0)
            return null;
        root = null;
        BinaryTreeNode[] created = new BinaryTreeNode[parent.length];
        for (int i = 0; i < parent.length; i++)
            createNode(parent, created, i);
        return root;
    }

    private static void createNode(int[] parent, BinaryTreeNode[] created, int i) {
        if (created[i] != null)
            return;
        created[i] = new BinaryTreeNode(i);
        if (parent[i] == -1) {
            root = created[i];
            return;
        }
        if (created[parent[i]] == null)
            createNode(parent, created, parent[i]);
        BinaryTreeNode node = created[parent[i]];
        if (node.left == null)
            node.left = created[i];
        else
            node.right = created[i];
    }
}
