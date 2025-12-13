package com.javarena.dsa.algorithms.greedy;

import java.util.PriorityQueue;

/**
 * Longest Happy String
 *
 * <p><b>Problem Statement:</b><br>
 * Given counts of 'a', 'b', 'c', construct longest happy string where no three
 * consecutive characters are the same. Return any valid longest happy string.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Greedy with max-heap priority queue:
 * - Always try to use character with highest remaining count
 * - If last 2 characters same as max: use second highest instead
 * - Add 1 or 2 characters based on count and avoid 3 consecutive
 * 
 * Strategy:
 * 1. Maintain max-heap of (count, char) pairs
 * 2. Pop max count character
 * 3. If would create 3 consecutive: pop second max instead
 * 4. Add 1-2 characters, push back to heap if count remains
 * 5. Repeat until heap empty or impossible to continue
 * 
 * Greedy works: Always using max available prevents getting stuck.
 *
 * <p><b>Time Complexity:</b> O((a+b+c) log 3) = O(N) - N total chars, heap of size 3
 * <br><b>Space Complexity:</b> O(1) - Heap of constant size 3, result string O(N)
 */
public class LongestHappyString {
    
    /**
     * Constructs longest happy string using greedy max-heap approach.
     */
    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[0] - x[0]);
        
        if (a > 0) pq.offer(new int[]{a, 'a'});
        if (b > 0) pq.offer(new int[]{b, 'b'});
        if (c > 0) pq.offer(new int[]{c, 'c'});
        
        StringBuilder result = new StringBuilder();
        
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            int len = result.length();
            
            // Check if adding this char would create 3 consecutive
            if (len >= 2 && result.charAt(len - 1) == first[1] && 
                result.charAt(len - 2) == first[1]) {
                
                if (pq.isEmpty()) break;
                
                // Use second highest instead
                int[] second = pq.poll();
                result.append((char) second[1]);
                second[0]--;
                
                if (second[0] > 0) pq.offer(second);
                pq.offer(first);
                
            } else {
                // Use highest count character
                int toAdd = Math.min(2, first[0]);
                for (int i = 0; i < toAdd; i++) {
                    result.append((char) first[1]);
                }
                first[0] -= toAdd;
                
                if (first[0] > 0) pq.offer(first);
            }
        }
        
        return result.toString();
    }
}
