package org.example.coding.arrays;

public class StockBuySell {

    /**
     * Problem
     * <p>
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     * <p>
     * Intuition
     * <p>
     * This problem is a textbook case of greedy strategy.
     * The goal is to buy at the lowest price so far and sell at the highest price after buying, all in one pass.
     * At each day:
     * We keep track of the minimum price seen so far — the best day to buy.
     * We calculate the profit if we sell today, and update our maximum profit if it's higher.
     * This local, greedy decision — to always buy at the lowest and sell today if profitable — guarantees a globally optimal answer.
     * No need to look back or try every combination — just greedily track the best buy and sell as you go.
     * <p>
     * Complexity
     * <p>
     * Time complexity:{@code O(n)}
     * <p></p>
     * Space complexity:{@code O(1)}
     * <p>
     */
    public static int maxProfit(int[] prices) {
        int profit = 0;
        int buyPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            buyPrice = Math.min(buyPrice, prices[i]);
            profit = Math.max(profit, prices[i] - buyPrice);
        }
        return profit;
    }
}
