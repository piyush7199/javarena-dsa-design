package com.javarena.dsa.datastructures.hashMapAndSet;

import java.util.*;

/**
 * Vowel Spell Checker
 *
 * <p><b>Problem Statement:</b><br>
 * Match queries against wordlist with three priority levels:
 * 1. Exact match (case-sensitive)
 * 2. Case-insensitive match
 * 3. Vowel-insensitive match (vowels interchangeable)
 *
 * <p><b>Intuition & Approach:</b><br>
 * Three-level matching with preprocessing:
 * - Build three data structures from wordlist:
 *   1. HashSet for exact matches
 *   2. HashMap: lowercase → first occurrence
 *   3. HashMap: devoweled pattern → first occurrence
 * 
 * For each query:
 * - Try exact match in HashSet
 * - Try lowercase match in case map
 * - Try devoweled match in vowel map
 * - Return first match found, or empty string
 * 
 * Devowel: Replace all vowels with '*' for pattern matching.
 *
 * <p><b>Time Complexity:</b> O((N+M)×L) - N words, M queries, L word length
 * <br><b>Space Complexity:</b> O(N×L) - Three data structures
 */
public class VowelSpellChecker {
    /**
     * Performs spell checking using three-level matching.
     */
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exactWords = new HashSet<>(Arrays.asList(wordlist));
        Map<String, String> caseInsensitive = new HashMap<>();
        Map<String, String> vowelInsensitive = new HashMap<>();

        // Build maps for case-insensitive and vowel-insensitive matches
        for (String word : wordlist) {
            String lower = word.toLowerCase();
            String devowelForm = deVowel(word);
            caseInsensitive.putIfAbsent(lower, word);
            vowelInsensitive.putIfAbsent(devowelForm, word);
        }

        String[] result = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];

            if (exactWords.contains(query)) {
                result[i] = query;
            } else {
                String lower = query.toLowerCase();
                String devowelForm = deVowel(query);

                if (caseInsensitive.containsKey(lower)) {
                    result[i] = caseInsensitive.get(lower);
                } else result[i] = vowelInsensitive.getOrDefault(devowelForm, "");
            }
        }
        return result;
    }

    /**
     * Helper: replaces all vowels with '*' and converts to lowercase.
     */
    private String deVowel(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if ("aeiou".indexOf(Character.toLowerCase(c)) >= 0) {
                sb.append('*');
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

}
