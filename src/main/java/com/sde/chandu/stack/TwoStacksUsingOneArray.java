package com.sde.chandu.stack;

public class TwoStacksUsingOneArray {
    private int top1, top2, size;
    private int[] arr;

    TwoStacksUsingOneArray(int size) {
        this.size = size;
        arr = new int[size];
        top1 = size / 2 + 1;
        top2 = size / 2;
    }

    public static void main(String[] args) {
        TwoStacksUsingOneArray stack = new TwoStacksUsingOneArray(5);
        stack.push1(5);
        stack.push2(10);
        stack.push2(15);
        stack.push1(11);
        stack.push2(7);
        System.out.println("Popped element from stack 1: " + stack.pop1());
        stack.push2(40);
        System.out.println("Popped element from stack 2: " + stack.pop2());
    }

    boolean push1(int data) {
        if (top1 > 0) {
            arr[--top1] = data;
            return true;
        } else {
            System.out.println("Stack overflow while pushing: " + data);
            return false;
        }
    }

    boolean push2(int data) {
        if (top2 < size - 1) {
            arr[++top2] = data;
            return true;
        } else {
            System.out.println("Stack overflow while pushing: " + data);
            return false;
        }
    }

    int pop1() {
        if (top1 <= size / 2)
            return arr[top1++];
        else
            return -1;
    }

    int pop2() {
        if (top2 >= size / 2 + 1)
            return arr[top2--];
        else
            return -1;
    }
}
