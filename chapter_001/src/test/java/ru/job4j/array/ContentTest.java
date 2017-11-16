package ru.job4j.array;

import org.junit.Test;

/**
 *Test.
 *
 *@author Kseniya Shustova (shustovakv@mail.ru)
 *@version $Id$
 *@since 0.1
 */
public class ContentTest {
    /**
     * Test для "Привет".
     */
    @Test
    public void whenSubIsContainedInTheOrigin() {
        Content content = new Content();
        String origin = "привет";
        String sub = "иве";
        boolean result = content.contains(origin, sub);
    }
}
