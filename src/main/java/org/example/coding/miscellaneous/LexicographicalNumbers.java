package org.example.coding.miscellaneous;

import java.util.ArrayList;
import java.util.List;

public class LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        int curr = 1;
        for (int i = 0; i < n; i++) {
            res.add(curr);
            if (curr * 10 <= n) {
                curr *= 10; // go deeper
            } else {
                while (curr % 10 == 9 || curr + 1 > n) {
                    curr /= 10; // go up
                }
                curr++; // go to next
            }
        }
        return res;
    }
}
