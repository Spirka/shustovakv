package ru.job4j.list;

/**
 * Interface SimpleContainer.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 20.07.2018
 */
public interface SimpleContainer<E> extends Iterable<E> {
    void add(E e);
    E get(int index);
}
