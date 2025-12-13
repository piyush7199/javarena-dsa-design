package com.javarena.dsa.datastructures.stackAndQueue;

/**
 * Two Stacks in One Array
 *
 * <p><b>Problem Statement:</b><br>
 * Implement two stacks in single array efficiently.
 * Both stacks should support push and pop operations.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Utilize array from both ends:
 * - Stack 1: Grows from left (index 0 onwards)
 * - Stack 2: Grows from right (index n-1 backwards)
 * - Top1 tracks Stack 1 top (-1 initially)
 * - Top2 tracks Stack 2 top (n initially)
 * - Overflow when top1 + 1 == top2 (stacks meet)
 * 
 * Maximizes space utilization for both stacks.
 *
 * <p><b>Time Complexity:</b> O(1) for all operations
 * <br><b>Space Complexity:</b> O(N) - Shared array
 */
public class TwoStacks {
    private final int[] arr;
    private int top1;
    private int top2;

    public TwoStacks() {
        arr = new int[100];
        top1 = -1;
        top2 = 100;
    }

    /**
     * Pushes into stack 1.
     */
    void push1(int x) {
        if (top1 + 1 == top2) return; // Overflow
        arr[++top1] = x;
    }

    /**
     * Pushes into stack 2.
     */
    void push2(int x) {
        if (top1 + 1 == top2) return; // Overflow
        arr[--top2] = x;
    }

    /**
     * Pops from stack 1.
     */
    int pop1() {
        if (top1 == -1) return -1; // Underflow
        return arr[top1--];
    }

    /**
     * Pops from stack 2.
     */
    int pop2() {
        if (top2 == 100) return -1; // Underflow
        return arr[top2++];
    }
}
