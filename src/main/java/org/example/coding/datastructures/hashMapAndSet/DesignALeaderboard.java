package org.example.coding.datastructures.hashMapAndSet;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DesignALeaderboard {
    private final Map<Integer, Integer> playerScores;      // playerId -> score
    private final TreeMap<Integer, Integer> scoreCounts;   // score -> frequency of players

    public DesignALeaderboard() {
        playerScores = new HashMap<>();
        scoreCounts = new TreeMap<>();
    }

    /**
     * Add a player's score or update existing player's score.
     * Time: O(log N)
     */
    public void addScore(int playerId, int score) {
        int oldScore = playerScores.getOrDefault(playerId, 0);
        int newScore = oldScore + score;

        // Update player's score
        playerScores.put(playerId, newScore);

        // Remove old score from TreeMap (if existed)
        if (oldScore > 0) {
            int count = scoreCounts.get(oldScore);
            if (count == 1) scoreCounts.remove(oldScore);
            else scoreCounts.put(oldScore, count - 1);
        }

        // Add new score to TreeMap
        scoreCounts.put(newScore, scoreCounts.getOrDefault(newScore, 0) + 1);
    }

    /**
     * Return the sum of the top K players' scores.
     * Time: O(K + log N)
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
     * Reset the player's score to 0 (remove from leaderboard).
     * Time: O(log N)
     */
    public void reset(int playerId) {
        int oldScore = playerScores.get(playerId);

        // Remove player's score from TreeMap
        int count = scoreCounts.get(oldScore);
        if (count == 1) scoreCounts.remove(oldScore);
        else scoreCounts.put(oldScore, count - 1);

        // Remove from HashMap
        playerScores.remove(playerId);
    }
}
