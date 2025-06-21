# 📚 Stack and Queue in Java

This module provides:

- ✅ Custom **generic implementations** of stack and queue data structures.
- 🧠 Practice solutions to popular **coding problems** from platforms like **LeetCode** and **GeeksforGeeks**
- 📖 Clear examples to reinforce data structure fundamentals.

---

## 🧱 Stack Implementation (`StackImpl<T>`)

```
package org.example.coding.datastructures.stackAndQueue;
```

### ✨ Features

- Generic type support (`T`)
- Array-backed, fixed-size stack
- Core methods: `push()`, `pop()`, `peek()`
- Overflow and underflow checks
- Utility methods: `isEmpty()`, `isFull()`, `top()`, `getCapacity()`

### 📘 Example Usage

```
StackImpl<Integer> stack = new StackImpl<>(5);
stack.push(10);
stack.push(20);
System.out.println(stack.pop());  // Output: 20
System.out.println(stack.peek()); // Output: 10
```

---

## 🔁 Queue Implementation (`QueueImpl<T>`)

```
package org.example.coding.datastructures.stackAndQueue;
```

### ✨ Features

- Generic type support (`T`)
- Circular array-based queue
- Fixed maximum size
- Core methods: `enqueue()`, `dequeue()`, `peek()`
- Overflow and underflow handling
- Utility methods: `isEmpty()`, `isFull()`, `getSize()`

### 📘 Example Usage

```
QueueImpl<Integer> queue = new QueueImpl<>(5);
queue.enqueue(10);
queue.enqueue(20);
System.out.println(queue.dequeue());  // Output: 10
System.out.println(queue.peek());     // Output: 20
```

---

## 💻 Problem Tracker

| #  | Problem                                                                                                  | Solution File                                  |
|----|----------------------------------------------------------------------------------------------------------|------------------------------------------------|
| 1  | [Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/)              | [MyQueue.java](./MyQueue.java)                 |
| 2  | [Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/)              | [MyStack.java](./MyStack.java)                 |
| 3  | [Generate Binary Numbers](https://www.geeksforgeeks.org/problems/generate-binary-numbers-1587115620/1)   | [Solution.java](./Solution.java)               |
| 4  | [Two Stacks in an Array](https://www.geeksforgeeks.org/problems/implement-two-stacks-in-an-array/1)      | [TwoStacks.java](./TwoStacks.java)             |
| 5  | [Reverse first K of a Queue](https://www.geeksforgeeks.org/problems/reverse-first-k-elements-of-queue/1) | [Solution.java](./Solution.java)               |
| 6  | [Sort a Stack](https://www.geeksforgeeks.org/problems/sort-a-stack/1)                                    | [Solution.java](./Solution.java)               |
| 7  | [Next Greater Element](https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1)          | [Solution.java](./Solution.java)               |
| 8  | [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)                                    | [Solution.java](./Solution.java)               |
| 9  | [Postfix Evaluation](https://www.geeksforgeeks.org/problems/evaluation-of-postfix-expression1735/1)      | [Solution.java](./Solution.java)               |
| 10 | [Min Stack](https://leetcode.com/problems/min-stack/)                                                    | [MinStack.java](./MinStack.java)               |
| 11 | [Prefix to Infix Conversion](https://www.geeksforgeeks.org/problems/prefix-to-infix-conversion/1)        | [StackConversion.java](./StackConversion.java) |
| 12 | [Prefix to Postfix Conversion](https://www.geeksforgeeks.org/problems/prefix-to-postfix-conversion/1)    | [StackConversion.java](./StackConversion.java) |
| 13 | [Postfix to Infix Conversion](https://www.geeksforgeeks.org/problems/postfix-to-infix-conversion/1)      | [StackConversion.java](./StackConversion.java) |
| 14 | [Postfix to Prefix Conversion](https://www.geeksforgeeks.org/problems/postfix-to-prefix-conversion/1)    | [StackConversion.java](./StackConversion.java) |
| 15 | [Infix to Postfix](https://www.geeksforgeeks.org/problems/infix-to-postfix-1587115620/1)                 | [StackConversion.java](./StackConversion.java) |
| 16 | [Convert Infix To Prefix Notation](https://www.geeksforgeeks.org/dsa/convert-infix-prefix-notation/)     | [StackConversion.java](./StackConversion.java) |
| 17 | [Next Greater Element II](https://leetcode.com/problems/next-greater-element-ii/description/)            | [Solution.java](./Solution.java)               |
| 18 | [Remove K Digits](https://leetcode.com/problems/remove-k-digits/description/)                            | [Solution.java](./Solution.java)               |

