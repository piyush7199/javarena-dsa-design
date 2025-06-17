package org.example.coding.datastructures.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    Queue<Integer> mainQueue;

    public MyStack() {
        this.mainQueue = new LinkedList<>();
    }

    public void push(int x) {
        Queue<Integer> helper = new LinkedList<>();
        while (!mainQueue.isEmpty()) {
            helper.offer(mainQueue.poll());
        }
        mainQueue.add(x);
        while (!helper.isEmpty()) {
            mainQueue.offer(helper.poll());
        }
    }

    public int pop() {
        return  mainQueue.poll();
    }

    public int top() {
        return  mainQueue.peek();
    }

    public boolean empty() {
        return mainQueue.isEmpty();
    }
}
