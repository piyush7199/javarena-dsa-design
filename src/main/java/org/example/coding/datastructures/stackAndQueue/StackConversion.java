package org.example.coding.datastructures.stackAndQueue;

import java.util.Set;
import java.util.Stack;

public class StackConversion {

    /**
     * Converts a prefix expression to an infix expression.
     *
     * <p><b>Intuition:</b> Traverse the prefix expression in reverse. For each operator, pop two operands from the stack,
     * form a string "(operand1 operator operand2)", and push it back. For each operand, push it as a string.</p>
     *
     * <p><b>Time Complexity:</b> O(n) – each character is processed once.</p>
     * <p><b>Space Complexity:</b> O(n) – due to the stack usage.</p>
     */
    public static String preToInfix(String pre_exp) {
        Stack<String> stack = new Stack<>();
        Set<Character> set = Set.of('+', '-', '*', '/', '%', '^');

        for (int i = pre_exp.length() - 1; i >= 0; i--) {
            char ch = pre_exp.charAt(i);
            if (set.contains(ch)) {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push("(" + op1 + ch + op2 + ")");
            } else {
                stack.push(Character.toString(ch));
            }
        }
        return stack.pop();
    }

    /**
     * Converts a prefix expression to a postfix expression.
     *
     * <p><b>Intuition:</b> Traverse from right to left. If it's an operand, push it to stack.
     * If it's an operator, pop two operands and form postfix: operand1 + operand2 + operator.</p>
     *
     * <p><b>Time Complexity:</b> O(n)</p>
     * <p><b>Space Complexity:</b> O(n)</p>
     */
    public static String preToPost(String pre_exp) {
        Stack<String> stack = new Stack<>();
        for (int i = pre_exp.length() - 1; i >= 0; i--) {
            char ch = pre_exp.charAt(i);
            if (Character.isLetter(ch)) {
                stack.push(Character.toString(ch));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push(op1 + op2 + ch);
            }
        }
        return stack.pop();
    }

    /**
     * Converts a postfix expression to an infix expression.
     *
     * <p><b>Intuition:</b> Traverse from left to right. Push operands.
     * On encountering an operator, pop two operands and form infix: (operand2 operator operand1).</p>
     *
     * <p><b>Time Complexity:</b> O(n)</p>
     * <p><b>Space Complexity:</b> O(n)</p>
     */
    public static String postToInfix(String post_exp) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < post_exp.length(); i++) {
            char ch = post_exp.charAt(i);
            if (Character.isLetter(ch)) {
                stack.push(Character.toString(ch));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push("(" + op2 + ch + op1 + ")");
            }
        }
        return stack.pop();
    }

    /**
     * Converts a postfix expression to a prefix expression.
     *
     * <p><b>Intuition:</b> Traverse from left to right. Push operands.
     * When an operator is found, pop two operands and form prefix: operator + operand2 + operand1.</p>
     *
     * <p><b>Time Complexity:</b> O(n)</p>
     * <p><b>Space Complexity:</b> O(n)</p>
     */
    public static String postToPre(String post_exp) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < post_exp.length(); i++) {
            char ch = post_exp.charAt(i);
            if (Character.isLetter(ch)) {
                stack.push(Character.toString(ch));
            } else {
                String op1 = stack.pop();
                String op2 = stack.pop();
                stack.push(ch + op2 + op1);
            }
        }
        return stack.pop();
    }

    /**
     * Returns the precedence of a given operator.
     *
     * <p>Higher value means higher precedence.<br>
     * - '^' = 3            <br>
     * - '*' or '/' = 2     <br>
     * - '+' or '-' = 1
     */
    private static int prec(char c) {
        if (c == '^') return 3;
        else if (c == '/' || c == '*') return 2;
        else if (c == '+' || c == '-') return 1;
        else return -1;
    }

    /**
     * Converts an infix expression to postfix.
     *
     * <p><b>Infix:</b> (A+B)*C <br>
     * <b>Postfix:</b> AB+C*
     *
     * <p><b>Algorithm:</b>
     * - Use a stack to hold operators.<br>
     * - Output operands directly.<br>
     * - Push '(' to stack.<br>
     * - On ')', pop and output until '('.<br>
     * - Pop higher/equal precedence operators before pushing a new one.
     *
     * <p><b>Time Complexity:</b> O(n) <br>
     * <b>Space Complexity:</b> O(n)
     */
    public static String infixToPostfix(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                sb.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop(); // Remove '('
            } else {
                while (!stack.isEmpty() && prec(ch) <= prec(stack.peek())) {
                    sb.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    /**
     * Converts an infix expression to prefix using reversal + postfix logic.
     *
     * <p><b>Infix:</b> (A+B)*C <br>
     * <b>Prefix:</b> *+ABC
     *
     * <p><b>Algorithm:</b>
     * - Reverse the infix expression.<br>
     * - Swap '(' with ')'.<br>
     * - Convert to postfix.<br>
     * - Reverse the postfix result to get prefix.
     *
     * <p><b>Time Complexity:</b> O(n) <br>
     * <b>Space Complexity:</b> O(n)
     */
    public static String infixToPrefix(String infix) {
        StringBuilder sb = new StringBuilder(infix).reverse();

        char[] chars = sb.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') chars[i] = ')';
            else if (chars[i] == ')') chars[i] = '(';
        }

        String postfix = infixToPostfix(new String(chars));
        return new StringBuilder(postfix).reverse().toString();
    }
}
