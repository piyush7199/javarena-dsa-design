package org.example.coding.datastructures.stackAndQueue;

import java.util.Stack;

public class MyQueue {
    Stack<Integer> stack;

    public MyQueue() {
        stack = new Stack<>();
    }

    public void push(int x) {
        Stack<Integer> helperStack = new Stack<>();
        while (!stack.isEmpty()) {
            helperStack.push(stack.pop());
        }
        stack.push(x);
        while (!helperStack.isEmpty()) {
            stack.push(helperStack.pop());
        }
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
