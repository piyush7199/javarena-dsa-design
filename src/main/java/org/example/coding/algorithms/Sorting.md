# 🔢 Sorting Algorithms

## Introduction

**Sorting Algorithms** arrange elements of a list or array in a specific order (e.g., ascending or descending). Common
sorting algorithms include:

- **Selection Sort:** Repeatedly selects the minimum element and places it at the beginning.
- **Bubble Sort:** Repeatedly swaps adjacent elements if they are in the wrong order.
- **Insertion Sort:** Inserts each element into its correct position in a sorted subarray.
- **QuickSort:** A divide-and-conquer algorithm with average-case efficiency.
- **MergeSort:** A stable, divide-and-conquer algorithm with guaranteed performance.
- **HeapSort:** Uses a heap for sorting.

---

## Intuition

Each sorting algorithm has a unique approach to arranging elements:

- **Selection Sort:**
    - **Idea:** Find the smallest element in the unsorted portion and place it at the start. Repeat until the array is
      sorted.
    - **Why it Works:** By selecting the minimum element in each iteration, the sorted portion grows from left to
      right.
    - **Key Insight:** Simple but inefficient due to O(n²) comparisons, not adaptive to partially sorted arrays.

- **Bubble Sort:**
    - **Idea:** Repeatedly compare and swap adjacent elements if they are out of order, “bubbling” larger elements to
      the end.
    - **Why it Works:** Each pass ensures the largest unsorted element moves to its correct position.
    - **Key Insight:** Stable but slow, best for small or nearly sorted arrays.

- **Insertion Sort:**
    - **Idea:** Build a sorted subarray by inserting each element into its correct position, shifting others as needed.
    - **Why it Works:** Mimics how people sort cards, efficient for small or partially sorted arrays.
    - **Key Insight:** Stable and adaptive, performs well when data is nearly sorted.

- **Merge Sort:**
    - **Idea:** Divide the array into two halves, recursively sort them, and merge the sorted halves.
    - **Why it Works:** Merging two sorted arrays is linear, and the divide step ensures logarithmic depth.
    - **Key Insight:** Stable and guaranteed O(n log n), ideal for large datasets but requires extra space.

- **Quick Sort:**
    - **Idea:** Choose a pivot, partition the array into elements less than and greater than the pivot, and recursively
      sort the partitions.
    - **Why it Works:** Good pivot choices lead to balanced partitions, achieving O(n log n) average time.
    - **Key Insight:** Fast in practice but not stable, with O(n²) worst case for bad pivots (e.g., sorted arrays).

- **HeapSort:**
    - **Idea:** Build a max-heap from the array, repeatedly extract the maximum element, and place it at the end of
      the sorted portion.
    - **Why it Works:** The heap property ensures the largest element is at the root, and heapify maintains the heap in
      O(log n)      per operation.
    - **Key Insight:** In-place and guaranteed O(n log n), but not stable and slower in practice than Quick Sort due to
      cache inefficiency.

---

## Use Cases

Sorting algorithms are widely used in:

- **Real-World Applications:** Sorting data in databases, spreadsheets, e-commerce product listings, or search results.
- **Algorithm Optimization:** Preprocessing step for algorithms requiring sorted data (e.g., Binary Search, event
  scheduling).
- **Specific Use Cases:**
    * **Selection Sort:** Educational purposes or small datasets due to simplicity.
    * **Bubble Sort:** Small datasets or teaching, rarely used in practice.
    * **Insertion Sort:** Small or nearly sorted datasets (e.g., online sorting where data arrives incrementally).
    * **Merge Sort:** Large datasets, external sorting (e.g., disk-based sorting), stable sorting needs.
    * **Quick Sort:** General-purpose sorting due to average-case efficiency, used in standard libraries (e.g., Java’s
      `Arrays.sort`).
    * **Heap Sort:** Scenarios requiring guaranteed O(n log n) with minimal extra space (e.g., embedded systems,
      priority queue-based problems).

---

## Complexity Analysis

The following table summarizes the complexity of the sorting algorithms implemented
in [SortingAlgorithms.java](./SortingAlgorithms.java):

| Algorithm      | Time Complexity (Average) | Time Complexity (Worst) | Space Complexity | Stable |
|----------------|---------------------------|-------------------------|------------------|--------|
| Selection Sort | O(n²)                     | O(n²)                   | O(1)             | No     |
| Bubble Sort    | O(n²)                     | O(n²)                   | O(1)             | Yes    |
| Insertion Sort | O(n²)                     | O(n²)                   | O(1)             | Yes    |
| Merge Sort     | O(n log n)                | O(n log n)              | O(n)             | Yes    |
| Quick Sort     | O(n log n)                | O(n²)                   | O(log n)         | No     |
| Heap Sort      | O(n log n)                | O(n log n)              | O(1)             | No     |

* **Selection Sort:** O(n²) comparisons and swaps, minimal space, not adaptive.
* **Bubble Sort:** O(n²) comparisons and swaps, adaptive for nearly sorted arrays (O(n) best case).
* **Insertion Sort:** O(n²) comparisons and shifts, adaptive for nearly sorted arrays (O(n) best case).
* **Merge Sort:** O(n log n) for all cases, requires O(n) extra space for merging.
* **Quick Sort:** O(n log n) average, O(n²) worst case (e.g., sorted array with bad pivot), O(log n) recursion stack.
* **Heap Sort:** O(n log n) for building and extracting from the heap, O(1) auxiliary space (in-place).

> Note: Stability matters when sorting objects with multiple keys (e.g., sorting by name then age). Merge Sort,
> Insertion
> Sort, and Bubble Sort are stable, while Selection Sort, Quick Sort, and Heap Sort are not.

---

## Algorithms

### Selection Sort

**Intuition:** In each iteration, find the minimum element in the unsorted portion and place it at the beginning,
growing the sorted portion.

```
function selectionSort(arr):
    # Input: array arr
    # Output: sorted array (in-place)
    for i from 0 to arr.length - 1:
        minIndex = i
        for j from i + 1 to arr.length - 1:
            if arr[j] < arr[minIndex]:
                minIndex = j
        swap arr[i] and arr[minIndex]
    return arr
```

### Bubble Sort

**Intuition:** Repeatedly compare adjacent elements and swap them if out of order, pushing larger elements to the end in
each pass.

```
function bubbleSort(arr):
    # Input: array arr
    # Output: sorted array (in-place)
    for i from 0 to arr.length - 1:
        for j from 0 to arr.length - i - 2:
            if arr[j] > arr[j + 1]:
                swap arr[j] and arr[j + 1]
    return arr
```

### Insertion Sort

**Intuition:** Build a sorted subarray by inserting each element into its correct position, shifting larger elements as
needed.

```
function insertionSort(arr):
    # Input: array arr
    # Output: sorted array (in-place)
    for i from 1 to arr.length - 1:
        key = arr[i]
        j = i - 1
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        arr[j + 1] = key
    return arr
```

### Merge Sort

**Intuition:** Divide the array into halves, recursively sort them, and merge the sorted halves by comparing elements in
order.

```
function mergeSort(arr):
    # Input: array arr
    # Output: sorted array
    if arr.length <= 1:
        return arr
    mid = arr.length / 2
    left = mergeSort(arr[0..mid-1])
    right = mergeSort(arr[mid..arr.length-1])
    return merge(left, right)

function merge(left, right):
    result = []
    i = 0, j = 0
    while i < left.length and j < right.length:
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    result.append(left[i..])
    result.append(right[j..])
    return result
```

### Quick Sort

**Intuition:** Choose a pivot, partition the array into elements less than and greater than the pivot, and recursively
sort the partitions.

```
function quickSort(arr, low, high):
    # Input: array arr, indices low and high
    # Output: sorted array (in-place)
    if low < high:
        pivotIndex = partition(arr, low, high)
        quickSort(arr, low, pivotIndex - 1)
        quickSort(arr, pivotIndex + 1, high)

function partition(arr, low, high):
    pivot = arr[high]
    i = low - 1
    for j from low to high - 1:
        if arr[j] <= pivot:
            i += 1
            swap arr[i] and arr[j]
    swap arr[i + 1] and arr[high]
    return i + 1
```

### Heap Sort

**Intuition:** Build a max-heap so the largest element is at the root. Swap the root with the last element, reduce the
heap size, and heapify the root to maintain the heap property, repeating until sorted.

```
function heapSort(arr):
    # Input: array arr
    # Output: sorted array (in-place)
    n = arr.length
    # Build max-heap
    for i from n/2 - 1 down to 0:
        heapify(arr, n, i)
    # Extract elements from heap
    for i from n-1 down to 1:
        swap arr[0] and arr[i]
        heapify(arr, i, 0)
    return arr

function heapify(arr, n, i):
    largest = i
    left = 2 * i + 1
    right = 2 * i + 2
    if left < n and arr[left] > arr[largest]:
        largest = left
    if right < n and arr[right] > arr[largest]:
        largest = right
    if largest != i:
        swap arr[i] and arr[largest]
        heapify(arr, n, largest)
```

---

## 🧪 Problems on Sorting Algorithms

| # | Problem                                                                     | Solution                                           |
|---|-----------------------------------------------------------------------------|----------------------------------------------------|
| 1 | [Group Anagrams](https://leetcode.com/problems/group-anagrams/description/) | [SortingAlgorithms.java](./SortingAlgorithms.java) |


