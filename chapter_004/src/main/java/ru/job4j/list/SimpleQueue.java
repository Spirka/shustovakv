package ru.job4j.list;

/**
 * Class SimpleQueue.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 24.07.2018
 */
public class SimpleQueue<T> {

    private ListContainer<T> container = new ListContainer<>();
    private Node<T> node;
    private int size;

    public SimpleQueue() {
        this.node = null;
        this.size = 0;
    }

    public void push(T value) {
        this.container.insertLast(value);
    }

    public T poll() {
        return this.container.deleteFirst();
    }

    private static class Node<T> {
        T item;
        Node<T> last;

        Node(T element) {
            this.item = element;
            this.last = null;
        }
    }
}
