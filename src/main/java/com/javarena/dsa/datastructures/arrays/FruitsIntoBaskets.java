package com.javarena.dsa.datastructures.arrays;

/**
 * Number of Unplaced Fruits Into Baskets
 *
 * <p><b>Problem Statement:</b><br>
 * Given an array of fruits and an array of basket capacities, determine how many fruits cannot be placed.
 * A fruit can be placed in a basket only if its value is less than or equal to the basket's capacity.
 * Once used, the basket capacity becomes 0.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Try placing each fruit in first available basket that can hold it
 * - For each fruit, iterate through baskets to find suitable capacity
 * - If fruit[i] <= basket[j], place it and mark basket as used (capacity = 0)
 * - Count successfully placed fruits
 * - Return total fruits - placed fruits = unplaced fruits
 * - Greedy approach: first-fit strategy
 *
 * <p><b>Time Complexity:</b> O(N × M) - N fruits, M baskets, nested loops
 * <br><b>Space Complexity:</b> O(1) - Only counter variable
 */
public class FruitsIntoBaskets {
    /**
     * Counts number of fruits that cannot be placed in baskets.
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
