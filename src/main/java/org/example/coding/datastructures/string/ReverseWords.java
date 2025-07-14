package org.example.coding.datastructures.string;

import java.util.Stack;

public class ReverseWords {

    /**
     * Reverses the words in a given string using built-in split.
     * <p>
     * Intuition:
     * - Split the input by one or more spaces (`\\s+`) to get clean words.
     * - Append them in reverse order to construct the result.
     * <p>
     * Time Complexity: O(n), where n is the length of the input string.
     * Space Complexity: O(n), for storing words and result.
     */
    public String reverseWords(String s) {
        String[] words = s.split("\\s+");
        StringBuilder res = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            res.append(words[i]);
            if (i != 0) {
                res.append(" ");
            }
        }

        return res.toString().trim();
    }

    /**
     * Reverses words using a stack to simulate reverse order.
     * <p>
     * Intuition:
     * - Traverse and build each word, push to stack on encountering space.
     * - After traversal, pop from stack to get reversed word order.
     * <p>
     * Time Complexity: O(n), where n is the length of the input.
     * Space Complexity: O(n), stack to hold all words.
     */
    public String reverseWordsUsingStack(String s) {
        Stack<String> st = new Stack<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                // To handle extra spaces
                if (temp.isEmpty()) continue;
                st.push(temp.toString());
                temp = new StringBuilder();
            } else {
                temp.append(s.charAt(i));
            }
        }

        // To handle the last word
        if (!temp.isEmpty()) st.push(temp.toString());

        StringBuilder ans = new StringBuilder();
        while (st.size() != 1) {
            ans.append(st.peek()).append(" ");
            st.pop();
        }

        // To handle last word
        ans.append(st.peek());
        return ans.toString();
    }

    /**
     * Reverses words using a two-pointer approach and string building from end.
     * <p>
     * Intuition:
     * - Traverse the string and extract words manually.
     * - Insert each word at the beginning of the result.
     * Time Complexity: O(n²) due to use of `insert(0, ...)` in StringBuilder repeatedly.
     * Space Complexity: O(n), for storing result.
     */
    public String reverseWordsUsingTwoPointer(String s) {
        int start = 0;
        int end = s.length() - 1;
        String temp = "";
        StringBuilder ans = new StringBuilder();
        while (start <= end) {
            char ch = s.charAt(start);
            if (ch != ' ') {
                temp += ch;
            } else {
                if (!temp.isEmpty()) {
                    if (!ans.isEmpty()) {
                        ans.insert(0, temp + " ");
                    } else {
                        ans = new StringBuilder(temp);
                    }
                }
                temp = "";
            }
            start++;
        }

        if (!temp.isEmpty()) {
            if (!ans.isEmpty()) {
                ans.insert(0, temp + " ");
            } else {
                ans = new StringBuilder(temp);
            }
        }

        return ans.toString();
    }
}
