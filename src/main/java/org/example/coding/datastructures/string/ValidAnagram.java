package org.example.coding.datastructures.string;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int n = s.length();
        int[] freq = new int[26];
        for(int i = 0;i<n;i++) {
            freq[s.charAt(i)-'a']++;
            freq[t.charAt(i)-'a']--;
        }

        for(int ele : freq) {
            if(ele != 0) return false;
        }
        return true;
    }
}
