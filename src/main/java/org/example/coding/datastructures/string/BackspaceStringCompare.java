package org.example.coding.datastructures.string;

public class BackspaceStringCompare {
    public boolean backspaceCompare(String s, String t) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == '#') {
                if (!sb.isEmpty()) {

                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(ch);
            }
        }

        String s1 = sb.toString();
        sb = new StringBuilder();
        for (char ch : t.toCharArray()) {
            if (ch == '#') {
                if (!sb.isEmpty()) {

                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(ch);
            }
        }

        String t1 = sb.toString();
        return s1.equals(t1);

    }
}
