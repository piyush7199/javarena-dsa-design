package org.example.coding.datastructures.binaryTree;

public class SubTree {
    public boolean isSubtree(Node root, Node subRoot) {

        boolean main = isSameTree(root, subRoot);
        if(main) return true;

        if(root != null)
        {
            boolean left = isSubtree(root.left, subRoot);
            if(left) return true;
            return isSubtree(root.right, subRoot);
        }
        return false;
    }

    public boolean isSameTree(Node p, Node q) {
        if(p == null && q == null) return true;
        if(p == null) return false;
        if(q == null) return false;
        if(p.val != q.val) return false;

        boolean left = isSameTree(p.left, q.left);
        if(left) return isSameTree(p.right, q.right);
        return false;
    }
}
