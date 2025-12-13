package com.javarena.dsa.datastructures.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Word Ladder
 *
 * <p><b>Problem Statement:</b><br>
 * Find shortest transformation sequence from beginWord to endWord, where each step changes one letter
 * and intermediate words must be in wordList. Return sequence length or 0 if impossible.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Treat as shortest path in unweighted graph:
 * - Each word is a node
 * - Edge exists between words differing by 1 letter
 * - Use BFS to find shortest path (level-order guarantees shortest)
 * 
 * Algorithm:
 * - Start BFS from beginWord with length 1
 * - For each word, try changing each character to 'a'-'z'
 * - If transformed word in wordList, add to queue
 * - Remove from wordList to avoid revisiting (acts as visited)
 * - If reach endWord, return length
 * - If queue empty, no path exists
 *
 * <p><b>Time Complexity:</b> O(N × L × 26) - N words, L word length, 26 letters
 * <br><b>Space Complexity:</b> O(N) - Queue + HashSet
 */
public class WordLadder {
    /**
     * Pair class to store word and transformation length.
     */
    static class Pair {
        String word;
        int len;

        public Pair(String word, int len) {
            this.word = word;
            this.len = len;
        }
    }

    /**
     * Finds shortest transformation sequence length.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(beginWord, 1));

        while (!q.isEmpty()) {
            String word = q.peek().word;
            int len = q.peek().len;
            q.poll();
            
            if (word.equals(endWord)) {
                return len;
            }

            // Try changing each character
            int n = word.length();
            char[] wordChar = word.toCharArray();
            for (int i = 0; i < n; i++) {
                char re = wordChar[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    wordChar[i] = ch;
                    String w = new String(wordChar);
                    if (set.contains(w)) {
                        q.offer(new Pair(w, len + 1));
                        set.remove(w); // Mark as visited
                    }
                }
                wordChar[i] = re; // Restore original character
            }
        }

        return 0; // No transformation possible
    }
}
