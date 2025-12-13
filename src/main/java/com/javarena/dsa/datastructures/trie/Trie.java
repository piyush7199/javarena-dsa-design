package com.javarena.dsa.datastructures.trie;

/**
 * Trie (Prefix Tree) Implementation
 *
 * <p><b>Problem Statement:</b><br>
 * Implement trie data structure for efficient string storage and retrieval.
 * Support insert, search, and prefix matching operations.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Tree-based structure where each node represents a character:
 * - Root is empty
 * - Each path from root represents a word/prefix
 * - Each node has array of 26 children (for lowercase letters)
 * - Flag marks end of complete word
 * 
 * Operations:
 * - Insert: Traverse/create path for each character
 * - Search: Traverse path, check end-of-word flag
 * - StartsWith: Traverse path, don't need end flag
 * 
 * Efficient for autocomplete, spell check, IP routing.
 *
 * <p><b>Time Complexity:</b> O(M) per operation, M = word/prefix length
 * <br><b>Space Complexity:</b> O(ALPHABET_SIZE × N × M) worst case, N words of length M
 */
public class Trie {
    /**
     * Trie node with 26 children for lowercase letters.
     */
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {
            children = new TrieNode[26]; // Lowercase letters a-z
            isEndOfWord = false;
        }
    }

    private TrieNode root;

    /**
     * Initializes empty Trie.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts word into Trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toLowerCase().toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    /**
     * Searches for exact word in Trie.
     */
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toLowerCase().toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEndOfWord;
    }

    /**
     * Checks if any word starts with given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toLowerCase().toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return true;
    }

    /**
     * Deletes word from Trie.
     */
    public boolean delete(String word) {
        return deleteHelper(root, word.toLowerCase(), 0);
    }

    /**
     * Helper for recursive deletion.
     */
    private boolean deleteHelper(TrieNode node, String word, int index) {
        if (node == null) {
            return false;
        }

        if (index == word.length()) {
            if (!node.isEndOfWord) {
                return false; // Word doesn't exist
            }
            node.isEndOfWord = false;
            return hasNoChildren(node);
        }

        int charIndex = word.charAt(index) - 'a';
        if (deleteHelper(node.children[charIndex], word, index + 1)) {
            node.children[charIndex] = null;
            return !node.isEndOfWord && hasNoChildren(node);
        }

        return false;
    }

    /**
     * Checks if node has no children.
     */
    private boolean hasNoChildren(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }
}
