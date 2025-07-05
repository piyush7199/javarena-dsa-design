package org.example.coding.datastructures.binaryTree;

import java.util.*;

public class Traversals {

    static class NodeInfo {
        int val;
        int level;

        public NodeInfo(int val, int level) {
            this.val = val;
            this.level = level;
        }
    }

    static class Pair {
        Node node;
        int level;
        int dir;  // horizontal distance (column)

        public Pair(Node node, int level, int dir) {
            this.node = node;
            this.level = level;
            this.dir = dir;
        }
    }

    /**
     * Performs a vertical order traversal of a binary tree.
     * <p>
     * Intuition:
     * - Treat the tree as a coordinate system: root is at (row=0, col=0).
     * - Traverse using BFS to ensure nodes are processed level-by-level.
     * - For each node, track its column (horizontal distance) and row (level).
     * - Use a HashMap to group nodes by column, and use a PriorityQueue to
     * sort nodes first by row, then by value within the same column.
     * <p>
     * Sorting rules:
     * - Nodes in the same vertical column are sorted top to bottom (i.e., by row/level).
     * - If multiple nodes share the same row and column, sort them by node value.
     */
    public List<List<Integer>> verticalTraversal(Node root) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0, 0));

        // Map of dir -> list of (val, level)
        Map<Integer, PriorityQueue<NodeInfo>> map = new HashMap<>();

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            Node node = curr.node;
            int level = curr.level;
            int dir = curr.dir;

            map.putIfAbsent(dir, new PriorityQueue<>((a, b) -> {
                if (a.level != b.level)
                    return a.level - b.level;
                return a.val - b.val;
            }));
            map.get(dir).offer(new NodeInfo(node.val, level));

            if (node.left != null)
                q.offer(new Pair(node.left, level + 1, dir - 1));
            if (node.right != null)
                q.offer(new Pair(node.right, level + 1, dir + 1));
        }

        List<Integer> dirs = new ArrayList<>(map.keySet());
        Collections.sort(dirs);

        List<List<Integer>> result = new ArrayList<>();
        for (int d : dirs) {
            List<Integer> col = new ArrayList<>();
            PriorityQueue<NodeInfo> pq = map.get(d);
            while (!pq.isEmpty()) {
                col.add(pq.poll().val);
            }
            result.add(col);
        }
        return result;
    }
}
