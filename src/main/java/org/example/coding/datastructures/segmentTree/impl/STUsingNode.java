package org.example.coding.datastructures.segmentTree.impl;

/**
 * A Segment Tree implementation using a node-based tree structure for range sum queries and point updates.
 * This class supports building a Segment Tree from an input array, querying the sum of a range [start, end],
 * and updating a single element in the array.
 *
 * <p><b>Space Complexity</b>: O(n), where n is the size of the input array. The tree has approximately 2n-1 nodes.</p>
 * <p><b>Time Complexity</b>:
 * <ul>
 *   <li><b>Construction</b>: O(n) to build the tree.</li>
 *   <li><b>Query</b>: O(log n) for range sum queries.</li>
 *   <li><b>Update</b>: O(log n) for point updates.</li>
 * </ul></p>
 */
public class STUsingNode {
    /**
     * Node class representing a segment in the Segment Tree.
     */
    static class Node {
        int startInterval; // Start of the segment interval
        int endInterval;   // End of the segment interval
        int data;          // Sum of the segment
        Node left;         // Left child node
        Node right;        // Right child node

        /**
         * Constructs a Node with the specified interval.
         *
         * @param startInterval The start of the segment interval.
         * @param endInterval   The end of the segment interval.
         */
        public Node(int startInterval, int endInterval) {
            this.startInterval = startInterval;
            this.endInterval = endInterval;
        }
    }

    private final Node root; // Root of the Segment Tree
    private final int[] arr; // Input array reference
    private final int n;     // Size of the input array

    /**
     * Constructs a Segment Tree for the given array.
     *
     * @param arr The input array to build the Segment Tree from.
     * @param n   The size of the input array.
     * @throws IllegalArgumentException if the input array is null or empty, or n is invalid.
     * @TimeComplexity O(n) to build the tree recursively.
     * @SpaceComplexity O(n) for the node-based tree (approximately 2n-1 nodes).
     */
    public STUsingNode(int[] arr, int n) {
        if (arr == null || n <= 0 || arr.length < n) {
            throw new IllegalArgumentException("Invalid input array or size");
        }
        this.arr = arr;
        this.n = n;
        this.root = buildTree(0, n - 1);
    }

    /**
     * Builds the Segment Tree recursively for the given interval.
     *
     * @param startInterval The start of the current interval.
     * @param endInterval   The end of the current interval.
     * @return The root node of the subtree for the interval.
     * @TimeComplexity O(n) for the entire tree construction.
     */
    private Node buildTree(int startInterval, int endInterval) {
        if (startInterval == endInterval) {
            Node leaf = new Node(startInterval, endInterval);
            leaf.data = arr[startInterval];
            return leaf;
        }
        Node node = new Node(startInterval, endInterval);
        int mid = (startInterval + endInterval) / 2;
        node.left = buildTree(startInterval, mid);
        node.right = buildTree(mid + 1, endInterval);
        node.data = node.left.data + node.right.data;
        return node;
    }

    /**
     * Updates the value at the specified index in the array and propagates the change to the Segment Tree.
     *
     * @param idx The index of the element to update (0-based).
     * @param val The new value for the element.
     * @throws IndexOutOfBoundsException if idx is out of bounds.
     * @TimeComplexity O(log n) to update along the tree path.
     */
    public void update(int idx, int val) {
        if (idx < 0 || idx >= n) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + idx);
        }
        updateTree(root, idx, val);
    }

    /**
     * Updates the Segment Tree recursively for the specified index and value.
     *
     * @param node  The current node in the Segment Tree.
     * @param idx   The index of the element to update (0-based).
     * @param value The new value for the element.
     * @return The updated sum for the current node.
     * @TimeComplexity O(log n) to update along the tree path.
     */
    private int updateTree(Node node, int idx, int value) {
        if (idx >= node.startInterval && idx <= node.endInterval) {
            if (idx == node.startInterval && idx == node.endInterval) {
                node.data = value;
                arr[idx] = value;
                return node.data;
            } else {
                int left = updateTree(node.left, idx, value);
                int right = updateTree(node.right, idx, value);
                node.data = left + right;
                return node.data;
            }
        }
        return node.data;
    }

    /**
     * Queries the sum of elements in the range [queryStart, queryEnd] (inclusive).
     *
     * @param queryStart The start of the query range (0-based).
     * @param queryEnd   The end of the query range (0-based).
     * @return The sum of elements in the range [queryStart, queryEnd].
     * @throws IllegalArgumentException if queryStart > queryEnd or bounds are invalid.
     * @TimeComplexity O(log n) to traverse the tree for the query.
     */
    public int query(int queryStart, int queryEnd) {
        if (queryStart > queryEnd || queryStart < 0 || queryEnd >= n) {
            throw new IllegalArgumentException("Invalid query range: [" + queryStart + ", " + queryEnd + "]");
        }
        return query(root, queryStart, queryEnd);
    }

    /**
     * Queries the sum of elements in the range [qs, qe] recursively.
     *
     * @param node The current node in the Segment Tree.
     * @param qs   The start of the query range (0-based).
     * @param qe   The end of the query range (0-based).
     * @return The sum of elements in the overlapping range.
     * @TimeComplexity O(log n) to traverse the tree for the query.
     */
    private int query(Node node, int qs, int qe) {
        if (node.startInterval >= qs && node.endInterval <= qe) {
            return node.data;
        } else if (node.startInterval > qe || node.endInterval < qs) {
            return 0;
        }
        return query(node.left, qs, qe) + query(node.right, qs, qe);
    }
}