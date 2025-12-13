package com.javarena.dsa.datastructures.fenwickTree;

/**
 * Fenwick Tree (Binary Indexed Tree)
 *
 * <p><b>Problem Statement:</b><br>
 * Data structure for efficient range sum queries and point updates on an array.
 * Supports prefix sum and range sum in logarithmic time.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Fenwick Tree uses binary representation for efficient updates and queries:
 * - Each index stores cumulative sum for a range
 * - Range size determined by rightmost set bit
 * - Update: Propagate change upward using index += index & (-index)
 * - Query: Sum downward using index -= index & (-index)
 * 
 * Key operations:
 * - update(index, delta): Add delta to element at index
 * - prefixSum(index): Sum from 1 to index
 * - rangeSum(left, right): Sum from left to right
 * 
 * Uses 1-based indexing internally.
 *
 * <p><b>Time Complexity:</b> O(log N) per update/query, O(N log N) construction
 * <br><b>Space Complexity:</b> O(N) for tree array
 */
public class FenwickTree {
    private int[] tree;
    private int n;

    /**
     * Constructs Fenwick Tree from array.
     */
    public FenwickTree(int[] arr) {
        n = arr.length;
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            update(i + 1, arr[i]);
        }
    }

    /**
     * Updates value at index by adding delta.
     *
     * @param index 1-based index
     * @param delta value to add
     */
    public void update(int index, int delta) {
        while (index <= n) {
            tree[index] += delta;
            index += index & (-index); // Move to parent
        }
    }

    /**
     * Computes prefix sum from 1 to index.
     *
     * @param index 1-based index
     * @return prefix sum
     */
    public int prefixSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & (-index); // Move to previous range
        }
        return sum;
    }

    /**
     * Computes range sum from left to right (inclusive).
     *
     * @param left 1-based left index
     * @param right 1-based right index
     * @return range sum
     */
    public int rangeSum(int left, int right) {
        if (left < 1 || right > n || left > right) return 0;
        return prefixSum(right) - prefixSum(left - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        FenwickTree ft = new FenwickTree(arr);

        // Test prefix sum
        System.out.println("Prefix sum up to index 3: " + ft.prefixSum(3)); // 1+2+3 = 6

        // Test range sum
        System.out.println("Range sum from 2 to 4: " + ft.rangeSum(2, 4)); // 2+3+4 = 9

        // Test update
        ft.update(3, 10); // Add 10 to element at index 3
        System.out.println("Range sum from 2 to 4 after update: " + ft.rangeSum(2, 4)); // 2+13+4 = 19

        // Test edge cases
        System.out.println("Range sum invalid range: " + ft.rangeSum(0, 6)); // 0
        System.out.println("Prefix sum index 0: " + ft.prefixSum(0)); // 0
    }
}
