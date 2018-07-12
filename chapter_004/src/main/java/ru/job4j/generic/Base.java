package ru.job4j.generic;

/**
 * Abstract class Base.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 10.07.2018
 */
public abstract class Base {

    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
