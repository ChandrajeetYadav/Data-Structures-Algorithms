package com.sde.chandu.string;

import java.util.PriorityQueue;

public class StringModificationNo2AdjacentAreSame {
    public static void main(String[] args) {
        String s1 = "geeksforgeeks";
        String s2 = "bbbbb";
        System.out.println("Modification possible for " + s1 + " : " + isModificationPossibleUsingHashing(s1));
        System.out.println();
        System.out.println("Modification possible for " + s2 + " : " + isModificationPossibleUsingHashing(s2));
        System.out.println();

        System.out.println("Modification possible for " + s1 + " : " + isModificationPossibleUsingPQ(s1));
        System.out.println();
        System.out.println("Modification possible for " + s2 + " : " + isModificationPossibleUsingPQ(s2));
        System.out.println();
    }

    // Time complexity : O(n) , n = s.length()
    // Space complexity : O(n) , for building result
    private static String isModificationPossibleUsingHashing(String s) {
        if (s == null || s.isEmpty())
            return "";
        int[] arr = new int[26];
        int maxFreq = Integer.MIN_VALUE, freq;
        char maxFreqCh = ' ';
        for (int i = 0; i < s.length(); i++) {
            freq = ++arr[s.charAt(i) - 'a'];
            if (maxFreq < freq) {
                maxFreq = freq;
                maxFreqCh = s.charAt(i);
            }
        }

        if (maxFreq - 1 > s.length() - maxFreq) {
            System.out.println("Modification is not possible");
            return null;
        }
        char[] res = new char[s.length()];
        int ind = 0;
        while (maxFreq > 0) {
            res[ind] = maxFreqCh;
            maxFreq--;
            ind += 2;
        }
        arr[maxFreqCh - 'a'] = 0;
        for (int i = 0; i < 26; i++) {
            while (arr[i] > 0) {
                ind = ind >= s.length() ? 1 : ind;
                res[ind] = (char) ('a' + i);
                arr[i]--;
                ind += 2;
            }
        }
        return new String(res);
    }

    // Time complexity : O(n * log n) , n = s.length()
    // Space complexity : O(n)
    private static String isModificationPossibleUsingPQ(String s) {
        if (s == null || s.isEmpty())
            return "";
        final int ALPHA_SIZE = 26;
        int[] count = new int[ALPHA_SIZE];
        for (int i = 0; i < s.length(); i++)
            count[s.charAt(i) - 'a']++;
        PriorityQueue<Key> pq = new PriorityQueue<>((Key k1, Key k2) -> k2.freq - k1.freq);
        for (int i = 0; i < ALPHA_SIZE; i++) {
            if (count[i] > 0)
                pq.add(new Key(count[i], (char) ('a' + i)));
        }
        String res = "";
        Key prev = new Key(-1, '#');
        Key key;
        while (!pq.isEmpty()) {
            key = pq.poll();
            res += key.ch;
            if (prev.freq > 0)
                pq.add(prev);
            key.freq--;
            prev = key;
        }
        if (res.length() != s.length()) {
            System.out.println("Modification is not possible");
            return null;
        }
        return res;
    }

    static class Key {
        int freq;
        char ch;

        Key(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }
    }
}