package com.javarena.dsa.datastructures.arrays;

/**
 * Best Time to Buy and Sell Stock
 *
 * <p><b>Problem Statement:</b><br>
 * Given an array prices where prices[i] is the stock price on day i, find the maximum profit from buying 
 * once and selling once. Return 0 if no profit possible.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Classic greedy strategy:
 * - Track minimum price seen so far (best day to buy)
 * - For each day, calculate profit if selling today
 * - Update maximum profit if current is better
 * - Single pass, no need to try all combinations
 * - Buy at lowest point before current day, sell at current
 * - Greedy local decisions lead to global optimum
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through prices
 * <br><b>Space Complexity:</b> O(1) - Only two variables
 */
public class StockBuySell {

    /**
     * Finds maximum profit from single buy-sell transaction.
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
