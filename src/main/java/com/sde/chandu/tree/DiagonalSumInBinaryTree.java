package com.sde.chandu.tree;

import java.util.*;

public class DiagonalSumInBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createBinaryTree();
        System.out.println("Diagonal sum of tree, using map: " + getDiagonalSumUsingMap(root));
        System.out.println("Diagonal sum of tree, without using map: " + getDiagonalSumWithoutUsingMap(root));
    }

    // Time complexity: O(n log n)
    // Space complexity: O(n)
    private static List<Integer> getDiagonalSumUsingMap(BinaryTreeNode root) {
        if (root == null)
            return null;
        Queue<DSNode> queue = new LinkedList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        queue.add(new DSNode(root, 0));
        int vd;
        DSNode curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            vd = curr.vd;
            while (curr != null) {
                map.put(vd, map.getOrDefault(vd, 0) + curr.node.data);
                if (curr.node.left != null)
                    queue.add(new DSNode(curr.node.left, vd + 1));
                if (curr.node.right != null)
                    curr = new DSNode(curr.node.right, 0);
                else
                    curr = null;
            }
        }
        List<Integer> result = new ArrayList<>();
        map.forEach((k, v) -> result.add(v));
        return result;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    private static List<Integer> getDiagonalSumWithoutUsingMap(BinaryTreeNode root) {
        if (root == null)
            return null;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        int count = 0, sum = 0, last = 0;
        while (root != null) {
            if (root.left != null) {
                queue.add(root.left);
                count++;
            }
            sum += root.data;
            root = root.right;
            if (root == null) {
                if (!queue.isEmpty())
                    root = queue.poll();
                if (last == 0) {
                    list.add(sum);
                    sum = 0;
                    last = count;
                    count = 0;
                }
                last--;
            }
        }
        return list;
    }

    private static class DSNode {
        BinaryTreeNode node;
        int vd;

        DSNode(BinaryTreeNode node, int vd) {
            this.node = node;
            this.vd = vd;
        }
    }
}
