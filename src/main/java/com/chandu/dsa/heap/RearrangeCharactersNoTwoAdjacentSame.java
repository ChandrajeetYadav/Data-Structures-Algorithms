package com.chandu.dsa.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RearrangeCharactersNoTwoAdjacentSame {
    public static void main(String[] args) {
        String s1 = "geeksforgeeks";
        String s2 = "bbbabaaacd";
        String s3 = "bbbbb";
        /*System.out.println("Is rearranging possible for s1: " + isRearrangingPossibleUsingHashing(s1));
        System.out.println("Is rearranging possible for s2: " + isRearrangingPossibleUsingHashing(s2));
        System.out.println("Is rearranging possible for s3: " + isRearrangingPossibleUsingHashing(s3));*/

        System.out.println("Is rearranging possible for s1: " + isRearrangingPossibleUsingMaxHeap(s1));
        System.out.println("Is rearranging possible for s2: " + isRearrangingPossibleUsingMaxHeap(s2));
        System.out.println("Is rearranging possible for s3: " + isRearrangingPossibleUsingMaxHeap(s3));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(A), A is the size of the alphabet
    public static boolean isRearrangingPossibleUsingHashing(String s){
        Map<Character, Integer> map = new HashMap<>();
        char ch;
        int maxFreq = 0;
        for(int i=0; i<s.length(); i++){
            ch = s.charAt(i);
            if(map.containsKey(ch))
                map.put(ch, map.get(ch)+1);
            else
                map.put(ch, 1);
            maxFreq = map.get(ch) > maxFreq ? map.get(ch) : maxFreq;
        }
        return maxFreq <= (s.length() - maxFreq + 1);
    }

    //Time Complexity: O(n log A)=O(n) as size of A is fixed
    //Space Complexity: O(A), A is the size of the alphabet
    public static boolean isRearrangingPossibleUsingMaxHeap(String s){
        int[] arr = new int[26];
        for (int i=0; i<s.length(); i++)
            arr[s.charAt(i) - 'a']++;
        PriorityQueue<Key> pq = new PriorityQueue<>((Key k1, Key k2)->-(k1.freq - k2.freq));
        int i;
        for(char c='a'; c<='z'; c++){
            i = c - 'a';
            if(arr[i] > 0)
                pq.add(new Key(c, arr[i]));
        }
        Key prev = new Key('#', 0);
        StringBuilder sb = new StringBuilder();
        Key temp;
        while (!pq.isEmpty()){
            temp = pq.poll();
            sb.append(temp.ch);
            if(prev.freq > 0)
                pq.add(prev);
            temp.freq--;
            prev = temp;
        }
        System.out.println("Rearranged string: " + sb);
        return sb.length() == s.length();
    }

    static class Key {
        char ch;
        int freq;
        Key(char ch, int freq){
            this.ch = ch;
            this.freq = freq;
        }
    }
}
