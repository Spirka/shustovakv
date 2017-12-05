package ru.job4j.start;

import org.junit.Test;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author Kseniya Shustova (shustovakv@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class StubInputTest {
    /**
     * Test add.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "123456789", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }
    /**
     * Test show all.
     */
    @Test
    public void whenUserFindAllItemsThenTrackerReturnAllItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test1", "test desc", 12345L));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        Item[] result = {item};
        assertThat(tracker.findAll(), is(result));
    }
    /**
     * Test update.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test1", "test description", 1234567L));
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "123456", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }
    /**
     * Test delete.
     */
    @Test
    public void whenUserDeleteItemThenTrackerReturnAllItems() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test 1", "test desc 1", 1234L));
        Input input = new StubInput(new String[]{"3", first.getId(), "6"});
        new StartUI(input, tracker).init();
        Item[] result = {};
        assertThat(tracker.findAll(), is(result));
    }
    /**
     * Test findById.
     */
    @Test
    public void whenUserFindItemByIdThenTrackerReturnFoundItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "test desc", 123L));
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }
    /**
     * Test findByName.
     */
    @Test
    public void whenUserFindItemsByNameThenTrackerReturnItems() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "test desc", 123L));
        Input input = new StubInput(new String[]{"5", item.getName(), "6"});
        new StartUI(input, tracker).init();
        Item[] result = {item};
        assertThat(tracker.findByName("test name"), is(result));
    }
}
