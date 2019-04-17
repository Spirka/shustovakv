package ru.job4j.map;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class Converter.
 *
 * @author shustovakv
 * @since 16.04.2019
 */
public class Converter {
    public List<Integer> convertToList(Integer[][] matrix) {
        return Stream.of(matrix)
                .flatMap(Stream::of)
                .collect(Collectors.toList());
    }
}
