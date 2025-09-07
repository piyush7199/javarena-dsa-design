package org.example.coding.datastructures.string;

import java.util.ArrayList;
import java.util.List;

public class EncodeAndDecode {
    public String encode(String strs[]) {
        StringBuilder ans = new StringBuilder();
        for (String str : strs) {
            ans.append(str.length()).append("#").append(str);
        }
        return ans.toString();
    }

    public String[] decode(String str) {
        int i = 0;
        int n = str.length();
        List<String> ans = new ArrayList<>();
        while (i < n) {
            StringBuilder numString = new StringBuilder();
            while (str.charAt(i) != '#') {
                numString.append(str.charAt(i));
                i++;
            }
            i++;
            int len = Integer.parseInt(numString.toString());
            ans.add(str.substring(i, i + len));
            i = i + len;
        }
        String[] res = new String[ans.size()];
        return ans.toArray(res);
    }
}
