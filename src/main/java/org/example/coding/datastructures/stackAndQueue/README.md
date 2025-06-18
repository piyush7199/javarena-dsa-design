# 📚 Stack and Queue in Java

This module contains custom **generic** implementations of:

- 🥞 `StackImpl<T>` – Fixed-size stack using array
- 🚶 `QueueImpl<T>` – Basic queue implementation (FIFO)

Additionally, this folder will include coding problems related to **stack** and **queue** data structures from platforms
like **LeetCode**, **GFG**, and **Educative.io**.

---

## 🧱 Stack Implementation (`StackImpl<T>`)

package org.example.coding.datastructures.stackAndQueue;

### ✨ Features

- Generic type support `T`
- Fixed size stack using array
- `push()`, `pop()`, `peek()` methods
- Overflow/underflow checks
- `isEmpty()`, `isFull()`, `top()`, `getCapacity()`

### 📘 Example Usage

```
StackImpl<Integer> stack = new StackImpl<>(5);
stack.push(10);
stack.push(20);
System.out.println(stack.pop());  // 20
System.out.println(stack.peek()); // 10
```

---

## 🔁 Queue Implementation (`QueueImpl<T>`)

```java
package org.example.coding.datastructures.stackAndQueue;
```

### ✨ Features

- Generic type support `T`
- Circular array-based queue
- Fixed maximum size
- `enqueue()`, `dequeue()`, `peek()` methods
- Overflow/underflow checks
- `isEmpty()`, `isFull()`, `getSize()`

### 📘 Example Usage

```
QueueImpl<Integer> queue = new QueueImpl<>(5);
queue.enqueue(10);
queue.enqueue(20);
System.out.println(queue.dequeue());  // 10
System.out.println(queue.peek());     // 20
```

---

## 📈 Problem Tracker

| # | Problem                                                                                                  | Solution                           |
|---|----------------------------------------------------------------------------------------------------------|------------------------------------|
| 1 | [Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/description/)  | [MyQueue.java](./MyQueue.java)     |
| 2 | [Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/description/)  | [MyStack.java](./MyStack.java)     |
| 3 | [Generate Binary Numbers](https://www.geeksforgeeks.org/problems/generate-binary-numbers-1587115620/1)   | [Solution.java](./Solution.java)   |
| 4 | [Two Stacks in an Array](https://www.geeksforgeeks.org/problems/implement-two-stacks-in-an-array/1)      | [TwoStacks.java](./TwoStacks.java) |
| 5 | [Reverse first K of a Queue](https://www.geeksforgeeks.org/problems/reverse-first-k-elements-of-queue/1) | [Solution.java](./Solution.java)   |
