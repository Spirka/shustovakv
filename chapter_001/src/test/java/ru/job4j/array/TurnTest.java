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
public class TurnTest {
    /**
     * Test когда количество чисел в массиве четное.
     */
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray (){
        Turn turn = new Turn();
        int[] array = {2, 6, 1, 4};
        int[] resultTest = {4, 1, 6, 2};
        int[] result = turn.back(array);
        assertThat(result, is(resultTest));
    }
    /**
     * Test когда количество чисел в массиве нечетное.
     */
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] array = {1, 2, 3, 4, 5};
        int[] resultTest = {5, 4, 3, 2, 1};
        int[] result = turn.back(array);
        assertThat(result, is(resultTest));
    }
}
