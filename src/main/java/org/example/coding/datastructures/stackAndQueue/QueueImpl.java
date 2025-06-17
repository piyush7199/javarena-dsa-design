package org.example.coding.datastructures.stackAndQueue;

/**
 * A generic queue implementation using circular array logic.
 *
 * @param <T> the type of elements stored in the queue
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