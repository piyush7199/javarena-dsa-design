# 🔤 String Algorithms

## Introduction

**String Algorithms** are techniques for efficiently processing and manipulating strings, widely used in problems
involving pattern matching, substring search, and string transformations. Key algorithms include **Knuth-Morris-Pratt (
KMP)** for pattern matching and **Rabin-Karp** for substring search using rolling hashes. These algorithms optimize
operations that would otherwise be computationally expensive (e.g., O(n*m) brute-force matching reduced to O(n+m) with
KMP).

Key characteristics:

- Work on strings or sequences of characters.
- Optimize tasks like finding substrings, longest palindromes, or common subsequences.
- Often leverage preprocessing (e.g., KMP’s prefix table) or hashing (e.g., Rabin-Karp’s rolling hash).

---

## Intuition

String algorithms are like finding a needle in a haystack efficiently:

- **KMP Algorithm**: Uses a prefix table (LPS array) to avoid redundant comparisons in pattern matching. It preprocesses
  the pattern to know how far to skip when a mismatch occurs, making it efficient for searching a pattern in a text.
- **Rabin-Karp Algorithm**: Uses a rolling hash to compute hash values for substrings of the text, comparing them with
  the pattern’s hash. It’s like checking fingerprints before comparing strings character-by-character.
- **Key Insight**:
    - **KMP**: Avoids backtracking in the text by leveraging pattern self-similarity, achieving O(n+m) time complexity.
    - **Rabin-Karp**: Reduces comparisons using hashing, with average-case efficiency but potential worst-case overhead
      due to hash collisions.
- **Example**: In KMP, searching “ABABC” in “ABABDABABC” uses the LPS array to skip unnecessary checks, while Rabin-Karp
  hashes substrings to quickly identify potential matches.

---

## Use Cases

String algorithms are used in:

- **Real-World Applications** (Relevant to Backend Development):
    - **Text Processing**: Parsing API payloads or logs (e.g., JSON string matching).
    - **Database Query Optimization**: Efficient string matching in SQL queries or NoSQL key searches.
    - **Search Engines**: Indexing and searching text (e.g., autocomplete with tries or KMP for exact matches).
    - **Data Validation**: Validating input strings in backend APIs (e.g., regex-like matching).
    - **Bioinformatics**: DNA sequence alignment using string algorithms.
- **Algorithm Optimization**:
    - Reducing time complexity for string operations in large-scale systems (e.g., Rabin-Karp for log analysis).
    - Solving problems requiring efficient substring or palindrome detection.

---

## Algorithm

Below are pseudocode and Java implementations for **KMP** and **Rabin-Karp** algorithms.

#### KMP Algorithm

**Intuition**: Preprocess the pattern to build an LPS (Longest Prefix Suffix) array, which indicates where to resume
matching after a mismatch. Use this to skip redundant comparisons in the text.

- **Time Complexity**:
    - Preprocessing (LPS array): O(m), where m is the pattern length.
    - Matching: O(n), where n is the text length.
    - Total: O(n + m).
- **Space Complexity**:
    - O(m) for the LPS array.
    - Total: O(m).

> Java Implementation - [KMPSearch.java](./string/KMPSearch.java)

```
function kmpSearch(text, pattern):
    # Input: text (string), pattern (string)
    # Output: Index of first occurrence of pattern in text, or -1 if not found
    n = length of text
    m = length of pattern
    lps = computeLPSArray(pattern)
    i = 0  # Index for text
    j = 0  # Index for pattern

    while i < n:
        if text[i] == pattern[j]:
            i += 1
            j += 1
        if j == m:
            return i - j  # Pattern found
        else if i < n and text[i] != pattern[j]:
            if j > 0:
                j = lps[j-1]
            else:
                i += 1
    return -1

function computeLPSArray(pattern):
    m = length of pattern
    lps = array of size m initialized to 0
    len = 0  # Length of previous longest prefix suffix
    i = 1

    while i < m:
        if pattern[i] == pattern[len]:
            len += 1
            lps[i] = len
            i += 1
        else:
            if len > 0:
                len = lps[len-1]
            else:
                lps[i] = 0
                i += 1
    return lps
```

> **Note**: KMP is preferred for exact pattern matching with guaranteed linear time, while Rabin-Karp is useful for
> multiple pattern matching or when hashing is advantageous. Both are critical for FAANG problems requiring efficient
> string processing.

### Rabin-Karp Algorithm

**Intuition**: Compute a hash for the pattern and each substring of the text using a rolling hash. If hashes match,
verify character-by-character to avoid collisions.

- **Time Complexity**:
    - Preprocessing (pattern hash): O(m).
    - Matching: O(n-m+1) average case (single hash comparison per substring), O(n*m) worst case (due to hash
      collisions).
    - Total: O(n + m) average, O(n*m) worst case.
- **Space Complexity**:
    - O(1) for hash values and temporary variables.
    - Total: O(1).

> Java Implementation - [RobinKarpSearch.java](./string/RobinKarpSearch.java)

```
function rabinKarpSearch(text, pattern, d, q):
    # Input: text (string), pattern (string), d (base for hashing), q (prime for modulo)
    # Output: Index of first occurrence of pattern in text, or -1 if not found
    n = length of text
    m = length of pattern
    h = pow(d, m-1) mod q  # For rolling hash
    p = 0  # Pattern hash
    t = 0  # Text substring hash

    for i from 0 to m-1:
        p = (d * p + pattern[i]) mod q
        t = (d * t + text[i]) mod q

    for i from 0 to n-m:
        if p == t:
            match = true
            for j from 0 to m-1:
                if pattern[j] != text[i+j]:
                    match = false
                    break
            if match:
                return i
        if i < n-m:
            t = (d * (t - text[i] * h) + text[i+m]) mod q
            if t < 0:
                t += q
    return -1
```

---

## 🧪 Practice Problems

| # | Problem                                                                                         | Solution                                                      |
|---|-------------------------------------------------------------------------------------------------|---------------------------------------------------------------|
| 1 | [KMP Search](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/) | [KMPSearch.java](./string/KMPSearch.java)                     |
| 2 | [Search Pattern (KMP-Algorithm)](https://www.geeksforgeeks.org/problems/search-pattern0205/1)   | [FindPatternsIndexes.java](./string/FindPatternsIndexes.java) |
| 3 | [Repeated String Match](https://leetcode.com/problems/repeated-string-match/)                   | [RepeatedStringMatch.java](./string/RepeatedStringMatch.java) |
| 4 | [Shortest Palindrome](https://leetcode.com/problems/shortest-palindrome/)                       | [ShortestPalindrome.java](./string/ShortestPalindrome.java)   |
