package org.example.coding.datastructures.trie;

public class TrieII {
    static class TrieNode {
        TrieNode[] children;
        int prefixCount;   // number of words passing through this node
        int wordCount;     // number of words ending at this node

        TrieNode() {
            children = new TrieNode[26]; // assuming lowercase a-z
            prefixCount = 0;
            wordCount = 0;
        }
    }

    private TrieNode root;

    public TrieII() {
        root = new TrieNode();
    }

    /**
     * Insert a word into the Trie
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
     * Count how many times the word was inserted
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
     * Count words that start with a given prefix
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

    public void erase(String word) {
        TrieNode node = root;
        for (char c : word.toLowerCase().toCharArray()) {
            int index = c - 'a';
            node = node.children[index];
            node.prefixCount--;  // every node on path loses one prefix
        }
        node.wordCount--;  // last node loses one full word
    }
}
