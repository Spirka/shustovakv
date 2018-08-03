package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class ListContainer.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 23.07.2018
 */
public class ListContainer<E> implements SimpleContainer<E> {

    private Node first;
    private Node last;
    private int size;
    private int modCount;

    ListContainer() {
        this.first = null;
        this.last = null;
        this.size = 0;
        this.modCount = 0;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public void insertLast(E date) {
        Node<E> newLink = new Node<>(date);
        if (isEmpty()) {
            this.first = newLink;
            this.last = newLink;
        } else {
            newLink.next = this.last;
            this.last.next = newLink;
            this.last = newLink;
        }
        this.size++;
        this.modCount++;
    }

    public void insertFirst(E date) {
        Node<E> newLink = new Node<>(date);
        if (isEmpty()) {
            this.last = newLink;
            newLink.next = this.first;
            this.first = newLink;
        }
    }

    @Override
    public void add(E date) {
        insertLast(date);
    }

    @Override
    public E get(int index) {
        Node<E> result = this.first;
        int indexSearch = 0;
        while (indexSearch != index) {
            result = result.prev;
            indexSearch++;
        }
        return (E) result.item;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node linkNext = ListContainer.this.first;
            private int expectedModeCount = ListContainer.this.modCount;

            @Override
            public boolean hasNext() {
                return this.linkNext != null;
            }

            @Override
            public E next() {
                if (this.expectedModeCount != ListContainer.this.modCount) {
                    throw new ConcurrentModificationException();
                } else if (this.linkNext == null) {
                    throw new NoSuchElementException();
                }
                Object element = this.linkNext.item;
                this.linkNext = this.linkNext.prev;
                return (E) element;
            }
        };
    }

    private static class Node<E> {

        E item;
        Node<E> prev;
        Node<E> next;

        Node(E date) {
            this.item = date;
            this.prev = null;
            this.next = null;
        }

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
