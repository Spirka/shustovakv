package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Node.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 29.10.2018
 */
public class Node<E extends Comparable<E>> {

    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    /**
     * Constructor
     * @param value value
     */
    public Node(final E value) {
        this.value = value;
    }

    /**
     * Adds children to the list of children
     * @param child  to be added.
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * @return the value of the node
     */
    public E getValue() {
        return value;
    }

    /**
     * Method leaves.
     * @return a list of all the children of this parent.
     */
    public List<Node<E>> leaves() {
        return this.children;

    }

    /**
     * Compares node
     * @param that to be compared
     * @return true or false
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }
}
