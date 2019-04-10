package ru.job4j.stream;

import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class SchoolTest {

    @Test
    public void whenStudentsGotIntoClassA() {
        Predicate<Student> predicate = s -> s.score() > 70 && s.score() <= 100;
        List<Student> expected = Arrays.asList(
                new Student(90, "Ivanov"),
                new Student(75, "Petrov"));
        schoolSort(predicate, expected);
    }

    @Test
    public void whenStudentsGotIntoClassB() {
        Predicate<Student> predicate = s -> s.score() > 50 && s.score() <= 70;
        List<Student> expected = Arrays.asList(
                new Student(60, "Sidorova"),
                new Student(53, "Morozov"));
        schoolSort(predicate, expected);
    }

    @Test
    public void whenStudentsGotIntoClassC() {
        Predicate<Student> predicate = s -> s.score() > 0 && s.score() <= 50;
        List<Student> expected = Arrays.asList(
                new Student(25, "Zlobin"),
                new Student(32, "Lenin"));
        schoolSort(predicate, expected);
    }

    private void schoolSort(Predicate<Student> predicate, List<Student> expected) {
        School school = new School();
        List<Student> students = Arrays.asList(
                new Student(25, "Zlobin"),
                new Student(32, "Lenin"),
                new Student(90, "Ivanov"),
                new Student(60, "Sidorova"),
                new Student(53, "Morozov"),
                new Student(75, "Petrov"));
        List<Student> classStudents = school.collect(students, predicate);
        assertEquals(classStudents, expected);
    }


    @Test
    public void whenListOfStudentsConvertToMap() {
        School school = new School();
        List<Student> students = Arrays.asList(
                new Student(25, "Zlobin"),
                new Student(32, "Lenin"));
        Map<String, Student> result = school.toMap(students);
        Map<String, Student> expected = new HashMap<>();
        expected.put("Lenin", new Student(32, "Lenin"));
        expected.put("Zlobin", new Student(25, "Zlobin"));
        assertEquals(result, expected);
    }

    @Test
    public void whenListOfStudentsContainsDuplicates() {
        School school = new School();
        List<Student> students = Arrays.asList(
                new Student(15, "Averina"),
                new Student(32, "Lenin"),
                new Student(25, "Zlobin"),
                new Student(15, "Averina")
                );
        Map<String, Student> result = school.toMap(students);
        Map<String, Student> expected = new HashMap<>();
        expected.put("Averina", new Student(15, "Averina"));
        expected.put("Lenin", new Student(32, "Lenin"));
        expected.put("Zlobin", new Student(25, "Zlobin"));
        assertEquals(result, expected);
    }
}