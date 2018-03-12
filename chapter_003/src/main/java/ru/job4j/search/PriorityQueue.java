package ru.job4j.search;

import java.util.LinkedList;

/**
 * Class PriorityQueue.
 * @author  shustovakv
 * @since 01.03.2018
 */
public class PriorityQueue {
    /**
     * List of task.
     */
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позицию определять по полю приоритет.
     * Для вставки использовать add(int index, E value)
     *
     * @param task задача
     */
    public void put(Task task) {
        this.tasks.add(find(task), task);
    }

    /**
     * Method find position for task.
     * @param task
     * @return index.
     */
    private int find(Task task) {
        int taskPriority = task.getPriority();
        int left = 0;
        int right = this.tasks.size();

        while (left != right) {
            int middle = (left + right) / 2;
            int valuePriority = this.tasks.get(middle).getPriority();
            if (valuePriority <= taskPriority) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return right;
    }

    public Task take() {
        return this.tasks.poll();
    }
}
