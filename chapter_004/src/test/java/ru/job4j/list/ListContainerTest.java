package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ListContainerTest {

    ListContainer<Integer> listContainer;

    @Before
    public void setUp() {
        this.listContainer = new ListContainer<>();
        this.listContainer.add(0);
        this.listContainer.add(1);
    }

    @Test(expected = NullPointerException.class)
    public void whenAddOneItemThenGetItemIndexByOne() {
        assertThat(this.listContainer.get(0), is(0));
        assertThat(this.listContainer.get(1), is(1));
        assertThat(this.listContainer.getSize(), is(2));
        listContainer.get(2);
    }

    @Test
    public void whenTestIterator() {
        Iterator iterator = this.listContainer.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(0));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCreatedIteratorThenReplaceItem() {
        Iterator iterator = listContainer.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(0));
        assertThat(iterator.next(), is(1));
        listContainer.add(2);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNoMoreElements() {
        Iterator iterator = listContainer.iterator();
        assertThat(iterator.next(), is(0));
        assertThat(iterator.next(), is(1));
        iterator.next();
    }
}