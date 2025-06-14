package org.example.coding.algorithms;

import java.util.*;

class Item {
    int value, weight;

    Item(int val, int wt) {
        this.value = val;
        this.weight = wt;
    }
}

class Pair {
    int first, second;

    public Pair(int start, int finish) {
        this.first = start;
        this.second = finish;
    }
}

public class GreedyAlgorithms {

    /**
     * 1. Assign Cookies Problem
     *
     * <p>You are a caring parent who wants to maximize the number of content children by
     * distributing cookies in a fair way. Each child has a greed factor indicating the
     * minimum cookie size they are content with, and each cookie has a size.
     *
     * <p>Each child can receive at most one cookie, and each cookie can be given to only
     * one child. Your goal is to assign cookies such that the number of content children
     * is maximized.
     *
     * <h3>Problem Statement:</h3>
     * <pre>
     * Given:
     * - An integer array {@code g[]} where g[i] is the greed factor of the i-th child.
     * - An integer array {@code s[]} where s[j] is the size of the j-th cookie.
     *
     * Task:
     * Assign cookies to children in a way that maximizes the number of content children.
     * A child i is content if there exists a cookie j such that s[j] >= g[i].
     * Each cookie can be assigned to at most one child.
     *
     * Return the maximum number of content children.
     * </pre>
     *
     * <h3>Approach (Greedy):</h3>
     * <ul>
     *   <li>Sort the {@code g[]} (greed factors) and {@code s[]} (cookie sizes) arrays in ascending order.</li>
     *   <li>Use two pointers: one for the children (i) and one for the cookies (j).</li>
     *   <li>Iterate through both arrays:
     *     <ul>
     *       <li>If s[j] >= g[i], assign the cookie to the child and increment both pointers.</li>
     *       <li>Otherwise, move to the next larger cookie (increment j).</li>
     *     </ul>
     *   </li>
     *   <li>Continue this until we run out of children or cookies.</li>
     * </ul>
     *
     * <h3>Time and Space Complexity:</h3>
     * <ul>
     *   <li>Time Complexity: O(n log n + m log m), due to sorting the two input arrays.</li>
     *   <li>Space Complexity: O(1), as sorting is done in-place (if using in-place sort).</li>
     * </ul>
     *
     * <h3>Note:</h3>
     * This is a perfect use-case for greedy since giving the smallest sufficient cookie
     * to the least greedy child preserves larger cookies for greedier children.
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int n = g.length;
        int m = s.length;
        int i = 0;
        int j = 0;
        int ans = 0;
        while (i < n && j < m) {
            if (g[i] <= s[j]) {
                i++;
                ans++;
            }
            j++;
        }
        return ans;
    }

    /**
     * 2. Lemonade Change
     *
     * <p>At a lemonade stand, each lemonade costs $5. Customers line up to buy one lemonade each
     * and pay using either a $5, $10, or $20 bill. The task is to determine whether you can provide
     * the correct change to each customer in the given order, starting with no change on hand.
     *
     * <h3>Problem Statement:</h3>
     * <pre>
     * Given:
     * - An integer array {@code bills[]} where bills[i] represents the bill the i-th customer pays.
     *
     * Return:
     * - {@code true} if you can provide correct change to every customer.
     * - {@code false} otherwise.
     * </pre>
     *
     * <h3>Intuition:</h3>
     * <ul>
     *   <li>Maintain counters for available $5 and $10 bills.</li>
     *   <li>If the customer pays with a $5 bill, increase the $5 counter (no change needed).</li>
     *   <li>If the customer pays with a $10 bill, check if a $5 bill is available to give as change.</li>
     *   <li>If the customer pays with a $20 bill:
     *     <ul>
     *       <li>First try to give change using one $10 and one $5 bill.</li>
     *       <li>If not possible, try giving change using three $5 bills.</li>
     *       <li>If neither option works, return {@code false} (cannot provide change).</li>
     *     </ul>
     *   </li>
     *   <li>If all transactions are processed without running out of change, return {@code true}.</li>
     * </ul>
     *
     * <h3>Greedy Strategy:</h3>
     * Always prefer giving a $10 + $5 combination as change for $20 bills if possible,
     * since $5 bills are more versatile and needed more often.
     *
     * <h3>Time and Space Complexity:</h3>
     * <ul>
     *   <li>Time Complexity: O(n), where {@code n} is the number of customers (length of {@code bills}).</li>
     *   <li>Space Complexity: O(1), constant space used for counters.</li>
     * </ul>
     */
    public static boolean lemonadeChange(int[] bills) {
        int five_dollars = 0, ten_dollars = 0;

        for (int bill : bills) {
            if (bill == 5) {
                five_dollars++;
            } else if (bill == 10) {
                if (five_dollars > 0) {
                    five_dollars--;
                    ten_dollars++;
                } else {
                    return false;
                }
            } else {
                if (five_dollars > 0 && ten_dollars > 0) {
                    five_dollars--;
                    ten_dollars--;
                } else if (five_dollars > 2) {
                    five_dollars -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 3. Find Minimum Number of Coins
     *
     * <p>Given an infinite supply of each denomination of Indian currency:
     * { 1, 2, 5, 10, 20, 50, 100, 200, 500, 2000 } and a target value N,
     * find the minimum number of coins/notes required to make the exact change for N.
     *
     * <h3>Problem Statement:</h3>
     * <pre>
     * Input:
     * - An integer N, representing the total amount of money.
     * Output:
     * - A list of coins/notes (from the given denominations) whose sum is exactly N.
     * - The number of elements in this list is the minimum number of coins/notes needed.
     * - If no combination can form N, return an empty list (though with given denominations and infinite supply, N is always formable).
     * </pre>
     *
     * <h3>Greedy Strategy:</h3>
     * <ul>
     *   <li>Start from the largest denomination and try to use it as many times as possible while it does not exceed N.</li>
     *   <li>Keep reducing the value of N by the denomination value and add the coin to the result.</li>
     *   <li>Continue to smaller denominations until N becomes 0.</li>
     * </ul>
     *
     * <h3>Algorithm:</h3>
     * <pre>
     * 1. Create an array of denominations in descending order.
     * 2. Initialize an empty result list.
     * 3. For each denomination:
     *    - While the denomination is less than or equal to the remaining N:
     *      - Subtract it from N.
     *      - Add it to the result list.
     * 4. Return the result list.
     * </pre>
     *
     * <h3>Time and Space Complexity:</h3>
     * <ul>
     *   <li>Time Complexity: O(N), in the worst case (like for N=3, use 1 rupee three times), though in practice much faster due to large denominations.</li>
     *   <li>Space Complexity: O(1) for variables; O(K) for result list, where K is number of coins/notes used.</li>
     * </ul>
     */
    public static List<Integer> minPartition(int amount) {
        int[] coins = {2000, 500, 200, 100, 50, 20, 10, 5, 2, 1};
        List<Integer> result = new ArrayList<>();

        for (int coin : coins) {
            while (amount >= coin) {
                amount -= coin;
                result.add(coin);
            }
        }

        return result;
    }

    /**
     * 4. Fractional Knapsack Problem
     *
     * <p>This problem involves selecting items to maximize total value in a knapsack that has a limited weight capacity.
     * Unlike the 0/1 Knapsack problem, in the fractional version, you are allowed to break items into fractions if needed.
     *
     * <h3>Problem Statement:</h3>
     * <pre>
     * You are given:
     * - An array 'val[]' where val[i] is the value of the i-th item.
     * - An array 'wt[]' where wt[i] is the weight of the i-th item.
     * - An integer 'capacity' representing the maximum weight the knapsack can carry.
     *
     * Goal:
     * Return the maximum total value you can put in the knapsack, allowing for fractional items.
     * Round the result to 6 decimal places.
     * </pre>
     *
     * <h3>Greedy Strategy:</h3>
     * <ul>
     *   <li>Calculate the value-to-weight ratio (val[i] / wt[i]) for each item.</li>
     *   <li>Sort items in descending order based on this ratio.</li>
     *   <li>Iterate over the sorted items:
     *     <ul>
     *       <li>If the full item fits, take it entirely.</li>
     *       <li>If it doesn't fit, take the possible fraction and stop.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * <h3>Algorithm:</h3>
     * <pre>
     * 1. Create a list of item objects with value, weight, and value/weight ratio.
     * 2. Sort the list in descending order based on the ratio.
     * 3. Initialize totalValue = 0.
     * 4. For each item:
     *    - If capacity >= item.weight:
     *        totalValue += item.value
     *        capacity -= item.weight
     *    - Else:
     *        totalValue += item.value * (capacity / item.weight)
     *        break;
     * 5. Return totalValue rounded to 6 decimal places.
     * </pre>
     *
     * <h3>Time and Space Complexity:</h3>
     * <ul>
     *   <li>Time Complexity: O(n log n) – due to sorting items based on value-to-weight ratio.</li>
     *   <li>Space Complexity: O(n) – additional space for storing item objects.</li>
     * </ul>
     */
    public static double fractionalKnapsack(int[] values, int[] weights, int W) {
        int n = values.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(values[i], weights[i]);
        }

        Arrays.sort(items, (a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        int currentWt = 0;
        double finalValue = 0.0;
        for (int i = 0; i < n; i++) {
            if (currentWt + items[i].weight <= W) {
                currentWt += items[i].weight;
                finalValue += items[i].value;
            } else {
                int remWt = W - currentWt;
                finalValue += ((double) items[i].value / (double) items[i].weight) * (double) remWt;
                break;
            }
        }
        return finalValue;
    }

    /**
     * 5. N Meetings in One Room
     *
     * <p>Problem Statement:
     * Given start and end times of 'n' meetings in two arrays (start[i], end[i]),
     * return the maximum number of meetings that can be accommodated in a single meeting room.
     * Only one meeting can be held in the room at a time.
     *
     * <h3>Constraints:</h3>
     * <ul>
     *   <li>Start time of one chosen meeting cannot be equal to the end time of another chosen meeting.</li>
     *   <li>You must return the maximum number of <b>non-overlapping</b> meetings.</li>
     * </ul>
     *
     * <h3>Approach (Greedy Strategy):</h3>
     * <ul>
     *   <li>Sort all the meetings by their end time in ascending order.</li>
     *   <li>Initialize a variable `endTime` to keep track of when the last selected meeting ends.</li>
     *   <li>Iterate through the sorted meetings:
     *     <ul>
     *       <li>If the current meeting's start time > `endTime`, select it.</li>
     *       <li>Update `endTime` to the current meeting's end time.</li>
     *       <li>Increment a counter for selected meetings.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * <h3>Time and Space Complexity:</h3>
     * <ul>
     *   <li>Time Complexity: O(n log n) – for sorting the meetings by end time.</li>
     *   <li>Space Complexity: O(n) – to store meeting pairs (can be O(1) if done in-place).</li>
     * </ul>
     */
    public static int maxMeetings(int start[], int end[]) {
        int n = start.length;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) pairs[i] = new Pair(start[i], end[i]);
        Arrays.sort(pairs, (a, b) -> a.second - b.second);
        int last = -1;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (pairs[i].first > last) {
                last = pairs[i].second;
                res++;
            }
        }
        return res;
    }

    /**
     * 6. Jump Game
     *
     * <p>Problem Statement:
     * Given an array of non-negative integers `nums`, where each element represents your maximum jump length
     * at that position, return `true` if you can reach the last index, or `false` otherwise.
     *
     * <h3>Approach (Greedy Strategy):</h3>
     * <ul>
     *   <li>Maintain a variable `maxReachable` to track the farthest index that can be reached so far.</li>
     *   <li>Iterate through the array:
     *     <ul>
     *       <li>If the current index is greater than `maxReachable`, return `false` (you’re stuck).</li>
     *       <li>Update `maxReachable = max(maxReachable, i + nums[i])`.</li>
     *     </ul>
     *   </li>
     *   <li>If you finish the loop, it means you can reach the end, so return `true`.</li>
     * </ul>
     *
     * <h3>Time and Space Complexity:</h3>
     * <ul>
     *   <li>Time Complexity: O(n)</li>
     *   <li>Space Complexity: O(1)</li>
     * </ul>
     */
    public boolean canJump(int[] nums) {
        int maxReachable = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReachable) return false;
            maxReachable = Math.max(maxReachable, i + nums[i]);
        }
        return true;
    }

    /**
     * 7. Constructs the lexicographically largest merge of two strings using greedy approach.
     *
     * <p><b>Intuition:</b></p>
     * To construct the largest merge string, at each step we greedily choose the lexicographically
     * larger suffix between word1 and word2.
     * - If the current character in word1 is greater than that in word2, pick from word1.
     * - If they are equal, compare the remaining substrings to decide which path will lead to a bigger result.
     * This ensures the final string is the largest possible at every branching point.
     *
     * <p><b>Time Complexity:</b> O(n * m) in the worst case, due to repeated use of `substring(i).compareTo(...)`.
     * However, with string slicing optimization or character-by-character comparison, it can be reduced to O(n + m).
     *
     * <p><b>Space Complexity:</b> O(n + m)</p>
     * - The space required for the resulting string of length n + m.
     */
    public String largestMerge(String word1, String word2) {
        int i = 0;
        int j = 0;
        int n = word1.length();
        int m = word2.length();
        StringBuilder ans = new StringBuilder();

        while (i < n && j < m) {
            // Compare substrings starting at i and j to decide the optimal pick
            if (word1.substring(i).compareTo(word2.substring(j)) > 0) {
                ans.append(word1.charAt(i++));
            } else {
                ans.append(word2.charAt(j++));
            }
        }

        // Append remaining characters from word1
        while (i < n) {
            ans.append(word1.charAt(i++));
        }

        // Append remaining characters from word2
        while (j < m) {
            ans.append(word2.charAt(j++));
        }

        return ans.toString();
    }

    /**
     * Rearranges the barcodes so that no two adjacent barcodes are the same.
     *
     * <p><b>Intuition:</b></p>
     * - First, count the frequency of each barcode using a HashMap.
     * - Use a max-heap (PriorityQueue with custom comparator) to always pick the barcode with the highest frequency.
     * - Place barcodes at every other index (0, 2, 4, ...) first to maximize spacing and avoid adjacent duplicates.
     * - If you reach the end of the array, start filling the odd indices (1, 3, 5, ...).
     * - Since it's guaranteed a valid rearrangement exists, this greedy approach ensures correctness.
     *
     * <p><b>Time Complexity:</b> O(n log k)</p>
     * - n = length of barcodes
     * - k = number of unique barcodes
     * - Building the heap takes O(k), and each insertion takes O(log k), repeated n times.
     *
     * <p><b>Space Complexity:</b> O(k)</p>
     * - For the HashMap and PriorityQueue storing up to k unique barcodes.
     */
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;

        // Step 1: Count frequency of each barcode
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int code : barcodes) {
            hm.put(code, hm.getOrDefault(code, 0) + 1);
        }

        // Step 2: Create max-heap based on frequency
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> hm.get(b) - hm.get(a));
        pq.addAll(hm.keySet());

        int[] res = new int[n];
        int i = 0;

        // Step 3: Place barcodes by frequency at even indices, then odd if needed
        while (!pq.isEmpty()) {
            int code = pq.poll();
            int freq = hm.get(code);

            for (int j = 0; j < freq; j++) {
                if (i >= n) i = 1; // Switch to odd indices after filling even ones
                res[i] = code;
                i += 2;
            }
        }

        return res;
    }
}
