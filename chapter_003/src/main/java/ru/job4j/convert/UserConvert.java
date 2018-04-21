package ru.job4j.convert;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class UserConvert.
 * @author  shustovakv
 * @since 07.04.2018
 */
public class UserConvert {
    /**
     * Method convert
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
    /**
     * Method sort
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }
}
