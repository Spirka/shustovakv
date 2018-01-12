package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author Kseniya Shustova (shustovakv@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class TriangleTest {
    @Test
    public void whenDrawTriangle() {
        Triangle triangle = new Triangle();
        assertThat(
                triangle.draw(),
                is(new StringBuilder()
                .append("  ^  ")
                        .append(System.lineSeparator())
                .append(" ^^^ ")
                        .append(System.lineSeparator())
                .append("^^^^^")
                .toString()
                )
        );
    }
}
