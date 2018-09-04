package ru.job4j.map;

import java.util.Calendar;

/**
 * Class User.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 04.09.2018
 */
public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}