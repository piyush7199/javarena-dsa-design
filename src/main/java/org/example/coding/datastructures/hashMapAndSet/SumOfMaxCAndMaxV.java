package org.example.coding.datastructures.hashMapAndSet;

public class SumOfMaxCAndMaxV {
    public int maxFreqSum(String s) {
        int[] freq = new int[26];
        int maxV = 0;
        int maxC = 0;
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                maxV = Math.max(freq[ch - 'a'], maxV);
            } else {
                maxC = Math.max(freq[ch - 'a'], maxC);
            }
        }
        return maxC + maxV;
    }
}
