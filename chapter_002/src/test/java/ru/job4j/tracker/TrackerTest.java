package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Kseniya Shustova (shustovakv@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }
    /**
     * Test update.
     */
    @Test
    public void whenUpdateNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.update(next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
    /**
     * Test delete.
     */
    @Test
    public void whenDeleteItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        tracker.delete(previous);
        Item[] result = {};
        assertThat(tracker.findAll().toArray(), is(result));
    }
    /**
     * Test findAll.
     */
    @Test
    public void whenFindAllItemsThenReturnAllItems() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription1", 123L);
        tracker.add(first);
        Item[] result = {first};
        assertThat(tracker.findAll().toArray(), is(result));
    }
    /**
     * Test findByName.
     */
    @Test
    public void whenFindItemsByNameThenReturnItems() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription1", 123L);
        tracker.add(first);
        Item[] result = {first};
        assertThat(tracker.findByName("test1").toArray(), is(result));
    }
    /**
     * Тест findById.
     */
    @Test
    public void whenFindItemByIdThenReturnItem() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription1", 123L);
        tracker.add(first);
        assertThat(tracker.findById(first.getId()).getName(), is("test1"));
    }
}
