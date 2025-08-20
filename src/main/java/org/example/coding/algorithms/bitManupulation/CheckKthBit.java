package org.example.coding.algorithms.bitManupulation;

public class CheckKthBit {
    static boolean checkKthBitUsingLeftShift(int n, int k) {
        // code here
        int ele = 1 << k;
        return (n & ele) != 0;
    }

    static boolean checkKthBitUsingRightShift(int n, int k) {
        // Your code here
        return 1 == ((n >> k) & 1);
    }
}
