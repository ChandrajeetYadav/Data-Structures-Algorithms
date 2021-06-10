package com.sde.chandu.string;

import java.util.Arrays;
import java.util.Comparator;

public class ReorderDataFiles {
    public static void main(String[] args) {
        String[] filename = {"file1 1 2 3", "space2 geeksforgeeks", "true1 check"};

        fileReorder(filename);
        System.out.println("Files after reordering: ");
        printArray(filename);
    }

    // Time complexity : O(n log n)
    // Space complexity : O(1)
    private static void fileReorder(String[] filename) {
        Arrays.sort(filename, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String identifier1 = s1.substring(0, s1.indexOf(" "));
                String fileContent1 = s1.substring(s1.indexOf(" ") + 1);

                String identifier2 = s2.substring(0, s2.indexOf(" "));
                String fileContent2 = s2.substring(s2.indexOf(" ") + 1);

                boolean isDigitFileS1 = fileContent1.charAt(0) >= '0' && fileContent1.charAt(0) <= '9';
                boolean isDigitFileS2 = fileContent2.charAt(0) >= '0' && fileContent2.charAt(0) <= '9';

                if (isDigitFileS1 && isDigitFileS2)
                    return 0;
                else if (isDigitFileS1)
                    return 1;
                else if (isDigitFileS2)
                    return -1;
                else {
                    if (fileContent1.equals(fileContent2))
                        return identifier1.compareTo(identifier2);
                    return fileContent1.compareTo(fileContent2);
                }
            }
        });
    }

    private static void printArray(String[] arr) {
        for (String s : arr)
            System.out.println(s);
    }
}
