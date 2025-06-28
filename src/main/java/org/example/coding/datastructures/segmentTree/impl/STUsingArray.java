package org.example.coding.datastructures.segmentTree.impl;

/**
 * A Segment Tree implementation using an array for efficient range sum queries and point updates.
 * This class supports building a Segment Tree from an input array, querying the sum of a range [L, R],
 * and updating a single element in the array.
 *
 * <p><b>Space Complexity</b>: O(n), where n is the size of the input array. The Segment Tree array requires approximately 4n space.</p>
 * <p><b>Time Complexity</b>:
 * <ul>
 *   <li><b>Construction</b>: O(n) to build the tree.</li>
 *   <li><b>Query</b>: O(log n) for range sum queries.</li>
 *   <li><b>Update</b>: O(log n) for point updates.</li>
 * </ul></p>
 */
public class STUsingArray {
    private final int n; // Size of the input array
    private final int[] ST; // Segment Tree array
    private final int[] arr; // Input array reference

    /**
     * Constructs a Segment Tree for the given array.
     *
     * @param arr The input array to build the Segment Tree from.
     * @throws IllegalArgumentException if the input array is null or empty.
     * @TimeComplexity O(n) to build the tree recursively.
     * @SpaceComplexity O(n) for the Segment Tree array (4n space).
     */
    public STUsingArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty");
        }
        this.arr = arr;
        this.n = arr.length;
        this.ST = new int[4 * n]; // Allocate space for Segment Tree (4*n is sufficient)
        buildTree(arr, 0, 0, n - 1);
    }

    /**
     * Updates the value at the specified index in the array and propagates the change to the Segment Tree.
     *
     * @param idx   The index of the element to update (0-based).
     * @param value The new value for the element.
     * @throws IndexOutOfBoundsException if idx is out of bounds.
     * @TimeComplexity O(log n) to update along the tree path.
     */
    public void updateTree(int idx, int value) {
        if (idx < 0 || idx >= n) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + idx);
        }
        updateTree(0, 0, n - 1, idx, value);
    }

    /**
     * Queries the sum of elements in the range [queryL, queryR] (inclusive).
     *
     * @param queryL The left bound of the query range (0-based).
     * @param queryR The right bound of the query range (0-based).
     * @return The sum of elements in the range [queryL, queryR].
     * @throws IllegalArgumentException if queryL > queryR or bounds are invalid.
     * @TimeComplexity O(log n) to traverse the tree for the query.
     */
    public int query(int queryL, int queryR) {
        if (queryL > queryR || queryL < 0 || queryR >= n) {
            throw new IllegalArgumentException("Invalid query range: [" + queryL + ", " + queryR + "]");
        }
        return query(0, 0, n - 1, queryL, queryR);
    }

    /**
     * Builds the Segment Tree recursively for the given range [L, R].
     *
     * @param arr  The input array.
     * @param node The current node index in the Segment Tree array.
     * @param L    The left bound of the current segment.
     * @param R    The right bound of the current segment.
     * @TimeComplexity O(n) for the entire tree construction.
     */
    private void buildTree(int[] arr, int node, int L, int R) {
        if (L == R) {
            ST[node] = arr[L];
        } else {
            int mid = (L + R) / 2;
            buildTree(arr, 2 * node + 1, L, mid);
            buildTree(arr, 2 * node + 2, mid + 1, R);
            ST[node] = ST[2 * node + 1] + ST[2 * node + 2];
        }
    }

    /**
     * Updates the Segment Tree recursively for the specified index and value.
     *
     * @param node  The current node index in the Segment Tree array.
     * @param L     The left bound of the current segment.
     * @param R     The right bound of the current segment.
     * @param idx   The index of the element to update (0-based).
     * @param value The new value for the element.
     * @TimeComplexity O(log n) to update along the tree path.
     */
    private void updateTree(int node, int L, int R, int idx, int value) {
        if (L == R) {
            arr[idx] = value;
            ST[node] = value;
        } else {
            int mid = (L + R) / 2;
            if (L <= idx && idx <= mid) {
                updateTree(2 * node + 1, L, mid, idx, value);
            } else {
                updateTree(2 * node + 2, mid + 1, R, idx, value);
            }
            ST[node] = ST[2 * node + 1] + ST[2 * node + 2];
        }
    }

    /**
     * Queries the sum of elements in the range [queryL, queryR] recursively.
     *
     * @param node   The current node index in the Segment Tree array.
     * @param L      The left bound of the current segment.
     * @param R      The right bound of the current segment.
     * @param queryL The left bound of the query range (0-based).
     * @param queryR The right bound of the query range (0-based).
     * @return The sum of elements in the overlapping range.
     * @TimeComplexity O(log n) to traverse the tree for the query.
     */
    private int query(int node, int L, int R, int queryL, int queryR) {
        if (L > queryR || R < queryL) {
            return 0;
        }
        if (queryL <= L && R <= queryR) {
            return ST[node];
        }
        int mid = (L + R) / 2;
        return query(node * 2 + 1, L, mid, queryL, queryR) + query(node * 2 + 2, mid + 1, R, queryL, queryR);
    }
}