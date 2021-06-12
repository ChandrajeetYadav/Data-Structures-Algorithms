package com.sde.chandu.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostFrequentNonBadWord {
    public static void main(String[] args) {
        String paragraph = "geeks for geeks is best place to learn";
        String badWord = "bad place";
        System.out.println("Most frequent non bad word: " + getMostFrequentWord(paragraph, badWord));

        paragraph = "Hello World. Its good to finally greet the world.";
        badWord = "";
        System.out.println("Most frequent non bad word: " + getMostFrequentWord(paragraph, badWord));
    }

    // Time complexity : O(p + b), p = paragraph.length(), b = badWord.length()
    // SPace complexity : O(p + b)
    private static String getMostFrequentWord(String paragraph, String badWord) {
        badWord = badWord.toLowerCase();
        paragraph = paragraph.toLowerCase();
        Set<String> banned = new HashSet<>();
        for (int i = 0; i < badWord.length(); i++) {
            if (badWord.charAt(i) < 'a' || badWord.charAt(i) > 'z')
                continue;
            String s = "";
            while (i < badWord.length() && (badWord.charAt(i) >= 'a' && badWord.charAt(i) <= 'z'))
                s += badWord.charAt(i++);
            banned.add(s);
        }

        Map<String, Integer> result = new HashMap<>();
        int freq = 0;
        String ans = "";
        for (int i = 0; i < paragraph.length(); i++) {
            if (paragraph.charAt(i) < 'a' || paragraph.charAt(i) > 'z')
                continue;
            String s = "";
            while (i < paragraph.length() && (paragraph.charAt(i) >= 'a' && paragraph.charAt(i) <= 'z'))
                s += paragraph.charAt(i++);
            if (!banned.contains(s)) {
                result.put(s, result.getOrDefault(s, 0) + 1);
                if (result.get(s) > freq || (result.get(s) == freq && s.compareTo(ans) < 0)) {
                    ans = s;
                    freq = result.get(s);
                }
            }
        }
        return ans;
    }
}
