package com.sde.chandu.tree;

import java.util.*;

public class BottomViewOfBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = create();
        System.out.println("Bottom view of binary tree, iterative approach: " + bottomViewIterative(root));
        System.out.println("Bottom view of binary tree, recursive approach: " + bottomViewRecursive(root));
    }

    // Time complexity: O(n log n)
    // Space complexity: O(n)
    private static List<Integer> bottomViewIterative(BinaryTreeNode root) {
        if (root == null)
            return null;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<BottomViewNode> queue = new LinkedList<>();
        queue.add(new BottomViewNode(root, 0));
        BottomViewNode temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            map.put(temp.hd, temp.node.data);
            if (temp.node.left != null)
                queue.add(new BottomViewNode(temp.node.left, temp.hd - 1));
            if (temp.node.right != null)
                queue.add(new BottomViewNode(temp.node.right, temp.hd + 1));
        }
        List<Integer> result = new ArrayList<>();
        map.forEach((k, v) -> result.add(v));
        return result;
    }

    // Time complexity: O(n log n)
    // Space complexity: O(n)
    private static List<Integer> bottomViewRecursive(BinaryTreeNode root) {
        if (root == null)
            return null;
        TreeMap<Integer, BottomViewNode> map = new TreeMap<>();
        bottomViewRecursiveHelper(root, 0, 0, map);
        List<Integer> result = new ArrayList<>();
        map.forEach((k, v) -> result.add(v.node.data));
        return result;
    }

    private static void bottomViewRecursiveHelper(BinaryTreeNode root, int hd, int curLevel, TreeMap<Integer, BottomViewNode> map) {
        if (root == null)
            return;
        if (!map.containsKey(hd))
            map.put(hd, new BottomViewNode(curLevel, root));
        else {
            BottomViewNode temp = map.get(hd);
            if (curLevel >= temp.curLevel)
                map.put(hd, new BottomViewNode(curLevel, root));
        }
        bottomViewRecursiveHelper(root.left, hd - 1, curLevel + 1, map);
        bottomViewRecursiveHelper(root.right, hd + 1, curLevel + 1, map);
    }

    private static BinaryTreeNode create() {
        BinaryTreeNode root = new BinaryTreeNode(20);
        root.left = new BinaryTreeNode(8);
        root.right = new BinaryTreeNode(22);
        root.left.left = new BinaryTreeNode(5);
        root.left.right = new BinaryTreeNode(3);
        root.right.left = new BinaryTreeNode(4);
        root.right.right = new BinaryTreeNode(25);
        root.left.right.left = new BinaryTreeNode(10);
        root.left.right.right = new BinaryTreeNode(14);
        return root;
    }

    private static class BottomViewNode {
        BinaryTreeNode node;
        int hd;
        int curLevel;

        BottomViewNode(BinaryTreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }

        BottomViewNode(int curLevel, BinaryTreeNode node) {
            this.node = node;
            this.curLevel = curLevel;
        }
    }


}
