package com.javarena.dsa.algorithms.miscellaneous;

public class PowerOfFour {
    public boolean isPowerOfFour(int n) {
        if (n < 1) return false;

        while (n % 4 == 0) {
            n /= 4;
        }

        return n == 1;
    }
}
