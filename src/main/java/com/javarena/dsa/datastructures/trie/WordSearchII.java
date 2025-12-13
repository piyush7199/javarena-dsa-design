package com.javarena.dsa.datastructures.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Word Search II
 *
 * <p><b>Problem Statement:</b><br>
 * Given M×N board and list of words, find all words that exist in board.
 * Words formed by sequentially adjacent cells (no reusing same cell).
 *
 * <p><b>Intuition & Approach:</b><br>
 * Optimize multi-word search using Trie:
 * - Build Trie from all words (share common prefixes)
 * - For each cell, start DFS with Trie traversal
 * - Prune search early if prefix not in Trie
 * - Mark found words to avoid duplicates
 * 
 * Algorithm:
 * 1. Build Trie from words array
 * 2. For each board cell:
 *    - DFS with backtracking
 *    - Follow Trie path matching board characters
 *    - If reach word end, add to result
 *    - Mark cell visited during DFS, unmark after
 * 
 * Much faster than searching each word separately.
 *
 * <p><b>Time Complexity:</b> O(M×N×4^L) - M×N cells, 4 directions, L max word length
 * <br><b>Space Complexity:</b> O(W×L) - Trie for W words of length L
 */
public class WordSearchII {

    /**
     * Trie node storing word at end.
     */
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // Store complete word at end
    }

    /**
     * Finds all words in board using Trie + DFS.
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        TrieNode trieNode = buildTrie(words);

        int n = board.length, m = board[0].length;
        boolean[][] vis = new boolean[n][m];

        // Try starting from each cell
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                backTrack(board, i, j, trieNode, vis, ans);
            }
        }

        return ans;
    }

    /**
     * Builds Trie from words array.
     */
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int ind = c - 'a';
                if (node.children[ind] == null) {
                    node.children[ind] = new TrieNode();
                }
                node = node.children[ind];
            }
            node.word = word; // Store word at end
        }
        return root;
    }

    /**
     * DFS with backtracking to find words.
     */
    private void backTrack(char[][] board, int i, int j, TrieNode node, boolean[][] vis, List<String> res) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || vis[i][j]) return;
        
        char ch = board[i][j];
        int idx = ch - 'a';

        if (node.children[idx] == null) return; // No words with this prefix

        node = node.children[idx];

        // Found complete word
        if (node.word != null) {
            res.add(node.word);
            node.word = null; // Avoid duplicates
        }
        
        vis[i][j] = true;

        // Explore 4 directions
        backTrack(board, i - 1, j, node, vis, res);
        backTrack(board, i + 1, j, node, vis, res);
        backTrack(board, i, j - 1, node, vis, res);
        backTrack(board, i, j + 1, node, vis, res);

        vis[i][j] = false; // Backtrack
    }

    /**
     * Brute force approach - search each word separately.
     */
    public List<String> findWordsBrute(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (isValidBrute(board, word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    /**
     * Checks if single word exists in board.
     */
    private boolean isValidBrute(char[][] board, String word) {
        int n = board.length, m = board[0].length;
        boolean[][] vis = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, i, j, word, 0, vis)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * DFS for single word search.
     */
    private boolean dfs(char[][] board, int i, int j, String word, int idx, boolean[][] vis) {
        if (idx == word.length()) return true;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || vis[i][j]) return false;
        if (board[i][j] != word.charAt(idx)) return false;

        vis[i][j] = true;
        
        boolean found = dfs(board, i - 1, j, word, idx + 1, vis) ||
                       dfs(board, i + 1, j, word, idx + 1, vis) ||
                       dfs(board, i, j - 1, word, idx + 1, vis) ||
                       dfs(board, i, j + 1, word, idx + 1, vis);
        
        vis[i][j] = false;
        return found;
    }
}
