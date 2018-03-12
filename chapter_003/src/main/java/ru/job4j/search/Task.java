package ru.job4j.search;

/**
 * Class Person.
 * @author  shustovakv
 * @since 01.03.2018
 */
public class Task {
    /**
     * Description.
     */
    private String desc;
    /**
     * Priority.
     */
    private int priority;
    /**
     * Constructor.
     */
    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }
}
