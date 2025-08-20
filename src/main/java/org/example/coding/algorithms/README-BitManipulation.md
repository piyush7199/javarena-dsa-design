# 🔢 Bit Manipulation

## Introduction

**Bit Manipulation** involves operating on numbers at the binary level using bitwise operations (AND, OR, XOR, NOT,
shifts) to solve problems efficiently. Common techniques include counting set bits, finding unique elements (e.g., XOR
properties), checking powers of two, and using bitmasks for state management.

Key characteristics:

- Operates on bits (0s and 1s) using operators like `&` (AND), `|` (OR), `^` (XOR), `~` (NOT), `<<` (left shift), and
  `>>` (right shift).
- Reduces memory usage (e.g., using bits to represent flags) and improves performance for certain operations.
- Frequently tested in problems like “Single Number,” “Power of Two,” and “Bitwise AND of Numbers Range.”

---

## Intuition

Bit manipulation is like solving puzzles at the binary level:

- **Idea**: Use bitwise operations to manipulate individual bits, enabling efficient solutions for problems involving
  counting, searching, or state representation.
- **Key Operations**:
    - **AND (`&`)**: Useful for checking if a bit is set (e.g., `n & 1` checks the least significant bit).
    - **OR (`|`)**: Sets bits (e.g., combining flags).
    - **XOR (`^`)**: Finds differences (e.g., `a ^ a = 0`, `a ^ 0 = a`), ideal for problems like finding a single number
      in an array.
    - **NOT (`~`)**: Inverts bits (e.g., `~0 = -1` in 2’s complement).
    - **Shifts (`<<`, `>>`)**: Move bits left or right, useful for multiplying/dividing by powers of 2 or extracting
      bits.
- **Bitmasks**: Use patterns of bits to select, toggle, or check specific bits (e.g., `n & (1 << k)` checks if the k-th
  bit is set).
- **Key Insight**:
    - Bit manipulation avoids expensive arithmetic or iterative operations, achieving O(1) or O(log n) time for many
      problems.
    - XOR is particularly powerful for problems with paired elements (e.g., finding a single number in an array where
      others appear twice).
- **Example**: To count set bits in 5 (binary: 101), check each bit using `n & 1` and right-shift `n` until it becomes
  0, yielding 2 set bits.

---

## Use Cases

Bit manipulation is used in:

- **Real-World Applications** (Relevant to Backend Development):
    - **Flag Management**: Store configurations or permissions in a single integer (e.g., role-based access control in
      Spring Security).
    - **Data Compression**: Encode data efficiently in databases or message queues (e.g., Kafka message headers).
    - **Database Query Optimization**: Use bitmasks for filtering or indexing in NoSQL databases.
    - **Networking**: Manipulate IP addresses or packet headers (e.g., subnet masks).
    - **Performance Optimization**: Optimize arithmetic operations in microservices (e.g., replacing division with right
      shifts).
- **Algorithm Optimization**:
    - Reduces time complexity for operations like bit counting (O(log n) to O(1) with lookup tables).
    - Minimizes memory usage in systems handling large datasets or flags.

> **Note**: Bit manipulation is highly efficient for problems requiring constant-time operations or minimal memory. For
> 32-bit integers, operations are bounded by O(32) = O(1) in practice.

---

## Algorithm

Below are pseudocode and Java implementations for three common bit manipulation problems: **Counting Set Bits**, *
*Finding Single Number**, and **Checking Power of Two**.

### Counting Set Bits

- **Intuition**: Check each bit using AND with 1, increment count if set, and right-shift to process the next bit.
- **Time Complexity**: O(log n), where n is the number (iterates over each bit, up to 32 for an integer).
- **Space Complexity**: O(1).
- Pseudocode

```pseudo
function countSetBits(n):
    # Input: n (integer)
    # Output: Number of 1s in binary representation of n
    count = 0
    while n > 0:
        count += n & 1  # Check least significant bit
        n >>= 1         # Right shift to check next bit
    return count
```

### Finding Single Number

- **Intuition**: XOR of a number with itself is 0, and XOR with 0 is the number itself. Thus, paired numbers cancel out,
  leaving the single number.
- **Time Complexity**: O(n), where n is the array length (single pass).
- **Space Complexity**: O(1).
- Pseudocode

```pseudo
function singleNumber(arr):
    # Input: arr (array where all elements appear twice except one)
    # Output: The single number
    result = 0
    for num in arr:
        result ^= num  # XOR cancels out paired numbers
    return result
```

### Checking Power of Two

- **Intuition**: A power of two has exactly one bit set (e.g., 4 = 100, 8 = 1000). Subtracting 1 flips all bits after
  the rightmost 1, so `n & (n-1)` clears the rightmost 1, resulting in 0 for powers of two.

- **Time Complexity**: O(1), using a single bitwise operation.
- **Space Complexity**: O(1).
- Pseudocode (Checking Power of Two)

```pseudo
function isPowerOfTwo(n):
    # Input: n (integer)
    # Output: True if n is a power of 2, false otherwise
    if n <= 0:
        return false
    return (n & (n - 1)) == 0
```

---

## Practice Problems

Below is a table to track bit manipulation problems from platforms like LeetCode, GeeksforGeeks (GFG), and Codeforces.
Add problems you solve, including their names, platforms, links, and solution file paths.

| # | Problem                                                                                                              | Solution                                                                                 |
|---|----------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------|
| 1 | [Longest Subarray With Maximum Bitwise AND](https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and) | [LongestSubarrayWithMaxBitAnd.java](./bitManupulation/LongestSubarrayWithMaxBitAnd.java) |
| 2 | [Bitwise ORs of Subarrays](https://leetcode.com/problems/bitwise-ors-of-subarrays/)                                  | [BitOrSubarray.java](./bitManupulation/BitOrSubarray.java)                               |
| 3 | [Power of Two](https://leetcode.com/problems/power-of-two/)                                                          |                                                                                          |
| 4 | [Sum of Two Integers](https://leetcode.com/problems/sum-of-two-integers/)                                            | [SumOfTwoIntegers.java](./bitManupulation/SumOfTwoIntegers.java)                         |
| 5 | [Check K-th Bit](https://www.geeksforgeeks.org/problems/check-whether-k-th-bit-is-set-or-not-1587115620/1)           | [CheckKthBit.java](./bitManupulation/CheckKthBit.java)                                   |
| 6 | [Number of 1 Bits](https://www.geeksforgeeks.org/problems/set-bits0143/1)                                            | [NoOfOneBits.java](./bitManupulation/NoOfOneBits.java)                                   |
| 7 | [Set the rightmost unset bit](https://www.geeksforgeeks.org/problems/set-the-rightmost-unset-bit4436/1)              | [SetTheRightMostBit.java](./bitManupulation/SetTheRightMostBit.java)                     |
| 8 | [Swap two numbers](https://www.geeksforgeeks.org/problems/swap-two-numbers3844/1)                                    | [SwapTwoNumbers.java](./bitManupulation/SwapTwoNumbers.java)                             |