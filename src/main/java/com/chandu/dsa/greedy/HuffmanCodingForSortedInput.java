package com.chandu.dsa.greedy;

import java.util.LinkedList;
import java.util.Queue;

public class HuffmanCodingForSortedInput {
    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] freq = {5, 9, 12, 13, 16, 45};

        HuffmanNode root = buildHuffmanTree(arr, freq);
        printCode(root, "");
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    //n = number of unique characters
    private static HuffmanNode buildHuffmanTree(char[] arr, int[] freq){
        Queue<HuffmanNode> q1 = new LinkedList<>();
        Queue<HuffmanNode> q2 = new LinkedList<>();
        for (int i=0; i< arr.length; i++)
            q1.add(new HuffmanNode(arr[i], freq[i]));

        HuffmanNode root = null, left, right;
        while (!q1.isEmpty() || q2.size()>1){
            left = getMinNode(q1, q2);
            right = getMinNode(q1, q2);
            root = new HuffmanNode('#', left.freq + right.freq);
            root.left = left;
            root.right = right;
            q2.add(root);
        }
        return root;
    }

    private static void printCode(HuffmanNode root, String s){
        if(root.left==null && root.right==null && Character.isLetter(root.data)){
            System.out.println(root.data + ": " + s);
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    private static HuffmanNode getMinNode(Queue<HuffmanNode> q1, Queue<HuffmanNode> q2){
        if(q1.isEmpty())
            return q2.poll();
        if( q2.isEmpty())
            return q1.poll();
        if(q1.peek().freq < q2.peek().freq)
            return q1.poll();
        return q2.poll();
    }

    static class HuffmanNode{
        char data;
        int freq;
        HuffmanNode left, right;
        HuffmanNode(char data, int freq){
            this.data = data;
            this.freq = freq;
        }
    }
}
