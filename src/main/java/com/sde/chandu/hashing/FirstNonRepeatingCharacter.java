package com.sde.chandu.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {
    public static void main(String[] args) {
        String s1 = "geeksforgeeks";
        String s2 = "abcabc";
        System.out.println("s1, first non repeating character brute: " + getFirstNonRepeatingCharacterBrute(s1));
        System.out.println("s2, first non repeating character brute: " + getFirstNonRepeatingCharacterBrute(s2));
        System.out.println();

        System.out.println("s1, first non repeating character array: " + getFirstNonRepeatingCharacterArray(s1));
        System.out.println("s2, first non repeating character array: " + getFirstNonRepeatingCharacterArray(s2));
        System.out.println();

        System.out.println("s1, first non repeating character array 2: " + getFirstNonRepeatingCharacterArray2(s1));
        System.out.println("s2, first non repeating character array 2: " + getFirstNonRepeatingCharacterArray2(s2));
        System.out.println();

        System.out.println("s1, first non repeating character hashing: " + getFirstNonRepeatingCharacterHashing(s1));
        System.out.println("s2, first non repeating character hashing: " + getFirstNonRepeatingCharacterHashing(s2));
    }

    //Time complexity : O(n^2), n = length of string
    //Space complexity : O(1)
    private static String getFirstNonRepeatingCharacterBrute(String s){
        if (s==null || s.length()==0)
            return -1+"";
        boolean isRepeating;
        for (int i=0; i<s.length(); i++){
            isRepeating = false;
            for (int j=0; j<s.length(); j++){
                if (i!=j && s.charAt(i) == s.charAt(j)){
                    isRepeating = true;
                    break;
                }
            }
            if (!isRepeating)
                return s.charAt(i)+"";
        }
        return -1+"";
    }

    //Time complexity : O(n), n = length of string
    //Space complexity : O(k) where k = 26
    private static String getFirstNonRepeatingCharacterArray(String s){
        if (s==null || s.length()==0)
            return -1+"";
        int[] ch = new int[26];
        int index;
        for (int i=0; i<s.length(); i++){
            index = s.charAt(i) - 'a';
            ch[index]++;
        }
        for (int i=0; i<s.length(); i++){
            index = s.charAt(i) - 'a';
            if (ch[index] == 1)
                return s.charAt(i)+"";
        }
        return -1+"";
    }

    //Time complexity : O(n), n = length of string
    //Space complexity : O(k) where k = 26
    private static String getFirstNonRepeatingCharacterArray2(String s){
        if (s==null || s.length()==0)
            return -1+"";
        int[] ch = new int[26];
        Arrays.fill(ch, -1);
        int index;
        for (int i=0; i<s.length(); i++){
            index = s.charAt(i) - 'a';
            if (ch[index] == -1)
                ch[index] = i;
            else
                ch[index] = -2;
        }
        index = Integer.MAX_VALUE;
        for (int i=0; i<ch.length; i++){
            if (ch[i] >= 0)
                index = Math.min(index, ch[i]);
        }
        if (index != Integer.MAX_VALUE)
            return s.charAt(index)+"";
        return -1+"";
    }

    //Time complexity : O(n), n = length of string
    //Space complexity : O(n)
    private static String getFirstNonRepeatingCharacterHashing(String s){
        if (s==null || s.length()==0)
            return -1+"";
        Map<Character, Node> map  = new HashMap<>();
        for (int i=0; i<s.length(); i++){
            if (map.containsKey(s.charAt(i)))
                map.get(s.charAt(i)).increaseCount();
            else
                map.put(s.charAt(i), new Node(i));
        }
        int index = Integer.MAX_VALUE;
        for(Map.Entry<Character, Node> entry : map.entrySet()){
            if(entry.getValue().count == 1)
                index = Math.min(index, entry.getValue().index);
        }
        if(index != Integer.MAX_VALUE)
            return s.charAt(index)+"";
        return -1+"";
    }

    static class Node{
        int index;
        int count;

        Node(int index){
            this.index = index;
            this.count = 1;
        }

        void increaseCount(){
            this.count++;
        }
    }
}
