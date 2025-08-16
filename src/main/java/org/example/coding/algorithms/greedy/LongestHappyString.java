package org.example.coding.algorithms.greedy;

import java.util.PriorityQueue;

public class LongestHappyString {
    /**
     * Constructs the longest possible "happy" string using given counts of 'a', 'b', and 'c'.
     *
     * <p>
     * A "happy string" is defined as a string where no three consecutive characters are the same.
     * Given counts of 'a', 'b', and 'c', we want to form the longest valid string
     * under this constraint.
     * </p>
     *
     * <p><b>Intuition:</b><br>
     * Always try to place the character with the highest remaining frequency
     * to maximize the length of the string. However, if placing that character
     * would result in three consecutive identical characters, we must instead
     * choose the next most frequent character. This greedy strategy ensures
     * the string is as long as possible while respecting the "no three in a row" rule.
     * </p>
     *
     * <p><b>Approach:</b><br>
     * 1. Use a max-heap (PriorityQueue) to always pick the character with the highest remaining frequency.<br>
     * 2. Poll the most frequent character:<br>
     * - If appending it would cause three consecutive same characters,
     * pick the second-most frequent character instead.<br>
     * - Append the chosen character to the result and decrement its frequency.<br>
     * 3. Reinsert characters back into the heap if they still have remaining frequency.<br>
     * 4. Repeat until the heap is empty or no valid move is possible.<br>
     * </p>
     *
     * <p><b>Time Complexity:</b> O((a + b + c) log 3) ≈ O(a + b + c)<br>
     * - Each character is pushed/popped from the heap at most once per occurrence.<br>
     * - Heap size is at most 3 (since we only have 'a', 'b', 'c').<br>
     * </p>
     *
     * <p><b>Space Complexity:</b> O(1)<br>
     * - Aside from the output StringBuilder, we only maintain a priority queue
     * of size at most 3 and a few helper variables.<br>
     * </p>
     */
    static class Pair {
        char ch;
        int freq;

        Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p2.freq - p1.freq);

        if (a > 0) pq.offer(new Pair('a', a));
        if (b > 0) pq.offer(new Pair('b', b));
        if (c > 0) pq.offer(new Pair('c', c));

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            Pair first = pq.poll();

            // Check if last two characters are same as first.ch
            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 1) == first.ch && sb.charAt(len - 2) == first.ch) {
                if (pq.isEmpty()) break; // No alternate option

                Pair second = pq.poll();
                sb.append(second.ch);
                second.freq--;

                if (second.freq > 0) pq.offer(second);
                pq.offer(first); // Retry first next time
            } else {
                sb.append(first.ch);
                first.freq--;

                if (first.freq > 0) pq.offer(first);
            }
        }

        return sb.toString();
    }

}
