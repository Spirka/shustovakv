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

    public SimpleArray() {
        this.objects = new Object[0];
    }

    public void add(T model) {
        if ((this.index + 1) > this.objects.length) {
            this.extendObjects();
        }
        this.objects[index++] = model;
    }

    public void set(int index, T model) {
        this.objects[index] = model;
    }

    public void delete(int index) {
        if (index >= 0 && index < this.objects.length - 1) {
            System.arraycopy(this.objects, index + 1, this.objects, index, objects.length - index - 1);
            this.index--;
        }
    }

    public T get(int position) {
        return (T) objects[position];
    }

    public int size() {
        return  this.index;
    }

    private void extendObjects() {
        Object[] oldObjects = this.objects;
        this.objects = new Object[oldObjects.length + 1];
        System.arraycopy(oldObjects, 0, this.objects, 0, oldObjects.length);
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
