package ru.job4j.convert;

import java.util.Objects;

/**
 * Class User.
 * @author  shustovakv
 * @since 07.04.2018
 */
public class User {
    /**
     * Field id.
     */
    private int id;
    /**
     * Field name.
     */
    private String name;
    /**
     * Field city.
     */
    private String city;
    /**
     * Constructor.
     */
    User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
    /**
     * Method getterId
     * @return id
     */
    public int getId() {
        return id;
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
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(city, user.city);
    }
    @Override
    public int hashCode() {

        return Objects.hash(id, name, city);
    }
}
