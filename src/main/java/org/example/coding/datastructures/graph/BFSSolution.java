package org.example.coding.datastructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    /**
     * Uses BFS to simulate the rotting process of oranges in a grid.
     *
     * <p>Each minute, a rotten orange can rot adjacent fresh oranges (up, down, left, right).
     * This method returns the minimum time required to rot all fresh oranges, or -1 if not possible.
     *
     * <p><b>Time Complexity:</b> O(n * m) <br>
     * <b>Space Complexity:</b> O(n * m)
     */
    public int orangesRotting(int[][] grid) {
        int freshOrange = 0;
        Queue<Pair> queue = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new Pair(i, j, 0));
                    visited[i][j] = true;
                } else if (grid[i][j] == 1) {
                    freshOrange++;
                }
            }
        }
        int ans = 0;
        int[] nRow = {-1, 0, 1, 0};
        int[] nCol = {0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int row = queue.peek().row;
            int col = queue.peek().col;
            int time = queue.peek().value;
            ans = Math.max(ans, time);
            queue.poll();
            for (int i = 0; i < 4; i++) {
                int r = row + nRow[i];
                int c = col + nCol[i];
                if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1 && !visited[r][c]) {
                    freshOrange--;
                    visited[r][c] = true;
                    queue.offer(new Pair(r, c, time + 1));
                }
            }
        }
        return freshOrange != 0 ? -1 : ans;
    }

    /**
     * Performs a BFS-based flood fill starting from a given pixel.
     *
     * <p>Recolors the starting pixel and all connected pixels (with the same color)
     * to a new target color using a BFS traversal.
     *
     * <p><b>Time Complexity:</b> O(n * m) <br>
     * <b>Space Complexity:</b> O(n * m)
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] nRow = {-1, 0, 1, 0};
        int[] nCol = {0, 1, 0, -1};

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(sr, sc, image[sr][sc]));
        while (!queue.isEmpty()) {
            int row = queue.peek().row;
            int col = queue.peek().col;
            int oldColor = queue.peek().value;
            queue.poll();
            image[row][col] = color;
            for (int i = 0; i < 4; i++) {
                int r = row + nRow[i];
                int c = col + nCol[i];
                if (r >= 0 && c >= 0 && r < n && c < m && image[r][c] == oldColor && !visited[r][c]) {
                    visited[r][c] = true;
                    queue.offer(new Pair(r, c, oldColor));
                }
            }
        }
        return image;
    }

    /**
     * Detects if there is a cycle in an undirected graph using Breadth-First Search (BFS).
     *
     * <p><b>Intuition:</b></p>
     * - In BFS traversal, we track the parent of each visited node. <br>
     * - If we encounter a visited neighbor that is not the parent, a cycle exists.<br>
     * - BFS helps explore level by level, so using a queue ensures we check all adjacent nodes fairly.
     *
     * <p><b>Time Complexity:</b> O(V + E) <br>
     * - We visit each node and edge once.
     * <p>
     * <b>Space Complexity:</b> O(V + E) <br>
     * - Adjacency list stores all edges: O(V + E) <br>
     * - Visited array: O(V) <br>
     * - Queue for BFS: O(V)
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
                if (isCycleUtils(i, adj, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCycleUtils(int src, List<List<Integer>> adj, boolean[] visited) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(src, -1));
        visited[src] = true;
        while (!queue.isEmpty()) {
            int node = queue.peek().row;
            int parent = queue.peek().col;

            queue.poll();
            for (int adjNode : adj.get(node)) {
                if (!visited[adjNode]) {
                    visited[adjNode] = true;
                    queue.add(new Pair(adjNode, node));
                } else if (parent != adjNode) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Performs topological sorting of a Directed Acyclic Graph (DAG) using Kahn's Algorithm (BFS-based).
     * <p>
     * Intuition: <br>
     * - Kahn's Algorithm uses in-degree to identify nodes with no incoming edges. <br>
     * - Start with all nodes with in-degree 0 and add them to the queue. <br>
     * - Remove them one by one, reducing the in-degree of their neighbors. <br>
     * - When a neighbor’s in-degree becomes 0, add it to the queue. <br>
     * - This gives a valid linear ordering of the vertices (topological order). <br>
     * <p>
     * Time Complexity: O(V + E) <br>
     * - V: Number of vertices <br>
     * - E: Number of edges (each edge is processed once)
     * <p>
     * Space Complexity: O(V + E) <br>
     * - Adjacency list: O(V + E) <br>
     * - Queue and visited array: O(V)
     */
    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        int[] inDegree = new int[V];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0 && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    public int[][] updateMatrix(int[][] mat) {
        class Pair {
            int row;
            int col;
            int dis;

            public Pair(int row, int col, int dis) {
                this.row = row;
                this.col = col;
                this.dis = dis;
            }
        }

        int n = mat.length;
        int m = mat[0].length;
        int[][] visited = new int[n][m];
        int[][] dis = new int[n][m];
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    visited[i][j] = 1;
                    q.add(new Pair(i, j, 0));
                }
            }
        }
        int rows[] = {-1, 0, 1, 0};
        int cols[] = {0, 1, 0, -1};
        while (!q.isEmpty()) {
            int r = q.peek().row;
            int c = q.peek().col;
            int d = q.peek().dis;
            q.poll();
            dis[r][c] = d;
            for (int i = 0; i < 4; i++) {
                int nrow = r + rows[i];
                int ncol = c + cols[i];
                if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m && visited[nrow][ncol] == 0) {
                    visited[nrow][ncol] = 1;
                    q.add(new Pair(nrow, ncol, d + 1));
                }
            }
        }
        return dis;
    }

}
