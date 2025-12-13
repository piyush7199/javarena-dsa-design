package com.javarena.dsa.datastructures.hashMapAndSet;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Design A Leaderboard
 *
 * <p><b>Problem Statement:</b><br>
 * Design leaderboard supporting addScore, top K scores sum, and reset operations.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Combine HashMap and TreeMap:
 * - HashMap: playerId → cumulative score (O(1) lookup)
 * - TreeMap: score → frequency (sorted, O(log N) operations)
 * 
 * Operations:
 * - addScore: Update player score in HashMap, adjust TreeMap frequencies
 * - top(K): Traverse TreeMap from highest scores, sum top K
 * - reset: Remove player from both structures
 * 
 * TreeMap enables efficient top K retrieval in sorted order.
 *
 * <p><b>Time Complexity:</b> O(log N) for add/reset, O(K) for top
 * <br><b>Space Complexity:</b> O(N) - Two maps
 */
public class DesignALeaderboard {
    private final Map<Integer, Integer> playerScores;      // playerId -> score
    private final TreeMap<Integer, Integer> scoreCounts;   // score -> frequency

    public DesignALeaderboard() {
        playerScores = new HashMap<>();
        scoreCounts = new TreeMap<>();
    }

    /**
     * Adds score to player's total.
     */
    public void addScore(int playerId, int score) {
        int oldScore = playerScores.getOrDefault(playerId, 0);
        int newScore = oldScore + score;

        // Update player's score
        playerScores.put(playerId, newScore);

        // Remove old score from TreeMap
        if (oldScore > 0) {
            int count = scoreCounts.get(oldScore);
            if (count == 1) scoreCounts.remove(oldScore);
            else scoreCounts.put(oldScore, count - 1);
        }

        // Add new score to TreeMap
        scoreCounts.put(newScore, scoreCounts.getOrDefault(newScore, 0) + 1);
    }

    /**
     * Returns sum of top K scores.
     */
    public int top(int K) {
        int sum = 0;
        int count = 0;

        for (Map.Entry<Integer, Integer> entry : scoreCounts.descendingMap().entrySet()) {
            int score = entry.getKey();
            int freq = entry.getValue();

            for (int i = 0; i < freq && count < K; i++) {
                sum += score;
                count++;
            }
            if (count == K) break;
        }
        return sum;
    }

    /**
     * Resets player's score to 0.
     */
    public void reset(int playerId) {
        int oldScore = playerScores.get(playerId);

        // Remove from TreeMap
        int count = scoreCounts.get(oldScore);
        if (count == 1) scoreCounts.remove(oldScore);
        else scoreCounts.put(oldScore, count - 1);

        // Remove from HashMap
        playerScores.remove(playerId);
    }
}
