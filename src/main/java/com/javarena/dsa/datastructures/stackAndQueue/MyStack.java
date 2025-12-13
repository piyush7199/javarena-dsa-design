package com.javarena.dsa.datastructures.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement Stack using Queues
 *
 * <p><b>Problem Statement:</b><br>
 * Implement LIFO stack using only queues. Support push, pop, top, empty operations.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use single queue with element reversal on each push:
 * - On push: Transfer all elements to helper queue, add new element, transfer back
 * - This ensures newest element stays at front (LIFO order)
 * - Pop/top operate directly on queue front
 * 
 * Alternative: Rotate queue after each push (push, then move front to back N-1 times).
 *
 * <p><b>Time Complexity:</b> O(N) per push, O(1) for pop/top/empty
 * <br><b>Space Complexity:</b> O(N) for queue
 */
public class MyStack {
    Queue<Integer> mainQueue;

    /**
     * Initializes stack.
     */
    public MyStack() {
        this.mainQueue = new LinkedList<>();
    }

    /**
     * Pushes element onto stack.
     */
    public void push(int x) {
        Queue<Integer> helper = new LinkedList<>();
        // Move all to helper
        while (!mainQueue.isEmpty()) {
            helper.offer(mainQueue.poll());
        }
        // Add new element
        mainQueue.add(x);
        // Move all back
        while (!helper.isEmpty()) {
            mainQueue.offer(helper.poll());
        }
    }

    /**
     * Removes top element.
     */
    public int pop() {
        return mainQueue.poll();
    }

    /**
     * Gets top element without removing.
     */
    public int top() {
        return mainQueue.peek();
    }

    /**
     * Checks if stack is empty.
     */
    public boolean empty() {
        return mainQueue.isEmpty();
    }
}
