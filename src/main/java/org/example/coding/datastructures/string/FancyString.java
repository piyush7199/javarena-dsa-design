package org.example.coding.datastructures.string;

public class FancyString {
    public String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        int cnt = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cnt++;
            } else {
                cnt = 1;
            }
            if (cnt < 3) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
