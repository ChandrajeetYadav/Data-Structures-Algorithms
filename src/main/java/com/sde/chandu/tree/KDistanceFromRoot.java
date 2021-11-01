package com.sde.chandu.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KDistanceFromRoot {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        int k = 2;
        System.out.println(k + "th nodes from root, using level order traversal: " + getKthNodesFromRootUsingLevelOrderTraversal(root, k));
        System.out.println(k + "th nodes from root, using recursive pre order traversal: " + getKthNodesFromRootRecursive(root, k));
    }

    // Time complexity: O(n)
    // Space complexity: O(w) , w = max width of the tree
    private static List<Integer> getKthNodesFromRootUsingLevelOrderTraversal(BinaryTreeNode root, int k) {
        if (root == null || k < 0)
            return null;
        List<Integer> result = new ArrayList<>();
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size, level = 0;
        while (!queue.isEmpty()) {
            size = queue.size();
            while (size-- > 0) {
                root = queue.poll();
                if (k == level)
                    result.add(root.data);
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            }
            if (level++ == k)
                break;
        }
        return result;
    }

    // Time complexity: O(n)
    // Space complexity : O(h) + O(w), h = height of the tree,  w = max width of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static List<Integer> getKthNodesFromRootRecursive(BinaryTreeNode root, int k) {
        if (root == null)
            return null;
        List<Integer> result = new ArrayList<>();
        getKthNodesFromRootRecursiveHelper(root, result, k);
        return result;
    }

    private static void getKthNodesFromRootRecursiveHelper(BinaryTreeNode root, List<Integer> result, int k) {
        if (root == null || k < 0)
            return;
        if (k == 0)
            result.add(root.data);
        getKthNodesFromRootRecursiveHelper(root.left, result, k - 1);
        getKthNodesFromRootRecursiveHelper(root.right, result, k - 1);
    }
}
