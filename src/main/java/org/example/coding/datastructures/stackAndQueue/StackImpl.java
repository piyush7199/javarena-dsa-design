package org.example.coding.datastructures.stackAndQueue;

public class StackImpl<T> {
    private final int maxSize;
    private final T[] arr;
    private int top;

    @SuppressWarnings("unchecked")
    public StackImpl(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        this.arr = (T[]) new Object[maxSize];
    }

    public int getCapacity() {
        return maxSize;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top >= maxSize - 1;
    }

    public T top() {
        return arr[top];
    }

    public void push(T ele) {
        if (isFull()) {
            throw new RuntimeException("Stack Overflow");
        }
        arr[++top] = ele;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Underflow");
        }
        return arr[top--];
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        return arr[top];
    }

    public static void main(String[] args) {
        StackImpl<Integer> stackImpl = new StackImpl<>(5);
        System.out.println(stackImpl.isEmpty());
        stackImpl.push(1);
        stackImpl.push(2);
        stackImpl.push(3);
        stackImpl.push(4);
        stackImpl.push(5);
    }
}
