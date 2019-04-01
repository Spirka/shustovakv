package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculateTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        Calculate calc = new Calculate();
        List<Double> result = calc.diapason(5, 8,
                x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        Calculate calc = new Calculate();
        List<Double> res = calc.diapason(5, 8,
                x -> x * x);
        List<Double> expected = Arrays.asList(25D, 36D, 49D);
        assertThat(res, is(expected));
    }

    @Test
    public void whenLogarithmicFunctionThenLogarithmicResults() {
        Calculate calc = new Calculate();
        List<Double> res = calc.diapason(5, 8,
                Math :: log);
        List<Double> expected = Arrays.asList(1.6094379124341003, 1.791759469228055, 1.9459101490553132);
        assertThat(res, is(expected));
    }
}