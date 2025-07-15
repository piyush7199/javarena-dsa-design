package org.example.coding.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.Collections;

public class MazeProblems {

    /**
     * Finds all possible paths for a rat to reach the destination in a maze.
     * <p>
     * Intuition:
     * - This is a standard backtracking problem where we explore all possible paths
     * from the top-left to bottom-right cell in a grid (maze) with only valid moves.
     * - A move is valid if the cell is within bounds, unvisited, and not blocked (`1`).
     * - At each step, we try all four directions (Down, Right, Up, Left) recursively.
     * - If we reach the destination, we record the path string.
     * <p>
     * Time Complexity: O(4^(n²)) in the worst case (every cell tries 4 directions)
     * Space Complexity: O(n²) due to the visited array and recursion stack
     */
    public ArrayList<String> ratInMaze(int[][] maze) {
        // code here
        int n = maze.length;
        ArrayList<String> ans = new ArrayList<>();
        if (maze[n - 1][n - 1] == 0) return ans;
        int[][] visited = new int[n][n];
        mazeHelper(maze, ans, "", 0, 0, visited);
        Collections.sort(ans);
        return ans;

    }

    /**
     * Recursive helper to explore paths using backtracking.
     */
    private void mazeHelper(int[][] maze, ArrayList<String> ans, String path, int i, int j, int[][] visited) {
        int n = maze.length;
        if (i < 0 || j < 0 || i >= n || j >= n) return;
        if (maze[i][j] == 0 || visited[i][j] == 1) return;
        if (i == n - 1 && j == n - 1) {
            ans.add(path);
            return;
        }
        visited[i][j] = 1;
        mazeHelper(maze, ans, path + "D", i + 1, j, visited);
        mazeHelper(maze, ans, path + "R", i, j + 1, visited);
        mazeHelper(maze, ans, path + "U", i - 1, j, visited);
        mazeHelper(maze, ans, path + "L", i, j - 1, visited);
        visited[i][j] = 0;
    }

}
