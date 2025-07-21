package org.example.coding.datastructures.string;

import java.util.Stack;

public class AddParentheses {
    /**
     * Uses a stack to track unmatched opening brackets '(' and counts unmatched closing ones ')'.
     * <p>
     * Intuition:
     * - Traverse each character in the string.
     * - If it’s an opening bracket '(', push it to the stack.
     * - If it’s a closing bracket ')':
     * - If the stack is not empty and top is '(', pop it (valid pair).
     * - Otherwise, we have an unmatched ')', so increment the counter.
     * - At the end, the stack will have all unmatched '(' brackets.
     * - Total insertions needed = unmatched ')' + unmatched '('.
     * Time Complexity: O(n)
     * - Each character is processed once.
     * <p>
     * Space Complexity: O(n)
     * - In worst case, all '(' are pushed to the stack.
     */
    public int minAddToMakeValid1(String s) {
        Stack<Character> stack = new Stack<>();
        int cnt = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push('(');
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); // Found a matching pair
                } else {
                    cnt++; // Unmatched ')'
                }
            }
        }
        return cnt + stack.size(); // Unmatched ')' + unmatched '('
    }

    /**
     * Optimized version using only counters — no need for a stack.
     * <p>
     * Intuition:
     * - Use `open` to count currently unmatched '(' brackets.
     * - For every ')':
     * - If there’s an unmatched '(', match and decrement `open`.
     * - Otherwise, it's an unmatched ')', so increment `cnt`.
     * - At the end, `open` = number of unmatched '('
     * - Total insertions = `cnt` (unmatched ')') + `open` (unmatched '(')
     * <p>
     * Time Complexity: O(n)
     * - Single pass through the string.
     * <p>
     * Space Complexity: O(1)
     * - Only constant space for counters.
     */
    public int minAddToMakeValid(String s) {
        int open = 0, cnt = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                open++; // Potential opening for a pair
            } else {
                if (open > 0) {
                    open--; // Found a match for a previous '('
                } else {
                    cnt++; // Unmatched ')', needs one '('
                }
            }
        }
        return cnt + open;
    }

}
