package org.example.coding.datastructures.arrays;

public class FruitsIntoBaskets {
    /**
     * TC - O(n^2);
     */
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int i, j;
        int c = 0;
        for (i = 0; i < fruits.length; i++) {
            for (j = 0; j < baskets.length; j++) {
                if (fruits[i] <= baskets[j]) {
                    c = c + 1;
                    baskets[j] = 0;
                    break;
                }
            }
        }
        return fruits.length - c;
    }
}
