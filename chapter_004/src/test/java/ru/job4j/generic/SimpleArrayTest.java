package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    SimpleArray<Integer> list;

    @Before
    public void setUp() {
        list = new SimpleArray<>();
        list.add(1);
        list.add(3);
        list.add(-3);
    }

    @Test
    public void whenAddNewItem() {
        list.add(5);
        assertThat(list.get(list.size() - 1), is(5));
    }

    @Test
    public void whenSetItem() {
        list.set(2, 4);
        assertThat(list.get(2), is(4));
    }

    @Test
    public void whenDeleteItem() {
        list.delete(0);
        assertThat(list.get(0), is(3));
    }

    @Test
    public void whenGetItem() {
        assertThat(list.get(2), is(-3));
    }

    @Test
    public void shouldReturnNumbers() {
        Iterator it = list.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(-3));
        assertThat(it.hasNext(), is(false));

    }
}