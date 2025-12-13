package com.javarena.dsa.algorithms.recursionAndBacktracking;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Rat in a Maze
 *
 * <p><b>Problem Statement:</b><br>
 * Given a maze represented as an n×n grid where 1 represents an open cell and 0 represents a blocked cell,
 * find all possible paths for a rat to move from top-left (0,0) to bottom-right (n-1,n-1).
 * The rat can move in four directions: Down (D), Right (R), Up (U), Left (L).
 *
 * <p><b>Intuition & Approach:</b><br>
 * - Use backtracking to explore all possible paths from source to destination
 * - At each cell, try all four directions (D, R, U, L) if the move is valid
 * - A move is valid if: cell is within bounds, not blocked (value = 1), and not already visited
 * - Mark cell as visited before exploring, unmark after (backtracking)
 * - When destination is reached, add the path string to result
 * - Sort final paths lexicographically
 *
 * <p><b>Time Complexity:</b> O(4^(N²)) - Each cell can try 4 directions
 * <br><b>Space Complexity:</b> O(N²) - Visited array and recursion stack
 */
public class MazeProblems {

    /**
     * Finds all possible paths for a rat to reach the destination in a maze.
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
