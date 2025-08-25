package org.example.coding.datastructures.trie;

public class Trie {
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {
            children = new TrieNode[26]; // Assuming lowercase letters
            isEndOfWord = false;
        }
    }

    private TrieNode root;

    /**
     * Initializes an empty Trie.
     *
     * @TimeComplexity O(1)
     * @SpaceComplexity O(1)
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the Trie.
     *
     * @param word Word to insert
     * @TimeComplexity O(m), where m is word length
     * @SpaceComplexity O(m)
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
     * Searches for a word in the Trie.
     *
     * @param word Word to search
     * @return True if word exists, false otherwise
     * @TimeComplexity O(m), where m is word length
     * @SpaceComplexity O(1)
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
     * Checks if there is any word with the given prefix.
     * @param prefix Prefix to check
     * @return True if prefix exists, false otherwise
     * @TimeComplexity O(m), where m is prefix length
     * @SpaceComplexity O(1)
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

    public static void main(String[] args) {
        Trie trie = new Trie();

        // Test insert
        trie.insert("cat");
        trie.insert("car");
        trie.insert("cake");

        // Test search
        System.out.println("Search 'cat': " + trie.search("cat")); // true
        System.out.println("Search 'cap': " + trie.search("cap")); // false

        // Test startsWith
        System.out.println("Prefix 'ca': " + trie.startsWith("ca")); // true
        System.out.println("Prefix 'cb': " + trie.startsWith("cb")); // false

        // Test edge cases
        System.out.println("Search empty: " + trie.search("")); // false
        System.out.println("Prefix empty: " + trie.startsWith("")); // true
    }
}
