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

    public void insertLast(E date) {
        Node<E> oldLast = this.last;
        Node<E> newLink = new Node<>(oldLast, date, null);
        this.last = newLink;
        if (oldLast == null) {
            this.first = newLink;
        } else {
            oldLast.next = newLink;
            newLink.prev = oldLast;
        }
        this.size++;
        this.modCount++;
    }

    public void insertFirst(E date) {
        Node<E> oldFirst = this.first;
        Node<E> newLink = new Node<>(oldFirst, date, null);
        if (oldFirst == null) {
            this.first = newLink;
        } else {
            oldFirst.prev = newLink;
            newLink.next = oldFirst;
        }
        this.size++;
        this.modCount++;
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
        return result.item;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> linkNext = ListContainer.this.first;
            int expectModeCount = ListContainer.this.modCount;

            @Override
            public boolean hasNext() {
                return linkNext != null;
            }
            @Override
            public E next() {
                checkModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E res = linkNext.item;
                linkNext = linkNext.next;
                return res;
            }
            private void checkModification() {
                if (this.expectModeCount != ListContainer.this.modCount) {
                    throw new ConcurrentModificationException();
                }
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

        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}
