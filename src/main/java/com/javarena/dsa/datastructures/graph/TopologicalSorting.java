package com.javarena.dsa.datastructures.graph;

import java.util.*;

/**
 * Topological Sorting Problems
 *
 * <p><b>Problem Statement:</b><br>
 * Implement topological sorting for Directed Acyclic Graphs (DAGs). Solve course schedule problems
 * and find eventual safe nodes.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Topological Sort gives linear ordering where prerequisites come first.
 * 
 * Kahn's Algorithm (BFS-based):
 * - Calculate in-degree for all nodes
 * - Start with nodes having in-degree 0 (no dependencies)
 * - Process each node, reduce in-degree of neighbors
 * - Add neighbors with in-degree 0 to queue
 * - If cycle exists, not all nodes processed
 * 
 * Applications:
 * - Course Schedule: Check if all courses can be finished
 * - Find Order: Return valid course sequence
 * - Safe Nodes: Reverse graph, find nodes leading to terminals
 *
 * <p><b>Time Complexity:</b> O(V + E) - Process all vertices and edges
 * <br><b>Space Complexity:</b> O(V + E) - Adjacency list + queue
 */
public class TopologicalSorting {

    /**
     * Finds order to take courses given prerequisites.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());

        int[] indegree = new int[numCourses];
        boolean[] vis = new boolean[numCourses];

        for (int[] row : prerequisites) {
            int u = row[0];
            int v = row[1];
            adj.get(v).add(u);
            indegree[u]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                vis[i] = true;
            }
        }
        int[] ans = new int[numCourses];
        int i = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            ans[i] = node;
            i++;
            for (int ele : adj.get(node)) {
                indegree[ele]--;
                if (indegree[ele] == 0 && !vis[ele]) {
                    vis[ele] = true;
                    queue.offer(ele);
                }
            }
        }
        if (i != numCourses) return new int[]{};
        return ans;
    }

    /**
     * Determines whether all courses can be finished given the prerequisites.
     * <p>
     * Intuition:
     * - A course plan can be finished if and only if the graph has no cycle.
     * - We reuse `findOrder()` and simply check if we were able to return all courses.
     * <p>
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return findOrder(numCourses, prerequisites).length == numCourses;
    }

    /**
     * Finds all the eventual safe nodes in a directed graph.
     * <p>
     * Intuition:
     * - A node is "eventually safe" if all paths starting from it lead to terminal (no outgoing edges).
     * - We reverse the graph, and perform topological sort (Kahn's Algorithm) starting from terminal nodes.
     * - Nodes that are eventually safe will have no incoming edges in the reversed graph.
     * <p>
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            inDegree[i] = graph[i].length;
            for (int ele : graph[i]) {
                adj.get(ele).add(i);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                vis[i] = true;
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            ans.add(node);
            for (int ele : adj.get(node)) {
                inDegree[ele]--;
                if (inDegree[ele] == 0 && !vis[ele]) {
                    q.offer(ele);
                    vis[ele] = true;
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
