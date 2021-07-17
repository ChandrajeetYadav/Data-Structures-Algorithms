package com.sde.chandu.stack;

public class TwoStacksUsingOneArrayEfficient {
    private int top1, top2;
    private int[] arr;
    private int size;

    TwoStacksUsingOneArrayEfficient(int size) {
        this.size = size;
        arr = new int[size];
        top1 = -1;
        top2 = size;
    }

    public static void main(String[] args) {
        TwoStacksUsingOneArrayEfficient ts = new TwoStacksUsingOneArrayEfficient(5);
        ts.push1(5);
        ts.push2(10);
        ts.push2(15);
        ts.push1(11);
        ts.push2(7);
        System.out.println("Popped element from stack1 is: " + ts.pop1());
        ts.push2(40);
        System.out.println("Popped element from stack2 is: " + ts.pop2());
    }

    boolean push1(int data) {
        if (top1 < top2 - 1) {
            arr[++top1] = data;
            return true;
        } else {
            System.out.println("Stack overflow while pushing: " + data);
            return false;
        }
    }

    boolean push2(int data) {
        if (top1 < top2 - 1) {
            arr[--top2] = data;
            return true;
        } else {
            System.out.println("Stack overflow while pushing: " + data);
            return false;
        }
    }

    int pop1() {
        if (top1 < 0)
            return -1;
        return arr[top1--];
    }

    int pop2() {
        if (top2 >= size)
            return -1;
        return arr[top2++];
    }
}
