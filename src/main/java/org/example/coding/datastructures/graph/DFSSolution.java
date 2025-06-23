package org.example.coding.datastructures.graph;

import java.util.ArrayList;
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

}
