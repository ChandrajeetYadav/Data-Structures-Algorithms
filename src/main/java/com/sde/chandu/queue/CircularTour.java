package com.sde.chandu.queue;

public class CircularTour {
    public static void main(String[] args) {
        int[] petrol = {4, 6, 7, 4};
        int[] distance = {6, 5, 3, 5};
        System.out.println("Starting index of circular tour, brute approach: " + tourBrute(petrol, distance));
        System.out.println("Starting index of circular tour, using queue: " + tourUsingQueue(petrol, distance));
    }

    // Time complexity : O(n^2)
    // Space complexity : O(1)
    private static int tourBrute(int[] petrol, int[] distance) {
        if (petrol == null || distance == null || petrol.length == 0 || distance.length == 0 || petrol.length != distance.length)
            return -1;
        int curPetrol = 0, curIndex, length = petrol.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                curIndex = (i + j) % length;
                curPetrol = curPetrol + petrol[curIndex] - distance[curIndex];
                if (curPetrol < 0)
                    break;
            }
            if (curPetrol >= 0)
                return i;
            curPetrol = 0;
        }
        return -1;
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static int tourUsingQueue(int[] petrol, int[] distance) {
        if (petrol == null || distance == null || petrol.length == 0 || distance.length == 0 || petrol.length != distance.length)
            return -1;
        int start = 0, end = 1, length = petrol.length;
        int curPetrol = petrol[start] - distance[start];
        while (start != end || curPetrol < 0) {
            while (curPetrol < 0 && start != end) {
                curPetrol = curPetrol - (petrol[start] - distance[start]);
                start = (start + 1) % length;
                if (start == 0)
                    return -1;
            }
            curPetrol = curPetrol + petrol[end] - distance[end];
            end = (end + 1) % length;
        }
        return start;
    }
}
