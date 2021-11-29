package com.sde.chandu.tree;

public class ExpressionTree {
    public static void main(String[] args) {
        ExpressionTreeNode root = create();
        System.out.println("Inorder traversal of expression tree: ");
        inOrderTraversal(root);
        System.out.println();
        System.out.println("Expression tree evaluation: " + evaluateExpressionTree(root));
    }

    // Time complexity: O(n)
    // Space complexity : O(1) if we don't consider stack size for function call.
    // Space complexity : O(h) where h is the height of the tree
    // Space complexity : O(n) if the tree is skewed tree
    private static int evaluateExpressionTree(ExpressionTreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return Integer.parseInt(root.data);
        int leftVal = evaluateExpressionTree(root.left);
        int rightVal = evaluateExpressionTree(root.right);
        switch (root.data) {
            case "+":
                return leftVal + rightVal;
            case "-":
                return leftVal - rightVal;
            case "*":
                return leftVal * rightVal;
            case "/":
                return leftVal / rightVal;
        }
        return 0;
    }

    private static ExpressionTreeNode create() {
        ExpressionTreeNode root = new ExpressionTreeNode("+");
        root.left = new ExpressionTreeNode("*");
        root.left.left = new ExpressionTreeNode("5");
        root.left.right = new ExpressionTreeNode("-4");
        root.right = new ExpressionTreeNode("-");
        root.right.left = new ExpressionTreeNode("100");
        root.right.right = new ExpressionTreeNode("20");
        return root;
    }

    private static void inOrderTraversal(ExpressionTreeNode root) {
        if (root == null)
            return;
        inOrderTraversal(root.left);
        System.out.print(root.data + " ");
        inOrderTraversal(root.right);
    }

    private static class ExpressionTreeNode {
        String data;
        ExpressionTreeNode left;
        ExpressionTreeNode right;

        ExpressionTreeNode(String data) {
            this.data = data;
        }
    }
}
