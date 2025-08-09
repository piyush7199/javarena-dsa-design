package org.example.coding.miscellaneous;

public class ThreeConsecutiveInt {
    /**
     * Brute force approach would be start with 1,2,3 and increase it will get to num;
     * and efficient approach would be use property of three consecutive integer
     */
    public long[] sumOfThree(long num) {
        if (num % 3 != 0) return new long[]{};
        long n = num / 3;
        return new long[]{n - 1, n, n + 1};
    }
}
