package org.example.coding.datastructures.stackAndQueue;

public class TwoStacks {
    private final int[] arr;
    private int top1;
    private int top2;

    public TwoStacks() {
        arr = new int[100];
        top1 = -1;
        top2 = 100;
    }

    // Function to push an integer into the stack1.
    void push1(int x) {
        if (top1 + 1 == top2) return;
        arr[++top1] = x;

    }

    // Function to push an integer into the stack2.
    void push2(int x) {
        if (top1 + 1 == top2) return;
        arr[--top2] = x;
        // code here
    }

    // Function to remove an element from top of the stack1.

    int pop1() {
        if (top1 == -1) return -1;
        return arr[top1--];
    }


    // Function to remove an element from top of the stack2.
    int pop2() {
        if (top2 == 100) return -1;
        return arr[top2++];
    }
}
