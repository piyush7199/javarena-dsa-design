package org.example.coding.datastructures.string;

public class MaxBalloons {
    public int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];
        for (char ch : text.toCharArray()) {
            freq[ch - 'a']++;
        }
        String b = "balon";
        int ans = Integer.MAX_VALUE;
        for (char ch : b.toCharArray()) {
            if (ch == 'l' || ch == 'o') {
                ans = Math.min(ans, freq[ch - 'a'] / 2);
            } else {
                ans = Math.min(ans, freq[ch - 'a']);
            }
        }
        return ans;
    }
}
