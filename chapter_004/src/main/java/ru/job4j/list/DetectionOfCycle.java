package ru.job4j.list;

/**
 * Class DetectionOfCycle.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 25.07.2018
 */
public class DetectionOfCycle {

    boolean hasCycle(Node node) {
        if (node == null) {
            return false;
        }
        Node slow, fast;
        slow = node;
        fast = node;
        while (true) {
            slow = slow.next;
            if (fast == null || slow == null || fast.next == null) {
                return false;
            } else {
                fast = fast.next.next;
            }
            if (slow == fast) {
                return true;
            }
        }
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
