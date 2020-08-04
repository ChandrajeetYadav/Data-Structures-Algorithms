package com.chandu.dsa.linked.list;

/*Input:
        First List: 5->6->3  // represents number 563
        Second List: 8->4->2 //  represents number 842
  Output
        Resultant list: 1->4->0->5  // represents number 1405*/
public class AddTwoNumbers2 {
    public static void main(String[] args) {
        Node head1 = Node.createLinkedList(new int[]{5, 6, 3});
        Node head2 = Node.createLinkedList(new int[]{8, 4, 2});

        System.out.println("List 1: ");
        Node.printList(head1);
        System.out.println("List 2: ");
        Node.printList(head2);
        System.out.println("Resultant list is: ");

        Node head3 = Node.createLinkedList(new int[]{7, 5, 9, 4, 6});
        Node head4 = Node.createLinkedList(new int[]{8, 4});
        System.out.println("List 3: ");
        Node.printList(head3);
        System.out.println("List 4: ");
        Node.printList(head4);

        System.out.println("Resultant list is: ");
        System.out.println("List on adding list 1 and 2:");
        //Node.printList(addListsBrute(head1, head2));
        Node.printList(addLists(head1, head2));
        System.out.println("List on adding list 3 and 4:");
        //Node.printList(addListsBrute(head3, head4));
        Node.printList(addLists(head3, head4));
    }

    //Time Complexity: O(M+N)
    //Space Complexity : O(max(M,N) + 1)
    public static Node addLists(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        int len1 = getLength(head1);
        int len2 = getLength(head2);

        Helper helper = new Helper();
        if (len1 == len2)
            addSameSizeLists(head1, head2, helper);
        else {
            if (len1 < len2) {
                Node temp = head1;
                head1 = head2;
                head2 = temp;
            }
            int diff = Math.abs(len1 - len2);
            int count = 0;
            Node temp = head1;
            while (count++ < diff)
                temp = temp.next;
            addSameSizeLists(temp, head2, helper);
            propogateCarry(head1, temp, helper);
        }
        if (helper.carry > 0) {
            Node temp = new Node(helper.carry);
            temp.next = helper.head;
            helper.head = temp;
        }
        return helper.head;
    }

    public static void addSameSizeLists(Node head1, Node head2, Helper helper) {
        if (head1 == null)
            return;
        addSameSizeLists(head1.next, head2.next, helper);
        int sum = head1.data + head2.data + helper.carry;
        helper.carry = sum >= 10 ? 1 : 0;
        sum = sum % 10;
        Node temp = new Node(sum);
        temp.next = helper.head;
        helper.head = temp;
    }

    public static void propogateCarry(Node head, Node curr, Helper helper) {
        int sum;
        Node temp;
        if (head != curr) {
            propogateCarry(head.next, curr, helper);
            sum = head.data + helper.carry;
            helper.carry = sum / 10;
            sum = sum % 10;
            temp = new Node(sum);
            temp.next = helper.head;
            helper.head = temp;
        }
    }

    public static int getLength(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    static class Helper {
        int carry;
        Node head;
    }

    //Time Complexity: O(M+N)
    //Space Complexity : O(max(M,N) + 1)
    //Note: This will fail if list is too large
    public static Node addListsBrute(Node head1, Node head2) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        int num1 = 0, num2 = 0;
        while (head1 != null) {
            num1 = num1 * 10 + head1.data;
            head1 = head1.next;
        }
        while (head2 != null) {
            num2 = num2 * 10 + head2.data;
            head2 = head2.next;
        }
        int res = num1 + num2;
        Node temp = null, prev = null;
        while (res != 0) {
            temp = new Node(res % 10);
            temp.next = prev;
            prev = temp;
            res = res / 10;
        }
        return temp;
    }
}
