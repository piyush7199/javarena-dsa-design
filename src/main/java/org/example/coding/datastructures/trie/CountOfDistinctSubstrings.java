package org.example.coding.datastructures.trie;

public class CountOfDistinctSubstrings {
    static class Node {
        Node children[] = new Node[26];
    }


    public static int countDistinctSubstring(String s) {
        int count = 1;
        Node root = new Node();
        for (int i = 0; i < s.length(); i++) {
            Node curr = root;
            for (int j = i; j < s.length(); j++) {
                int idx = s.charAt(j) - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new Node();
                    count++;
                }
                curr = curr.children[idx];
            }
        }
        return count;
    }
}
