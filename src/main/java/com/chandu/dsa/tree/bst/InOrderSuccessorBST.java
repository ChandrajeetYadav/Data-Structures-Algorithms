package com.chandu.dsa.tree.bst;

import com.chandu.dsa.tree.BinaryTreeNode;
import com.chandu.dsa.tree.TreeTraversal;

public class InOrderSuccessorBST {
    public static void main(String[] args) {
        BinaryTreeNode root = BinarySearchTree.createDemoBST();
        System.out.println("InOrder traversal of bst: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("InOrder successor of 20 is: " + inOrderSuccessorUsingReverseInOrder(root, 20));
        System.out.println("InOrder successor of 40 is: " + inOrderSuccessorUsingReverseInOrder(root, 40));
        System.out.println("InOrder successor of 80 is: " + inOrderSuccessorUsingReverseInOrder(root, 80));
        System.out.println("InOrder successor of 50 is: " + inOrderSuccessorUsingReverseInOrder(root, 50));

        System.out.println("InOrder traversal of bst: ");
        TreeTraversal.inOrderTraversal(root);
        System.out.println();
        System.out.println("InOrder successor of 20 is: " + inOrderSuccessorEfficient(root, 20));
        System.out.println("InOrder successor of 40 is: " + inOrderSuccessorEfficient(root, 40));
        System.out.println("InOrder successor of 80 is: " + inOrderSuccessorEfficient(root, 80));
        System.out.println("InOrder successor of 50 is: " + inOrderSuccessorEfficient(root, 50));
        System.out.println("InOrder successor of 70 is: " + inOrderSuccessorEfficient(root, 70));
        System.out.println("InOrder successor of 45 is: " + inOrderSuccessorEfficient(root, 45));
    }

    //Time Complexity: O(h), where h is the height of the tree.
    //Space Complexity: O(1)
    public static int inOrderSuccessorEfficient(BinaryTreeNode root, int key){
        if(root == null)
            return -1;
        BinaryTreeNode succ = null;
        boolean isFound = false;

        while(root != null){
            if(root.data > key){
                succ = root;
                root = root.left;
            }else if(root.data < key)
                root = root.right;
            else{
                isFound = true;
                break;
            }
        }
        if(!isFound)
            return -1;
        if(succ==null)
            succ = getLeftMostNode(root.right);
        return succ!=null ? succ.data : -1;
    }

    //Time Complexity: O(h), where h is the height of the tree.
    //Space Complexity: O(1)
    public static int inOrderSuccessorEfficient(BinaryTreeNode root, BinaryTreeNode key){
        if(root == null)
            return -1;
        if(key.right != null)
            return getLeftMostNode(root.right).data;
        BinaryTreeNode succ = null;
        while(root != null){
            if(root.data > key.data){
                succ = root;
                root = root.left;
            }else if(root.data < key.data)
                root = root.right;
            else
                return succ!=null ? succ.data : -1;
        }
        return -1;
    }

    public static BinaryTreeNode getLeftMostNode(BinaryTreeNode root){
        while(root!=null && root.left!=null)
            root = root.left;
        return root;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n);
    public static int inOrderSuccessorUsingReverseInOrder(BinaryTreeNode root, int key){
        PreviousNode prev = new PreviousNode();
        inOrderSuccessorUsingReverseInOrder(root, prev, key);
        return prev.res!=null ? prev.res.data : -1;
    }

    public static void inOrderSuccessorUsingReverseInOrder(BinaryTreeNode root, PreviousNode prev, int key){
        if(root == null)
            return;
        if(root.right != null)
            inOrderSuccessorUsingReverseInOrder(root.right, prev, key);
        if(root.data == key){
            prev.res = prev.prev;
            return;
        }
        prev.prev = root;
        if(root.left != null)
            inOrderSuccessorUsingReverseInOrder(root.left, prev, key);
    }

    static  class PreviousNode{
        BinaryTreeNode prev;
        BinaryTreeNode res;
    }
}
