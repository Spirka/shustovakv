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
    private Node next;
    private int size;
    private int modCount;

    ListContainer() {
        this.first = null;
        this.next = null;
        this.size = 0;
        this.modCount = 0;
    }

    @Override
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        if (this.first == null) {
            this.next = newLink;
            this.first = newLink;
        } else {
            newLink.next = this.next;
            newLink.next.first = newLink;
            this.next = newLink;
        }
        this.size++;
        this.modCount++;
    }

    @Override
    public E get(int index) {
        Node<E> result = this.first;
        int indexSearch = 0;
        while (indexSearch != index) {
            result = result.first;
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

            private Node linkNext = first;
            private int expectedModeCount = modCount;

            @Override
            public boolean hasNext() {
                return linkNext != null;
            }

            @Override
            public E next() {
                if (expectedModeCount != modCount) {
                    throw new ConcurrentModificationException();
                } else if (linkNext == null) {
                    throw new NoSuchElementException();
                }
                Object element = linkNext.item;
                linkNext = linkNext.first;
                return (E) element;
            }
        };
    }

    private static class Node<E> {

        E item;
        Node<E> first;
        Node<E> next;

        Node(E date) {
            this.item = date;
            this.first = null;
            this.next = null;
        }
    }
}
