package com.javarena.dsa.algorithms.string;

/**
 * Rabin-Karp Pattern Matching Algorithm
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/">GFG - Rabin-Karp Algorithm</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> String, Pattern Matching, Hashing, Rolling Hash
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given a text and a pattern, find the first occurrence of pattern in text using the Rabin-Karp 
 * algorithm. This algorithm uses rolling hash technique to achieve average O(n+m) time complexity.
 * Return the index of first occurrence, or -1 if pattern is not found.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: text = "ABABDABABC", pattern = "ABABC"
 * Output: 5
 * Explanation: Pattern starts at index 5.
 *
 * Input: text = "AABAACAADAA", pattern = "AAD"
 * Output: 7
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Rolling hash for efficient pattern matching:
 * - Use hash function to convert pattern and text windows to numbers
 * - Compare hashes instead of character-by-character comparison
 * - Rolling hash: update hash in O(1) when sliding window
 * - Hash formula: hash = Σ(char[i] × PRIME^i) for i = 0 to m-1
 * - When hashes match, verify with actual string comparison (handle collisions)
 * - Average case O(n+m), worst case O(n×m) with many hash collisions
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Calculate hash of pattern</li>
 *   <li>Calculate hash of first window of text (size = pattern length)</li>
 *   <li>Slide window through text:</li>
 *   <li>- Compare hashes</li>
 *   <li>- If match, verify with actual string comparison</li>
 *   <li>- If verified, return index</li>
 *   <li>Update hash using rolling hash formula:</li>
 *   <li>- Remove contribution of leftmost character</li>
 *   <li>- Add contribution of new rightmost character</li>
 *   <li>Return -1 if pattern not found</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n + m) average, O(n × m) worst<br>
 * Average case when hash collisions are rare. Worst case with many collisions.
 *
 * <p><b>Space Complexity:</b> O(1)<br>
 * Only uses constant extra space for hash values.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Pattern longer than text: Return -1</li>
 *   <li>Empty pattern: Convention-dependent</li>
 *   <li>Hash collisions: Handled by string verification</li>
 *   <li>No match: Return -1</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/repeated-string-match/">Repeated String Match</a>
 */
public class RobinKarpSearch {
    private final int PRIME = 101;

    /**
     * Finds first occurrence of pattern in text using Rabin-Karp algorithm.
     *
     * @param text the text to search in
     * @param pattern the pattern to find
     * @return index of first occurrence, or -1 if not found
     */
    public int search(String text, String pattern) {
        int patternLength = pattern.length();
        int textLength = text.length();
        
        // Step 1: Calculate initial hashes
        double textHash = calculateHash(text.substring(0, patternLength));
        double patternHash = calculateHash(pattern);

        // Step 2: Slide window through text
        for (int i = 0; i <= textLength - patternLength; i++) {
            // Step 3: Compare hashes
            if (textHash == patternHash) {
                // Step 4: Verify with actual string comparison (handle collisions)
                if (text.substring(i, i + patternLength).equals(pattern)) {
                    return i;
                }
            }
            
            // Step 5: Update hash for next window using rolling hash
            if (i < textLength - patternLength) {
                textHash = updateHash(
                    textHash, 
                    text.charAt(i), 
                    text.charAt(i + patternLength), 
                    patternLength
                );
            }
        }

        return -1;
    }

    /**
     * Calculates hash value for a string using polynomial rolling hash.
     * Hash = Σ(char[i] × PRIME^i) for i = 0 to length-1
     *
     * @param str the string to hash
     * @return hash value
     */
    private double calculateHash(String str) {
        double hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash += (str.charAt(i) * Math.pow(PRIME, i));
        }
        return hash;
    }

    /**
     * Updates hash when sliding window by removing old character and adding new one.
     * newHash = (oldHash - oldChar) / PRIME + newChar × PRIME^(patternLength-1)
     *
     * @param oldHash current hash value
     * @param oldChar character being removed (left end)
     * @param newChar character being added (right end)
     * @param patternLength length of the pattern
     * @return updated hash value
     */
    private double updateHash(double oldHash, char oldChar, char newChar, int patternLength) {
        // Remove leftmost character's contribution
        double newHash = (oldHash - oldChar) / PRIME;
        
        // Add rightmost character's contribution
        newHash = newHash + newChar * Math.pow(PRIME, patternLength - 1);
        
        return newHash;
    }
}
