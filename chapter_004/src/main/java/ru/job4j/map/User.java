package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Class User.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 04.09.2018
 */
public class User {

    private String name;
    private int children;
    private Calendar birthday;

    @Override
    public int hashCode() {

        return Objects.hash(name, children, birthday);
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
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
