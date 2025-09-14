package org.example.coding.datastructures.hashMapAndSet;

import java.util.*;

public class VowelSpellChecker {
    /**
     * Solves the spell checker problem by matching queries against a given wordlist using three levels of matching rules:
     *
     * <p><strong>Intuition:</strong></p>
     * The problem requires finding a match for each query from the wordlist under the following priority rules:
     * <ol>
     *   <li><b>Exact Match:</b> The query matches a word in the wordlist exactly (case-sensitive).</li>
     *   <li><b>Case-Insensitive Match:</b> If no exact match, match ignoring case. The first word in the wordlist with that lowercase form is chosen.</li>
     *   <li><b>Vowel-Insensitive Match:</b> If no case-insensitive match, match by treating all vowels ('a', 'e', 'i', 'o', 'u') as interchangeable.
     *       Each vowel is replaced with a special marker (e.g., '*'). The first word in the wordlist with that vowel pattern is chosen.</li>
     *   <li>If none of the above matches are found, return an empty string.</li>
     * </ol>
     * <p>
     * This three-tiered matching ensures exactness first, then relaxed case matching, and finally relaxed vowel matching.
     *
     * <p><strong>Approach:</strong></p>
     * <ul>
     *   <li>Preprocess the wordlist to build three data structures:
     *     <ul>
     *       <li>A <code>HashSet</code> (<code>exactWords</code>) for fast O(1) exact match lookups.</li>
     *       <li>A <code>HashMap</code> (<code>caseInsensitive</code>) that maps lowercase words to their first occurrence.</li>
     *       <li>A <code>HashMap</code> (<code>vowelInsensitive</code>) that maps "devoweled" words to their first occurrence.</li>
     *     </ul>
     *   </li>
     *   <li>For each query:
     *     <ul>
     *       <li>Check for exact match using the <code>HashSet</code>.</li>
     *       <li>If no exact match, convert to lowercase and check the <code>caseInsensitive</code> map.</li>
     *       <li>If still no match, convert to "devowel" form and check the <code>vowelInsensitive</code> map.</li>
     *       <li>If all fail, return an empty string.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * <p><strong>Time Complexity:</strong></p>
     * <ul>
     *   <li>Building the data structures: O(N * L)
     *     <ul>
     *       <li>N = number of words in the wordlist</li>
     *       <li>L = average length of a word</li>
     *     </ul>
     *   </li>
     *   <li>Processing the queries: O(M * L)
     *     <ul>
     *       <li>M = number of queries</li>
     *     </ul>
     *   </li>
     *   <li><b>Total:</b> O((N + M) * L)</li>
     * </ul>
     *
     * <p><strong>Space Complexity:</strong></p>
     * <ul>
     *   <li><b>O(N * L)</b> for storing the wordlist in HashSet and HashMaps:
     *     <ul>
     *       <li>HashSet stores up to N words.</li>
     *       <li>Two HashMaps store mappings of lowercase and devoweled forms of each word.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param wordlist Array of unique words forming the reference dictionary.
     * @param queries  Array of query words to be spellchecked.
     * @return Array of strings where each element corresponds to the result of spellchecking a query.
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
     * Helper function that replaces all vowels in the word with '*'
     * and converts the rest of the characters to lowercase.
     * <p>
     * Example:
     * <ul>
     *   <li>"KiTe" → "k*t*"</li>
     *   <li>"hare" → "h*r*"</li>
     * </ul>
     *
     * @param word Input string to transform.
     * @return The transformed string where all vowels are replaced by '*'.
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
