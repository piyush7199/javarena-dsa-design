package org.example.coding.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateBinaryString {
    public List<String> validStrings(int n) {
        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        helper(sb, n, ans);
        return ans;
    }

    private void helper(StringBuilder sb, int n, List<String> ans) {
        if (n == 0) {
            ans.add(sb.toString());
            return;
        }

        sb.append("1");
        helper(sb, n - 1, ans);
        sb.deleteCharAt(sb.length() - 1);
        if (sb.isEmpty() || sb.charAt(sb.length() - 1) != '0') {
            sb.append("0");
            helper(sb, n - 1, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
