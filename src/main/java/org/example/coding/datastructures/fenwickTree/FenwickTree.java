package org.example.coding.datastructures.fenwickTree;

public class FenwickTree {
    private int[] tree;
    private int n;

    /**
     * Constructs a Fenwick Tree from an array.
     *
     * @param arr Input array
     * @TimeComplexity O(n log n)
     * @SpaceComplexity O(n)
     */
    public FenwickTree(int[] arr) {
        n = arr.length;
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            update(i + 1, arr[i]);
        }
    }

    /**
     * Updates the value at index with delta.
     *
     * @param index 1-based index
     * @param delta Value to add
     * @TimeComplexity O(log n)
     * @SpaceComplexity O(1)
     */
    public void update(int index, int delta) {
        while (index <= n) {
            tree[index] += delta;
            index += index & (-index);
        }
    }

    /**
     * Computes prefix sum from index 1 to index.
     *
     * @param index 1-based index
     * @return Prefix sum
     * @TimeComplexity O(log n)
     * @SpaceComplexity O(1)
     */
    public int prefixSum(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & (-index);
        }
        return sum;
    }

    /**
     * Computes range sum from left to right.
     *
     * @param left  1-based left index
     * @param right 1-based right index
     * @return Range sum
     * @TimeComplexity O(log n)
     * @SpaceComplexity O(1)
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
        ft.update(3, 10); // Add 10 to element at index 3 (original 3 becomes 13)
        System.out.println("Range sum from 2 to 4 after update: " + ft.rangeSum(2, 4)); // 2+13+4 = 19

        // Test edge cases
        System.out.println("Range sum invalid range: " + ft.rangeSum(0, 6)); // 0
        System.out.println("Prefix sum index 0: " + ft.prefixSum(0)); // 0
    }
}
