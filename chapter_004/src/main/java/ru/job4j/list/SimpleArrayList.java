package ru.job4j.list;

/**
 * Class SimpleArrayList.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 17.07.2018
 */
public class SimpleArrayList<e> {

    private int size;
    private Node<e> first;

    public void add(e date) {
        Node<e> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    public e delete() {
        Node<e> temp = this.first;
        this.first = this.first.next;
        this.size--;
        return temp.date;
    }

    public e get(int index) {
        Node<e> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    public int getSize() {
        return this.size;
    }

    private static class Node<e> {

        e date;
        Node<e> next;

        Node(e date) {
            this.date = date;
        }
    }
}
