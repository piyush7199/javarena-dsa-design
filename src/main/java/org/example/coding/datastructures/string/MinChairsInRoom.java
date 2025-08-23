package org.example.coding.datastructures.string;

public class MinChairsInRoom {
    public int minimumChairs(String s) {
        int ans = 0;
        int cnt = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'E') {
                cnt++;
            } else {
                cnt--;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
