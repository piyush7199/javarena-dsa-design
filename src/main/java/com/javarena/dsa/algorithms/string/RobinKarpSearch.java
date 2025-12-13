package com.javarena.dsa.algorithms.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Rabin-Karp Pattern Matching Algorithm
 *
 * <p><b>Problem Statement:</b><br>
 * Given text and pattern, find all occurrences of pattern in text using rolling hash.
 * Return list of starting indices where pattern is found.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Rolling hash for efficient pattern matching:
 * - Compute hash of pattern
 * - Slide window of pattern length over text
 * - Use rolling hash to update window hash in O(1)
 * - When hash matches, verify actual string (handle collisions)
 * 
 * Rolling hash formula:
 * - hash = (c[0] × d^(m-1) + c[1] × d^(m-2) + ... + c[m-1]) % q
 * - d = radix (26 for lowercase, 256 for ASCII)
 * - q = prime modulo to reduce collisions
 * 
 * Rolling update:
 * - Remove leftmost: subtract c[left] × d^(m-1)
 * - Shift: multiply by d
 * - Add rightmost: add c[right]
 * - All mod q
 * 
 * Better than naive O(nm) when multiple patterns searched.
 *
 * <p><b>Time Complexity:</b> O(N + M) average, O(NM) worst with many collisions
 * <br><b>Space Complexity:</b> O(1) excluding output list
 */
public class RobinKarpSearch {
    
    private static final int d = 256;  // Radix (ASCII)
    private static final int q = 101; // Prime modulo
    
    /**
     * Finds all pattern occurrences using Rabin-Karp.
     */
    public List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        
        if (m > n) return result;
        
        int patternHash = 0;
        int textHash = 0;
        int h = 1;
        
        // Calculate h = d^(m-1) % q
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }
        
        // Calculate initial hash for pattern and first window
        for (int i = 0; i < m; i++) {
            patternHash = (d * patternHash + pattern.charAt(i)) % q;
            textHash = (d * textHash + text.charAt(i)) % q;
        }
        
        // Slide pattern over text
        for (int i = 0; i <= n - m; i++) {
            // Check if hash matches
            if (patternHash == textHash) {
                // Verify actual string (handle hash collision)
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    result.add(i);
                }
            }
            
            // Calculate hash for next window (rolling hash)
            if (i < n - m) {
                textHash = (d * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % q;
                if (textHash < 0) {
                    textHash += q;
                }
            }
        }
        
        return result;
    }
}
