package com.javarena.dsa.datastructures.stackAndQueue;

import java.util.Stack;

/**
 * Max Stack
 *
 * <p><b>Problem Statement:</b><br>
 * Design stack supporting push, pop, top, peekMax, and popMax operations.
 * All operations should be efficient.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Similar to MinStack but tracking maximum:
 * - Maintain regular stack + track current max
 * - On push: Update max if new value larger
 * - PopMax: Find and remove max element, recalculate new max
 * - Uses temporary stack to access middle elements
 * 
 * Optimization: Use two stacks or TreeMap for better popMax performance.
 *
 * <p><b>Time Complexity:</b> O(1) for push/top/peekMax, O(N) for popMax
 * <br><b>Space Complexity:</b> O(N) for stack
 */
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
