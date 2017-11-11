package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 *Test.
 *
 *@author Kseniya Shustova (shustovakv@mail.ru)
 *@version $Id$
 *@since 0.1
 */
public class ArrayDuplicateTest {
    /**
     * Test для строкового массива, убираем дубликаты.
     */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] input = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] expectArray = {"Привет", "Мир", "Супер"};
        String[] resultArray = arrayDuplicate.remove(input);
        assertThat(resultArray, is(expectArray));
    }
}
