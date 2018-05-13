package ru.job4j.bank;

import java.util.Objects;

/**
 * Class User.
 * @author  shustovakv
 * @since 06.05.2018
 */
public class User {

/**
     * Field name.
     */
    private String name;

    /**
     * Field passport.
     */
    private String passport;

    /**
     * Constructor.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
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
        return Objects.equals(name, user.name) && Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, passport);
    }

    public boolean checkPassport(String passport) {
        return Objects.equals(passport, this.passport);
    }
}
