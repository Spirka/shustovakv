package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenTheMaxElementIsFive() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 5, -1));
        MaxMin maxMin = new MaxMin();
        Integer result = maxMin.max(list, Integer::compareTo);
        assertThat(result, is(5));
    }

    @Test
    public void whenTheMinElementIsOneBelowZero() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 5, -1));
        MaxMin maxMin = new MaxMin();
        Integer result = maxMin.min(list, Integer::compareTo);
        assertThat(result, is(-1));
    }
}
