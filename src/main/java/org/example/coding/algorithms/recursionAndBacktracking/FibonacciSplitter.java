package org.example.coding.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

public class FibonacciSplitter {

    /**
     * Splits a numeric string into a Fibonacci-like sequence.
     *
     * <p><b>Intuition:</b></p>
     * The Fibonacci property requires that each number in the sequence (after the first two)
     * is the sum of the previous two numbers. The problem is to find such a sequence
     * by splitting the given numeric string into valid integers, such that:
     * <ul>
     *   <li>The sequence contains at least three numbers</li>
     *   <li>No number has leading zeros (unless the number itself is 0)</li>
     *   <li>All numbers are within the 32-bit signed integer range</li>
     * </ul>
     *
     * <p><b>Approach:</b></p>
     * This is solved using backtracking. We iterate over the string and try all valid splits:
     * <ul>
     *   <li>At each step, convert a substring to a number (as long as it's valid)</li>
     *   <li>Use recursion to try building the Fibonacci sequence</li>
     *   <li>If at any point the next number doesn't satisfy the Fibonacci condition,
     *       backtrack and try a different split</li>
     * </ul>
     * We prune branches early when:
     * <ul>
     *   <li>A number has leading zero (e.g. "01")</li>
     *   <li>A number exceeds Integer.MAX_VALUE</li>
     *   <li>The current number is greater than the expected Fibonacci sum</li>
     * </ul>

     */
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> ans = new ArrayList<>();
        helper(num, 0, ans);
        return ans;
    }

    /**
     * Recursive backtracking helper method.
     */
    private boolean helper(String num, int index, List<Integer> ans) {
        if (index == num.length()) {
            return ans.size() >= 3;
        }

        long currNum = 0;
        for (int i = index; i < num.length(); i++) {
            // Skip numbers with leading zeros
            if (i > index && num.charAt(index) == '0') break;

            currNum = currNum * 10 + (num.charAt(i) - '0');
            if (currNum > Integer.MAX_VALUE) break;

            int size = ans.size();
            if (size >= 2) {
                long expectedSum = (long) ans.get(size - 1) + ans.get(size - 2);
                if (currNum < expectedSum) continue;
                else if (currNum > expectedSum) break;
            }

            ans.add((int) currNum);
            if (helper(num, i + 1, ans)) return true;
            ans.remove(ans.size() - 1);
        }
        return false;
    }
}
