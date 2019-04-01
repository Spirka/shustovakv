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

    public Student(int scope) {
        this.score = scope;
    }

    public int score() {
        return this.score;
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
        return score == student.score;
    }

    @Override
    public int hashCode() {

        return Objects.hash(score);
    }
}
