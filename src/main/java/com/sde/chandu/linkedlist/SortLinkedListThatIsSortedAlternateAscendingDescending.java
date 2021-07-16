package com.sde.chandu.linkedlist;

public class SortLinkedListThatIsSortedAlternateAscendingDescending {
    public static void main(String[] args) {
        int[] arr = {10, 40, 53, 30, 67, 12, 89};
        Node head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after sorting, using merge sort:");
        head = sortUsingMergeSort(head);
        head.display();
        System.out.println();

        head = Node.createLinkedList(arr);
        System.out.println("Original list: ");
        head.display();
        System.out.println("List after sorting, efficient approach:");
        head = sortEfficient(head);
        head.display();
    }

    // Time complexity : O(n)
    // Space complexity : O(1)
    private static Node sortEfficient(Node head) {
        Node asc = new Node(0);
        Node des = new Node(0);
        splitList(head, asc, des);
        asc = asc.next;
        des = des.next;
        des = reverse(des);
        return merge(asc, des);
    }

    private static Node merge(Node head1, Node head2) {
        Node dummy = new Node(0);
        Node last = dummy;
        while (true) {
            if (head1 == null) {
                last.next = head2;
                break;
            }
            if (head2 == null) {
                last.next = head1;
                break;
            }
            if (head1.data < head2.data) {
                last.next = head1;
                head1 = head1.next;
            } else {
                last.next = head2;
                head2 = head2.next;
            }
            last = last.next;
        }
        return dummy.next;
    }

    private static Node reverse(Node head) {
        if (head == null || head.next == null)
            return head;
        Node rest = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return rest;
    }

    private static void splitList(Node head, Node asc, Node des) {
        Node cur = head;
        while (cur != null) {
            asc.next = cur;
            asc = asc.next;
            cur = cur.next;
            if (cur != null) {
                des.next = cur;
                des = des.next;
                cur = cur.next;
            }
        }
        asc.next = null;
        des.next = null;
    }

    // Time complexity: O(n log n), The merge sort of linked list takes O(n log n) time.
    // In the merge sort tree, the height is log n. Sorting each level will take O(n) time.
    // So time complexity is O(n log n).

    // Space complexity: O(n log n),  In the merge sort tree the height is log n.
    // Storing each level will take O(n) space. So space complexity is O(n log n).
    private static Node sortUsingMergeSort(Node head) {
        if (head == null || head.next == null)
            return head;
        Node mid = getMiddle(head);
        Node nextOfMid = mid.next;
        mid.next = null;
        Node left = sortUsingMergeSort(head);
        Node right = sortUsingMergeSort(nextOfMid);
        return mergeRecursively(left, right);
    }

    private static Node mergeRecursively(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        if (head1.data < head2.data) {
            head1.next = mergeRecursively(head1.next, head2);
            return head1;
        } else {
            head2.next = mergeRecursively(head1, head2.next);
            return head2;
        }
    }

    private static Node getMiddle(Node head) {
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
