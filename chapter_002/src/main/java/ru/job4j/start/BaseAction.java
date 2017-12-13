package ru.job4j.start;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;
/**
 * Abstract Class BaseAction.
 * @author  shustovakv
 * @since 14.12.2017
 */
public abstract class BaseAction {

    public String name;
    public int key;

    BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }
    public int key() {
        return this.key;
    }
    abstract void execute(Input input, Tracker tracker);

    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }


}
