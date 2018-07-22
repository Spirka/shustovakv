package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class ArrayContainer.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 20.07.2018
 */
public class ArrayContainer<E> implements SimpleContainer {

    private Object[] container;
    private int modCount = 0;
    private int index = 0;
    private final int defaultSize = 10;

    public ArrayContainer() {
        this.container = new Object[defaultSize];
    }

    public ArrayContainer(int size) {
        if (size < 1) {
            size = defaultSize;
        }
        this.container = new Object[size];
    }

    public int getSize() {
        return this.index;
    }

    private void resize() {
        Object[] newArray = new Object[getSize() * 3 / 2 + 1];
        System.arraycopy(container, 0, newArray, 0, getSize());
        container = newArray;
    }

    @Override
    public void add(Object o) {
        this.container[index++] = o;
        this.modCount++;

        if (index == getSize()) {
            resize();
        }
    }

    @Override
    public E get(int index) {
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int itIndex = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return itIndex < getSize();
            }

            @Override
            public E next() {
                if (itIndex == getSize()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (E) container[itIndex++];
            }
        };
    }
}
