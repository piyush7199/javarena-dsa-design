package org.example.coding.algorithms.string;

public class KMPSearch {
    public int search(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        int[] lps = calculateLPS(pattern, m);

        int i = 0, j = 0;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                return i - j;
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1;
    }

    private int[] calculateLPS(String pattern, int m) {
        int[] lps = new int[m];

        int len = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public static void main(String[] args) {
        String text = "ABABDABABC";
        KMPSearch kmp = new KMPSearch();
        System.out.println(kmp.search(text, "ABABC"));
        System.out.println(kmp.search(text, ""));
        System.out.println(kmp.search(text, "XYZ"));
    }
}
