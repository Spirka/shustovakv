package ru.job4j.array;

import java.util.Arrays;

/**
 * Class Turn.
 * @author  shustovakv
 * @since 10.11.2017
 */
public class ArrayDuplicate {
    /**
     * remove;
     *
     * @param array массив строковых значений;
     * @return array without duplicates;
     */
    public String[] remove(String[] array) {
        int unique = array.length;
        for (int out = 0; out < unique; out++) {
            for (int in = out + 1; in < unique; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[unique - 1];
                    unique--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(array, unique);
    }
}
