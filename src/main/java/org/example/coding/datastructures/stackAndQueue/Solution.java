package org.example.coding.datastructures.stackAndQueue;

import java.util.*;

public class Solution {

    /**
     * Generates binary numbers from 1 to n in string format using BFS.
     *
     * <p><b>Intuition:</b> Use a queue to generate numbers by appending "0" and "1" to previously generated binary strings.
     * This ensures that the numbers are generated in level-order (i.e., lexicographically increasing in binary form).
     *
     * <p><b>Time Complexity:</b> O(n) — One operation per binary number.
     * <p><b>Space Complexity:</b> O(n) — For the queue and output list.
     */
    public static ArrayList<String> generate(int n) {
        ArrayList<String> ans = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("1");
        while (n > 0) {
            String s1 = queue.poll();
            ans.add(s1);
            queue.offer(s1 + "0");
            queue.offer(s1 + "1");
            n--;
        }
        return ans;
    }

    /**
     * Reverses the first k elements of a queue.
     *
     * <p><b>Intuition:</b> Use a stack to reverse the first k elements,
     * then enqueue them back, followed by the remaining queue elements.
     *
     * <p><b>Time Complexity:</b> O(n) — All elements are touched once.
     * <p><b>Space Complexity:</b> O(k) — Stack stores first k elements.
     */
    public Queue<Integer> reverseFirstK(Queue<Integer> q, int k) {
        // code here
        int qSize = q.size();
        if (qSize < k) return q;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < k; i++) {
            st.push(q.poll());
        }
        Queue<Integer> ans = new LinkedList<>();
        while (!st.isEmpty()) {
            ans.offer(st.pop());
        }
        while (!q.isEmpty()) {
            ans.offer(q.poll());
        }
        return ans;
    }


    /**
     * Sorts the elements of a stack in ascending order using only recursion.
     *
     * <p><b>Intuition:</b></p>
     * - Use recursion to pop all elements from the stack.
     * - As recursion unwinds, insert each element back into the sorted position using a helper method.
     * - This mimics insertion sort using recursion and the stack's LIFO nature.
     *
     * <p><b>Time Complexity:</b> O(n^2)</p>
     * - Each element is compared with all other elements in the worst case.
     *
     * <p><b>Space Complexity:</b> O(n)</p>
     * - Due to recursion stack and stack space itself.
     */
    public Stack<Integer> sort(Stack<Integer> s) {
        if (!s.isEmpty()) {
            int ele = s.pop();
            sort(s);
            insert(s, ele);
        }
        return s;
    }

    /**
     * Inserts an element into its correct position in a sorted stack.
     *
     * <p><b>Intuition:</b></p>
     * - Compare the element with the top of the stack.<br>
     * - If it's smaller, keep popping until its correct position is found.<br>
     * - Then reinsert all the popped elements.
     */
    public void insert(Stack<Integer> s, int ele) {
        if (s.isEmpty()) {
            s.push(ele);
        } else {
            int a = s.peek();
            if (ele < a) {
                s.pop();
                insert(s, ele);
                s.push(a);
            } else {
                s.push(ele);
            }
        }
    }

    /**
     * Finds the next greater element for each element in the array.
     *
     * <p><b>Problem:</b></p>
     * Given an array `arr[]`, for each element, find the next greater element to its right.
     * If no such element exists, return -1 for that position.
     *
     * <p><b>Intuition:</b></p>
     * Traverse the array from right to left and use a stack to keep track of possible next greater elements.
     * For each element:
     * - Pop elements from the stack until you find one greater than the current element.
     * - The top of the stack will then be the next greater element.
     * - Push the current element onto the stack for future elements.
     *
     * <p><b>Time Complexity:</b> O(n)</p>
     * - Each element is pushed and popped at most once from the stack.
     *
     * <p><b>Space Complexity:</b> O(n)</p>
     * - Stack and result list both use up to O(n) space.
     */
    public ArrayList<Integer> nextLargerElement(int[] arr) {
        Stack<Integer> resultStack = new Stack<>();
        Stack<Integer> supportStack = new Stack<>();
        int n = arr.length;

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            while (!supportStack.isEmpty() && arr[i] >= supportStack.peek()) {
                supportStack.pop();
            }
            if (supportStack.isEmpty()) {
                resultStack.push(-1);
            } else {
                resultStack.push(supportStack.peek());
            }
            supportStack.push(arr[i]);
        }

        // Reverse stack into ArrayList
        ArrayList<Integer> res = new ArrayList<>();
        while (!resultStack.isEmpty()) {
            res.add(resultStack.pop());
        }

        return res;
    }


    /**
     * Validates if a given string of brackets is properly balanced.
     *
     * <p><b>Problem:</b></p>
     * Given a string `s` containing just the characters '(', ')', '{', '}', '[' and ']',
     * determine if the input string is valid.
     * A string is valid if:
     * - Open brackets are closed by the same type of brackets.
     * - Open brackets are closed in the correct order.
     *
     * <p><b>Intuition:</b></p>
     * - Use a stack to track opening brackets.
     * - When a closing bracket is encountered, check if it matches the top of the stack.
     * - If mismatched or stack is empty, return false.
     * - In the end, the stack should be empty if all brackets were matched correctly.
     *
     * <p><b>Time Complexity:</b> O(n)</p>
     * - Each character is processed once.
     *
     * <p><b>Space Complexity:</b> O(n)</p>
     * - In the worst case, all characters could be pushed to the stack.
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put(')', '(');
        mapping.put('}', '{');
        mapping.put(']', '[');

        for (char c : s.toCharArray()) {
            if (mapping.containsValue(c)) {
                stack.push(c);
            } else if (mapping.containsKey(c)) {
                if (stack.isEmpty() || mapping.get(c) != stack.pop()) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    /**
     * Evaluates the value of a Reverse Polish Notation (RPN) expression.
     *
     * <p><b>Problem:</b></p>
     * Given an array of strings `arr` representing an arithmetic expression in Reverse Polish Notation (RPN),
     * evaluate and return the final integer result.
     * Supported operators are "+", "-", "*", and "/". Each operand is a valid integer.
     * In RPN, operators follow their operands.
     * <p>
     *
     * <p><b>Intuition:</b></p>
     * - Use a stack to store operands.
     * - Traverse the array:
     * - If the element is an operand, push it to the stack.
     * - If the element is an operator, pop the top two operands from the stack,
     * perform the operation, and push the result back to the stack.
     * - The final result will be at the top of the stack after processing all elements.
     *
     * <p><b>Time Complexity:</b> O(n)</p>
     * - Each element in the array is processed exactly once.
     *
     * <p><b>Space Complexity:</b> O(n)</p>
     * - In the worst case, all elements are operands and pushed onto the stack.
     */
    public static int evaluate(String[] arr) {
        Stack<String> stack = new Stack<>();
        Set<String> set = new HashSet<>();
        set.add("+");
        set.add("-");
        set.add("*");
        set.add("/");

        for (String ele : arr) {
            if (set.contains(ele)) {
                int op1 = Integer.parseInt(stack.pop());
                int op2 = Integer.parseInt(stack.pop());
                switch (ele) {
                    case "*" -> stack.push(Integer.toString(op1 * op2));
                    case "-" -> stack.push(Integer.toString(op2 - op1));
                    case "/" -> stack.push(Integer.toString(op2 / op1));
                    default -> stack.push(Integer.toString(op1 + op2));
                }
            } else {
                stack.push(ele);
            }
        }

        return Integer.parseInt(stack.pop());
    }

    /**
     * Finds the Next Greater Element for each element in a circular array.
     *
     * <p><b>Problem:</b> Given a circular array, for each element, find the next greater element.
     * If no such element exists, assign -1.
     *
     * <p><b>Intuition:</b>
     * - Simulate the circular array by iterating 2n times.                                 <br>
     * - Use a monotonic stack to store candidates for next greater elements.               <br>
     * - While traversing from the end, maintain a stack of elements in decreasing order.
     *
     * <p><b>Time Complexity:</b> O(n) — each element is pushed/popped at most once.
     * <br><b>Space Complexity:</b> O(n) — for the stack and output array.
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i % n] >= stack.peek()) {
                stack.pop();
            }
            if (i < n) {
                ans[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums[i % n]);
        }

        return ans;
    }

    /**
     * Removes k digits from the number to get the smallest possible number.
     *
     * <p><b>Problem:</b> Given a string num and an integer k, remove k digits to get
     * the smallest possible number.
     *
     * <p><b>Intuition:</b>
     * - Use a monotonic increasing stack to build the smallest possible number.    <br>
     * - Remove digits from the stack if a smaller digit comes and k > 0.           <br>
     * - Remove extra digits from the end if k > 0 after loop.                      <br>
     * - Strip leading zeros before returning.
     *
     * <p><b>Time Complexity:</b> O(n) — one pass through the string.
     * <br><b>Space Complexity:</b> O(n) — for the stack and result building.
     */
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (n <= k) return "0";

        Stack<Character> stack = new Stack<>();
        for (Character ch : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > ch) {
                stack.pop();
                k--;
            }
            stack.push(ch);
        }

        // Remove extra digits from the end if needed
        while (!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }

        // Build result from stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        sb.reverse();

        // Remove leading zeros
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }

    /**
     * Simulates asteroid collisions and returns the state of the asteroids after all collisions.
     *
     * <p><b>Problem:</b> Each asteroid moves in a straight line with a size. Positive values move right,
     * negative values move left. When two asteroids collide, the smaller one explodes. If both are same size,
     * both explode. You need to return the state after all collisions.
     *
     * <p><b>Intuition:</b>                                                                                 <br>
     * - Use a stack to simulate the collisions.                                                            <br>
     * - Push positive (right-moving) asteroids onto the stack.                                             <br>
     * - For a negative asteroid, simulate collisions by popping smaller positive asteroids.                <br>
     * - Push the negative asteroid if it survives.
     *
     * <p><b>Time Complexity:</b> O(n) — Each asteroid is pushed and popped at most once.
     * <br><b>Space Complexity:</b> O(n) — Stack stores surviving asteroids.
     */
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int a : asteroids) {
            if (a > 0) {
                stack.push(a);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -a) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(a);
                } else if (stack.peek() == -a) {
                    stack.pop();
                }
            }
        }

        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }

        return res;
    }

    /**
     * Calculates the total amount of rainwater that can be trapped between buildings.
     *
     * <p><b>Problem:</b> Given an array of non-negative integers representing elevation map,
     * compute how much water it can trap after raining.
     *
     * <p><b>Intuition:</b>
     * - Use two pointers (`l`, `r`) to move inward from both ends.         <br>
     * - Track max height on both sides (`lMax`, `rMax`).                   <br>
     * - Water trapped at each index is min(lMax, rMax) - height[i].
     *
     * <p><b>Time Complexity:</b> O(n) — Each index is visited once.
     * <br><b>Space Complexity:</b> O(1) — Constant extra space.
     */
    public int trap(int[] height) {
        int rMax = 0, lMax = 0, r = height.length - 1, l = 0, amt = 0;

        while (r > l) {
            if (height[l] <= height[r]) {
                if (lMax > height[l]) {
                    amt += lMax - height[l];
                } else {
                    lMax = height[l];
                }
                l++;
            } else {
                if (rMax > height[r]) {
                    amt += rMax - height[r];
                } else {
                    rMax = height[r];
                }
                r--;
            }
        }

        return amt;
    }
}
