package org.example.coding.datastructures.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserialize {
    /**
     * Definition for a binary tree node.
     */
    class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    class Codec {

        /**
         * Intuition:
         * ----------
         * We need to convert a binary tree into a single string (serialization)
         * and then reconstruct the exact same tree from that string (deserialization).
         *
         * The best way to do this is using a **Level Order Traversal (BFS)**.
         * - While traversing the tree level by level, we add each node's value to the result string.
         * - For null nodes, we insert a special marker like `"n"` to indicate missing children.
         * - This allows us to reconstruct the exact structure later.
         *
         * Example:
         * --------
         * Tree:
         *        1
         *       / \
         *      2   3
         *         / \
         *        4   5
         *
         * Serialized String: "1 2 3 n n 4 5 n n n n"
         */

        /**
         * Approach for Serialization:
         * ---------------------------
         * 1. Use a queue for BFS traversal.
         * 2. Start from the root, add nodes to the queue.
         * 3. For each node:
         * - If it's not null, append its value and add its left and right children to the queue.
         * - If it's null, append "n".
         * 4. Return the constructed string.
         * <p>
         * Time Complexity: O(N)
         * - Each node is processed exactly once.
         * <p>
         * Space Complexity: O(N)
         * - Queue can hold up to N/2 nodes at the last level in the worst case.
         *
         * @param root The root of the binary tree
         * @return A string representing the serialized binary tree
         */
        public String serialize(Node root) {
            if (root == null) return "";

            Queue<Node> q = new LinkedList<>();  // Queue for BFS
            q.offer(root);

            StringBuilder res = new StringBuilder();

            while (!q.isEmpty()) {
                Node node = q.poll();

                if (node == null) {
                    res.append("n "); // "n" represents null nodes
                    continue;
                }

                // Append current node's value
                res.append(node.val).append(" ");

                // Add children to the queue (can be null)
                q.offer(node.left);
                q.offer(node.right);
            }

            return res.toString();
        }

        /**
         * Approach for Deserialization:
         * -----------------------------
         * 1. Split the serialized string by spaces to get node values.
         * 2. The first value is the root node.
         * 3. Use a queue to rebuild the tree level-by-level:
         * - For each node in the queue, assign its left and right children
         * using the next available values from the list.
         * - If a value is "n", it means the child is null.
         * 4. Continue until all values are processed.
         * <p>
         * Time Complexity: O(N)
         * - Each node and null placeholder is processed once.
         * <p>
         * Space Complexity: O(N)
         * - Queue holds nodes during reconstruction.
         *
         * @param data The serialized string representing the binary tree
         * @return The root node of the reconstructed binary tree
         */
        public Node deserialize(String data) {
            if (data.equals("")) return null; // Empty string means empty tree

            // Split serialized data by spaces
            String[] values = data.split(" ");

            // First value is the root
            Node root = new Node(Integer.parseInt(values[0]));
            Queue<Node> q = new LinkedList<>();
            q.offer(root);

            // Use BFS to reconstruct the tree
            for (int i = 1; i < values.length; i++) {
                Node parent = q.poll(); // Get the current parent node

                // Build the left child
                if (!values[i].equals("n")) {
                    Node leftNode = new Node(Integer.parseInt(values[i]));
                    parent.left = leftNode;
                    q.offer(leftNode);
                }

                // Build the right child
                if (++i < values.length && !values[i].equals("n")) {
                    Node rightNode = new Node(Integer.parseInt(values[i]));
                    parent.right = rightNode;
                    q.offer(rightNode);
                }
            }

            return root;
        }
    }
}