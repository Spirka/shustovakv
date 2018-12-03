package ru.job4j.tree;

import java.util.*;

/**
 * Class Tree.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 29.10.2018
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * First element of the tree.
     */
    private transient Node<E> root;

    /**
     * The count of modifications for the iterator working.
     */
    private int modCount = 0;

    /**
     * Constructor
     *
     * @param element
     */
    public Tree(E element) {
        this.root = new Node<>(element);
    }

    /**
     * Method add. Must find the parent element in the tree and add a child element to it.
     * Parent element can contain a list of child elements.
     * If an element with this value already exists, it will return false.
     * If the parent element is not found, false is returned.
     *
     * @param parent parent.
     * @param child  child.
     * @return true if work is done, else false.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean res = false;
        if (!findBy(child).isPresent()) {
            Optional<Node<E>> exist = findBy(parent);
            if (exist.isPresent()) {
                exist.get().add(new Node<>(child));
                res = true;
                this.modCount++;
            }
        }
        return res;
    }

    /**
     * Method findBy.
     *
     * @param value value
     * @return node found by value
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.eqValue(value)) {
                result = Optional.of(element);
                break;
            }
            for (Node<E> child : element.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    /**
     * Checks that the tree is binary
     * @return true if the tree is binary or false if the tree is not binary
     */
    public boolean isBinary() {
        boolean res = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            List<Node<E>> children = element.leaves();
            if (children.size() > 2) {
                res = false;
                break;
            }
            for (Node<E> child : children) {
                data.offer(child);
            }
        }
        return res;
    }

    /**
     * Iterator
     *
     * @return an iterator over the items in this list.
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIterator();
    }

    private class TreeIterator implements Iterator<E> {

        Stack<Node<E>> stack = new Stack<>();

        private TreeIterator() {
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return (!stack.isEmpty());
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> pop = stack.pop();
            List<Node<E>> leaves = pop.leaves();
            if (!leaves.isEmpty()) {
                stack.addAll(leaves);
            }
            return pop.getValue();
        }
    }
}
