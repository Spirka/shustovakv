package ru.job4j.generic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class SimpleArray</T>.
 * @author  shustovakv
 * @since 09.07.2018
 */
public class SimpleArray<T> implements Iterable<T> {

    private ArrayList<T> simpleList = new ArrayList<>();

    public void add(T model) {
        this.simpleList.add(model);
    }

    public void set(int index, T model) {
        this.simpleList.set(index, model);
    }

    public void delete(int index) {
        this.simpleList.remove(index);
    }

    public T get(int index) {
        return this.simpleList.get(index);
    }
    public int size() {
        return simpleList.size();
    }

    @Override
    public Iterator iterator() {
        return this.simpleList.iterator();
    }
}
