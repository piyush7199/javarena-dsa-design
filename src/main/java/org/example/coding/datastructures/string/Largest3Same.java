package org.example.coding.datastructures.string;

public class Largest3Same {
    public String largestGoodInteger(String num) {
        String ans = "";
        int n = num.length();
        for (int i = 1; i < n - 1; i++) {
            char a1 = num.charAt(i - 1);
            char a2 = num.charAt(i);
            char a3 = num.charAt(i + 1);
            if (a1 == a2 && a2 == a3) {
                if (ans.isEmpty() || ans.charAt(0) < a1) ans = "" + a1 + a2 + a3;
            }
        }
        return ans;
    }
}
