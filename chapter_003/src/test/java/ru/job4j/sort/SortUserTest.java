package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    @Test
    public void whenListOfUsersIsSortedByLengthOfName() {
        User first = new User("Sergey", 25);
        User second = new User("Ivan", 20);
        List<User> userList = new ArrayList<>(Arrays.asList(first, second));
        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortNameLength(userList);
        Iterator<User> it = result.iterator();
        assertThat(it.next(), is(second));
    }
    @Test
    public void whenListOfUsersIsSortedByNameThenByAge() {
        User first = new User("Sergey", 25);
        User second = new User("Ivan", 30);
        User third = new User("Sergey", 20);
        User fourth = new User("Ivan", 25);
        List<User> userList = new ArrayList<>(Arrays.asList(first, second, third, fourth));
        List<User> result = new SortUser().sortByAllFields(userList);
        Iterator<User> it = result.iterator();
        assertThat(it.next(), is(fourth));
        assertThat(it.next(), is(second));
        assertThat(it.next(), is(third));
        assertThat(it.next(), is(first));
    }
}
