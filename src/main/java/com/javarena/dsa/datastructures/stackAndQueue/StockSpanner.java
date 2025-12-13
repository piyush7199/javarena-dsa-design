package com.javarena.dsa.datastructures.stackAndQueue;

import java.util.Stack;

/**
 * Online Stock Span
 *
 * <p><b>Problem Statement:</b><br>
 * Design algorithm to calculate stock span: number of consecutive days (including today)
 * where stock price was less than or equal to today's price.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Monotonic decreasing stack with span tracking:
 * - Each stack entry stores (price, span)
 * - On new price: pop all entries with price ≤ current
 * - Sum their spans (consecutive days with lower prices)
 * - Push current (price, total_span) to stack
 * - Stack maintains decreasing prices
 * 
 * Similar to "previous greater element" problem.
 * Efficiently handles streaming data.
 *
 * <p><b>Time Complexity:</b> O(1) amortized per next() call
 * <br><b>Space Complexity:</b> O(N) - Stack grows with unique prices
 */
public class StockSpanner {
    /**
     * Pair stores price and its span.
     */
    static class Pair {
        int ele;
        int counter;

        public Pair(int ele, int counter) {
            this.ele = ele;
            this.counter = counter;
        }
    }

    Stack<Pair> stack;

    /**
     * Initializes stock spanner.
     */
    public StockSpanner() {
        stack = new Stack<>();
    }

    /**
     * Calculates span for today's price.
     */
    public int next(int price) {
        int cnt = 1;
        
        // Pop prices less than or equal to current
        while (!stack.isEmpty() && stack.peek().ele <= price) {
            cnt += stack.peek().counter;
            stack.pop();
        }
        
        stack.push(new Pair(price, cnt));
        return cnt;
    }
}
