package org.example.coding.datastructures.stackAndQueue;

import java.util.Stack;

public class MaxStack {
    private int max;
    private final Stack<Integer> maxStack;

    public MaxStack() {
        this.maxStack = new Stack<>();
        this.max = Integer.MIN_VALUE;
    }

    public void push(int x) {
        if (x > max) {
            max = x;
        }
        maxStack.push(x);
    }

    public int top() {
        if(maxStack.isEmpty()) return -1;
        return maxStack.peek();
    }

    public int pop() {
        int ele = maxStack.peek();
        if (ele == max) {
            updateMax();
        }
        maxStack.pop();
        return ele;
    }

    public int peekMax() {
        return max;
    }

    public int popMax() {
        return updateMax();
    }

    private int updateMax() {
        Stack<Integer> temp = new Stack<>();
        while (!maxStack.isEmpty() && max != maxStack.peek()) {
            temp.push(maxStack.pop());
        }
        int oldMax = maxStack.pop();
        while (!temp.isEmpty()) {
            maxStack.push(temp.pop());
        }
        max = Integer.MIN_VALUE;
        for (Integer integer : maxStack) {
            max = Math.max(integer, max);
        }
        return oldMax;
    }
}
