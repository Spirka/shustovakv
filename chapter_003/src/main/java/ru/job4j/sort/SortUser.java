package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class SortUser.
 * @author  shustovakv
 * @since 20.04.2018
 */
public class SortUser {
    /**
     * Method sort
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }
}
