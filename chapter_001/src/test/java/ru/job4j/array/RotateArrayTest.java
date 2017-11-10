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
public class RotateArrayTest {
    /**
     * Test для массива 2 х 2.
     */
    @Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
        RotateArray rotateArray = new RotateArray();
        int[][] array = {{1, 2}, {3, 4}};
        int[][] resultTest = {{3, 1}, {4, 2}};
        int[][] result = rotateArray.rotate(array);
        assertThat(result, is(resultTest));
        }
    /**
     * Test для массива 3 х 3.
     */
    @Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
        RotateArray rotateArray = new RotateArray();
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] resultTest = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        int[][] result = rotateArray.rotate(array);
        assertThat(result, is(resultTest));
    }
}