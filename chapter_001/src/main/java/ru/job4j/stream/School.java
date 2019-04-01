package ru.job4j.stream;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class School.
 *
 * @author shustovakv
 * @since 01.04.2019
 */
public class School {

    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }
}
