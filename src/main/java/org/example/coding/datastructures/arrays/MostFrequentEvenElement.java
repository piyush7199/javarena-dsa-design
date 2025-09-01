package org.example.coding.datastructures.arrays;

public class MostFrequentEvenElement {
    public int mostFrequentEven(int[] nums) {
        int[] cnt = new int[100001];
        int best = -1, freq = 0;
        for (int x : nums) {
            if ((x & 1) == 1) continue;
            int f = ++cnt[x];
            if (f > freq || (f == freq && (best == -1 || x < best))) {
                best = x;
                freq = f;
            }
        }
        return best;
    }
}
