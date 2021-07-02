package com.sde.chandu.linkedlist;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CloneALinkedListWithNextAndRandomPointer {
    public static void main(String[] args) {
        DLL head = createList(5);
        DLL clone = cloneUsingExtraSpace(head);
        System.out.println("Original list: ");
        display(head);
        System.out.println();
        System.out.println("Cloned list using extra space: ");
        display(clone);
        System.out.println();

        System.out.println("Cloned list using hashing: ");
        clone = cloneUsingHashing(head);
        display(clone);
        System.out.println();

        System.out.println("Cloned list efficient: ");
        clone = cloneEfficient(head);
        display(clone);
        System.out.println();
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static DLL cloneUsingExtraSpace(DLL head) {
        if (head == null)
            return null;
        DLL clone = new DLL(head.data);
        DLL tempH = head, tempC = clone, next;
        while (tempH.next != null) {
            tempC.next = new DLL(tempH.next.data);
            tempH = tempH.next;
            tempC = tempC.next;
        }
        LinkedHashMap<DLL, DLL> map = new LinkedHashMap<>();
        tempH = head;
        while (tempH != null) {
            map.put(tempH, tempH.next);
            tempH = tempH.next;
        }
        tempH = head;
        tempC = clone;
        while (tempH != null) {
            next = tempH.next;
            tempH.next = tempC;
            tempC.random = tempH;
            tempC = tempC.next;
            tempH = next;
        }
        tempC = clone;
        while (tempC != null) {
            tempC.random = tempC.random.random != null ? tempC.random.random.next : null;
            tempC = tempC.next;
        }
        for (Map.Entry<DLL, DLL> entry : map.entrySet())
            entry.getKey().next = entry.getValue();
        return clone;
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    private static DLL cloneUsingHashing(DLL head) {
        if (head == null)
            return null;
        DLL cur = head, clone;
        Map<DLL, DLL> map = new HashMap<>();
        while (cur != null) {
            clone = new DLL(cur.data);
            map.put(cur, clone);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            clone = map.get(cur);
            clone.next = map.get(cur.next);
            clone.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static DLL cloneEfficient(DLL head) {
        if (head == null)
            return null;
        DLL cur = head, temp, copy;
        while (cur != null) {
            temp = cur.next;
            cur.next = new DLL(cur.data);
            cur.next.next = temp;
            cur = temp;
        }
        cur = head;
        while (cur != null) {
            cur.next.random = cur.random != null ? cur.random.next : null;
            cur = cur.next.next;
        }
        cur = head;
        copy = head.next;
        temp = copy;
        while (cur != null) {
            cur.next = cur.next.next;
            temp.next = temp.next != null ? temp.next.next : null;
            cur = cur.next;
            temp = temp.next;
        }
        return copy;
    }


    private static DLL createList(int n) {
        if (n <= 0)
            return null;
        DLL head = new DLL(1);
        DLL temp = head;
        for (int i = 2; i <= n; i++) {
            temp.next = new DLL(i);
            temp = temp.next;
        }
        head.random = head.next.next;
        head.next.random = head;
        head.next.next.random = head.next.next.next.next;
        head.next.next.next.random = head.next.next.next.next;
        head.next.next.next.next.random = head.next;
        return head;
    }

    private static void display(DLL head) {
        while (head != null) {
            System.out.print("Data=" + head.data + ", Random=" + head.random.data + "\t");
            head = head.next;
        }
        System.out.println();
    }

    static class DLL {
        int data;
        DLL next;
        DLL random;

        DLL(int data) {
            this.data = data;
        }
    }
}
