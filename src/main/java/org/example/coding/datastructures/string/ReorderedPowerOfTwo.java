package org.example.coding.datastructures.string;

import java.util.Arrays;

public class ReorderedPowerOfTwo {
    public boolean reorderedPowerOf2(int n) {
        String target = sortDigits(n);

        for (int i = 0; i < 31; i++) {
            int powerOfTwo = 1 << i;
            if (sortDigits(powerOfTwo).equals(target)) return true;
        }

        return false;
    }

    public String sortDigits(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
