package com.javarena.dsa.datastructures.graph;

/**
 * Disjoint Set Union (DSU) / Union-Find
 *
 * <p><b>Problem Statement:</b><br>
 * Data structure to efficiently track and merge disjoint sets. Supports find (which set element belongs to)
 * and union (merge two sets) operations.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Two optimizations for near-constant time:
 * 
 * 1. Path Compression in Find:
 *    - Make nodes point directly to root during find
 *    - Flattens tree structure
 * 
 * 2. Union by Rank/Size:
 *    - Attach smaller tree under larger tree
 *    - Keeps tree balanced
 * 
 * Operations:
 * - Find: Get root with path compression
 * - Union: Merge sets by rank or size
 * - Connected: Check if same root
 *
 * <p><b>Time Complexity:</b> O(α(N)) amortized per operation (α = inverse Ackermann, practically constant)
 * <br><b>Space Complexity:</b> O(N) for parent, rank, and size arrays
 */
public class DisjointSet {
    private int[] parent;
    private int[] rank; // For union by rank
    private int[] size; // For union by size
    private boolean useRank; // Toggle between rank and size

    /**
     * Initializes Disjoint Set with n elements.
     */
    public DisjointSet(int n, boolean useRank) {
        this.useRank = useRank;
        parent = new int[n];
        rank = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    /**
     * Finds the root of the set containing x with path compression.
     *
     * @TimeComplexity Amortized O(α(n))
     */
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    /**
     * Merges the sets containing x and y.
     *
     * @TimeComplexity Amortized O(α(n))
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (useRank) {
                // Union by rank
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            } else {
                // Union by size
                if (size[rootX] < size[rootY]) {
                    parent[rootX] = rootY;
                    size[rootY] += size[rootX];
                } else {
                    parent[rootY] = rootX;
                    size[rootX] += size[rootY];
                }
            }
        }
    }

    /**
     * Checks if x and y are in the same set.
     *
     * @TimeComplexity Amortized O(α(n))
     */
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

}
