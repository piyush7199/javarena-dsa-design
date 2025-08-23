# 📚 Stack and Queue in Java

## Introduction

**Stack and Queue** are fundamental linear data structures used to manage data in specific orders.

- **Stack:** Follows the Last-In-First-Out (LIFO) principle, where the last element added is the first to be removed.
  Implemented as a generic `StackImpl<T>` using arrays, with overflow and underflow handling.
- **Queue:** Follows the First-In-First-Out (FIFO) principle, where the first element added is the first to be removed.
  Implemented as a circular `QueueImpl<T>` using arrays for efficient space utilization.

---

## Intuition

### Stack

- **Idea:** A stack operates like a stack of plates—elements are added (pushed) and removed (popped) from the top. It
  supports
  LIFO operations, making it ideal for reversing, tracking state, or backtracking.
- **Why it Works:** The array-based implementation (StackImpl<T>) maintains a top pointer, enabling O(1) push and pop
  operations. Overflow (when the stack is full) and underflow (when the stack is empty) are handled with checks.
- **Key Insight:** The LIFO property is perfect for problems requiring nested or recursive logic, such as evaluating
  expressions or checking balanced parentheses.

### Queue

- **Idea:** A queue operates like a line of people—elements are added (enqueued) at the rear and removed (dequeued) from
  the
  front. The circular QueueImpl<T> reuses space by wrapping around the array.
- **Why it Works:** The circular array implementation optimizes space by reusing emptied slots, with front and rear
  pointers
  enabling O(1) enqueue and dequeue operations. Checks for full or empty states prevent errors.
- **Key Insight:** The FIFO property suits problems involving sequential processing, such as task scheduling or sliding
  window
  maximums.

---

## Use Cases

Stack and Queue are widely used in:

### Real-World Applications:

- Stack: Undo functionality in editors, function call stack in programming languages, or backtracking in algorithms.
- Queue: Task scheduling in operating systems, message queues in distributed systems, or print job queues.

### Algorithm Optimization:

- Stack: Used in DFS, monotonic stack problems, or conversion of expressions (e.g., infix to postfix).
- Queue: Used in BFS, sliding window techniques, or implementing stacks using queues.

---

## Complexity Analysis

Below is the complexity analysis for key operations in `StackImpl<T>` and `QueueImpl<T>`:

### 🧱 Stack (StackImpl)

- Generic `StackImpl<T>` using arrays
- Overflow & underflow handling
- Utility methods: `top()`, `peek()`, `isEmpty()`

- Time Complexity:

    - Push: O(1) average, O(n) if resizing is needed.
    - Pop: O(1).
    - Peek/Top: O(1).
    - isEmpty: O(1).

- Space Complexity: O(n) for storing n elements, plus O(n) if resizing doubles the array.

### 🔁 Queue (QueueImpl)

- Circular `QueueImpl<T>` with array backing
- Efficient `enqueue()` and `dequeue()`
- Size & capacity checks
- Time Complexity:
    - Enqueue: O(1) average, O(n) if resizing is needed.
    - Dequeue: O(1).
    - Front: O(1).
    - isEmpty/isFull: O(1).

- Space Complexity: O(n) for storing n elements, plus O(n) if resizing doubles the array.

---

## 💻 Problem Tracker

| #  | Problem                                                                                                                                   | Solution File                                                            |
|----|-------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------|
| 1  | [Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/)                                               | [MyQueue.java](./MyQueue.java)                                           |
| 2  | [Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/)                                               | [MyStack.java](./MyStack.java)                                           |
| 3  | [Generate Binary Numbers](https://www.geeksforgeeks.org/problems/generate-binary-numbers-1587115620/1)                                    | [Solution.java](./Solution.java)                                         |
| 4  | [Two Stacks in an Array](https://www.geeksforgeeks.org/problems/implement-two-stacks-in-an-array/1)                                       | [TwoStacks.java](./TwoStacks.java)                                       |
| 5  | [Reverse first K of a Queue](https://www.geeksforgeeks.org/problems/reverse-first-k-elements-of-queue/1)                                  | [Solution.java](./Solution.java)                                         |
| 6  | [Sort a Stack](https://www.geeksforgeeks.org/problems/sort-a-stack/1)                                                                     | [Solution.java](./Solution.java)                                         |
| 7  | [Next Greater Element](https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1)                                           | [Solution.java](./Solution.java)                                         |
| 8  | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)                                                                     | [Solution.java](./Solution.java)                                         |
| 9  | [Postfix Evaluation](https://www.geeksforgeeks.org/problems/evaluation-of-postfix-expression1735/1)                                       | [Solution.java](./Solution.java)                                         |
| 10 | [Min Stack](https://leetcode.com/problems/min-stack/)                                                                                     | [MinStack.java](./MinStack.java)                                         |
| 11 | [Prefix to Infix Conversion](https://www.geeksforgeeks.org/problems/prefix-to-infix-conversion/1)                                         | [StackConversion.java](./StackConversion.java)                           |
| 12 | [Prefix to Postfix Conversion](https://www.geeksforgeeks.org/problems/prefix-to-postfix-conversion/1)                                     | [StackConversion.java](./StackConversion.java)                           |
| 13 | [Postfix to Infix Conversion](https://www.geeksforgeeks.org/problems/postfix-to-infix-conversion/1)                                       | [StackConversion.java](./StackConversion.java)                           |
| 14 | [Postfix to Prefix Conversion](https://www.geeksforgeeks.org/problems/postfix-to-prefix-conversion/1)                                     | [StackConversion.java](./StackConversion.java)                           |
| 15 | [Infix to Postfix](https://www.geeksforgeeks.org/problems/infix-to-postfix-1587115620/1)                                                  | [StackConversion.java](./StackConversion.java)                           |
| 16 | [Convert Infix To Prefix Notation](https://www.geeksforgeeks.org/dsa/convert-infix-prefix-notation/)                                      | [StackConversion.java](./StackConversion.java)                           |
| 17 | [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/)                                                         | [Solution.java](./Solution.java)                                         |
| 18 | [Remove K Digits](https://leetcode.com/problems/remove-k-digits/)                                                                         | [Solution.java](./Solution.java)                                         |
| 19 | [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/)                                                                   | [Solution.java](./Solution.java)                                         |
| 20 | [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)                                                                 | [Solution.java](./Solution.java)                                         |
| 21 | [First negative in every window of size k](https://www.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1) | [Solution.java](./Solution.java)                                         |
| 22 | [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)                                                           | [Solution.java](./Solution.java)                                         |
| 23 | [Online Stock Span](https://leetcode.com/problems/online-stock-span/)                                                                     | [StockSpanner.java](./StockSpanner.java)                                 |
| 24 | [Max Stack](https://leetcode.com/problems/max-stack/)                                                                                     | [MaxStack.java](./MaxStack.java)                                         |
| 25 | [Number of Visible People in a Queue](https://leetcode.com/problems/number-of-visible-people-in-a-queue/)                                 | [NumberOfVisiblePeopleInAQueue.java](NumberOfVisiblePeopleInAQueue.java) |

