package com.javarena.dsa.datastructures.trie;

/**
 * Trie II - With Frequency Counting
 *
 * <p><b>Problem Statement:</b><br>
 * Enhanced Trie that tracks word frequencies and prefix counts.
 * Support insert, count exact words, count words with prefix, and erase operations.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Extended Trie with counters at each node:
 * - prefixCount: Number of words passing through this node
 * - wordCount: Number of times word ends at this node
 * 
 * Operations:
 * - Insert: Increment prefixCount along path, wordCount at end
 * - CountWordsEqualTo: Return wordCount at end of word
 * - CountWordsStartingWith: Return prefixCount at end of prefix
 * - Erase: Decrement prefixCount along path, wordCount at end
 * 
 * Useful for autocomplete with frequency, word suggestions.
 *
 * <p><b>Time Complexity:</b> O(M) per operation, M = word/prefix length
 * <br><b>Space Complexity:</b> O(ALPHABET_SIZE × N × M) for N words
 */
public class TrieII {
    /**
     * Trie node with frequency counters.
     */
    static class TrieNode {
        TrieNode[] children;
        int prefixCount;   // Words passing through
        int wordCount;     // Words ending here

        TrieNode() {
            children = new TrieNode[26]; // Lowercase a-z
            prefixCount = 0;
            wordCount = 0;
        }
    }

    private TrieNode root;

    /**
     * Initializes empty Trie.
     */
    public TrieII() {
        root = new TrieNode();
    }

    /**
     * Inserts word, increments counters.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toLowerCase().toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
            node.prefixCount++;
        }
        node.wordCount++;
    }

    /**
     * Counts exact occurrences of word.
     */
    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for (char c : word.toLowerCase().toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return 0;
            }
            node = node.children[index];
        }
        return node.wordCount;
    }

    /**
     * Counts words starting with prefix.
     */
    public int countWordsStartingWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toLowerCase().toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return 0;
            }
            node = node.children[index];
        }
        return node.prefixCount;
    }

    /**
     * Erases one occurrence of word.
     */
    public void erase(String word) {
        TrieNode node = root;
        for (char c : word.toLowerCase().toCharArray()) {
            int index = c - 'a';
            node = node.children[index];
            node.prefixCount--;  // Decrement prefix count
        }
        node.wordCount--;  // Decrement word count
    }
}
