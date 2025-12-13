package com.javarena.dsa.algorithms.dynamicProgramming;

import java.util.HashMap;

/**
 * Decode Ways
 *
 * <p><b>Problem Statement:</b><br>
 * Given encoded message containing digits, count number of ways to decode it.
 * 'A' = "1", 'B' = "2", ..., 'Z' = "26". String only contains digits.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Choice at each position:
 * - Take single digit (if 1-9): decode as one letter
 * - Take two digits (if 10-26): decode as one letter
 * - Sum ways from both choices
 * 
 * Similar to climbing stairs with variable step sizes.
 * 
 * DP state: dp[i] = number of ways to decode string[0...i-1]
 * - If s[i] valid (1-9): dp[i] += dp[i-1]
 * - If s[i-1:i] valid (10-26): dp[i] += dp[i-2]
 * 
 * Handle leading zeros: "06" invalid, "10" valid.
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass with memoization/tabulation
 * <br><b>Space Complexity:</b> O(N) for DP array, optimizable to O(1)
 */
public class DecodeWays {
    
    /**
     * Memoization approach.
     */
    public int numDecodings(String s) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        return helper(s, 0, memo);
    }

    private int helper(String s, int index, HashMap<Integer, Integer> memo) {
        if (index == s.length()) return 1;
        if (s.charAt(index) == '0') return 0;
        if (memo.containsKey(index)) return memo.get(index);

        int ways = helper(s, index + 1, memo);
        
        if (index + 1 < s.length()) {
            int twoDigit = Integer.parseInt(s.substring(index, index + 2));
            if (twoDigit <= 26) {
                ways += helper(s, index + 2, memo);
            }
        }
        
        memo.put(index, ways);
        return ways;
    }

    /**
     * Tabulation approach - optimal.
     */
    public int numDecodingsDP(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit >= 1) {
                dp[i] += dp[i - 1];
            }
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
