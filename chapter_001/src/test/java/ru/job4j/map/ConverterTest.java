package ru.job4j.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConverterTest {

    @Test
    public void convertToList() {
        Converter converter = new Converter();
        Integer[][] matrix = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        List<Integer> result = converter.convertToList(matrix);
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        assertThat(result, is(expected));
    }
}