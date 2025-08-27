package org.example.coding.datastructures.graph;

import java.util.*;

public class CutOffTreesForGolfEvent {
    /**
     * Problem: Cut Off Trees for Golf Event (LeetCode 675)
     * ----------------------------------------------------
     * We are given a forest represented as a 2D grid where:
     * - 0 represents obstacles (impassable cells),
     * - 1 represents grass (walkable cells),
     * - values > 1 represent trees (walkable and cuttable cells).
     * <p>
     * Task:
     * We must cut all trees in **increasing order of their height**.
     * The starting position is (0,0). From each tree to the next, we can only move
     * in 4 directions (up, down, left, right) and must find the **shortest path**.
     * <p>
     * If any tree is unreachable, return -1. Otherwise, return the total number
     * of steps required to cut all trees.
     * <p>
     * ----------------------------------------------------
     * Intuition:
     * 1. Since trees must be cut in ascending height order, we sort all tree positions by height.
     * 2. From the current position, use BFS to compute the shortest path to the next tree.
     * 3. Accumulate the steps; if BFS cannot reach a tree, return -1.
     * <p>
     * ----------------------------------------------------
     * Approach:
     * 1. Collect all trees (value > 1) into a list of (height, row, col).
     * 2. Sort the list by height.
     * 3. Initialize (sr, sc) = (0, 0).
     * 4. For each tree in sorted order:
     * - Run BFS from (sr, sc) to the tree's position (tr, tc).
     * - If BFS returns -1, return -1.
     * - Otherwise, add BFS distance to total steps.
     * - Update (sr, sc) = (tr, tc).
     * 5. Return total steps after processing all trees.
     * <p>
     * ----------------------------------------------------
     * Time Complexity:
     * - Sorting trees: O(T log T), where T = number of trees.
     * - BFS per tree: O(m * n), where m = rows, n = cols.
     * - Total: O(T log T + T * m * n).
     * <p>
     * Space Complexity:
     * - O(m * n) for visited array during BFS.
     * - O(T) for storing tree list.
     */
    public int cutOffTree(List<List<Integer>> forest) {
        int n = forest.size();
        int m = forest.get(0).size();

        // Step 1: Collect all trees (height > 1)
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int h = forest.get(i).get(j);
                if (h > 1) {
                    trees.add(new int[]{h, i, j});
                }
            }
        }

        // Step 2: Sort trees by height (ascending)
        trees.sort(Comparator.comparingInt(a -> a[0]));

        int steps = 0;
        int sr = 0, sc = 0; // starting position

        // Step 3: Process each tree
        for (int[] tree : trees) {
            int tr = tree[1], tc = tree[2];

            // Run BFS to get shortest distance from (sr, sc) to (tr, tc)
            int dist = bfs(forest, sr, sc, tr, tc, n, m);
            if (dist == -1) return -1; // unreachable tree

            steps += dist;
            sr = tr; // update current position
            sc = tc;
        }

        return steps;
    }

    /**
     * BFS function to compute shortest path from (sr, sc) → (tr, tc).
     *
     * @param forest input grid
     * @param sr     start row
     * @param sc     start col
     * @param tr     target row
     * @param tc     target col
     * @param n      number of rows
     * @param m      number of cols
     * @return shortest distance, or -1 if unreachable
     */
    private int bfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc, int n, int m) {
        if (sr == tr && sc == tc) return 0; // already at target

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];

        // enqueue start position
        queue.offer(new int[]{sr, sc, 0});
        vis[sr][sc] = true;

        // Directions: up, right, down, left
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], d = cur[2];

            // explore neighbors
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                // check boundaries + walkable cell + not visited
                if (nr >= 0 && nc >= 0 && nr < n && nc < m && forest.get(nr).get(nc) != 0 && !vis[nr][nc]) {

                    if (nr == tr && nc == tc) return d + 1; // reached target

                    vis[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, d + 1});
                }
            }
        }

        return -1; // unreachable
    }
}
