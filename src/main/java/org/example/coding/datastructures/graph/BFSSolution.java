package org.example.coding.datastructures.graph;

import java.util.ArrayList;
import java.util.List;

public class BFSSolution {

    private static final GraphAlgorithms graphAlgorithms = new GraphAlgorithms();

    /**
     * "Number of Provinces" problem using BFS traversal.
     *
     * <p><b>Intuition:</b>
     * The problem reduces to counting the number of connected components in an undirected graph.
     * Each node is a city, and a connection implies an edge. We perform BFS from unvisited nodes,
     * counting how many times we initiate BFS — that’s the number of provinces.
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
                graphAlgorithms.bfs(adj, i, visited, res);
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
