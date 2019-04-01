package ru.job4j.lambda;

/**
 * 1. Реализовать метод подсчета функции в диапазоне.
 *
 * List<Double> diapason(int start, int end, Function<Double, Double> func);
 *
 * 2. Реализации функций в тестах.
 *     - линейная.
 *     - квадратичная.
 *     - логарифмическая.
 *
 * Пример:
 * @Test
 * public void whenLinearFunctionThenLinearResults() {
 *     List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
 *     List<Double> expected = Arrays.asList(11D, 13D, 15D);
 *     assertThat(result, is(expected));
 * }
 *
 * 3. Необходимо использовать только встроенные функциональные интерфейсы
 */

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Class Calculate.
 *
 * @author shustovakv
 * @since 01.04.2019
 */
public class Calculate {

    List<Double> diapason(int start, int and, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i != and; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}
