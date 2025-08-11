package org.example.coding.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

public class NoOfConnectedComponent {
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] vis = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                ans++;
                dfs(i, graph, vis);
            }
        }
        return ans;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] vis) {
        vis[node] = true;
        for (int ele : graph.get(node)) {
            if (!vis[ele]) {
                dfs(ele, graph, vis);
            }
        }
    }
}
