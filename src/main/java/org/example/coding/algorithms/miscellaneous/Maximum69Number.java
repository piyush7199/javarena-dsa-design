package org.example.coding.algorithms.miscellaneous;

public class Maximum69Number {
    public int maximum69Number(int num) {
        int currIndex = 0;
        int leftMostSixIndex = -1;

        int temp = num;
        while (temp > 0) {
            if (temp % 10 == 6)
                leftMostSixIndex = currIndex;
            temp /= 10;
            currIndex++;
        }

        if (leftMostSixIndex != -1)
            return num + (int) Math.pow(10, leftMostSixIndex) * 3;

        return num;
    }
}
