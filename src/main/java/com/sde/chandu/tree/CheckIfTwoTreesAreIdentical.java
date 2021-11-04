package com.sde.chandu.tree;

import java.util.LinkedList;
import java.util.Queue;

public class CheckIfTwoTreesAreIdentical {
    public static void main(String[] args) {
        BinaryTreeNode root1 = BinaryTreeNode.createBinaryTree();
        BinaryTreeNode root2 = BinaryTreeNode.createBinaryTree();
        System.out.println("Are tree1 and tree2 identical, recursive approach: " + isIdenticalRecursive(root1, root2));
        System.out.println("Are tree1 and tree2 identical, iterative approach: " + isIdenticalIterative(root1, root2));
    }

    // Time complexity: O(m), where m, n are the number of nodes in both trees respectively and m<=n
    // Space complexity: (h1), where h1, h2 are the heights of both trees and h1<=h2
    private static boolean isIdenticalRecursive(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 != null && root2 != null)
            return (root1.data == root2.data)
                    && isIdenticalRecursive(root1.left, root2.left)
                    && isIdenticalRecursive(root1.right, root2.right);
        return false;
    }

    // Time complexity: O(m), where m, n are the number of nodes in both trees respectively and m<=n
    // Space complexity: (w1), where w1, w2 are the widths of both trees and w1>=w2
    private static boolean isIdenticalIterative(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        Queue<BinaryTreeNode> queue1 = new LinkedList<>();
        Queue<BinaryTreeNode> queue2 = new LinkedList<>();
        queue1.add(root1);
        queue2.add(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            root1 = queue1.poll();
            root2 = queue2.poll();
            if (root1.data != root2.data)
                return false;
            if (root1.left != null && root2.left != null) {
                queue1.add(root1.left);
                queue2.add(root2.left);
            } else if (root1.left != null || root2.left != null)
                return false;
            if (root1.right != null && root2.right != null) {
                queue1.add(root1.right);
                queue2.add(root2.right);
            } else if (root1.right != null || root2.right != null)
                return false;
        }
        return true;
    }
}
