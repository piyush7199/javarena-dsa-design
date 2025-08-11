package org.example.coding.algorithms.bitManupulation;

public class SumOfTwoIntegers {
    public int getSum(int a, int b) {

        // Iterate till there is no carry
        while (b != 0) {

            // carry contains common set bits of a and b, left shifted by 1
            int carry = (a & b) << 1;

            // Update a with (a + b without carry)
            a = a ^ b;

            // Update b with carry
            b = carry;
        }
        return a;
    }
}
