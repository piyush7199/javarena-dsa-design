package com.javarena.dsa.datastructures.stackAndQueue;

import java.util.Stack;

/**
 * Min Stack
 *
 * <p><b>Problem Statement:</b><br>
 * Design stack that supports push, pop, top, and retrieving minimum element in constant time.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Track minimum alongside stack operations:
 * - Maintain regular stack for all values
 * - Track current minimum value
 * - On push: Update min if new value smaller
 * - On pop: If popped value was min, recalculate min from remaining elements
 * 
 * Optimization: Use two stacks (main + min stack) for O(1) getMin without recalculation.
 *
 * <p><b>Time Complexity:</b> O(1) for push/pop/top, O(N) for getMin after pop
 * <br><b>Space Complexity:</b> O(N) for stack
 */
public class MinStack {
    private int min;
    private final Stack<Integer> minStack;

    /**
     * Initializes min stack.
     */
    public MinStack() {
        this.min = Integer.MAX_VALUE;
        this.minStack = new Stack<>();
    }

    /**
     * Pushes value onto stack.
     */
    public void push(int val) {
        if (val <= min) {
            this.min = val;
        }
        minStack.push(val);
    }

    /**
     * Removes top element.
     */
    public void pop() {
        if (minStack.isEmpty()) return;
        int ele = minStack.pop();
        if (ele <= min) {
            this.min = Integer.MAX_VALUE;
            this.updateMin();
        }
    }

    /**
     * Updates minimum after popping min element.
     */
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

    /**
     * Gets top element without removing.
     */
    public int top() {
        if (minStack.isEmpty()) return -1;
        return minStack.peek();
    }

    /**
     * Retrieves minimum element.
     */
    public int getMin() {
        return min;
    }
}
