package ru.job4j.sort;

import java.util.ArrayList;
import java.util.Comparator;
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
     * Method sort.
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }

    /**
     * Method sortNameLength
     * в этом методе необходимо определить Comparator для метода Collections.sort
     * и отсортировать List<User> по длине имени.
     * @return sorted list of users by name length.
     */
    public List<User> sortNameLength(final List<User> users) {
        List<User> result = new ArrayList<>(users);
                result.sort((user1, user2) -> {
                    int lengthUser1 = user1.getName().length();
                    int lengthUser2 = user2.getName().length();
                    return Integer.compare(lengthUser1, lengthUser2);
                }
                );
        return result;
    }

    /**
     * Method sort by all fields
     * в этом методе необходимо определить Comparator для метода Collections.sort
     * и отсортировать List<User> по обоим полям, сначала сортировка по имени в лексикографическом порядке,
     * потом по возрасту.
     * @return sorted list of users by name length and then by age.
     */
    public List<User> sortByAllFields(final List<User> users) {
        //throw new UnsupportedOperationException("sortByAllFields");
        List<User> result = new ArrayList<>(users);
        result.sort(Comparator.comparing(User::getName).thenComparingInt(User::getAge)
        );
        return result;
    }
}
