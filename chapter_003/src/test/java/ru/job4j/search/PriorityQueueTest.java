package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class PriorityQueueTest.
 * @author  shustovakv
 * @since 01.03.2018
 */
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("second", 2));
        queue.put(new Task("four", 4));
        queue.put(new Task("middle2", 3));
        queue.put(new Task("middle3", 3));
        queue.put(new Task("four2", 4));
        assertThat(queue.take().getDesc(), is("urgent"));
        assertThat(queue.take().getDesc(), is("second"));
        assertThat(queue.take().getDesc(), is("middle"));
        assertThat(queue.take().getDesc(), is("middle2"));
        assertThat(queue.take().getDesc(), is("middle3"));
        assertThat(queue.take().getDesc(), is("four"));
        assertThat(queue.take().getDesc(), is("four2"));
        assertThat(queue.take().getDesc(), is("low"));
    }
}
