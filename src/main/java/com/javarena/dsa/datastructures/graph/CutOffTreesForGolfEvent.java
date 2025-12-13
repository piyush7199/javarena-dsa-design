package com.javarena.dsa.datastructures.graph;

import java.util.*;

/**
 * Cut Off Trees for Golf Event
 *
 * <p><b>Problem Statement:</b><br>
 * Given forest grid: 0=obstacle, 1=grass, >1=tree (height).
 * Start at (0,0), cut all trees in increasing height order.
 * Return total steps, or -1 if any tree unreachable.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Multiple shortest path problem:
 * - Must visit trees in sorted height order
 * - Use BFS to find shortest path between consecutive trees
 * - Accumulate total distance
 * 
 * Algorithm:
 * 1. Collect all trees (value > 1) with positions
 * 2. Sort trees by height (ascending)
 * 3. Start at (0, 0)
 * 4. For each tree in sorted order:
 *    - BFS from current position to tree
 *    - If unreachable, return -1
 *    - Add distance to total
 *    - Update current position
 * 5. Return total steps
 *
 * <p><b>Time Complexity:</b> O(T log T + T×M×N) - T trees, M×N grid, BFS per tree
 * <br><b>Space Complexity:</b> O(M×N) - Visited array + O(T) for tree list
 */
public class CutOffTreesForGolfEvent {
    /**
     * Calculates total steps to cut all trees in order.
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
