package com.chandu.dsa.heap;

import java.util.Arrays;

public class RearrangeCharactersNoAdjacentConsecutive {
    public static void main(String[] args) {
        String s1 = "aabcd";
        String s2 = "aab";
        System.out.println("Is rearranging possible for s1: " + isRearrangePossible(s1));
        System.out.println("Is rearranging possible for s2: " + isRearrangePossible(s2));
    }

    public static boolean isRearrangePossible(String s){
        StringBuilder evenSb = new StringBuilder();
        StringBuilder oddSb = new StringBuilder();

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)%2 == 0)
                evenSb.append(s.charAt(i));
            else
                oddSb.append(s.charAt(i));
        }
        String even = sort(evenSb.toString());
        String odd = sort(oddSb.toString());
        if(check(even + odd)){
            System.out.println(even + odd);
            return true;
        }else if (check(odd + even)){
            System.out.println(odd + even);
            return true;
        }
        return false;
    }

    public static boolean check(String s){
        for (int i=0; i<s.length()-1; i++){
            if(Math.abs(s.charAt(i) - s.charAt(i+1)) == 1)
                return false;
        }
        return true;
    }

    public static String sort(String s){
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
