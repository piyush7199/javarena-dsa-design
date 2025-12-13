package com.javarena.dsa.datastructures.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Encode and Decode Strings
 *
 * <p><b>Problem Statement:</b><br>
 * Design algorithm to encode list of strings to single string and decode back.
 * Must handle strings containing any characters.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Use length-prefix encoding:
 * - Encode: For each string, prepend "length#" before content
 *   - Example: ["hello", "world"] → "5#hello5#world"
 * - Decode: Read length, skip '#', extract that many characters
 * 
 * This handles special characters and empty strings correctly.
 * Alternative: Use escape characters (more complex).
 *
 * <p><b>Time Complexity:</b> O(N) for both encode and decode, N = total characters
 * <br><b>Space Complexity:</b> O(N) for result string
 */
public class EncodeAndDecode {
    /**
     * Encodes list of strings to single string.
     */
    public String encode(String strs[]) {
        StringBuilder ans = new StringBuilder();
        for (String str : strs) {
            ans.append(str.length()).append("#").append(str);
        }
        return ans.toString();
    }

    /**
     * Decodes single string back to list of strings.
     */
    public String[] decode(String str) {
        int i = 0;
        int n = str.length();
        List<String> ans = new ArrayList<>();
        
        while (i < n) {
            // Read length
            StringBuilder numString = new StringBuilder();
            while (str.charAt(i) != '#') {
                numString.append(str.charAt(i));
                i++;
            }
            i++; // Skip '#'
            
            // Extract string of that length
            int len = Integer.parseInt(numString.toString());
            ans.add(str.substring(i, i + len));
            i = i + len;
        }
        
        String[] res = new String[ans.size()];
        return ans.toArray(res);
    }
}
