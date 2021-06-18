package com.sde.chandu.string;

import java.util.ArrayList;
import java.util.List;

public class GenerateIpAddresses {
    public static void main(String[] args) {
        String ip = "25525511135";
        System.out.println("Ip addresses that can be formed from " + ip + " :");
        generateIpAddresses(ip);
    }

    // Time complexity : O(n^3)
    // Space complexity : O(n)
    private static void generateIpAddresses(String ip) {
        if (ip == null || ip.length() < 4 || ip.length() > 12)
            return;
        List<String> list = new ArrayList<>();
        int n = ip.length();
        for (int i = 1; i < n && i < 4; i++) {
            String first = ip.substring(0, i);
            if (!isValidPart(first))
                continue;
            for (int j = 1; i + j < n && j < 4; j++) {
                String second = ip.substring(i, i + j);
                if (!isValidPart(second))
                    continue;
                for (int k = 1; i + j + k < n && k < 4; k++) {
                    String third = ip.substring(i + j, i + j + k);
                    String fourth = ip.substring(i + j + k);
                    if (!isValidPart(third) || !isValidPart(fourth))
                        continue;
                    list.add(first + "." + second + "." + third + "." + fourth);
                }
            }
        }
        list.sort((String s1, String s2) -> s1.compareTo(s2));
        System.out.println(list);
    }

    private static boolean isValidPart(String s) {
        if (s.length() > 3)
            return false;
        if (s.startsWith("0") && s.length() > 1)
            return false;
        int val = Integer.parseInt(s);
        return val >= 0 && val <= 255;
    }
}
