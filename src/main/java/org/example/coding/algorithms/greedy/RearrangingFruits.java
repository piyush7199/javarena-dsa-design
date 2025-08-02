package org.example.coding.algorithms.greedy;

import java.util.*;

public class RearrangingFruits {

    /**
     * Calculates the minimum cost to make both baskets have the same fruit composition
     * by swapping elements between them.
     *
     * <p><b>Intuition:</b></p>
     * If both baskets can be made identical by swapping fruits, we must balance the number
     * of each fruit type. For each fruit, the difference in counts between the two baskets
     * must be even (as each swap fixes two occurrences). If this is not the case, return -1.
     *
     * <p><b>Approach:</b></p>
     * 1. Count the net difference of each fruit type between basket1 and basket2.
     * 2. If a fruit has an odd difference, it's impossible to balance → return -1.
     * 3. Divide excess fruits into two lists:
     * - `b1Extra`: Fruits that need to be swapped *out* from basket1.
     * - `b2Extra`: Fruits that need to be swapped *out* from basket2.
     * 4. Sort `b1Extra` in ascending and `b2Extra` in descending order.
     * 5. Pair them greedily, and for each pair, the cost is:
     * - `min(fruitA, fruitB)` — swap directly,
     * - or `2 * minElem` — replace both with the cheapest fruit type (if cheaper).
     *
     * <p><b>Time Complexity:</b> O(n log n), where n is the number of fruits.
     * - O(n) for count diff and extra list creation.
     * - O(n log n) for sorting the extras.
     *
     * <p><b>Space Complexity:</b> O(n) for maps and extra lists.
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

            int half = Math.abs(diff) / 2;

            if (diff > 0) {
                for (int i = 0; i < half; i++) b1Extra.add(fruit);
            } else if (diff < 0) {
                for (int i = 0; i < half; i++) b2Extra.add(fruit);
            }
        }

        if (b1Extra.size() != b2Extra.size()) return -1;

        Collections.sort(b1Extra);
        Collections.sort(b2Extra, Collections.reverseOrder());

        long cost = 0;
        for (int i = 0; i < b1Extra.size(); i++) {
            int a = b1Extra.get(i);
            int b = b2Extra.get(i);
            cost += Math.min(Math.min(a, b), 2 * minElem);
        }

        return cost;

    }
}
