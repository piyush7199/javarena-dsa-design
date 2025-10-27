package com.javarena.dsa.algorithms.string;

/**
 * 686. Repeated String Match
 *
 * <p><b>Problem Link:</b> 
 * <a href="https://leetcode.com/problems/repeated-string-match/">LeetCode - Repeated String Match</a>
 *
 * <p><b>Difficulty:</b> Medium
 *
 * <p><b>Topics:</b> String, String Matching
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * Given two strings a and b, return the minimum number of times you should repeat string a 
 * so that string b is a substring of it. If it is impossible for b to be a substring of a 
 * after repeating it, return -1.
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: a = "abcd", b = "cdabcdab"
 * Output: 3
 * Explanation: "abcdabcdabcd" contains b.
 *
 * Input: a = "a", b = "aa"
 * Output: 2
 * Explanation: "aa" contains b.
 *
 * Input: a = "a", b = "a"
 * Output: 1
 * Explanation: Single "a" contains b.
 *
 * Input: a = "abc", b = "wxyz"
 * Output: -1
 * Explanation: No amount of repetition will create b as substring.
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * Minimum repetitions with boundary considerations:
 * - At minimum, need to repeat a until length >= b.length()
 * - String b might span across repetition boundaries
 * - Check if b exists in current concatenation
 * - If not, try one more repetition (handles boundary cases)
 * - More than one extra repetition is never needed
 * - If b doesn't appear in these cases, it's impossible
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Initialize empty StringBuilder and count = 0</li>
 *   <li>Keep appending a until StringBuilder length >= b.length()</li>
 *   <li>Check if current concatenation contains b</li>
 *   <li>If yes, return count</li>
 *   <li>If no, try one more repetition (handles boundary case)</li>
 *   <li>If still not found, return -1 (impossible)</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(n × (m + n))<br>
 * Where n = length of a, m = length of b. StringBuilder building O(m), contains() O(n×m).
 *
 * <p><b>Space Complexity:</b> O(n + m)<br>
 * Space for StringBuilder storing repeated string.
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>b longer than a: Need multiple repetitions</li>
 *   <li>b equals a: Return 1</li>
 *   <li>b spans repetition boundary: One extra check handles this</li>
 *   <li>Characters in b not in a: Return -1</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/rotate-string/">Rotate String</a>
 */
public class RepeatedStringMatch {
    
    /**
     * Returns minimum repetitions of a needed for b to be substring.
     *
     * @param a the string to repeat
     * @param b the target substring
     * @return minimum number of repetitions, or -1 if impossible
     */
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        
        // Step 1: Repeat a until length >= b.length()
        while (sb.length() < b.length()) {
            sb.append(a);
            count++;
        }
        
        // Step 2: Check if b is substring now
        if (sb.toString().contains(b)) return count;
        
        // Step 3: Try one more repetition (handles boundary cases)
        if (sb.append(a).toString().contains(b)) return ++count;
        
        // Step 4: Not possible
        return -1;
    }
}
