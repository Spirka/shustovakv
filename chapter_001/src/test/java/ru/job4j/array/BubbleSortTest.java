package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Test.
 *
 *@author Kseniya Shustova (shustovakv@mail.ru)
 *@version $Id$
 *@since 0.1
 */
public class BubbleSortTest {
    /**
     * Test когда надо отсортировать 10 элементов.
     */
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] resultTest = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        int[] result = bubbleSort.sort(array);
        assertThat(result, is(resultTest));
    }
}
