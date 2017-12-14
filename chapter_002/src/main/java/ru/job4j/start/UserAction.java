package ru.job4j.start;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;

/**
 * Interface UserAction.
 * @author  shustovakv
 * @since 11.12.2017
 */
public interface UserAction {

    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
