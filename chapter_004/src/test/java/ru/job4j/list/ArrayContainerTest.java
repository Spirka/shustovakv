package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayContainerTest {

    private ArrayContainer<Integer> arrayContainer;

    @Before
    public void setUp() {
        this.arrayContainer = new ArrayContainer<>(1);
        this.arrayContainer.add(0);
    }

    @Test
    public void whenCreatedArrayInSizeOneElementThenPutTwoItem() {
        assertThat(this.arrayContainer.getSize(), is(1));
        assertThat(this.arrayContainer.get(0), is(0));
        this.arrayContainer.add(5);
        assertThat(this.arrayContainer.getSize(), is(2));
        assertThat(this.arrayContainer.get(1), is(5));
    }

    @Test
    public void whenTestIterator() {
        this.arrayContainer.add(1);
        this.arrayContainer.add(2);
        Iterator it = this.arrayContainer.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCreatedIteratorThenAddNewItem() {
        Iterator it = this.arrayContainer.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        this.arrayContainer.add(1);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNoMoreElement() {
        Iterator it = this.arrayContainer.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        it.next();
    }
}