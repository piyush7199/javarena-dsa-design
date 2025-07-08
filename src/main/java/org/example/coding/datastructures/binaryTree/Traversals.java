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
     * - Treat the binary tree as a 2D grid:
     * - Root is at (0, 0)
     * - Left child is at (row + 1, col - 1)
     * - Right child is at (row + 1, col + 1)
     * - Use BFS to traverse level by level, keeping track of (row, col)
     * - Group nodes by their column index.
     * - Within each column, sort nodes by:
     * 1. Row (level)
     * 2. Node value (for tie-breaking if multiple nodes are at same row/col)
     * - Collect and return the vertical columns in order from left to right.
     * <p>
     * Time Complexity: O(n log n)
     * - Each node is visited once → O(n)
     * - Insertion in PriorityQueue per column → O(log n) each
     * - Total: O(n log n)
     * <p>
     * Space Complexity: O(n)
     * - HashMap stores n nodes grouped by column
     * - BFS queue and final result list also take O(n) space
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

    /**
     * Returns the boundary traversal of a binary tree in anti-clockwise direction.
     * <p>
     * The boundary includes:
     * 1. Root node (if not a leaf)
     * 2. Left boundary (excluding leaf nodes)
     * 3. All leaf nodes (left to right)
     * 4. Right boundary (excluding leaf nodes), added in reverse order
     * <p>
     * Intuition:
     * - Traverse around the "boundary" or outer edge of the tree.
     * - The traversal is split into three phases:
     * a. Add left boundary nodes (excluding leaves)
     * b. Add all leaf nodes using DFS
     * c. Add right boundary nodes (excluding leaves) in reverse order
     * - Each node is added only once, and we avoid duplicates (e.g., leaf on left/right boundary).
     * <p>
     * Time Complexity: O(n)
     * - Every node is visited once across the three phases.
     * <p>
     * Space Complexity: O(n)
     * - Result list stores boundary nodes (up to all nodes in worst case)
     * - Recursive DFS for leaves can take up to O(h) stack space, where h is tree height
     */
    ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (!isLeaf(root)) {
            res.add(root.val);
        }

        addLeftBoundary(root, res);
        addLeaves(root, res);
        addRightBoundary(root, res);

        return res;
    }

    boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }

    // Function to add the
    // left boundary of the tree
    void addLeftBoundary(Node root, List<Integer> res) {
        Node curr = root.left;
        while (curr != null) {
            // If the current node is not a leaf,
            // add its value to the result
            if (!isLeaf(curr)) {
                res.add(curr.val);
            }
            // Move to the left child if it exists,
            // otherwise move to the right child
            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    // Function to add the
    // right boundary of the tree
    void addRightBoundary(Node root, List<Integer> res) {
        Node curr = root.right;
        List<Integer> temp = new ArrayList<>();
        while (curr != null) {
            // If the current node is not a leaf,
            // add its value to a temporary list
            if (!isLeaf(curr)) {
                temp.add(curr.val);
            }
            // Move to the right child if it exists,
            // otherwise move to the left child
            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        // Reverse and add the values from
        // the temporary list to the result
        for (int i = temp.size() - 1; i >= 0; --i) {
            res.add(temp.get(i));
        }
    }

    // Function to add the
    // leaves of the tree
    void addLeaves(Node root, List<Integer> res) {
        // If the current node is a
        // leaf, add its value to the result
        if (isLeaf(root)) {
            res.add(root.val);
            return;
        }
        // Recursively add leaves of
        // the left and right subtrees
        if (root.left != null) {
            addLeaves(root.left, res);
        }
        if (root.right != null) {
            addLeaves(root.right, res);
        }
    }

    /**
     * Computes the top view of a binary tree.
     * <p>
     * Intuition:
     * Perform level order traversal and keep track of horizontal distances (HD).
     * For each HD, store the first node encountered.
     * <p>
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    static ArrayList<Integer> topView(Node root) {
        // code here
        class NodeInfo {
            int dir;
            Node node;

            public NodeInfo(int dir, Node node) {
                this.dir = dir;
                this.node = node;
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<NodeInfo> q = new LinkedList<>();
        q.offer(new NodeInfo(0, root));
        while (!q.isEmpty()) {
            NodeInfo info = q.peek();
            Node node = info.node;
            int dir = info.dir;
            q.poll();
            if (!map.containsKey(dir)) {
                map.put(dir, node.val);
            }
            if (node.left != null) q.offer(new NodeInfo(dir - 1, node.left));
            if (node.right != null) q.offer(new NodeInfo(dir + 1, node.right));
        }
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        ArrayList<Integer> ans = new ArrayList<>();
        for (int ele : keys) {
            ans.add(map.get(ele));
        }
        return ans;
    }

    /**
     * Computes the bottom view of a binary tree.
     * <p>
     * Intuition:
     * Use level order traversal and update the value at each horizontal distance (HD).
     * The last value seen at each HD is part of the bottom view.
     * <p>
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public ArrayList<Integer> bottomView(Node root) {
        class NodeInfo {
            int dir;
            Node node;

            public NodeInfo(int dir, Node node) {
                this.dir = dir;
                this.node = node;
            }
        }
        // Code here
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<NodeInfo> q = new LinkedList<>();
        q.offer(new NodeInfo(0, root));
        while (!q.isEmpty()) {
            NodeInfo info = q.peek();
            Node node = info.node;
            int dir = info.dir;
            q.poll();
            map.put(dir, node.val);

            if (node.left != null) q.offer(new NodeInfo(dir - 1, node.left));
            if (node.right != null) q.offer(new NodeInfo(dir + 1, node.right));
        }
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        ArrayList<Integer> ans = new ArrayList<>();
        for (int ele : keys) {
            ans.add(map.get(ele));
        }
        return ans;
    }

    /**
     * Computes the right side view of a binary tree.
     * <p>
     * Intuition:
     * Use level order traversal and pick the last node at each level.
     * <p>
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public List<Integer> rightSideView(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            int ele = -1;
            for (int i = 0; i < n; i++) {
                Node node = q.poll();
                ele = node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            ans.add(ele);
        }
        return ans;
    }
}
