package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SortUserTest.
 * @author  shustovakv
 * @since 21.04.2018
 */
public class SortUserTest {
    /**
     * Test.
     */
    @Test
    public void whenListOfUsersIsSortedByAgeToTreeSet() {
        User first = new User("Egor", 35);
        User second = new User("Kirill", 32);
        User third = new User("Maxim", 18);
        List<User> userList = new ArrayList<>(Arrays.asList(first, second, third));
        SortUser sortUser = new SortUser();
        Set<User> treeSet = sortUser.sort(userList);
        Iterator<User> it = treeSet.iterator();
        assertThat(it.next(), is(third));
    }
}
