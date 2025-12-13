package com.javarena.dsa.algorithms.string;

/**
 * Repeated String Match
 *
 * <p><b>Problem Statement:</b><br>
 * Given strings a and b, return minimum number of times string a must be repeated
 * so that string b is a substring of the repeated string. Return -1 if impossible.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Calculate minimum repetitions needed:
 * - Minimum possible: ceil(b.length / a.length)
 * - May need one extra repetition if b spans across repeat boundary
 * - Try minimum repetitions, then minimum + 1
 * - Check if b is substring using contains()
 * 
 * Key insight:
 * - If b can be found, it will appear within at most (len(b)/len(a)) + 2 repetitions
 * - Beyond that, pattern will repeat without new possibilities
 * 
 * Example: a="abc", b="cabcab"
 * - Repeat 3 times: "abcabcabc" contains "cabcab" starting at index 2
 * 
 * Early termination if b contains characters not in a.
 *
 * <p><b>Time Complexity:</b> O(N × M) - N=length(a), M=length(b), substring check
 * <br><b>Space Complexity:</b> O(N) - String concatenation space
 */
public class RepeatedStringMatch {
    
    /**
     * Finds minimum repetitions of a to contain b as substring.
     */
    public int repeatedStringMatch(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        
        // Calculate minimum repetitions needed
        int minReps = (lenB + lenA - 1) / lenA;  // Ceiling division
        
        StringBuilder repeated = new StringBuilder();
        
        // Build string with minimum repetitions
        for (int i = 0; i < minReps; i++) {
            repeated.append(a);
        }
        
        // Check if b is substring
        if (repeated.toString().contains(b)) {
            return minReps;
        }
        
        // Try one more repetition (b might span boundary)
        repeated.append(a);
        if (repeated.toString().contains(b)) {
            return minReps + 1;
        }
        
        // Impossible to form b
        return -1;
    }
}
