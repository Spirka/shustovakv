package ru.job4j.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class UserConvertTest.
 * @author  shustovakv
 * @since 09.04.2018
 */
public class UserConvertTest {
    /**
     * Test.
     */
    @Test
    public void whenListOfUsersConvertToHashMap() {
        User first = new User(1, "Ivan", "Moscow");
        User second = new User(2, "Kirill", "Saint-Petersburg");
        List<User> userList = new ArrayList<>();
        userList.add(first);
        userList.add(second);
        UserConvert userConvert = new UserConvert();
        Map<Integer, User> hashMap = userConvert.process(userList);
        assertTrue(hashMap.containsKey(1));
        assertTrue(hashMap.containsValue(second));
        assertThat(hashMap.size(), is(2));
    }
}
