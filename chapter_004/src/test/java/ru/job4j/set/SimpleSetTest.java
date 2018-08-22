package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class SimpleSet.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 31.07.2018
 */
public class SimpleSetTest {
    @Test(expected = NoSuchElementException.class)
    public void whenTestSimpleSet() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("Petr");
        simpleSet.add("Petr");
        simpleSet.add("Maria");
        Iterator<String> it = simpleSet.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Petr"));
        assertThat(it.next(), is("Maria"));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}