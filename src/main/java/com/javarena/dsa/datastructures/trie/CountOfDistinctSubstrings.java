package com.javarena.dsa.datastructures.trie;

/**
 * Count of Distinct Substrings
 *
 * <p><b>Problem Statement:</b><br>
 * Given string, count number of distinct substrings (including empty string).
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use Trie to count distinct substrings efficiently:
 * - For each starting position i:
 *   - Insert all substrings starting at i into Trie
 *   - Each new node created = new distinct substring
 * - Count = 1 (empty) + number of nodes created
 * 
 * Key insight: Trie automatically handles duplicates
 * - If substring already exists, no new node created
 * - Only new substrings create new nodes
 * 
 * Alternative: Use HashSet (simpler but more space).
 *
 * <p><b>Time Complexity:</b> O(N²) - N starting positions, up to N characters each
 * <br><b>Space Complexity:</b> O(N²) - Trie nodes for all substrings
 */
public class CountOfDistinctSubstrings {
    /**
     * Trie node for substring storage.
     */
    static class Node {
        Node children[] = new Node[26];
    }

    /**
     * Counts distinct substrings using Trie.
     */
    public static int countDistinctSubstring(String s) {
        int count = 1; // Count empty string
        Node root = new Node();
        
        // Try all starting positions
        for (int i = 0; i < s.length(); i++) {
            Node curr = root;
            // Insert all substrings starting at i
            for (int j = i; j < s.length(); j++) {
                int idx = s.charAt(j) - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new Node();
                    count++; // New distinct substring
                }
                curr = curr.children[idx];
            }
        }
        return count;
    }
}
