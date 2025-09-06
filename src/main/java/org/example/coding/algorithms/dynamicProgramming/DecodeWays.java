package org.example.coding.algorithms.dynamicProgramming;

public class DecodeWays {
    /**
     * Public method to initiate the decoding count.
     *
     * @param s The input string consisting of digits.
     * @return The total number of ways to decode the string.
     */
    public int numDecodings(String s) {
        return helper(s, 0);
    }

    /**
     * Recursive helper function that calculates the number of decoding ways
     * starting from a given index.
     *
     * <p><b>Logic:</b>
     * <ol>
     *   <li>If the current index reaches the end of the string, return 1 (a valid decoding path).</li>
     *   <li>If the current character is '0', return 0 (invalid as no mapping exists for '0').</li>
     *   <li>Option 1: Decode the current single digit and move to the next index.</li>
     *   <li>Option 2: Decode two consecutive digits (if they form a valid number from 10 to 26).</li>
     *   <li>Sum both options to get total decodings from this index.</li>
     * </ol>
     *
     * <p><b>Base Cases:</b>
     * <ul>
     *   <li>If index >= length of string → return 1 (successful decoding path).</li>
     *   <li>If current character is '0' → return 0 (invalid path).</li>
     * </ul>
     *
     * <p><b>Time Complexity:</b>
     * <ul>
     *   <li>O(2^n) in the worst case since each position can branch into two recursive calls
     *   (single digit or two digits).</li>
     * </ul>
     *
     * <p><b>Space Complexity:</b>
     * <ul>
     *   <li>O(n) due to recursion stack depth.</li>
     * </ul>
     *
     * @param digits The string of digits to decode.
     * @param index  The current position in the string being processed.
     * @return Number of ways to decode from this index onward.
     */
    private int helper(String digits, int index) {
        int n = digits.length();

        // Base case: reached the end successfully
        if (index >= n) return 1;

        // A leading '0' cannot be decoded
        if (digits.charAt(index) == '0') return 0;

        // Decode single digit
        int ans = helper(digits, index + 1);

        // Decode two digits if valid (10 to 26)
        if (index + 1 < n &&
                ((digits.charAt(index) == '1' && digits.charAt(index + 1) <= '9') ||
                        (digits.charAt(index) == '2' && digits.charAt(index + 1) <= '6'))) {
            ans += helper(digits, index + 2);
        }

        return ans;
    }

    public int numDecodingsEff(String s) {
        int n = s.length();
        int prev1 = 1, prev2 = 0;
        for (int i = n - 1; i >= 0; i--) {
            int cur = 0;
            if (s.charAt(i) != '0') {
                cur = prev1;
            }

            if ((i + 1 < n) && ((s.charAt(i) == '1'
                    && s.charAt(i + 1) <= '9')
                    || (s.charAt(i) == '2'
                    && s.charAt(i + 1) <= '6'))) {
                cur += prev2;
            }
            prev2 = prev1;
            prev1 = cur;
        }

        return prev1;
    }
}
