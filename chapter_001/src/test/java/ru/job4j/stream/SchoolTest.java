package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SchoolTest {

    @Test
    public void whenStudentsGotIntoClassA() {
        Predicate<Student> predicate = s -> s.score() > 70 && s.score() <= 100;
        List<Student> expected = Arrays.asList(new Student(90), new Student(75));
        schoolTest(predicate, expected);
    }

    @Test
    public void whenStudentsGotIntoClassB() {
        Predicate<Student> predicate = s -> s.score() > 50 && s.score() <= 70;
        List<Student> expected = Arrays.asList(new Student(60), new Student(53));
        schoolTest(predicate, expected);
    }

    @Test
    public void whenStudentsGotIntoClassC() {
        Predicate<Student> predicate = s -> s.score() > 0 && s.score() <= 50;
        List<Student> expected = Arrays.asList(new Student(25), new Student(32));
        schoolTest(predicate, expected);
    }

    private void schoolTest(Predicate<Student> predicate, List<Student> expected) {
        School school = new School();
        List<Student> students = Arrays.asList(
                new Student(25),
                new Student(32),
                new Student(90),
                new Student(60),
                new Student(53),
                new Student(75));
        List<Student> classStudents = school.collect(students, predicate);
        assertThat(classStudents, is(expected));
    }
}