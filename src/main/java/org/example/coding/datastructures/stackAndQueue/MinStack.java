package org.example.coding.datastructures.stackAndQueue;

import java.util.Iterator;
import java.util.Stack;

public class MinStack {
    private int min;
    private final Stack<Integer> minStack;

    public MinStack() {
        this.min = Integer.MAX_VALUE;
        this.minStack = new Stack<>();
    }

    public void push(int val) {
        if (val <= min) {
            this.min = val;
        }
        minStack.push(val);
    }

    public void pop() {
        if (minStack.isEmpty()) return;
        int ele = minStack.pop();
        if (ele <= min) {
            this.min = Integer.MAX_VALUE;
            this.updateMin();
        }
        return;
    }

    private void updateMin() {
        if (minStack.isEmpty()) {
            this.min = Integer.MAX_VALUE;
            return;
        }
        for (int ele : minStack) {
            if (ele <= this.min) {
                this.min = ele;
            }
        }
    }

    public int top() {
        if (minStack.isEmpty()) return -1;
        return minStack.peek();
    }

    public int getMin() {
        return min;
    }
}