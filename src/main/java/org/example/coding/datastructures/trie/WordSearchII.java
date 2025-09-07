package org.example.coding.datastructures.trie;


import java.util.ArrayList;
import java.util.List;

public class WordSearchII {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        TrieNode trieNode = buildTrie(words);

        int n = board.length, m = board[0].length;

        boolean[][] vis = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

            }
        }

        return ans;
    }

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
            node.word = word;
        }
        return root;
    }

    private void backTrack(char[][] board, int i, int j, TrieNode node, boolean[][] vis, List<String> res) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || vis[i][j]) return;
        char ch = board[i][j];
        int idx = ch - 'a';

        if (node.children[idx] == null) return;

        node = node.children[idx];

        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        vis[i][j] = true;

        backTrack(board, i - 1, j, node, vis, res);
        backTrack(board, i + 1, j, node, vis, res);
        backTrack(board, i, j - 1, node, vis, res);
        backTrack(board, i, j + 1, node, vis, res);

        vis[i][j] = false;
    }

    public List<String> findWordsBrute(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (isValidBrute(board, word)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean isValidBrute(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] vis = new boolean[n][m];
                    if (helperBrute(board, word, 0, i, j, vis)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean helperBrute(char[][] grid, String word, int ind, int i, int j, boolean[][] vis) {
        // Out of bounds or mismatch
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || vis[i][j] || grid[i][j] != word.charAt(ind)) {
            return false;
        }

        // If we matched the last character
        if (ind == word.length() - 1) {
            return true;
        }

        // Mark as visited
        vis[i][j] = true;

        int[] rows = {-1, 0, 1, 0};
        int[] cols = {0, 1, 0, -1};

        for (int k = 0; k < 4; k++) {
            if (helperBrute(grid, word, ind + 1, i + rows[k], j + cols[k], vis)) {
                return true;
            }
        }

        // Backtrack
        vis[i][j] = false;

        return false;
    }
}
