# 🌲 Trie (Prefix Tree)

## Introduction

A **Trie** (or **Prefix Tree**) is an advanced tree-based data structure used for efficiently storing and retrieving
strings, particularly for prefix-based operations like autocomplete, dictionary lookups, and pattern matching. It is a
staple problems involving string searches, prefix matching, or word validation (e.g., “Implement
Trie,” “Word Search II”). Each node in a Trie represents a character, and paths from the root to leaf nodes form words
or prefixes.

Key characteristics:

- Organizes strings as a tree where each edge represents a character.
- Supports **insertion**, **search**, and **prefix search** operations efficiently.
- Ideal for problems requiring prefix-based lookups or autocomplete functionality.

## Intuition

A Trie is like a dictionary organized as a tree, where each node stores a character and paths represent words or
prefixes:

- **Idea**: Each node has an array or map of child nodes (one for each possible character, typically 26 for lowercase
  letters). A boolean flag indicates whether a node represents the end of a word.
- **Key Operations**:
    - **Insert**: Traverse or create nodes for each character of the word, marking the final node as a word’s end.
    - **Search**: Traverse nodes for the word’s characters, checking if the final node is marked as a word.
    - **Prefix Search**: Traverse nodes for the prefix’s characters, checking if the prefix exists (no need for the
      final node to be a word).
- **Key Insight**:
    - Tries store common prefixes once, reducing redundancy and enabling O(m) time complexity for operations, where m is
      the word length.
    - Unlike hash tables, Tries support prefix-based queries efficiently.
- **Example**: Inserting “cat” and “car” creates a Trie with a shared path for “ca,” followed by branches for “t” and
  “r.” Searching for “cat” follows the path c → a → t and checks the word flag.

## Use Cases

Tries are used in:

- **Real-World Applications** (Relevant to Backend Development):
    - **Search Engines**: Implement autocomplete or spell-check in APIs (e.g., Elasticsearch query suggestions).
    - **Database Query Optimization**: Efficient prefix-based searches in NoSQL databases (e.g., MongoDB text indexes).
    - **Routing Systems**: Match URL patterns in Spring Boot or API gateways.
    - **Data Validation**: Validate or suggest input strings in backend APIs (e.g., form autocompletion).
    - **Text Processing**: Analyze logs or messages in Kafka streams for pattern matching.
- **Algorithm Optimization**:
    - Reduces time complexity for prefix-based operations from O(n*m) (naive string matching) to O(m) (Trie-based).
    - Minimizes memory for shared prefixes in large string datasets.

## Algorithm

Below are pseudocode and Java implementations for constructing a Trie, inserting words, searching for words, and
checking prefixes.

**Intuition**:

- **Insert**: Create or traverse nodes for each character, marking the final node as a word’s end.
    - **Time Complexity**: O(m), where m is the word length (traverse/create m nodes).
    - **Space Complexity**: O(m) per word, but shared prefixes reduce total space for similar words.
- **Search**: Traverse nodes for the word, checking if the final node is marked as a word.
    - **Time Complexity**: O(m), where m is the word length (traverse m nodes).
    - **Space Complexity**: O(1) additional space.
- **Prefix Search**: Traverse nodes for the prefix, ensuring the path exists.
    - **Time Complexity**: O(m), where m is the prefix length.
    - **Space Complexity**: O(1) additional space.

> **Note**: Tries are more space-intensive than hash tables but excel in prefix-based operations, making them ideal for
> autocomplete or dictionary problems.

### Pseudocode (Trie Operations)

```pseudo
class TrieNode:
    children = array or map of child nodes (e.g., 26 for lowercase letters)
    isEndOfWord = boolean indicating if node ends a word

function insert(root, word):
    # Insert word into Trie
    node = root
    for char in word:
        if char not in node.children:
            node.children[char] = new TrieNode()
        node = node.children[char]
    node.isEndOfWord = true

function search(root, word):
    # Search for word in Trie
    node = root
    for char in word:
        if char not in node.children:
            return false
        node = node.children[char]
    return node.isEndOfWord

function startsWith(root, prefix):
    # Check if prefix exists in Trie
    node = root
    for char in prefix:
        if char not in node.children:
            return false
        node = node.children[char]
    return true
```

## Practice Problems

| # | Problem                                                                                               | Solution                 |
|---|-------------------------------------------------------------------------------------------------------|--------------------------|
| 1 | [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/description/) | [Trie.java](./Trie.java) |

