package com.chandu.dsa.greedy;

import java.util.*;

public class ActivitySelectionProblem {

    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        printMaximumActivities(start, end);

        int[] start1 = {1, 3, 2, 5, 8, 5};
        int[] end1 = {2, 4, 6, 7, 9, 9};
        printMaximumActivities(start, end);
    }

    //Time Complexity : O(n log n)
    //Space Complexity : O(n)
    //If array is sorted, then we can do in O(n) time without sorting
    private static void printMaximumActivities(int[] start, int[] end){
        List<Activity> list = new ArrayList<>();
        for (int i=0; i< start.length; i++)
            list.add(new Activity(start[i], end[i]));
        Collections.sort(list, (Activity a, Activity b)->a.end - b.end);
        int count = 1;
        Activity prev = list.get(0);
        Activity temp;
        System.out.println("Activities are: ");
        System.out.println(0);
        for (int i=1; i<list.size(); i++){
            temp = list.get(i);
            if (temp.start >= prev.end){
                count++;
                prev = temp;
                System.out.println(i);
            }
        }

        System.out.println("Maximum number of activities is : " + count);
    }

    static class Activity{
        int start;
        int end;
        Activity(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}
