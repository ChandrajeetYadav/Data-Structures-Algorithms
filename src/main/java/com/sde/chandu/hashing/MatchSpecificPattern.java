package com.sde.chandu.hashing;

import java.util.HashMap;
import java.util.Map;

public class MatchSpecificPattern {
    public static void main(String[] args) {
        String[] dict = {"abb", "abc", "xyz", "xyy"};
        String pattern = "foo";

        System.out.println("Matched words, hashing:");
        findMatchedWordsHashing(dict, pattern);
        System.out.println();

        System.out.println("Matched words, hashing 2:");
        findMatchedWordsHashing2(dict, pattern);
    }

    //Time complexity: O(n * k)
    //Space complexity: O(n)
    private static void findMatchedWordsHashing(String[] dict, String pattern){
        String hash = encodeString(pattern);
        for (String s : dict){
            if (s.length()==pattern.length() && hash.equals(encodeString(s)))
                System.out.print(s + "\t");
        }
        System.out.println();
    }

    private static String encodeString(String s){
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        char ch;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            ch = s.charAt(i);
            if (!map.containsKey(ch))
                map.put(ch, count++);
            sb.append(map.get(ch));
        }
        return sb.toString();
    }

    //Time complexity: O(n * k)
    //Space complexity: O(n)
    private static void findMatchedWordsHashing2(String[] dict, String pattern){
        for (String s : dict){
            if (isSamePattern(s, pattern))
                System.out.print(s + "\t");
        }
        System.out.println();
    }

    private static boolean isSamePattern(String s, String pattern){
        if (s.length() != pattern.length())
            return false;
        Map<Character, Character> map = new HashMap<>();
        char ch;
        for (int i=0; i<s.length(); i++){
            ch = pattern.charAt(i);
            if (!map.containsKey(ch))
                map.put(ch, s.charAt(i));
            else if (map.get(ch) != s.charAt(i))
                    return false;
        }
        return true;
    }
}
