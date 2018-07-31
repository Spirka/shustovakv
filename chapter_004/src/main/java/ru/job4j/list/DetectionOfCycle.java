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

        slow = fast = node;

        while (true) {

            slow = slow.next;

            if (fast.next != null) {
                fast = fast.next.next;

            } else {
                return false;
            }

            if (slow == null || fast == null) {
                return false;
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
