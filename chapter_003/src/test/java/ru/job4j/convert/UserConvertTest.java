package ru.job4j.convert;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class UserConvertTest.
 * @author  shustovakv
 * @since 20.04.2018
 */
public class UserConvertTest {
    /**
     * Test.
     */
    @Test
    public void whenListOfUsersConvertToHashMap() {
        User first = new User(1, "Ivan", "Moscow", 27);
        User second = new User(2, "Kirill", "Saint-Petersburg", 32);
        List<User> userList = new ArrayList<>();
        userList.add(first);
        userList.add(second);
        UserConvert userConvert = new UserConvert();
        Map<Integer, User> hashMap = userConvert.process(userList);
        assertTrue(hashMap.containsKey(1));
        assertTrue(hashMap.containsValue(second));
        assertThat(hashMap.size(), is(2));
    }
    @Test
    public void whenListOfUsersIsSortedByAgeToTreeSet() {
        User first = new User(1, "Egor", "Moskow", 35);
        User second = new User(2, "Kirill", "Saint-Petersburg", 32);
        User third = new User(3, "Maxim", "Ivanovo", 18);
        List<User> userList = new ArrayList<>(Arrays.asList(first, second, third));
        UserConvert userConvert = new UserConvert();
        Set<User> treeSet = userConvert.sort(userList);
        Iterator<User> it = treeSet.iterator();
        assertThat(it.next(), is(third));
    }
}
