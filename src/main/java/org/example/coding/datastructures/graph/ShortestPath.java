package org.example.coding.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPath {
    static class Pair {
        int node;
        int level;

        public Pair(int node, int level) {
            this.node = node;
            this.level = level;
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
            int node = q.peek().node;
            int level = q.peek().level;
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
}
