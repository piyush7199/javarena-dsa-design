package com.javarena.dsa.algorithms.greedy;

import java.util.*;

/**
 * Rearranging Fruits (Minimum Cost Swaps)
 *
 * <p><b>Problem Statement:</b><br>
 * Given two baskets with fruits, find minimum cost to make both baskets identical.
 * Can swap fruits between baskets with cost = min(fruitA, fruitB).
 * Return -1 if impossible.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Greedy balancing with swap optimization:
 * - Count net difference of each fruit between baskets
 * - If any fruit has odd difference: impossible (can't balance)
 * - Identify excess fruits in each basket (need swapping out)
 * - Greedy pairing: pair smallest excess from basket1 with largest from basket2
 * 
 * Cost optimization:
 * - Direct swap: cost = min(fruit1, fruit2)
 * - Indirect swap via cheapest fruit: cost = 2 × minElement
 * - Choose minimum of both options
 * 
 * Why greedy works: Pairing extremes minimizes total cost.
 *
 * <p><b>Time Complexity:</b> O(N log N) - Sorting excess lists
 * <br><b>Space Complexity:</b> O(N) - Maps and excess lists
 */
public class RearrangingFruits {
    
    /**
     * Calculates minimum cost to balance baskets using greedy swaps.
     */
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int n = basket1.length;
        int minElem = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            countMap.put(basket1[i], countMap.getOrDefault(basket1[i], 0) + 1);
            countMap.put(basket2[i], countMap.getOrDefault(basket2[i], 0) - 1);
            minElem = Math.min(minElem, Math.min(basket1[i], basket2[i]));
        }

        List<Integer> b1Extra = new ArrayList<>();
        List<Integer> b2Extra = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int fruit = entry.getKey();
            int diff = entry.getValue();
            
            if (diff % 2 != 0) return -1;
            
            int halfDiff = Math.abs(diff) / 2;
            if (diff > 0) {
                for (int i = 0; i < halfDiff; i++) {
                    b1Extra.add(fruit);
                }
            } else if (diff < 0) {
                for (int i = 0; i < halfDiff; i++) {
                    b2Extra.add(fruit);
                }
            }
        }

        Collections.sort(b1Extra);
        Collections.sort(b2Extra, Collections.reverseOrder());

        long cost = 0;
        for (int i = 0; i < b1Extra.size(); i++) {
            cost += Math.min(Math.min(b1Extra.get(i), b2Extra.get(i)), 2L * minElem);
        }

        return cost;
    }
}
