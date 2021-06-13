package com.sde.chandu.string;

import java.util.*;

public class PrintAnagramsTogether {
    public static void main(String[] args) {
        String[] wordArr = {"cat", "dog", "tac", "god", "act"};
        System.out.println("Anagrams together, auxiliary array : ");
        printAnagramsTogetherUsingAuxiliaryArrays(wordArr);

        System.out.println();
        System.out.println("Anagrams together, using hashing : ");
        printAnagramsTogetherUsingHashing(wordArr);
    }

    // Time complexity : O(n * m log m), n = no of words, m = maximum characters in each word
    //Space complexity : O(n * m)
    private static void printAnagramsTogetherUsingHashing(String[] wordArr) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : wordArr) {
            char[] ch = word.toCharArray();
            Arrays.sort(ch);
            String newWord = new String(ch);
            if (map.containsKey(newWord))
                map.get(newWord).add(word);
            else {
                List<String> list = new ArrayList<>();
                list.add(word);
                map.put(newWord, list);
            }
        }
        map.forEach((m, n) -> System.out.print(n));
    }

    // Time complexity : O(n * m log m + m * n log n), n = no of words, m = maximum characters in each word
    //Space complexity : O(n * m)
    private static void printAnagramsTogetherUsingAuxiliaryArrays(String[] wordArr) {
        if (wordArr == null || wordArr.length <= 2)
            return;
        Word[] words = new Word[wordArr.length];
        for (int i = 0; i < wordArr.length; i++) {
            char[] ch = wordArr[i].toCharArray();
            Arrays.sort(ch);
            words[i] = new Word(new String(ch), i);
        }
        Arrays.sort(words, (Word a, Word b) -> a.str.compareTo(b.str));
        for (Word word : words)
            System.out.print(wordArr[word.index] + "\t");
    }

    static class Word {
        String str;
        int index;

        Word(String str, int index) {
            this.str = str;
            this.index = index;
        }
    }
}
