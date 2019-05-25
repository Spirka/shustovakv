package ru.job4j.start;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Input;

/**
 * Interface UserAction.
 * @author  shustovakv
 * @since 11.12.2017
 */
public interface UserAction {

    int key();

    void execute(Input input, ITracker tracker);

    String info();
}
