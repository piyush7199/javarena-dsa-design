package org.example.coding.datastructures.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordLadder {
    static class Pair {
        String word;
        int len;

        public Pair(String word, int len) {
            this.word = word;
            this.len = len;
        }
    }

    /**
     * Solves the Word Ladder problem using BFS.
     * <p>
     * Intuition:
     * - Treat each word as a node in a graph.
     * - Add edges between words that differ by 1 letter.
     * - Use BFS to find the shortest path from beginWord to endWord.
     * <p>
     * Time Complexity: O(N * L * 26), where N is wordList size, L is word length
     * Space Complexity: O(N)
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

            int n = word.length();
            char[] wordChar = word.toCharArray();
            for (int i = 0; i < n; i++) {
                char re = wordChar[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    wordChar[i] = ch;
                    String w = new String(wordChar);
                    if (set.contains(w)) {
                        q.offer(new Pair(w, len + 1));
                        set.remove(w);
                    }
                }
                wordChar[i] = re;
            }

        }

        return 0;
    }
}
