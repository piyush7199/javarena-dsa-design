package org.example.coding.datastructures.segmentTree;


/**
 * Segment Tree implementation for Range Minimum Query (RMQ).
 * <p>
 * Supports building the segment tree and querying the minimum value
 * in a given range [l, r].
 * <p>
 * Time Complexities:
 * - Build: O(n)
 * - Query: O(log n)
 * <p>
 * Space Complexity: O(4n)
 */

public class RangeMinimumQuery {

    // Segment tree array
    static int[] st;

    /**
     * Constructs the segment tree from the input array.
     *
     * @param arr the input array
     * @param n   the size of the input array
     * @return the constructed segment tree
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(4n)
     */
    public static int[] constructST(int[] arr, int n) {
        st = new int[4 * n];  // Allocate 4n space for segment tree
        build(arr, 0, 0, n - 1);
        return st;
    }

    /**
     * Recursively builds the segment tree.
     *
     * @param arr  the input array
     * @param node current node index in segment tree
     * @param l    left bound of current segment
     * @param r    right bound of current segment
     */
    private static void build(int[] arr, int node, int l, int r) {
        if (l == r) {
            st[node] = arr[l];  // Leaf node
            return;
        }

        int mid = (l + r) / 2;
        build(arr, 2 * node + 1, l, mid);         // Build left subtree
        build(arr, 2 * node + 2, mid + 1, r);     // Build right subtree

        // Internal node stores min of its children
        st[node] = Math.min(st[2 * node + 1], st[2 * node + 2]);
    }

    /**
     * Returns the minimum value in the range [l, r] in the array.
     *
     * @param st the segment tree array
     * @param n  the size of the original array
     * @param l  left bound of query
     * @param r  right bound of query
     * @return the minimum value in range [l, r]
     * <p>
     * Time Complexity: O(log n)
     */
    public static int RMQ(int[] st, int n, int l, int r) {
        return query(st, 0, 0, n - 1, l, r);
    }

    /**
     * Recursively queries the segment tree for the minimum in the given range.
     *
     * @param st   the segment tree array
     * @param node current node index in the segment tree
     * @param l    left bound of the current segment
     * @param r    right bound of the current segment
     * @param qs   left bound of the query range
     * @param qe   right bound of the query range
     * @return the minimum value in the range [qs, qe]
     * <p>
     * Time Complexity: O(log n)
     */
    private static int query(int[] st, int node, int l, int r, int qs, int qe) {
        // No overlap
        if (l > qe || r < qs) return Integer.MAX_VALUE;

        // Complete overlap
        if (l >= qs && r <= qe) return st[node];

        // Partial overlap
        int mid = (l + r) / 2;
        int leftMin = query(st, 2 * node + 1, l, mid, qs, qe);
        int rightMin = query(st, 2 * node + 2, mid + 1, r, qs, qe);
        return Math.min(leftMin, rightMin);
    }
}
