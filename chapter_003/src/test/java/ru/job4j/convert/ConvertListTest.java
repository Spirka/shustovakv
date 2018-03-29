package ru.job4j.convert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class ConvertListTest.
 * @author  shustovakv
 * @since 28.03.2018
 */
public class ConvertListTest {
    /**
     * Test to list.
     */
    @Test
    public void whenArrayConvertToList() {
        ConvertList convertList = new ConvertList();
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7}};
        List<Integer> result = new ArrayList<>();
        int[] element = {1, 2, 3, 4, 5, 6, 7};
        for (int value : element) {
            result.add(value);
        }
        assertThat(convertList.toList(array), is(result));
    }

    /**
     * Test to array.
     */
    @Test
    public void whenArrayConvertToArray() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>();
        int[] element = {1, 2, 3, 4, 5, 6, 7};
        for (int value : element) {
            list.add(value);
        }
        int[][] result = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        assertThat(convertList.toArray(list, 3), is(result));
    }
}