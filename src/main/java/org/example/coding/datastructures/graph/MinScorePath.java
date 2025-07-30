package org.example.coding.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

public class MinScorePath {

    /**
     * Disjoint Set Union (Union-Find) based solution to find the minimum score
     * (edge weight) in the connected component that includes node n (or node 1).
     *
     * <p><b>Intuition:</b> Use Union-Find to group connected cities and track
     * the minimum score (edge weight) within each component during union.
     *
     * <p><b>Approach:</b>
     * <ul>
     *   <li>Initialize Union-Find with `n` nodes and set initial rank as Integer.MAX_VALUE.</li>
     *   <li>For every road [u, v, score], union u and v and update the min score in that set.</li>
     *   <li>Return the minimum score in the connected component of node n (or node 1).</li>
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(N + E * α(N)) – where α is the inverse Ackermann function (almost constant).</p>
     * <p><b>Space Complexity:</b> O(N)</p>
     */
    public int minScoreUsingDisjointSet(int n, int[][] roads) {
        Dsjoin ds = new Dsjoin(n);
        for (int[] road : roads) {
            ds.union(road[0] - 1, road[1] - 1, road[2]);
        }

        return ds.getMin(0); // 0-based index for city 1
    }

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
     * DFS-based approach to find the minimum score (edge weight) in the
     * connected component containing node 1.
     *
     * <p><b>Intuition:</b> Traverse all reachable cities from city 1 and keep track
     * of the minimum edge encountered during DFS.
     *
     * <p><b>Approach:</b>
     * <ul>
     *   <li>Build an undirected graph with edges and weights.</li>
     *   <li>Use DFS to explore all cities reachable from node 1, maintaining
     *       the minimum distance (score) found.</li>
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(N + E) – for DFS traversal.</p>
     * <p><b>Space Complexity:</b> O(N + E) – for graph representation and visited array.</p>
     */
    public int minScore(int n, int[][] roads) {
        List<List<int[]>> gr = new ArrayList<>();
        for (int i = 0; i <= n; i++) gr.add(new ArrayList<>());

        for (int[] edge : roads) {
            gr.get(edge[0]).add(new int[]{edge[1], edge[2]});
            gr.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        boolean[] vis = new boolean[n + 1];
        return dfs(1, vis, gr, Integer.MAX_VALUE);
    }

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
