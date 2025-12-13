package com.javarena.dsa.datastructures.string;

import java.util.Stack;

/**
 * Minimum Add to Make Parentheses Valid
 *
 * <p><b>Problem Statement:</b><br>
 * Given string of '(' and ')' parentheses, return minimum number of parentheses to add
 * to make the string valid (every opening has matching closing).
 *
 * <p><b>Intuition & Approach:</b><br>
 * Track unmatched parentheses:
 * - For '(': increment open counter (potential match)
 * - For ')': 
 *   - If open > 0: match with previous '(', decrement open
 *   - Else: unmatched ')', increment close counter
 * - Result = unmatched '(' + unmatched ')'
 * 
 * Two approaches:
 * 1. Stack-based: Push '(', pop on matching ')'
 * 2. Counter-based: Use integers (more efficient)
 *
 * <p><b>Time Complexity:</b> O(N) - Single pass through string
 * <br><b>Space Complexity:</b> O(1) for counter approach, O(N) for stack
 */
public class AddParentheses {
    /**
     * Stack-based approach.
     */
    public int minAddToMakeValid1(String s) {
        Stack<Character> stack = new Stack<>();
        int cnt = 0;
        
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push('(');
            } else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); // Found matching pair
                } else {
                    cnt++; // Unmatched ')'
                }
            }
        }
        return cnt + stack.size(); // Unmatched ')' + unmatched '('
    }

    /**
     * Optimized counter-based approach.
     */
    public int minAddToMakeValid(String s) {
        int open = 0, cnt = 0;
        
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                open++; // Potential opening
            } else {
                if (open > 0) {
                    open--; // Match with previous '('
                } else {
                    cnt++; // Unmatched ')'
                }
            }
        }
        return cnt + open;
    }
}
