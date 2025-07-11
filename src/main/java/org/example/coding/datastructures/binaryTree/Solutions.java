package org.example.coding.datastructures.binaryTree;

import java.util.*;

/**
 * A collection of binary tree problems commonly asked in top tech companies.
 */
public class Solutions {

    /**
     * cProblem: Check if two binary trees are identical.
     * <p>
     * Intuition:
     * - Two trees are identical if:
     * - Their roots have the same value
     * - Their left subtrees are identical
     * - Their right subtrees are identical
     * <p>
     * Time Complexity: O(n) — Traverse each node once
     * Space Complexity: O(h) — Call stack for recursion (h = height)
     * <p>
     */
    public boolean isIdentical(Node r1, Node r2) {
        if (r1 == null || r2 == null) return r1 == r2;
        if (r1.val != r2.val) return false;
        boolean left = isIdentical(r1.left, r2.left);
        if (left) {
            return isIdentical(r1.right, r2.right);
        }
        return false;
    }

    /**
     * Problem: Find the maximum depth (height) of a binary tree.
     * <p>
     * Intuition:
     * - The depth of a tree is the longest path from root to leaf.
     * - Recursively compute depth of left and right subtrees and return the maximum.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     * <p>
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int left = 1 + maxDepth(root.left);
        int right = 1 + maxDepth(root.right);
        return Math.max(left, right);
    }

    /**
     * Problem: Zigzag (spiral) level order traversal.
     * <p>
     * Intuition:
     * - Use a deque to support both left-to-right and right-to-left traversal alternately.
     * - Add children in reverse order depending on the current direction.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * <p>
     */
    public List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Deque<Node> q = new LinkedList<>();
        q.addFirst(root);
        boolean reverse = false;

        while (!q.isEmpty()) {
            List<Integer> current = new ArrayList<>();
            int level = q.size();

            for (int i = 0; i < level; i++) {
                if (!reverse) {
                    Node curr = q.pollFirst();
                    current.add(curr.val);
                    if (curr.left != null) q.addLast(curr.left);
                    if (curr.right != null) q.addLast(curr.right);
                } else {
                    Node curr = q.pollLast();
                    current.add(curr.val);
                    if (curr.right != null) q.addFirst(curr.right);
                    if (curr.left != null) q.addFirst(curr.left);
                }
            }

            res.add(current);
            reverse = !reverse;
        }
        return res;
    }

    /**
     * Problem: Validate if a binary tree is a Binary Search Tree (BST).
     * <p>
     * Intuition:
     * - Each node must lie in a valid range: (min, max).
     * - For left child: new max = current node value
     * - For right child: new min = current node value
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     * <p>
     */
    public boolean isValidBST(Node root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(Node root, long lower, long higher) {
        if (root == null) return true;
        if (!(root.val > lower && root.val < higher)) return false;
        boolean left = isValid(root.left, lower, root.val);
        if (left) {
            return isValid(root.right, root.val, higher);
        }
        return false;
    }

    /**
     * Problem: Check if two trees are structurally identical and have the same node values.
     * <p>
     * Intuition:
     * - Similar to isIdentical() function, compare node values and recurse on left and right.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     * <p>
     */
    public boolean isSameTree(Node p, Node q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        boolean left = isSameTree(p.left, q.left);
        if (left) {
            return isSameTree(p.right, q.right);
        }
        return false;
    }

    /**
     * Determines if a binary tree is height-balanced.
     * A binary tree is balanced if the height difference between
     * left and right subtrees of every node is no more than 1.
     * <p>
     * Intuition:
     * - Perform post-order traversal (bottom-up).
     * - At each node, compute left and right heights.
     * - If height difference > 1, propagate `-1` up as a failure flag.
     * <p>
     * Time Complexity: O(n)
     * - Each node is visited once.
     * Space Complexity: O(h)
     * - Due to recursion stack, where h is the height of the tree.
     */
    public boolean isBalanced(Node root) {
        if (root == null) return true;
        return maxDepth2(root) != -1;
    }

    /**
     * Helper function to compute the depth of the binary tree and
     * check for height balance in one traversal.
     * <p>
     * Intuition:
     * - Returns -1 if subtree is unbalanced.
     * - Otherwise, returns the actual depth.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public int maxDepth2(Node root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        if (left == -1) return -1;
        int right = maxDepth(root.right);
        if (right == -1) return -1;
        if (Math.abs(right - left) > 1) return -1;
        return 1 + Math.max(right, left);
    }

    /**
     * Finds the maximum path sum in a binary tree.
     * A path is any sequence of nodes where each pair is connected via parent-child edge.
     * It may or may not pass through the root, and must contain at least one node.
     * <p>
     * Intuition:
     * - Use post-order traversal to calculate the max sum from left and right subtrees.
     * - For each node, compute the maximum contribution:
     * - `max(root + left, root + right, root)` for recursion return.
     * - `left + root + right` for updating global max.
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(h)
     */
    public int maxPathSum(Node root) {
        int[] ans = new int[1];
        ans[0] = Integer.MIN_VALUE;
        maxSum(root, ans);
        return ans[0];
    }

    /**
     * Helper function that computes the maximum root-to-leaf path sum and
     * updates the overall max path sum.
     */
    public int maxSum(Node root, int[] sum) {
        if (root == null) return 0;
        int l = Math.max(maxSum(root.left, sum), 0);
        int r = Math.max(maxSum(root.right, sum), 0);
        sum[0] = Math.max(sum[0], l + r + root.val);
        return root.val + Math.max(l, r);
    }

    /**
     * Checks whether a binary tree is symmetric around its center.
     * <p>
     * Intuition:
     * A tree is symmetric if its left and right subtrees are mirror images of each other.
     * We use recursion to check if the left subtree of one node is a mirror of the right subtree of the other node.
     * <p>
     * Time Complexity: O(N) – where N is the number of nodes.
     * Space Complexity: O(H) – for recursion stack, where H is the height of the tree.
     */
    public boolean isSymmetric(Node root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(Node p, Node q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val == q.val) {
            return isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
        }
        return false;
    }

    /**
     * Checks whether the value of each non-leaf node is equal to the sum of its children.
     * <p>
     * Intuition:
     * Perform a DFS traversal.
     * At each node, check if `node.val == left.val + right.val`.
     * Recursively validate for left and right subtrees.
     * <p>
     * Time Complexity: O(N)
     * Space Complexity: O(H) – where H is the height of the tree (stack space)
     */
    public boolean checkTree(Node root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        int sum = 0;
        if (root.left != null) sum += root.left.val;
        if (root.right != null) sum += root.right.val;
        if (sum == root.val) {
            return checkTree(root.left) && checkTree(root.right);
        }
        return false;
    }

    /**
     * Return all root-to-leaf paths in a binary tree as strings.
     * <p>
     * Intuition:
     * - Perform a DFS traversal, appending the path as we go.
     * - On reaching a leaf node, record the complete path.
     * <p>
     * Time Complexity: O(n) — visits each node once
     * Space Complexity: O(h) — recursion stack, where h = tree height
     */
    public List<String> binaryTreePaths(Node root) {
        List<String> ans = new ArrayList<>();
        if (root == null) return ans;
        binaryTreeHelper(root, ans, "");
        return ans;
    }

    private void binaryTreeHelper(Node node, List<String> ans, String str) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            ans.add(str + Integer.toString(node.val));
            return;
        }
        String newPath = str + Integer.toString(node.val);
        if (node.left != null) {
            binaryTreeHelper(node.left, ans, newPath + "->");
        }
        if (node.right != null) {
            binaryTreeHelper(node.right, ans, newPath + "->");
        }
    }

    /**
     * Find the lowest common ancestor (LCA) of two nodes in a binary tree.
     * <p>
     * Intuition:
     * - Recursively traverse left and right.
     * - If one node is found in each subtree, current node is LCA.
     * - If one is null, propagate the non-null result up.
     * <p>
     * <p>
     * Time Complexity: O(n) — visits each node once
     * Space Complexity: O(h) — recursion stack, where h = tree height
     */
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        else if (right == null) return left;
        return root;

    }

    /**
     * Find the maximum width of a binary tree.
     * <p>
     * Intuition:
     * - Use level-order traversal (BFS).
     * - Assign position indexes to nodes to simulate full binary tree structure.
     * - Width of each level = last index - first index + 1.
     * <p>
     * Time Complexity: O(n) — each node visited once
     * Space Complexity: O(n) — queue stores all nodes in a level
     */
    public int widthOfBinaryTree(Node root) {
        if (root == null) return 0;
        int ans = 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            int mmin = q.peek().num;
            int last = 0;
            int n = q.size();
            for (int i = 0; i < n; i++) {
                int cur_id = q.peek().num - mmin;
                Node node = q.peek().node;
                q.poll();
                last = cur_id;
                if (node.left != null) {
                    q.add(new Pair(node.left, cur_id * 2 + 1));
                }
                if (node.right != null) {
                    q.add(new Pair(node.right, cur_id * 2 + 2));
                }
            }
            ans = Math.max(ans, last + 1);
        }
        return ans;
    }

    /**
     * Helper method to record parent relationships for all nodes in the binary tree.
     * <p>
     * Intuition:
     * - Perform a level-order traversal (BFS).
     * - For every child node, store its parent in a HashMap.
     * <p>
     * Time Complexity: O(n) — each node is visited once
     * Space Complexity: O(n) — to store the parent map and queue
     */
    private HashMap<Node, Node> markParents(Node root) {
        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.left != null) {
                q.offer(cur.left);
                map.put(cur.left, cur);
            }

            if (cur.right != null) {
                q.offer(cur.right);
                map.put(cur.right, cur);
            }
        }

        return map;
    }

    /**
     * Returns all node values that are exactly k distance away from the target node.
     * <p>
     * Intuition:
     * - First, build a parent map using BFS (to allow upward traversal).
     * - Then perform a second BFS starting from the target node.
     * - Stop when current level equals k.
     * <p>
     * Time Complexity: O(n) — every node is visited at most once
     * Space Complexity: O(n) — for parent map, visited set, and queue
     */
    public List<Integer> distanceK(Node root, Node target, int k) {
        HashMap<Node, Node> map = markParents(root);
        Set<Node> vis = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        int cur = 0;
        q.offer(target);
        vis.add(target);
        while (!q.isEmpty()) {
            int n = q.size();
            if (cur == k) break;
            cur++;
            for (int i = 0; i < n; i++) {
                Node node = q.poll();
                if (node.left != null && !vis.contains(node.left)) {
                    q.offer(node.left);
                    vis.add(node.left);
                }

                if (node.right != null && !vis.contains(node.right)) {
                    q.offer(node.right);
                    vis.add(node.right);
                }

                if (map.get(node) != null && !vis.contains(map.get(node))) {
                    q.offer(map.get(node));
                    vis.add(map.get(node));
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            ans.add(q.poll().val);
        }
        return ans;
    }

    /**
     * Marks each node's parent in a binary tree and finds the target node with value `start`.
     * <p>
     * Intuition:
     * - To simulate fire spreading to parent nodes, we need a way to move upwards in the tree.
     * - Use BFS to traverse the tree and record each node’s parent in a map.
     * - Simultaneously, identify and return the target node where fire starts.
     * <p>
     * Time Complexity: O(N) — each node is visited once
     * Space Complexity: O(N) — for parent map and BFS queue
     */
    private Node markParents(Node root, HashMap<Integer, Node> map, int start) {
        Node target = root;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.left != null) {
                q.offer(cur.left);
                map.put(cur.left.val, cur);
            }

            if (cur.right != null) {
                q.offer(cur.right);
                map.put(cur.right.val, cur);
            }
            if (cur.val == start) target = cur;
        }

        return target;
    }

    /**
     * Returns the total time to burn the entire binary tree from a given starting node.
     * <p>
     * Intuition:
     * - Model the burning process as a BFS traversal from the starting node.
     * - Fire spreads in all three directions: left, right, and parent.
     * - Use a set to track visited nodes and count BFS levels to measure time.
     * <p>
     * Time Complexity: O(N) — each node is visited once
     * Space Complexity: O(N) — for queue, visited set, and parent map
     */
    public int amountOfTime(Node root, int start) {
        HashMap<Integer, Node> map = new HashMap<>();
        Node target = markParents(root, map, start);
        Set<Node> vis = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        int cur = -1;
        q.offer(target);
        vis.add(target);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                Node node = q.poll();
                if (node.left != null && !vis.contains(node.left)) {
                    q.offer(node.left);
                    vis.add(node.left);
                }
                if (node.right != null && !vis.contains(node.right)) {
                    q.offer(node.right);
                    vis.add(node.right);
                }
                Node parent = map.get(node.val);
                if (parent != null && !vis.contains(parent)) {
                    q.offer(parent);
                    vis.add(parent);
                }
            }
            cur++;
        }

        return cur;
    }
}
