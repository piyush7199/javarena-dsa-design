package com.javarena.dsa.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Split Array into Fibonacci Sequence
 *
 * <p><b>Problem Statement:</b><br>
 * Given a string num of digits, split it into a Fibonacci-like sequence where each number (after the first two)
 * is the sum of the previous two numbers. The sequence must contain at least three numbers, have no leading zeros,
 * and all numbers must fit in 32-bit signed integer range.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use backtracking to try all valid ways to split the string
 * - At each step, try to form a number from current position
 * - If we have less than 2 numbers, accept any valid number
 * - If we have 2+ numbers, only accept numbers that equal sum of previous two
 * - Prune early: skip numbers with leading zeros or exceeding Integer.MAX_VALUE
 * - Return first valid sequence found
 *
 * <p><b>Time Complexity:</b> O(2^N) - Try all possible splits
 * <br><b>Space Complexity:</b> O(N) - Recursion depth and result list
 */
public class FibonacciSplitter {

    /**
     * Splits a numeric string into a Fibonacci-like sequence.
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
