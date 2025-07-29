package org.example.coding.datastructures.graph;

public class ProcessRestrictions {
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
     * <p>
     * Problem Summary:
     * Given a number of people and restrictions on who can be in the same friend group,
     * process friend requests and determine if they can be accepted without violating any restriction.
     * <p>
     * Approach:
     * - Use Disjoint Set Union (DSU) with path compression and union by rank.
     * - For each request, check if union violates any restriction.
     * - If not, perform union and accept the request.
     * <p>
     * Time Complexity: O(Q * R * α(N))
     * - Q = number of requests
     * - R = number of restrictions
     * - α(N) is inverse Ackermann function (almost constant)
     * <p>
     * Space Complexity: O(N)
     * <p>
     */
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        DS dsJoin = new DS(n);
        boolean[] res = new boolean[requests.length];
        for (int i = 0; i < requests.length; i++) {
            int u = requests[i][0];
            int v = requests[i][1];
            int firstParent = dsJoin.find(u);
            int secondParent = dsJoin.find(v);
            if (firstParent == secondParent) {
                res[i] = true;
                continue;
            }

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

            if (flag) {
                res[i] = true;
                dsJoin.union(firstParent, secondParent);
            }
        }

        return res;
    }
}
