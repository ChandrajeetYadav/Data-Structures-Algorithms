package com.chandu.dsa.linked.list;

import java.util.Stack;

public class IsPalindrome {
    public static void main(String[] args) {
        Node head1 = Node.createLinkedList(new int[]{1, 2, 1});
        System.out.println("List 1:");
        Node.printList(head1);
        Node head2 = Node.createLinkedList(new int[]{1, 2, 3, 4});
        System.out.println("List 2:");
        Node.printList(head2);
        //System.out.println("Is List1 palindrome: " + isPalindromeUsingStack(head1));
        //System.out.println("Is List2 palindrome: " + isPalindromeUsingStack(head2));
        //System.out.println("Is List1 palindrome: " + isPalindromeUsingRecursion(head1));
        //System.out.println("Is List2 palindrome: " + isPalindromeUsingRecursion(head2));
        System.out.println("Is List1 palindrome: " + isPalindrome(head1));
        System.out.println("Is List2 palindrome: " + isPalindrome(head2));
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public static boolean isPalindrome(Node head){
        if(head==null || head.next==null)
            return true;
        Node slow = head, fast = head;
        Node prevSlow = null;
        Node mid = null;

        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            prevSlow = slow;
            slow = slow.next;
        }
        if(fast != null){
            mid = slow;
            slow = slow.next;
        }

        prevSlow.next = null;
        slow = reverse(slow);
        boolean isPalindrome = compareLists(head, slow);
        slow = reverse(slow);

        if(mid != null){
            prevSlow.next = mid;
            mid.next = slow;
        }else {
            prevSlow.next = slow;
        }
        return isPalindrome;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static boolean isPalindromeUsingStack(Node head){
        if(head==null || head.next==null)
            return true;
        Node temp = head;
        Stack<Integer> stack = new Stack<>();
        while(temp != null){
            stack.push(temp.data);
            temp = temp.next;
        }

        while (!stack.isEmpty()){
            if(stack.pop() != head.data)
                return false;
            head = head.next;
        }
        return true;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public static boolean isPalindromeUsingRecursion(Node head){
        Helper helper = new Helper(head);
        return isPalindromeUsingRecursion(helper, head);
    }


    public static boolean isPalindromeUsingRecursion(Helper helper, Node right){
        if(right == null)
            return true;
        boolean isPalindrome = isPalindromeUsingRecursion(helper, right.next);
        if(!isPalindrome)
            return false;
        isPalindrome = (helper.left.data == right.data);
        helper.left = helper.left.next;
        return isPalindrome;
    }

    public static Node reverse(Node head){
        Node prev=null, curr=head, next;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static boolean compareLists(Node head1, Node head2){
        while (head1!=null && head2!=null){
            if(head1.data != head2.data)
                return false;
            head1 = head1.next;
            head2 = head2.next;
        }
        if(head1==null && head2==null)
            return true;
        return false;
    }

    static class Helper{
        Node left;
        Helper(Node node){
            left = node;
        }
    }
}
