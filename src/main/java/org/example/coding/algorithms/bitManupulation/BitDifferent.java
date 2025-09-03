package org.example.coding.algorithms.bitManupulation;

public class BitDifferent {
    public int minChanges(int n, int k) {
        if (k > n) return -1;
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1 && (k & 1) == 0) {
                count++;
            }
            if ((n & 1) == 0 && (k & 1) == 1) {
                return -1;
            }
            n = n >> 1;
            k = k >> 1;
        }
        return count;
    }
}
