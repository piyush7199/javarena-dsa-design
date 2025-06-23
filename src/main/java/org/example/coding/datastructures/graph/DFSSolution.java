package org.example.coding.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

public class DFSSolution {
    private static final GraphAlgorithms graphAlgorithms = new GraphAlgorithms();

    /**
     * "Number of Provinces" problem using DFS traversal.
     *
     * <p><b>Intuition:</b>
     * We use DFS to explore all cities connected to a given city. For every new unvisited city,
     * we initiate a DFS and count it as a new province.
     *
     * <p><b>Time Complexity:</b> O(V + E), where V is number of cities and E is number of connections.</p>
     * <p><b>Space Complexity:</b> O(V + E) for adjacency list and visited array.</p>
     */
    public static int findCircleNum(int[][] isConnected) {
        int V = isConnected.length;
        List<List<Integer>> adj = Graph.convertMatrixToAdjList(isConnected);
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[V];
        int island = 0;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                graphAlgorithms.dfs(adj, i, visited, res);
                island++;
            }
        }
        return island;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(mat));
    }
}
