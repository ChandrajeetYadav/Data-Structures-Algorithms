package com.sde.chandu.hashing;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        /*String s = "this is a test string";
        String pattern = "tist";*/

        String s = "zoomlazapzo";
        String pattern = "oza";

        System.out.println("Minimum window substring, brute: " + findSubstringBrute(s, pattern));
        System.out.println("Minimum window substring, hahsing: " + findSubstringHashing(s, pattern));
    }

    //Time complexity : O(n^3)
    //Space complexity : O(n)
    private static String findSubstringBrute(String s, String pattern){
        int sLength = s.length();
        int pLength = pattern.length();
        if (sLength < pLength)
            return null;
        boolean flag;
        String substring, res=null;
        int minLen = Integer.MAX_VALUE;
        for (int i=0; i<sLength-1; i++){
            for (int j=i+1; j<=s.length(); j++){
                substring = s.substring(i, j);
                flag = containsPattern(substring, pattern);
                if (flag && substring.length()<minLen){
                    minLen = substring.length();
                    res = substring;
                }
            }
        }
        return res;
    }

    private static boolean containsPattern(String s, String pattern){
        if (s.length() < pattern.length())
            return false;
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();
        char ch;
        for (int i=0; i<s.length(); i++){
            ch = s.charAt(i);
            sMap.put(ch, sMap.getOrDefault(ch, 0)+1);
        }
        for (int i=0; i<pattern.length(); i++){
            ch = pattern.charAt(i);
            pMap.put(ch, pMap.getOrDefault(ch, 0)+1);
        }
        Integer sVal, pVal;
        for (Map.Entry<Character, Integer> entry : pMap.entrySet()){
            sVal = sMap.get(entry.getKey());
            pVal = entry.getValue();
            if (sVal==null || sVal<pVal)
                return false;
        }
        return true;
    }

    //Time complexity: O(|S|)
    //Space complexity: O(1)
    private static String findSubstringHashing(String s, String pattern){
        if (s.length() < pattern.length())
            return null;
        int[] hashStr = new int[256];
        int[] hashPat = new int[256];

        for (int i=0; i<pattern.length(); i++)
            hashPat[pattern.charAt(i)]++;
        int count=0, start=0, startIndex=-1, minLen=Integer.MAX_VALUE, winLen;

        for (int j=0; j<s.length(); j++){
            hashStr[s.charAt(j)]++;
            if (hashStr[s.charAt(j)] <= hashPat[s.charAt(j)])
                count++;
            if (count == pattern.length()){
                while (hashStr[s.charAt(start)] > hashPat[s.charAt(start)] || hashPat[s.charAt(start)]==0){
                    if (hashStr[s.charAt(start)] > hashPat[s.charAt(start)])
                        hashStr[s.charAt(start)]--;
                    start++;
                }
                winLen = j - start + 1;
                if (minLen > winLen){
                    minLen = winLen;
                    startIndex = start;
                }
            }
        }
        if (startIndex == -1)
            return null;
        return s.substring(startIndex, startIndex+minLen);
    }
}
