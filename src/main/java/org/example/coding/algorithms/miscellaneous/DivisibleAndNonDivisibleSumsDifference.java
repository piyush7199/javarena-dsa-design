package org.example.coding.algorithms.miscellaneous;

public class DivisibleAndNonDivisibleSumsDifference {
    /**
     * Computes the difference between:
     * 1. The sum of all integers from 1 to n.
     * 2. The sum of all integers from 1 to n that are divisible by m (counted properly).
     * <p>
     * Intuition:
     * - First, compute the total sum of numbers from 1 to n using the formula: n * (n + 1) / 2.
     * - Then, subtract the contribution of numbers divisible by m.
     * - Numbers divisible by m form an arithmetic progression: m, 2m, 3m, ... up to the largest multiple ≤ n.
     * - The sum of this sequence can be derived using the formula for the sum of the first k natural numbers.
     * <p>
     * Approach:
     * 1. Compute total sum of [1..n].
     * 2. Compute how many multiples of m exist within [1..n] → noOfEle = n / m.
     * 3. Use formula: sumMultiples = m * (noOfEle * (noOfEle + 1)) / 2.
     * 4. Return totalSum - sumMultiples.
     * <p>
     * Time Complexity: O(1), since only a few arithmetic operations are performed.
     * Space Complexity: O(1), no extra space is used.
     *
     * @param n the upper limit of the range [1..n].
     * @param m the divisor used to exclude multiples.
     * @return the difference between the total sum and the sum of multiples of m.
     */
    public int differenceOfSums(int n, int m) {
        int totalSum = n * (n + 1) / 2;
        if (m > n) return totalSum; // no multiples exist

        int noOfEle = n / m; // number of multiples of m within [1..n]
        int sumMultiples = m * noOfEle * (noOfEle + 1); // formula for sum of multiples

        return totalSum - sumMultiples;
    }

}
