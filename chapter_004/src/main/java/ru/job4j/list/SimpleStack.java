package ru.job4j.list;


import java.util.NoSuchElementException;

/**
 * Class SimpleStack.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 24.07.2018
 */
public class SimpleStack<T> {

    private Node node;
    private int size;

    public SimpleStack() {
        this.node = null;
        this.size = 0;
    }

    public void push(T value) {
        Node<T> node = new Node<>(value);

        if (this.node == null) {
            this.node = node;
        } else {
            node.next = this.node;
            this.node = node;
        }
        this.size++;
    }

    public T poll() {

        if (this.node == null) {
            throw new NoSuchElementException();
        }

        Node<T> result = node;
        this.node = this.node.next;
        this.size--;
        return result.item;
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
