package com.sde.chandu.queue;

import java.util.*;

public class LRUCache {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 4, 5, 2, 2, 1};
        System.out.println("LRU cache using queue: ");
        int cacheSize = 4;
        LRUCacheUsingQueue(arr, cacheSize);
        System.out.println();

        System.out.println("LRU cache using linked hash set: ");
        LRUCacheUsingLinkedHashSet(arr, cacheSize);
    }

    // set - Time complexity: O(1), Space complexity: O(1)
    // get - Time complexity: O(1), Space complexity: O(1)
    private static void LRUCacheUsingQueue(int[] arr, int cacheSize) {
        Deque<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int last;
        for (int i : arr) {
            if (!set.contains(i)) {
                if (queue.size() == cacheSize) {
                    System.out.println("Page fault");
                    last = queue.removeFirst();
                    set.remove(last);
                }
            } else {
                System.out.println("Hit");
                queue.remove(i);
            }
            set.add(i);
            queue.add(i);
            System.out.println("Cache is: " + queue);
        }
    }

    // set - Time complexity: O(1), Space complexity: O(1)
    // get - Time complexity: O(1), Space complexity: O(1)
    private static void LRUCacheUsingLinkedHashSet(int[] arr, int cacheSize) {
        Set<Integer> set = new LinkedHashSet<>(cacheSize);
        for (int i : arr) {
            if (set.contains(i)) {
                System.out.println("Hit");
                set.remove(i);
            } else {
                if (set.size() == cacheSize) {
                    System.out.println("Page fault");
                    set.remove(set.iterator().next());
                }
            }
            set.add(i);
            System.out.println("Cache is: " + set);
        }
    }
}
