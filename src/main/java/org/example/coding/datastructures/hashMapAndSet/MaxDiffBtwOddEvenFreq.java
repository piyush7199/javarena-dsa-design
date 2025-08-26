package org.example.coding.datastructures.hashMapAndSet;

public class MaxDiffBtwOddEvenFreq {
    public int maxDifference(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int maxOdd = 1, minEven = s.length();
        for (int value : freq) {
            if (value == 0) continue;
            if (value % 2 == 1) {
                maxOdd = Math.max(maxOdd, value);
            } else {
                minEven = Math.min(minEven, value);
            }
        }
        return maxOdd - minEven;
    }
}
