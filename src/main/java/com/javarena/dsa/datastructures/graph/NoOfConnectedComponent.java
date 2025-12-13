package com.javarena.dsa.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Number of Connected Components in Undirected Graph
 *
 * <p><b>Problem Statement:</b><br>
 * Given n nodes and list of undirected edges, count the number of connected components in the graph.
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Build adjacency list from edges
 * - Use DFS to explore each component
 * - For each unvisited node:
 *   - Start DFS to mark entire component as visited
 *   - Increment component counter
 * - Number of DFS initiations = number of components
 * - Classic connected components problem
 *
 * <p><b>Time Complexity:</b> O(V + E) - Visit all vertices and edges once
 * <br><b>Space Complexity:</b> O(V + E) - Adjacency list + visited array + recursion stack
 */
public class NoOfConnectedComponent {
    /**
     * Counts connected components in graph.
     */
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] vis = new boolean[n];
        int ans = 0;
        
        // Count components
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                ans++;
                dfs(i, graph, vis);
            }
        }
        return ans;
    }

    /**
     * DFS to mark all nodes in component.
     */
    private void dfs(int node, List<List<Integer>> graph, boolean[] vis) {
        vis[node] = true;
        for (int ele : graph.get(node)) {
            if (!vis[ele]) {
                dfs(ele, graph, vis);
            }
        }
    }
}
