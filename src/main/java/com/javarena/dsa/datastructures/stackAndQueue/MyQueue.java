package com.javarena.dsa.datastructures.stackAndQueue;

import java.util.Stack;

/**
 * Implement Queue using Stacks
 *
 * <p><b>Problem Statement:</b><br>
 * Implement FIFO queue using only two stacks. Support push, pop, peek, empty operations.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use single stack with element reversal on each push:
 * - On push: Transfer all elements to helper stack, push new element, transfer back
 * - This ensures oldest element stays on top (FIFO order)
 * - Pop/peek operate directly on stack top
 * 
 * Alternative: Use two stacks (input/output) for amortized O(1) operations.
 *
 * <p><b>Time Complexity:</b> O(N) per push, O(1) for pop/peek/empty
 * <br><b>Space Complexity:</b> O(N) for stack
 */
public class MyQueue {
    Stack<Integer> stack;

    /**
     * Initializes queue.
     */
    public MyQueue() {
        stack = new Stack<>();
    }

    /**
     * Pushes element to back of queue.
     */
    public void push(int x) {
        Stack<Integer> helperStack = new Stack<>();
        // Move all to helper
        while (!stack.isEmpty()) {
            helperStack.push(stack.pop());
        }
        // Push new element
        stack.push(x);
        // Move all back
        while (!helperStack.isEmpty()) {
            stack.push(helperStack.pop());
        }
    }

    /**
     * Removes element from front of queue.
     */
    public int pop() {
        return stack.pop();
    }

    /**
     * Gets front element.
     */
    public int peek() {
        return stack.peek();
    }

    /**
     * Checks if queue is empty.
     */
    public boolean empty() {
        return stack.isEmpty();
    }
}
