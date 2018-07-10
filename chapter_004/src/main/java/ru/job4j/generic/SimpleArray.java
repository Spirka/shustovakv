package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleArray</T>.
 * @author  shustovakv
 * @since 09.07.2018
 */
public class SimpleArray<T> implements Iterable<T> {

    private Object[] objects;

    private int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T model) {
        if ((this.index + 1) > this.objects.length) {
            throw new IndexOutOfBoundsException();
        }
        this.objects[index++] = model;
    }

    public void set(int index, T model) {
        this.objects[index] = model;
    }

    public void delete(int index) {
        this.objects[index] = this.objects[index + 1];
        Object[] oldObjects = this.objects;
        this.objects = new Object[this.objects.length - 1];
        System.arraycopy(oldObjects, 0, this.objects, 0, index);
        System.arraycopy(oldObjects, index + 1, this.objects, index, objects.length - index - 1);
    }

    public T get(int position) {
        return (T) objects[position];
    }
    public int size() {
        return this.objects.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorSimpleArray();
    }

    public class IteratorSimpleArray implements Iterator<T> {

        int position;

        @Override
        public boolean hasNext() {
            return this.position < objects.length;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) objects[position++];
        }
    }

}
