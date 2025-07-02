package org.example.coding.datastructures.binaryTree;

import java.util.*;

/**
 * BinaryTree provides various recursive and iterative tree traversal algorithms,
 * including inorder, preorder, postorder, level-order, and a combined traversal.
 */
public class BinaryTree {

    /**
     * Recursive Inorder Traversal: Left → Root → Right
     * <p>
     * Time: O(n) <br>
     * Space: O(h) recursive stack space
     */
    public void inorderTraversal(Node root) {
        if (root == null) return;
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }

    /**
     * Iterative Inorder Traversal using Stack
     * <p>
     * Time: O(n) <br>
     * Space: O(h)
     */
    public void inorderTraversalIterative(Node root) {
        if (root == null) return;
        Stack<Node> s = new Stack<>();
        Node curr = root;
        while (curr != null || !s.isEmpty()) {
            while (curr != null) {
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
    }

    /**
     * Recursive Postorder Traversal: Left → Right → Root
     * <p>
     * Time: O(n) <br>
     * Space: O(h)
     */
    public void postorderTraversal(Node root) {
        if (root == null) return;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.println(root.val);
    }

    /**
     * Iterative Postorder using Two Stacks
     * <p>
     * Time: O(n) <br>
     * Space: O(n)
     */
    public List<Integer> postOrderIterative(Node root) {
        List<Integer> postOrder = new ArrayList<>();
        if (root == null) return postOrder;

        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        st1.push(root);

        while (!st1.isEmpty()) {
            Node node = st1.pop();
            st2.push(node);
            if (node.left != null) st1.push(node.left);
            if (node.right != null) st1.push(node.right);
        }

        while (!st2.isEmpty()) {
            postOrder.add(st2.pop().val);
        }
        return postOrder;
    }

    /**
     * Iterative Postorder using One Stack
     * <p>
     * Time: O(n) <br>
     * Space: O(n)
     */
    public List<Integer> postOrderIterativeUsingOneStack(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Stack<Node> stack = new Stack<>();
        Node cur = root;

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                Node temp = stack.peek().right;
                if (temp == null) {
                    temp = stack.pop();
                    ans.add(temp.val);
                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        ans.add(temp.val);
                    }
                } else {
                    cur = temp;
                }
            }
        }
        return ans;
    }

    /**
     * Recursive Preorder Traversal: Root → Left → Right
     * <p>
     * Time: O(n) <br>
     * Space: O(h)
     */
    public void preorderTraversal(Node root) {
        if (root == null) return;
        System.out.println(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    /**
     * Iterative Preorder Traversal using Stack
     * <p>
     * Time: O(n) <br>
     * Space: O(n)
     */
    public void preorderIterative(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);  // Missing in your version

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }

    /**
     * Level-Order Traversal (Breadth-First)
     * <p>
     * Time: O(n) <br>
     * Space: O(n)
     */
    public List<List<Integer>> levelOrderTraversal(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                Node node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            ans.add(level);
        }

        return ans;
    }

    /**
     * Performs Preorder, Inorder, and Postorder traversals in one pass.
     * Uses a Pair class with traversal stage (1=pre, 2=in, 3=post).
     * <p>
     * Time: O(n) <br>
     * Space: O(n)
     */
    public void traversalInOneGo(Node root) {
        if (root == null) return;
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        while (!stack.isEmpty()) {
            Pair p = stack.pop();

            if (p.num == 1) {
                pre.add(p.node.val);
                p.num++;
                stack.push(p);
                if (p.node.left != null) stack.push(new Pair(p.node.left, 1));
            } else if (p.num == 2) {
                in.add(p.node.val);
                p.num++;
                stack.push(p);
                if (p.node.right != null) stack.push(new Pair(p.node.right, 1));
            } else {
                post.add(p.node.val);
            }
        }

        System.out.println("Preorder: " + pre);
        System.out.println("Inorder: " + in);
        System.out.println("Postorder: " + post);
    }
}

class Node {
    int val;
    Node left, right;

    Node(int val) {
        this.val = val;
    }
}

class Pair {
    Node node;
    int num;

    Pair(Node node, int num) {
        this.node = node;
        this.num = num;
    }
}
