package org.example.coding.algorithms.bitManupulation;

public class NoOfOneBits {
    static int setBits(int n) {
        // code here
        int ans = 0;
        while (n != 0) {
            ans += (n & 1);
            n = n >> 1;
        }
        return ans;
    }
}
