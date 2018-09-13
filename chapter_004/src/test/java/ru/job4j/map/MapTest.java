package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class MapTest.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 12.09.2018
 */
public class MapTest {
    @Test
    public void whenAddTwoEqualsElementsToMap() {
        User first = new User("Name", 0, null);
        User second = new User("Name", 0, null);
        System.out.println(String.format("Hash code User One: %d", first.hashCode()));
        System.out.println(String.format("Hash code User Two: %d", second.hashCode()));
        System.out.println("User one equals user two?: ");
        System.out.println(first.equals(second));
        assertThat(first.equals(second), is(true));
        Map<User, Object> testMap = new HashMap<>();
        testMap.put(first, "first");
        testMap.put(second, "second");
        System.out.println(testMap);
    }
}
