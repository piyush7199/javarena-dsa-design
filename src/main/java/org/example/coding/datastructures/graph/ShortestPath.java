package org.example.coding.datastructures.graph;

import java.util.*;

public class ShortestPath {
    static class Pair {
        int first;
        int sec;

        public Pair(int first, int sec) {
            this.first = first;
            this.sec = sec;
        }
    }

    /**
     * Computes the shortest path from a source node to all other nodes in an unweighted undirected graph.
     * <p>
     * Intuition:
     * - Since all edges have equal weight (unweighted), we can use Breadth-First Search (BFS).
     * - The first time we reach a node will always be through the shortest path.
     * - We use a queue to perform level-wise traversal and maintain visited states and distance.
     * <p>
     * Time Complexity: O(V + E), where V = number of vertices and E = number of edges
     * Space Complexity: O(V) for visited array and distance array
     */
    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        // code here
        int n = adj.size();
        boolean vis[] = new boolean[n];
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(src, 0));
        vis[src] = true;
        dis[src] = 0;
        while (!q.isEmpty()) {
            int node = q.peek().first;
            int level = q.peek().sec;
            q.poll();
            dis[node] = Math.min(dis[node], level);
            for (int ele : adj.get(node)) {
                if (!vis[ele]) {
                    vis[ele] = true;
                    q.offer(new Pair(ele, level + 1));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (dis[i] == Integer.MAX_VALUE) dis[i] = -1;
        }
        return dis;
    }

    /**
     * Computes the shortest path from the source (node 0) to all other vertices in a Directed Acyclic Graph (DAG).
     * <p>
     * Intuition:
     * - Since the graph is a DAG, we can perform a **topological sort** to linearize the graph.
     * - We then **relax** each vertex in topological order to compute the shortest distance.
     * <p>
     * Time Complexity: O(V + E), where V = number of vertices, E = number of edges
     * Space Complexity: O(V + E) for adjacency list and auxiliary structures
     */
    public int[] shortestPath(int V, int E, int[][] edges) {
        // Code here
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] row : edges) {
            int u = row[0];
            int v = row[1];
            int wt = row[2];
            adj.get(u).add(new Pair(v, wt));
        }

        boolean[] vis = new boolean[V];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                topoSort(adj, i, vis, st);
            }
        }

        int[] dis = new int[V];
        Arrays.fill(dis, (int) (1e9));
        dis[0] = 0;

        while (!st.isEmpty()) {
            int node = st.pop();
            for (Pair p : adj.get(node)) {
                int v = p.first;
                int wt = p.sec;
                dis[v] = Math.min(dis[v], dis[node] + wt);
            }
        }
        for (int i = 0; i < V; i++) {
            if (dis[i] >= (int) (1e9)) {
                dis[i] = -1;
            }
        }
        return dis;
    }

    private void topoSort(ArrayList<ArrayList<Pair>> adj, int node, boolean[] vis, Stack<Integer> st) {
        vis[node] = true;
        for (Pair ele : adj.get(node)) {
            if (!vis[ele.first]) {
                topoSort(adj, ele.first, vis, st);
            }
        }
        st.push(node);
    }

    /**
     * Returns the length of the shortest path from top-left to bottom-right in a binary matrix.
     * Movement is allowed in 8 directions and only on 0s.
     * <p>
     * Intuition:
     * - This is a classic **multi-source BFS** where we expand in all 8 directions.
     * - At each level, we update the minimum steps needed to reach a cell.
     * - We use a distance matrix to keep track of the shortest distance from the source.
     * <p>
     * Time Complexity: O(N^2) for an N x N grid
     * Space Complexity: O(N^2) for visited array and queue
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) return -1;
        int n = grid.length;
        int[][] vis = new int[n][n];
        for (int row[] : vis) {
            Arrays.fill(row, (int) 1e9);
        }
        int level = 0;
        vis[0][0] = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        while (!q.isEmpty()) {
            int row[] = q.poll();
            int u = row[0];
            int v = row[1];
            int dis = vis[u][v];
            if (u == n - 1 && v == n - 1) return dis;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nRow = u + i;
                    int nCol = v + j;
                    if (nRow >= 0 && nCol >= 0 && nRow < n && nCol < n && grid[nRow][nCol] == 0 && dis + 1 < vis[nRow][nCol]) {
                        vis[nRow][nCol] = dis + 1;
                        q.offer(new int[]{nRow, nCol});
                    }
                }
            }
        }
        return -1;
    }
}
