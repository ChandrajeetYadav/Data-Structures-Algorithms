package com.chandu.dsa.greedy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {
    public static void main(String[] args) {
        char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] charFreq = { 5, 9, 12, 13, 16, 45 };

        HuffmanNode root = buildHuffmanTree(charArray, charFreq);
        System.out.println("Huffman codes are: ");
        printHuffmanCode(root, "");

        String str = "chandrajeet";
        Map<Character, Integer> charFreqMap = getCharacterAndItsFrequency(str);
        charArray = new char[charFreqMap.size()];
        charFreq = new int[charFreqMap.size()];
        int i=0;
        for (Map.Entry<Character, Integer> entry : charFreqMap.entrySet()){
            charArray[i] = entry.getKey();
            charFreq[i] = entry.getValue();
            i++;
        }
        root = buildHuffmanTree(charArray, charFreq);
        Map<Character, String> huffmanMap = new HashMap<>();
        getEncodedCharacter(root, "", huffmanMap);

        System.out.println("Character and their encoded value: ");
        System.out.println(huffmanMap);
        StringBuilder sb = new StringBuilder();
        for (i=0; i<str.length(); i++)
            sb.append(huffmanMap.get(str.charAt(i)));
        System.out.println("Encoded Huffman data: " + sb);
        System.out.println("Decoded Huffman data: " + getHuffmanDecodedData(root, sb.toString()));
    }

    //Time Complexity: O(n log n)
    //Space Complexity: O(n)
    private static HuffmanNode buildHuffmanTree(char[] charArray, int[] charFreq){
        if(charArray==null || charFreq==null || charArray.length==0 ||
            charFreq.length==0 || charArray.length!=charFreq.length)
            return null;

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>((HuffmanNode a, HuffmanNode b)->a.data-b.data);
        for (int i=0; i< charArray.length; i++)
            pq.add(new HuffmanNode(charFreq[i], charArray[i]));
        Iterator<HuffmanNode> iterator = pq.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + ", ");
        }
        System.out.println();
        HuffmanNode root=null, left, right;
        while (pq.size() > 1) {
            left = pq.poll();
            right = pq.poll();

            root = new HuffmanNode(left.data + right.data, '-');
            root.left = left;
            root.right = right;
            pq.add(root);
        }

        return root;
    }

    private static void printHuffmanCode(HuffmanNode root, String s) {
        if (root.left==null && root.right==null && Character.isLetter(root.c)) {
            System.out.println(root.c + ": " + s);
            return;
        }
        printHuffmanCode(root.left, s + "0");
        printHuffmanCode(root.right, s + "1");
    }

    static class HuffmanNode {
        int data;
        char c;
        HuffmanNode left;
        HuffmanNode right;

        HuffmanNode(int data, char c){
            this.data = data;
            this.c = c;
        }

        public String toString(){
            return c + " = " + data;
        }
    }

    private static void getEncodedCharacter(HuffmanNode root, String s, Map<Character, String> huffmanMap){
        if (root.c != '-'){
            huffmanMap.put(root.c , s);
            return ;
        }
        getEncodedCharacter(root.left, s + "0", huffmanMap);
        getEncodedCharacter(root.right, s + "1", huffmanMap);
    }

    private static Map<Character, Integer> getCharacterAndItsFrequency(String word){
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0; i<word.length(); i++){
            if(map.containsKey(word.charAt(i)))
                map.put(word.charAt(i), map.get(word.charAt(i)) + 1);
            else
                map.put(word.charAt(i), 1);
        }
        return map;
    }

    private static String getHuffmanDecodedData(HuffmanNode root, String encodedString){
        StringBuilder sb = new StringBuilder();
        char ch;
        HuffmanNode temp = root;
        for (int i=0; i<encodedString.length(); i++){
            ch = encodedString.charAt(i);
            if(ch == '0')
                temp = temp.left;
            else
                temp = temp.right;
            if (temp.left==null && temp.right==null){
                sb.append(temp.c);
                temp = root;
            }
        }
        return sb.toString();
    }
}
