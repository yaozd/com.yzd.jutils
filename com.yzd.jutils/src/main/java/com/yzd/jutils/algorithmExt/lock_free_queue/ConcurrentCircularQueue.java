package com.yzd.jutils.algorithmExt.lock_free_queue;

/**
 * @Author: yaozh
 * @Description:
 * https://github.com/tbateson/ConcurrentCircularQueue
 * Lock-free concurrent circular queue (also known as a circular buffer or ring buffer) using the Java Queue interface.
 */

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentCircularQueue<T> extends AbstractQueue<T> {

    private class Node {

        private final T item;
        private Node next;

        private Node(final T item) {
            this.item = Objects.requireNonNull(item);
        }

    }

    private class Data implements Iterable<T> {

        private Node head; // Oldest item in the queue
        private Node tail; // Newest item in the queue
        private int size; // Cached size, since it would take O(n) to calculate this

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                Node curNode = null;

                @Override
                public boolean hasNext() {
                    return (curNode == null && head != null) || (curNode != null && curNode.next != null);
                }

                @Override
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    curNode = curNode == null ? head : curNode.next;
                    return curNode.item;
                }
            };
        }

    }

    private final AtomicReference<Data> data;
    private final int capacity;

    public ConcurrentCircularQueue(final int capacity) {
        this.data = new AtomicReference<>(new Data());
        this.capacity = capacity;
    }

    @Override
    public boolean offer(final T e) {
        final Node newNode = new Node(e);
        final Data newData = new Data();
        newData.tail = newNode;
        Data curData;
        do {
            curData = data.get();
            if (curData.size + 1 > capacity) {
                newData.head = curData.head.next;
                newData.size = curData.size;
            } else {
                if (curData.head == null) {
                    newData.head = newNode;
                } else {
                    newData.head = curData.head;
                }
                newData.size = curData.size + 1;
            }
        } while (!data.compareAndSet(curData, newData));
        if (curData.tail != null) {
            curData.tail.next = newNode;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        Objects.requireNonNull(c);
        if (this == c) {
            throw new IllegalArgumentException();
        }
        if (c.isEmpty()) {
            return false;
        }
        final Iterator<? extends T> iter = c.iterator();
        Node newHead = new Node(iter.next());
        Node newTail = newHead;
        while (iter.hasNext()) {
            newTail.next = new Node(iter.next());
            newTail = newTail.next;
        }
        for (int i = 0; i < c.size() - capacity; ++i) {
            newHead = newHead.next;
        }
        final int newElementsSize = Math.min(capacity, c.size());
        final Data newData = new Data();
        newData.tail = newTail;
        if (newElementsSize == capacity) {
            newData.head = newHead;
            newData.size = capacity;
            data.set(newData);
        } else {
            Data curData;
            do {
                curData = data.get();
                if (curData.head != null) {
                    newData.head = curData.head;
                    for (int i = 0; i < newElementsSize + curData.size - capacity; ++i) {
                        newData.head = newData.head.next;
                    }
                } else {
                    newData.head = newHead;
                }
                newData.size = Math.min(capacity, newElementsSize + curData.size);
            } while (!data.compareAndSet(curData, newData));
            if (curData.tail != null) {
                curData.tail.next = newHead;
            }
        }
        return true;
    }

    @Override
    public T peek() {
        return data.get().head.item;
    }

    @Override
    public T poll() {
        final Data newData = new Data();
        Node head;
        Data curData;
        do {
            curData = data.get();
            head = curData.head;
            if (head == null) {
                return null;
            }
            newData.head = head.next;
            newData.tail = curData.tail;
            newData.size = curData.size - 1;
        } while (!data.compareAndSet(curData, newData));
        return head.item;
    }

    @Override
    public Iterator<T> iterator() {
        return data.get().iterator();
    }

    @Override
    public void clear() {
        data.set(new Data());
    }

    @Override
    public boolean isEmpty() {
        return data.get().head == null;
    }

    @Override
    public int size() {
        final Iterator<T> iter = iterator();
        int size = 0;
        while (iter.hasNext()) {
            ++size;
            iter.next();
        }
        return size;
    }

    @Override
    public boolean remove(final Object e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public int capacity() {
        return capacity;
    }

}