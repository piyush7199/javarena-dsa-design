package org.example.coding.datastructures.graph;

public class DisjointSet {
    private int[] parent;
    private int[] rank; // For union by rank
    private int[] size; // For union by size
    private boolean useRank; // Toggle between rank and size

    /**
     * Initializes a Disjoint Set with n elements.
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
