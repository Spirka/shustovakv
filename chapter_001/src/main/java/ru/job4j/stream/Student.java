package ru.job4j.stream;

import java.util.Objects;

/**
 * Class Student.
 *
 * @author shustovakv
 * @since 01.04.2019
 */
public class Student {

    private int score;
    private String surname;

    public Student(int scope, String surname) {
        this.score = scope;
        this.surname = surname;
    }

    public int score() {
        return this.score;
    }

    public String surname() {
        return this.surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return score == student.score && Objects.equals(surname, student.surname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(score, surname);
    }
}
