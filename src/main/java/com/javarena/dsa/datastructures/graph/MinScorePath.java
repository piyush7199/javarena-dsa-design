package com.javarena.dsa.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Minimum Score of a Path Between Two Cities
 *
 * <p><b>Problem Statement:</b><br>
 * Given n cities and roads [u, v, score], find minimum score of any path from city 1 to city n.
 * Score of path = minimum edge weight in that path.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Key insight: If cities are connected, answer = minimum edge in entire connected component.
 * 
 * Two approaches:
 * 
 * 1. DFS Approach:
 *    - Build undirected graph
 *    - DFS from city 1 to explore all reachable cities
 *    - Track minimum edge weight encountered
 *    - Return minimum across all edges in component
 * 
 * 2. Union-Find (DSU) Approach:
 *    - Use DSU to track connected components
 *    - During union, maintain minimum edge in each component
 *    - Query minimum for component containing city 1
 *    - More efficient for multiple queries
 *
 * <p><b>Time Complexity:</b> O(V + E) for DFS, O(E × α(V)) for DSU
 * <br><b>Space Complexity:</b> O(V + E) for graph, O(V) for DSU
 */
public class MinScorePath {

    /**
     * Finds minimum score using Union-Find.
     */
    public int minScoreUsingDisjointSet(int n, int[][] roads) {
        Dsjoin ds = new Dsjoin(n);
        for (int[] road : roads) {
            ds.union(road[0] - 1, road[1] - 1, road[2]);
        }
        return ds.getMin(0); // 0-based index for city 1
    }

    /**
     * Disjoint Set Union with minimum tracking.
     */
    static class Dsjoin {
        int[] parent;
        int[] rank;

        public Dsjoin(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = Integer.MAX_VALUE;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y, int dis) {
            int rootx = find(x);
            int rooty = find(y);

            if (rootx == rooty) {
                rank[rootx] = Math.min(rank[rootx], dis);
                return;
            }

            int newMin = Math.min(rank[rootx], Math.min(rank[rooty], dis));
            parent[rootx] = rooty;
            rank[rooty] = newMin;
        }

        public int getMin(int x) {
            return rank[find(x)];
        }
    }

    /**
     * Finds minimum score using DFS.
     */
    public int minScore(int n, int[][] roads) {
        List<List<int[]>> gr = new ArrayList<>();
        for (int i = 0; i <= n; i++) gr.add(new ArrayList<>());

        // Build undirected graph
        for (int[] edge : roads) {
            gr.get(edge[0]).add(new int[]{edge[1], edge[2]});
            gr.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        boolean[] vis = new boolean[n + 1];
        return dfs(1, vis, gr, Integer.MAX_VALUE);
    }

    /**
     * DFS to find minimum edge in connected component.
     */
    public int dfs(int src, boolean[] vis, List<List<int[]>> gr, int currentMin) {
        vis[src] = true;

        for (int[] nbr : gr.get(src)) {
            int nextNode = nbr[0];
            int dist = nbr[1];

            currentMin = Math.min(currentMin, dist);

            if (!vis[nextNode]) {
                currentMin = dfs(nextNode, vis, gr, currentMin);
            }
        }

        return currentMin;
    }
}
