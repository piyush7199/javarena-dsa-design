package org.example.coding.datastructures.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor != newColor) {
            dfsFill(image, sr, sc, oldColor, newColor);
        }
        return image;
    }

    private void dfsFill(int[][] image, int row, int col, int oldColor, int newColor) {
        int n = image.length;
        int m = image[0].length;

        if (row < 0 || col < 0 || row >= n || col >= m || image[row][col] != oldColor) {
            return;
        }

        image[row][col] = newColor;

        dfsFill(image, row - 1, col, oldColor, newColor); // up
        dfsFill(image, row + 1, col, oldColor, newColor); // down
        dfsFill(image, row, col - 1, oldColor, newColor); // left
        dfsFill(image, row, col + 1, oldColor, newColor); // right
    }

    /**
     * Performs a DFS-based flood fill starting from a given pixel.
     *
     * <p>This method is an alternative to the BFS flood fill, using an explicit stack (DFS)
     * to recolor the starting pixel and all connected pixels of the same color.
     *
     * <p><b>Time Complexity:</b> O(n * m) <br>
     * <b>Space Complexity:</b> O(n * m)
     */
    public int[][] floodFill2(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] nRow = {-1, 0, 1, 0};
        int[] nCol = {0, 1, 0, -1};

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(sr, sc, image[sr][sc]));
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            int row = pair.row;
            int col = pair.col;
            int oldColor = pair.value;
            image[row][col] = color;
            for (int i = 0; i < 4; i++) {
                int r = row + nRow[i];
                int c = col + nCol[i];
                if (r >= 0 && c >= 0 && r < n && c < m && image[r][c] == oldColor && !visited[r][c]) {
                    visited[r][c] = true;
                    stack.push(new Pair(r, c, oldColor));
                }
            }
        }
        return image;
    }

    /**
     * Detects if there is a cycle in an undirected graph using Depth-First Search (DFS).
     *
     * <p><b>Intuition:</b></p>
     * - In DFS traversal, we recursively visit each node. <br>
     * - While visiting neighbors, if we encounter a visited node that is not the parent, a cycle is detected.
     *
     * <p><b>Time Complexity:</b> O(V + E) <br>
     * - Each node and edge is visited once in DFS.
     * <p>
     * <b>Space Complexity:</b> O(V + E) <br>
     * - Adjacency list stores all edges: O(V + E) <br>
     * - Visited array: O(V) <br>
     * - Recursion stack: O(V) in worst case (linear graph)
     */

    public boolean isCycle(int V, int[][] edges) {
        // Code here
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (isCycleUtils(i, adj, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCycleUtils(int node, List<List<Integer>> adj, boolean[] visited, int parent) {
        visited[node] = true;
        for (int newNode : adj.get(node)) {
            if (!visited[newNode]) {
                if (isCycleUtils(newNode, adj, visited, node)) {
                    return true;
                }
            } else if (parent != newNode) {
                return true;
            }
        }
        return false;
    }

    /**
     * Performs topological sorting of a Directed Acyclic Graph (DAG) using Depth-First Search (DFS).
     * <p>
     * 📌 Intuition: <br>
     * - Perform DFS traversal and, after visiting all descendants of a node, add it to a stack. <br>
     * - Finally, reverse the stack to get the topological order. <br>
     * - A node is added *after* all its children (post-order), ensuring prerequisites come first.
     * <p>
     * Time Complexity: O(V + E) <br>
     * - Each node and edge is visited exactly once.
     * <p>
     * Space Complexity: O(V + E) <br>
     * - Adjacency list: O(V + E) <br>
     * - Visited array and recursion stack: O(V)
     */
    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
        }

        boolean[] visited = new boolean[V];
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(adj, visited, i, result);
            }
        }

        Collections.reverse(result);  // Because DFS adds in post-order
        return result;
    }

    /**
     * Recursive DFS helper for topological sorting.
     */
    public static void dfs(List<List<Integer>> adj, boolean[] visited, int node, ArrayList<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(adj, visited, neighbor, stack);
            }
        }
        stack.add(node);
    }


}
