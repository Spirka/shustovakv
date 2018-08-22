package ru.job4j.set;

import ru.job4j.list.ArrayContainer;

import java.util.Iterator;

/**
 * Class SimpleSet.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 31.07.2018
 */
public class SimpleSet<E> implements Iterable<E> {

    private ArrayContainer<E> container = new ArrayContainer<>();

    public void add(E value) {
        if (!container.contains(value)) {
            container.add(value);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return this.container.iterator();
    }
}
