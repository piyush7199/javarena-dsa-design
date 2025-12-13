package com.javarena.dsa.datastructures.string;

/**
 * Valid Anagram
 *
 * <p><b>Problem Statement:</b><br>
 * Determine if two strings are anagrams (contain same characters with same frequencies).
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use frequency counting:
 * - If lengths differ, not anagrams
 * - Count character frequencies using array (26 letters)
 * - For string s: increment frequency
 * - For string t: decrement frequency
 * - If all frequencies are 0, strings are anagrams
 * 
 * Alternative: Sort both strings and compare.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through both strings
 * <br><b>Space Complexity:</b> O(1) - Fixed size array (26)
 */
public class ValidAnagram {
    /**
     * Checks if two strings are anagrams.
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        
        int n = s.length();
        int[] freq = new int[26];
        
        for(int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for(int ele : freq) {
            if(ele != 0) return false;
        }
        return true;
    }
}
