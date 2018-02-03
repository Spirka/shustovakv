package ru.job4j.machine;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Class Test Chess board.
 *
 * @author Kseniya Shustova (shustovakv@mail.ru)
 * @version $Id$
 * @since 02.02.2018
 */
public class CoffeeMachineTest {
    /**
     * TestMethod
     * @param value denomination.
     * @param price cost of coffee.
     * @param resultChange expected delivery.
     */
    private void testMethod(int value, int price, int[] resultChange) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        assertThat(coffeeMachine.changes(value, price), is(resultChange));
    }

    /**
     * Test fifteen.
     */
    @Test
    public void whenFifteen() {
        testMethod(50, 35, new int[] {10, 5});
    }

    /**
     * Test zero.
     */
    @Test
    public void whenZero() {
        testMethod(35, 35, new int[] {});
    }

    /**
     * Test twenty.
     */
    @Test
    public void whenTwenty() {
        testMethod(100, 80, new int[] {10, 10});
    }
}
