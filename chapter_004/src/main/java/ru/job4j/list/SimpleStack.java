package ru.job4j.list;

/**
 * Class SimpleStack.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 24.07.2018
 */
public class SimpleStack<T> {

    private ListContainer<T> container = new ListContainer<>();
    private Node node;
    private int size;

    public SimpleStack() {
        this.node = null;
        this.size = 0;
    }

    public void push(T value) {
        this.container.insertFirst(value);
    }

    public T poll() {
        return this.container.deleteFirst();
    }

    private static class Node<T> {

        T item;
        Node<T> next;

        Node(T element) {
            this.item = element;
            this.next = null;
        }
    }
}
