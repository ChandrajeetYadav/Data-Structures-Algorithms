package com.sde.chandu.linkedlist;

public class PolynomialAddition {
    public static void main(String[] args) {
        int[] coeff1 = {5, 4, 2};
        int[] pow1 = {2, 1, 0};
        int[] coeff2 = {-5, -5};
        int[] pow2 = {1, 0};
        PolynomialNode poly1 = createPolynomialList(coeff1, pow1);
        PolynomialNode poly2 = createPolynomialList(coeff2, pow2);
        System.out.println("Polynomial expression 1: ");
        display(poly1);
        System.out.println("Polynomial expression 2: ");
        display(poly2);
        System.out.println("Sum pof polynomial expressions 1 and 2: ");
        PolynomialNode res = addPolynomial(poly1, poly2);
        display(res);
    }

    // Time complexity : O(m+n)
    // Space complexity : O(1)
    private static PolynomialNode addPolynomial(PolynomialNode head1, PolynomialNode head2) {
        PolynomialNode dummy = new PolynomialNode(0, 0);
        PolynomialNode last = dummy;
        while (head1 != null || head2 != null) {
            if (head1 == null) {
                last.next = head2;
                break;
            }
            if (head2 == null) {
                last.next = head1;
                break;
            }
            if (head1.pow == head2.pow) {
                last.next = new PolynomialNode(head1.coeff + head2.coeff, head1.pow);
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.pow > head2.pow) {
                last.next = new PolynomialNode(head1.coeff, head1.pow);
                head1 = head1.next;
            } else {
                last.next = new PolynomialNode(head2.coeff, head2.pow);
                head2 = head2.next;
            }
            last = last.next;
        }
        return dummy.next;
    }

    private static void display(PolynomialNode head) {
        if (head == null)
            return;
        char sign;
        System.out.print(head.coeff + "x" + "^" + head.pow + " ");
        head = head.next;
        int coeff;
        while (head != null) {
            sign = head.coeff < 0 ? '-' : '+';
            coeff = sign == '-' ? head.coeff * (-1) : head.coeff;
            System.out.print(sign + " " + coeff + "x" + "^" + head.pow + " ");
            head = head.next;
        }
        System.out.println();
    }

    private static PolynomialNode createPolynomialList(int[] coeff, int[] pow) {
        if (coeff == null || coeff.length == 0)
            return null;
        PolynomialNode head = new PolynomialNode(coeff[0], pow[0]);
        PolynomialNode temp = head;
        for (int i = 1; i < coeff.length; i++) {
            temp.next = new PolynomialNode(coeff[i], pow[i]);
            temp = temp.next;
        }
        return head;
    }

    static class PolynomialNode {
        int coeff;
        int pow;
        PolynomialNode next;

        PolynomialNode(int coeff, int pow) {
            this.coeff = coeff;
            this.pow = pow;
        }
    }
}
