package ru.job4j.start;

import org.junit.Test;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
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
        assertThat(tracker.findAll().toArray(), is(result));
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
        assertThat(tracker.findAll().toArray(), is(result));
    }

    /**
     * Test findById.
     */
    @Test
    public void whenUserFindItemByIdThenTrackerReturnFoundItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "test desc", 123L));
        Input input = new StubInput(new String[]{"5", item.getId(), "6"});
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
        Input input = new StubInput(new String[]{"4", item.getName(), "6"});
        new StartUI(input, tracker).init();
        Item[] result = {item};
        assertThat(tracker.findByName("test name").toArray(), is(result));
    }


    @Test
    public void whenUpdate() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test1", "test description", 1234567L));
        String replacedName = "New item name";
        Iterator<String> it = Arrays.asList("2", item.getId(), replacedName, "desc", "123456", "6").iterator();
        Input input = mock(Input.class);
        when(input.ask(any(String.class))).thenAnswer(any -> it.next());
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));
        verify(input, times(6)).ask(any(String.class));
    }

    @Test
    public void whenAdd() {
        Tracker tracker = new Tracker();
        Iterator<String> it = Arrays.asList("0", "test name", "desc", "123456789", "6").iterator();
        Input input = mock(Input.class);
        when(input.ask(any(String.class))).thenAnswer(any -> it.next());
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    @Test
    public void whenFindAll() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test1", "test desc", 12345L));
        Iterator<String> it = Arrays.asList("1", "6").iterator();
        Input input = mock(Input.class);
        when(input.ask(any(String.class))).thenAnswer(any -> it.next());
        new StartUI(input, tracker).init();
        Item[] result = {item};
        assertThat(tracker.findAll().toArray(), is(result));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("test 1", "test desc 1", 1234L));
        Iterator<String> it = Arrays.asList("3", first.getId(), "6").iterator();
        Input input = mock(Input.class);
        when(input.ask(any(String.class))).thenAnswer(any -> it.next());
        new StartUI(input, tracker).init();
        Item[] result = {};
        assertThat(tracker.findAll().toArray(), is(result));
    }

    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "test desc", 123L));
        Iterator<String> it = Arrays.asList("5", item.getId(), "6").iterator();
        Input input = mock(Input.class);
        when(input.ask(any(String.class))).thenAnswer(any -> it.next());
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "test desc", 123L));
        Iterator<String> it = Arrays.asList("4", item.getName(), "6").iterator();
        Input input = mock(Input.class);
        when(input.ask(any(String.class))).thenAnswer(any -> it.next());
        new StartUI(input, tracker).init();
        Item[] result = {item};
        assertThat(tracker.findByName("test name").toArray(), is(result));
    }
}
