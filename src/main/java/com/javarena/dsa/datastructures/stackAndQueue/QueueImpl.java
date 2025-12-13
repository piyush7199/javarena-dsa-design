package com.javarena.dsa.datastructures.stackAndQueue;

/**
 * Queue Implementation using Circular Array
 *
 * <p><b>Problem Statement:</b><br>
 * Implement generic queue using fixed-size circular array.
 * Support enqueue, dequeue, peek, isEmpty, isFull operations.
 *
 * <p><b>Intuition & Approach:</b><br>
 * Circular array with front and rear pointers:
 * - Front: points to first element
 * - Rear: points to last element
 * - Enqueue: Move rear forward (circularly), add element
 * - Dequeue: Remove from front, move front forward
 * - Use modulo for circular wrapping
 * - Track size to detect full/empty
 * 
 * Efficient space utilization with wraparound.
 *
 * <p><b>Time Complexity:</b> O(1) for all operations
 * <br><b>Space Complexity:</b> O(N) - Array size
 */
public class QueueImpl<T> {
    private final T[] arr;
    private final int maxSize;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public QueueImpl(int maxSize) {
        this.maxSize = maxSize;
        this.arr = (T[]) new Object[maxSize];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public int getSize() {
        return size;
    }

    public void enqueue(T element) {
        if (isFull()) {
            throw new RuntimeException("Queue Overflow");
        }
        rear = (rear + 1) % maxSize;
        arr[rear] = element;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow");
        }
        T element = arr[front];
        front = (front + 1) % maxSize;
        size--;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }
        return arr[front];
    }

    public static void main(String[] args) {
        QueueImpl<Integer> queue = new QueueImpl<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue()); // 1
        System.out.println(queue.peek());    // 2
    }
}