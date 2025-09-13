package org.example.coding.datastructures.string;

public class BinaryString {
    public boolean queryString(String s, int n) {

        for (int i = 1; i <= n; i++) {
            String str = binary(i);
            int a = s.indexOf(str);
            if (a == -1) return false;
        }
        return true;
    }

    private String binary(int n) {

        StringBuilder s = new StringBuilder();

        while (n > 0) {
            int r = n % 2;
            s.insert(0, r);
            n = n / 2;
        }
        return s.toString();
    }
}
