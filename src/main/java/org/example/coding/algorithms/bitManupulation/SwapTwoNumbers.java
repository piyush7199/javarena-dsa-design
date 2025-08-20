package org.example.coding.algorithms.bitManupulation;

import java.util.Arrays;
import java.util.List;

public class SwapTwoNumbers {
    static List<Integer> getWithOperators(int a, int b) {
        // code here
        a = a + b;
        b = a - b;
        a = a - b;
        return Arrays.asList(a, b);
    }

    static List<Integer> getWithoutOperators(int a, int b) {
        // code here
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        return Arrays.asList(a, b);
    }
}
