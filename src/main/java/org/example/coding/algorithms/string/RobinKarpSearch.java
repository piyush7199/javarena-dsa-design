package org.example.coding.algorithms.string;

public class RobinKarpSearch {
    private final int PRIME = 101;

    private double calculateHash(String str) {
        double hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash += (str.charAt(i) * Math.pow(PRIME, i));
        }
        return hash;
    }

    private double updateHash(double oldHash, char oldChar, char newChar, int patternLength) {
        double newHash = (oldHash - oldChar) / PRIME;
        newHash = newHash + newChar * Math.pow(PRIME, patternLength - 1);
        return newHash;
    }

    public int search(String text, String pattern) {
        int patternLength = pattern.length();
        int textLength = text.length();
        double textHash = calculateHash(text.substring(0, patternLength));
        double patternHash = calculateHash(pattern);

        for (int i = 0; i <= textLength - patternLength; i++) {
            if (textHash == patternHash) {
                if (text.substring(i, i + patternLength).equals(pattern)) {
                    return i;
                }
            }
            if (i < textLength - patternLength) {
                textHash = updateHash(textHash, text.charAt(i), text.charAt(i + patternLength), patternLength);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String text = "ABABDABABC";
        RobinKarpSearch rks = new RobinKarpSearch();
        System.out.println(rks.search(text, "ABABC"));
        System.out.println(rks.search(text, ""));
        System.out.println(rks.search(text, "XYZ"));
    }
}
