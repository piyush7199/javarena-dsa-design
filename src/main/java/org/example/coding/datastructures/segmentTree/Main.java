package org.example.coding.datastructures.segmentTree;

import org.example.coding.datastructures.segmentTree.impl.STUsingNode;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 3, 4, 2, 5, 7, 2, 1, 4, 5};
        /*
        STUsingArray st = new STUsingArray(arr);
        System.out.println(st.query(2, 4));
        st.updateTree(5, 8);
        System.out.println(st.query(2, 4));
         */
        STUsingNode st = new STUsingNode(arr, arr.length);
        System.out.println(st.query(2, 4));
        st.update(2, 8);
        System.out.println(st.query(2, 4));
    }
}
