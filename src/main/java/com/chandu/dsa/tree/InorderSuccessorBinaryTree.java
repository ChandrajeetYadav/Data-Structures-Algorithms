package com.chandu.dsa.tree;

public class InorderSuccessorBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = BinaryTreeNode.createDemoBinaryTree();
        System.out.println("InOrder traversal of binary tree is: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        BinaryTreeNode keyNode = root.left.right;
        System.out.println("InOrder successor of 5 is: " + inOrderSuccessorFromNode(root, keyNode));
        keyNode = root.right.right;
        System.out.println("InOrder successor of 7 is: " + inOrderSuccessorFromNode(root, keyNode));
        keyNode = root;
        System.out.println("InOrder successor of 1 is: " + inOrderSuccessorFromNode(root, keyNode));
        System.out.println();
        System.out.println("InOrder traversal of binary tree is: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("InOrder successor of 5 is: " + getInOrderSuccessorFromValueUsingReverseInOrder(root, 5));
        System.out.println("InOrder successor of 7 is: " + getInOrderSuccessorFromValueUsingReverseInOrder(root, 7));
        System.out.println("InOrder successor of 1 is: " + getInOrderSuccessorFromValueUsingReverseInOrder(root, 1));
        System.out.println("InOrder successor of 8 is: " + getInOrderSuccessorFromValueUsingReverseInOrder(root, 8));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static int inOrderSuccessorFromNode(BinaryTreeNode root, BinaryTreeNode keyNode){
        if(keyNode.right != null){
            BinaryTreeNode inOrderSuccessor = getLeftMostNode(root.right);
            return inOrderSuccessor.data;
        }else{
            BinaryTreeNode  rightMost = getRightMostNode(root);
            if(rightMost == keyNode)
                return -1;
            else{
                BinaryTreeNode temp = findInOrderRecursive(root, keyNode, null);
                return temp!=null ? temp.data : -1;
            }

        }
    }

    private static BinaryTreeNode findInOrderRecursive(BinaryTreeNode root, BinaryTreeNode keyNode, BinaryTreeNode temp) {
        if(root == null)
            return null;
        if(root==keyNode || (temp = findInOrderRecursive(root.left, keyNode, temp)) != null
            || (temp = findInOrderRecursive(root.right, keyNode, temp)) != null){
            if(temp != null && root.left == temp)
                return root;
            return root;
        }
        return null;
    }


    public static BinaryTreeNode getLeftMostNode(BinaryTreeNode root){
        while(root != null && root.left != null)
            root = root.left;
        return root;
    }

    public static BinaryTreeNode getRightMostNode(BinaryTreeNode root){
        while(root != null && root.right != null)
            root = root.right;
        return root;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static int getInOrderSuccessorFromValueUsingReverseInOrder(BinaryTreeNode root, int key){
        PrevNode prev = new PrevNode();
        getInOrderSuccessorFromValueUsingReverseInOrder(root, prev, key);
        return prev.res!=null ? prev.res.data : -1;
    }

    public static void getInOrderSuccessorFromValueUsingReverseInOrder(BinaryTreeNode root, PrevNode prev, int key){
        if(root == null)
            return;
        if(root.right != null)
            getInOrderSuccessorFromValueUsingReverseInOrder(root.right, prev, key);
        if(root.data == key){
            prev.res = prev.prev;
            return;
        }
        prev.prev = root;
        if(root.left != null)
            getInOrderSuccessorFromValueUsingReverseInOrder(root.left, prev, key);
    }

    static class PrevNode{
        BinaryTreeNode prev;
        BinaryTreeNode res;
    }
}
