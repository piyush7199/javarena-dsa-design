# 🔄 Recursion and Backtracking

## Introduction

**Recursion** is a problem-solving technique where a function calls itself to solve smaller instances of the same
problem. **Backtracking** is a refined recursive approach that systematically explores all possible solutions by
building a solution incrementally and abandoning (backtracking) partial solutions that fail to satisfy constraints.
These techniques are fundamental for solving combinatorial, search, and optimization problems, such
as generating permutations, solving puzzles, or navigating mazes.

Key concepts:

- **Recursion**: Breaks a problem into smaller subproblems, leveraging the call stack to manage state.
- **Backtracking**: Explores all possible configurations, pruning invalid branches to optimize search.
- **Examples**: N-Queens, Subsets, Sudoku Solver, Permutations.

---

## Intuition

Recursion and backtracking are like exploring a maze:

- **Recursion**: You take a step forward, solve a smaller maze, and combine results to solve the larger maze. Each
  recursive call reduces the problem size, and the call stack tracks your position.
- **Backtracking**: You try all possible paths in the maze, but when you hit a dead end, you backtrack to the last valid
  position and try another path. This ensures all solutions (or the optimal one) are found.
- **Key Insight**:
    - Recursion relies on a base case to terminate and a recursive case to break down the problem.
    - Backtracking builds partial solutions, checks constraints, and undoes choices (backtracks) when constraints fail,
      effectively pruning the search space.
- **Example**: In the N-Queens problem, place queens row by row, checking for valid placements. If a placement leads to
  a conflict, backtrack and try another position.

---

## Use Cases

Recursion and backtracking are used in:

- **Real-World Applications**:
    - **Game Development**: Solving puzzles or generating valid game states (e.g., chess move generation).
    - **AI and Search**: Exploring decision trees in AI algorithms or constraint satisfaction problems.
    - **Compilers**: Parsing expressions using recursive descent parsers.
    - **File Systems**: Traversing directory trees recursively.
- **Algorithm Optimization**:
    - Backtracking optimizes exhaustive search by pruning invalid branches (e.g., avoiding invalid queen placements in
      N-Queens).
    - Recursion simplifies complex problems by breaking them into manageable subproblems.

---

## Complexity Analysis

Complexity varies by problem, but general guidelines for recursion and backtracking are:

- **Time Complexity**:
    - **Recursion**: Depends on the number of recursive calls and work per call. For example:
        - Factorial: O(n) calls, O(1) work per call → O(n).
        - Fibonacci (naive): O(2^n) calls due to branching → O(2^n).
    - **Backtracking**: Depends on the solution space size and pruning efficiency. For example:
        - N-Queens: O(n!) for exploring all possible queen placements, reduced by pruning.
        - Subsets: O(2^n) for generating all subsets of n elements.
    - Pruning (via constraints) can significantly reduce time complexity in backtracking.
- **Space Complexity**:
    - **Recursion**: O(h) for the call stack, where h is the recursion depth (e.g., O(n) for linear recursion, O(log n)
      for divide-and-conquer).
    - **Backtracking**: O(h) for the call stack plus space for partial solutions (e.g., O(n) for N-Queens to store the
      board).
    - Additional space may be needed for storing results (e.g., O(2^n) for storing all subsets).

> **Note**: Memoization can reduce time complexity in recursive problems (e.g., Fibonacci from O(2^n) to O(n)) but
> increases space complexity.


---

## 🧪 Practice Problems

| # | Problem                                                                                                       | Solution                                                                        |
|---|---------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| 1 | [Pow (x,n)](https://leetcode.com/problems/powx-n/)                                                            | [RecursionMyPow.java](./recursionAndBacktracking/MyPow.java)                    |
| 2 | [N Queens](https://leetcode.com/problems/n-queens/)                                                           | [NQueens.java](./recursionAndBacktracking/NQueens.java)                         |
| 3 | [Rat in a Maze Problem - I](https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1)                   | [MazeProblems.java](./recursionAndBacktracking/MazeProblems.java)               |
| 4 | [Word Break](https://leetcode.com/problems/word-break/)                                                       | [RecursionWordBreak.java](./recursionAndBacktracking/WordBreak.java)            |
| 5 | [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/)                                                 | [SudokuSolver.java](./recursionAndBacktracking/SudokuSolver.java)               |
| 6 | [Word Search](https://leetcode.com/problems/word-search/)                                                     | [WordSearch.java](recursionAndBacktracking/WordSearch.java)                     |
| 7 | [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/) | [LetterCombination.java](./recursionAndBacktracking/LetterCombination.java)     |
| 8 | [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)                                   | [GenerateParentheses.java](./recursionAndBacktracking/GenerateParentheses.java) |