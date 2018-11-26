package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Test Class TreeTest.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 29.10.2018
 */
public class TreeTest {

    Tree<Integer> tree;

    @Before
    public void prepare() {
        tree = new Tree<>(1);
        tree.add(1, 2);
    }


    @Test
    public void whenSixElementsFindLastThenSixElement() {
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        assertThat(tree.add(1, 2), is(false));
        assertThat(tree.findBy(7).isPresent(),
                is(false));
    }
    @Test
    public void whenIterateThenTrue() {
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(3, 5);
        Iterator it = tree.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIterateAndModifiedThenException() {
        Iterator it = tree.iterator();
        tree.add(2, 3);
        assertThat(it.hasNext(), is(false));
    }
}
