package com.javarena.dsa.datastructures.graph;

/**
 * Process Restricted Friend Requests
 *
 * <p><b>Problem Statement:</b><br>
 * Given n people, restrictions on who cannot be friends, and friend requests,
 * determine which requests can be accepted without violating restrictions.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Union-Find with constraint checking:
 * - Use DSU to track friend groups
 * - For each friend request [u, v]:
 *   - If already in same group → accept
 *   - Check all restrictions:
 *     - If merging u and v would connect restricted pair → reject
 *     - Check if roots of u and v match roots of any restriction
 *   - If no violation → union and accept
 * 
 * Key: Before union, verify no restriction violated.
 *
 * <p><b>Time Complexity:</b> O(Q × R × α(N)) - Q requests, R restrictions, α = inverse Ackermann
 * <br><b>Space Complexity:</b> O(N) - DSU arrays
 */
public class ProcessRestrictions {
    /**
     * Disjoint Set Union data structure.
     */
    static class DS {
        int[] parent;
        int[] rank;

        public DS(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }

    /**
     * Processes friend requests with restrictions.
     */
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        DS dsJoin = new DS(n);
        boolean[] res = new boolean[requests.length];
        
        for (int i = 0; i < requests.length; i++) {
            int u = requests[i][0];
            int v = requests[i][1];
            int firstParent = dsJoin.find(u);
            int secondParent = dsJoin.find(v);
            
            // Already friends
            if (firstParent == secondParent) {
                res[i] = true;
                continue;
            }

            // Check if union violates any restriction
            boolean flag = true;
            for (int j = 0; j < restrictions.length; j++) {
                int firstRestriction = dsJoin.find(restrictions[j][0]);
                int secondRestriction = dsJoin.find(restrictions[j][1]);

                if ((firstRestriction == firstParent && secondRestriction == secondParent) ||
                        (secondRestriction == firstParent && firstRestriction == secondParent)) {
                    flag = false;
                    break;
                }
            }

            // Accept if no violation
            if (flag) {
                res[i] = true;
                dsJoin.union(firstParent, secondParent);
            }
        }

        return res;
    }
}
