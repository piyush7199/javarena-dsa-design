package org.example.coding.datastructures.stackAndQueue;

import java.util.Stack;

public class StockSpanner {
    static class Pair {
        int ele;
        int counter;

        public Pair(int ele, int counter) {
            this.ele = ele;
            this.counter = counter;
        }
    }

    Stack<Pair> stack;

    public StockSpanner() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int cnt = 1;
        while (!stack.isEmpty() && stack.peek().ele <= price) {
            cnt += stack.peek().counter;
            stack.pop();
        }
        stack.push(new Pair(price, cnt));
        return cnt;
    }
}
