package ru.job4j.list;

import java.util.ArrayList;

/**
 * Class DetectionOfCycle.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 25.07.2018
 */
public class DetectionOfCycle {

    boolean hasCycle(Node node) {
        ArrayList<Node> list = new ArrayList<>();
        do {
            list.add(node);
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) == node) {
                    return true;
                }
            }
            node = node.next;
        } while (node != null);
        return false;
    }
    public static class Node<T> {
        T value;
        Node<T> next;

        public Node(T element) {
            this.value = element;
            this.next = null;
        }
    }
}
