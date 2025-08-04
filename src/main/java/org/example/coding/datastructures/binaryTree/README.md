# Binary Tree

Welcome to the **Binary Tree** section of **Javarena!** This guide covers binary tree concepts, common operations, and
interview
problems with Java implementations to help you ace coding interviews.

---

## 📌 Table of Contents

- [🌟 What is a Binary Tree?](#-what-is-a-binary-tree)
- [🌲 Types of Binary Trees](#-types-of-binary-trees)
- [📏 Key Properties](#-key-properties)
- [⚙️ Common Operations](#-common-operations)
- [💻 Code Implementations](#-code-implementations)
- [🧪 Practice Problems](#-practice-problems)
- [📚 Resources](#-resources)

---

## 🌟 What is a Binary Tree?

A binary tree is a hierarchical data structure where each node has at most two children, referred to as the left child
and right child. Binary trees are widely used in search algorithms, expression parsing, and hierarchical data
representation.

### Visual Representation

```
1
/
2   3
/ \

4   5   6 
```

- **Root:** Node 1 (topmost node)
- **Parent:** A node with children (e.g., 2 is the parent of 4 and 5)
- **Leaf:** Nodes with no children (e.g., 4, 5, 6)

---

## 🌲 Types of Binary Trees

- **Full Binary Tree:** Every node has either 0 or 2 children.
- **Complete Binary Tree:** All levels except possibly the last are fully filled, and the last level is filled from left
  to right.
- **Perfect Binary Tree:** All internal nodes have 2 children, and all leaf nodes are at the same level.
- **Binary Search Tree (BST):** Left subtree contains nodes with values less than the parent, and right subtree contains
  nodes with values greater than the parent.
- **Balanced Binary Tree:** Height of left and right subtrees of any node differs by at most 1 (e.g., AVL, Red-Black
  trees).

---

## 📏 Key Properties

- **Height: Number of edges on the longest path from root to leaf.**
- **Depth: Number of edges from the root to a specific node.**
- **Maximum nodes at level `L`: 2^L nodes.**
- **Maximum nodes in a tree of height `H`: `2^(H+1) - 1`.**
- **Minimum height for `N` nodes: `floor(log2(N))`.**

---

## ⚙️ Common Operations

| Operation             | Time Complexity   | Description             |
|-----------------------|-------------------|-------------------------|
| Inorder Traversal     | O(n)              | Left → Root → Right     |
| Preorder Traversal    | O(n)              | Root → Left → Right     |
| Postorder Traversal   | O(n)              | Left → Right → Root     |
| Level Order Traversal | O(n)              | Traverse level by level |
| Insertion (BST)       | O(h) (h = height) | Insert a node in BST    |
| Deletion (BST)        | O(h)              | Remove a node from BST  |
| Search (BST)          | O(h)              | Find a node in BST      |

---

## 💻 Code Implementations

Below is a Java implementation of a binary tree node and inorder traversal:

```java
public class BinaryTree {
    // Node class for binary tree
    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // Inorder traversal: Left -> Root -> Right
    public void inorderTraversal(Node root) {
        if (root == null) return;
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        System.out.print("Inorder Traversal: ");
        tree.inorderTraversal(root); // Output: 4 2 5 1 3 6
    }
}
```

---

## 🧪 Practice Problems

| #  | Problem                                                                                                                                                                                          | Solution File                                            |
|----|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------|
| 1  | [Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/)                                                                                                              | [BinaryTree.java](./BinaryTree.java)                     |
| 2  | [Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)                                                                                                                | [BinaryTree.java](./BinaryTree.java)                     |
| 3  | [Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal/)                                                                                                            | [BinaryTree.java](./BinaryTree.java)                     |
| 4  | [Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)                                                                                                        | [BinaryTree.java](./BinaryTree.java)                     |
| 5  | [Preorder, Postorder and Inorder Traversal of a Binary Tree using a single Stack](https://www.geeksforgeeks.org/preorder-postorder-and-inorder-traversal-of-a-binary-tree-using-a-single-stack/) | [BinaryTree.java](./BinaryTree.java)                     |
| 6  | [Identical Trees](https://www.geeksforgeeks.org/problems/determine-if-two-trees-are-identical/1)                                                                                                 | [Solutions.java](./Solutions.java)                       |
| 7  | [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)                                                                                                                                  | [Solutions.java](./Solutions.java)                       |
| 8  | [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)                                                                                                      | [Solutions.java](./Solutions.java)                       |
| 9  | [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)                                                                              | [Solutions.java](./Solutions.java)                       |
| 10 | [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)                                                                                                        | [Solutions.java](./Solutions.java)                       |
| 11 | [Same Tree](https://leetcode.com/problems/same-tree/)                                                                                                                                            | [Solutions.java](./Solutions.java)                       |
| 12 | [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/)                                                                                                                      | [Solutions.java](./Solutions.java)                       |
| 13 | [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)                                                                                                      | [Solutions.java](./Solutions.java)                       |
| 14 | [Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/)                                                                            | [Traversals.java](./Traversals.java)                     |
| 15 | [Tree Boundary Traversal](https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1)                                                                                            | [Traversals.java](./Traversals.java)                     |
| 16 | [Top View of Binary Tree](https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1)                                                                                                      | [Traversals.java](./Traversals.java)                     |
| 17 | [Bottom View of Binary Tree](https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1)                                                                                                | [Traversals.java](./Traversals.java)                     |
| 18 | [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view)                                                                                                         | [Traversals.java](./Traversals.java)                     |
| 19 | [Root Equals Sum of Children](https://leetcode.com/problems/root-equals-sum-of-children/)                                                                                                        | [Solutions.java](./Solutions.java)                       |
| 20 | [Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/)                                                                                                                            | [Solutions.java](./Solutions.java)                       |
| 21 | [LCA of Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)                                                                                                     | [Solutions.java](./Solutions.java)                       |
| 22 | [Maximum Width of Binary Tree](https://leetcode.com/problems/maximum-width-of-binary-tree/)                                                                                                      | [Solutions.java](./Solutions.java)                       |
| 23 | [All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/)                                                                                        | [Solutions.java](./Solutions.java)                       |
| 24 | [Amount of Time for Binary Tree to Be Infected](https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/)                                                                    |                                                          |
| 25 | [Count Complete Tree Nodes](https://leetcode.com/problems/count-complete-tree-nodes/)                                                                                                            | [CountNodes.java](./CountNodes.java)                     |
| 26 | [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)                                            | [ConstructTree.java](./ConstructTree.java)               |
| 27 | [Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)                                          | [ConstructTree.java](./ConstructTree.java)               |
| 28 | [BST Search](https://leetcode.com/problems/search-in-a-binary-search-tree/)                                                                                                                      | [BSTSearch.java](./BSTSearch.java)                       |
| 29 | [Closest Nodes Queries in a Binary Search Tree](https://leetcode.com/problems/closest-nodes-queries-in-a-binary-search-tree/)                                                                    | [BSTClosetNode.java](./BSTClosetNode.java)               |
| 30 | [Even Odd Tree](https://leetcode.com/problems/even-odd-tree/)                                                                                                                                    | [EvenOddTree.java](./EvenOddTree.java)                   |
| 31 | [Diameter Of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/)                                                                                                                | [DiameterOfBinaryTree.java](./DiameterOfBinaryTree.java) |

---

## 📚 Resources

- [GeeksforGeeks | Binary Tree](https://www.geeksforgeeks.org/dsa/binary-tree-data-structure/)
- [Take U Forward | Binary Tree](https://takeuforward.org/binary-tree/introduction-to-trees/)