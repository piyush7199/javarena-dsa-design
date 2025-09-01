package org.example.coding.algorithms.greedy;

public class MaxScoreFromRemovingString {
    public int maximumGain(String s, int x, int y) {
        if (x > y) {
            return remove(s, "ab", x) + removeRemaining(s, "ba", y, "ab", x);
        } else {
            return remove(s, "ba", y) + removeRemaining(s, "ab", x, "ba", y);
        }
    }

    private int remove(String s, String target, int score) {
        StringBuilder sb = new StringBuilder();
        int total = 0;

        for (char c : s.toCharArray()) {
            sb.append(c);
            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 2) == target.charAt(0) && sb.charAt(len - 1) == target.charAt(1)) {
                sb.delete(sb.length() - 2, sb.length());
                total += score;
            }
        }
        return total;
    }

    private int removeRemaining(String s, String second, int score2, String first, int score1) {
        // Remove first type greedily
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c);
            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 2) == first.charAt(0) && sb.charAt(len - 1) == first.charAt(1)) {
                sb.delete(sb.length() - 2, sb.length());
            }
        }

        // Now remove second type
        int total = 0;
        StringBuilder sb2 = new StringBuilder();
        for (char c : sb.toString().toCharArray()) {
            sb2.append(c);
            int len = sb2.length();
            if (len >= 2 && sb2.charAt(len - 2) == second.charAt(0) && sb2.charAt(len - 1) == second.charAt(1)) {
                sb2.delete(sb2.length() - 2, sb2.length());
                total += score2;
            }
        }
        return total;
    }
}
