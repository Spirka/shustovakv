package ru.job4j.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test class SimpleQueueTest.
 * @author  shustovakv (mailto: shustovakv@mail.ru)
 * @since 24.07.2018
 */
public class SimpleQueueTest {

    @Test(expected = NoSuchElementException.class)
    public void whenSimpleQueuePushAndPollElements() {
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();
        simpleQueue.push(1);
        simpleQueue.push(2);
        simpleQueue.push(3);
        assertThat(simpleQueue.poll(), is(1));
        assertThat(simpleQueue.poll(), is(2));
        assertThat(simpleQueue.poll(), is(3));
        simpleQueue.poll();
    }
}