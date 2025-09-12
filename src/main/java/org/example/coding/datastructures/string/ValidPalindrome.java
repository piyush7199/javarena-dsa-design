package org.example.coding.datastructures.string;

public class ValidPalindrome {
    public boolean isPalindrome(String s1) {
        int i = 0;
        int j = s1.length() - 1;

        String s = s1.toLowerCase();
        while (i <= j) {
            char ch1 = s.charAt(i);
            char ch2 = s.charAt(j);
            if (!isValidAlph(ch1)) {
                i++;
            } else if (!isValidAlph(ch2)) {
                j--;
            } else {
                if (ch1 != ch2) return false;
                i++;
                j--;
            }

        }
        return true;
    }

    private boolean isValidAlph(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
    }
}
