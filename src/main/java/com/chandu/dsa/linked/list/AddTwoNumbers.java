package com.chandu.dsa.linked.list;
/*Input:
        List1: 5->6->3 // represents number 365
        List2: 8->4->2 // represents number 248
  Output:
        Resultant list: 3->1->6 // represents number 613*/
public class AddTwoNumbers {
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
        //Node.printList(addBrute(head1, head2));
        Node.printList(addLists(head1, head2));
        System.out.println("List on adding list 3 and 4:");
        //Node.printList(addBrute(head3, head4));
        Node.printList(addLists(head3, head4));
    }

    //Time complexity: O(max(M, N))
    //Space complexity: O(max(M, N) + 1)
    public static Node addLists(Node head1, Node head2){
        int sum;
        int carry = 0;
        Node res = null;
        Node temp = null;
        while(head1!=null || head2!=null){
            sum = carry + (head1!=null ? head1.data : 0) + (head2!=null ? head2.data : 0);
            carry = sum>=10 ? 1 : 0;
            sum = sum % 10;
            if(res == null){
                res = new Node(sum);
                temp = res;
            }else {
                temp.next = new Node(sum);
                temp = temp.next;
            }
            if(head1 != null)
                head1 = head1.next;
            if(head2 != null)
                head2 = head2.next;
        }
        if(carry > 0)
            temp.next = new Node(carry);
        return res;
    }

    //Time complexity: O(M + N)
    //Space complexity: O(max(M,N) + 1)
    public static Node addBrute(Node head1, Node head2){
        if(head1 == null)
            return head2;
        if(head2 == null)
            return head1;
        int num1 = 0;
        int num2 = 0;
        int i = 0;
        while(head1 != null){
            num1 = head1.data * (int)Math.pow(10, i++) + num1;
            head1 = head1.next;
        }
        i = 0;
        while(head2 != null){
            num2 = head2.data * (int)Math.pow(10, i++) + num2;
            head2 = head2.next;
        }
        int res = num1 + num2;
        Node temp = null;
        Node head = null;

        while(res != 0){
            if(temp == null){
                temp = new Node(res % 10);
                head = temp;
            }else {
                temp.next = new Node(res % 10);
                temp = temp.next;
            }
            res = res/10;
        }
        return head;
    }
}
