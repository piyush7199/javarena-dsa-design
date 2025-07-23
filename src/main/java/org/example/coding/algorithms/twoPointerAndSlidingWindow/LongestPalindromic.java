package org.example.coding.algorithms.twoPointerAndSlidingWindow;

public class LongestPalindromic {
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        int maxLen = 1;
        String maxStr = s.substring(0, 1);
        int n = s.length();

        for (int i = 0; i < n; i++) {
            for (int j = i + maxLen; j < s.length(); j++) {
                if (j - i + 1 > maxLen && isPalindrome(s, i, j)) {
                    maxLen = j - i;
                    maxStr = s.substring(i, j + 1);
                }
            }
        }
        return maxStr;
    }

    private boolean isPalindrome(String val, int i, int j) {
        while (j >= i) {
            if (val.charAt(i) != val.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
