package ru.job4j.sort;

import java.util.Objects;

/**
 * Class User.
 * @author  shustovakv
 * @since 20.04.2018
 */
public class User implements Comparable<User> {

    /**
     * Field name.
     */
    private String name;

    /**
     * Field age.
     */
    private int age;

    /**
     * Constructor.
     */
    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Method getter
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method getter
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**${@inheritDoc}
     */
    @Override
    public int compareTo(User o) {
        return Integer.compare(this.age, o.age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age);
    }
}
