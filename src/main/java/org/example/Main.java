package org.example;

import org.example.arrays.MoveZeroToLeft;

public class Main {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 0, 0, 2, 0, 1};
        MoveZeroToLeft.moveZeroToLeft(arr);
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }
}