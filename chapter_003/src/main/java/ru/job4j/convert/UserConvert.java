package ru.job4j.convert;

import java.util.HashMap;
import java.util.List;

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
}
