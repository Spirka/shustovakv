package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Class SimpleQueue.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 24.07.2018
 */
public class SimpleQueue<T> {

    private Node<T> node;
    private int size;

    public SimpleQueue() {
        this.node = null;
        this.size = 0;
    }

    public void push(T value) {
        Node<T> node = new Node<>(value);
        if (this.node == null) {
            this.node = node;
        } else {
            Node<T> current = this.node;
            while (current.last != null) {
                current = current.last;
            }
            current.last = node;
        }
        this.size++;
    }

    public T poll() {
        if (this.node == null) {
            throw new NoSuchElementException();
        }
        Node<T> result = this.node;
        node = node.last;
        this.size--;
        return result.item;
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
