package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {

    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenAddItemThenAdded() {
        TrackerSQL sql = new TrackerSQL();
        Item item = new Item("Petrov", "Ivan", 123L);
        Item result = sql.add(item);
        assertEquals(result, item);
        sql.delete("1");
    }

    @Test
    public void whenReplaceItemThenReplaced() {
        TrackerSQL sql = new TrackerSQL();
        Item first = new Item("Petrov", "Ivan", 123L);
        sql.add(first);
        Item second = new Item("Ivanov", "Petr", 123L);
        sql.replace("1", second);
        Item result = sql.findById("1");
        assertThat(second, is(result));
        sql.dropTable();
    }

    @Test
    public void whenDeleteItemThenDeleted() {
        TrackerSQL sql = new TrackerSQL();
        Item first = new Item("Petrov", "Ivan", 123L);
        Item second = new Item("Socolov", "Maxim", 123L);
        sql.add(first);
        sql.add(second);
        sql.delete("1");
        List<Item> result = sql.findByName("Socolov");
        assertThat(sql.findAll(), is(result));
        sql.dropTable();
    }

    @Test
    public void whenTrackerFindsAllItemsThenReturnTwoItems() {
        TrackerSQL sql = new TrackerSQL();
        Item first = new Item("Petrov", "Ivan", 123L);
        Item second = new Item("Ivanov", "Petr", 123L);
        sql.add(first);
        sql.add(second);
        List<Item> result = Arrays.asList(first, second);
        assertThat(sql.findAll(), is(result));
        sql.dropTable();
    }

    @Test
    public void whenTrackerFindsItemByNameThenReturnSecondItem() {
        TrackerSQL sql = new TrackerSQL();
        Item first = new Item("Ivanov", "Petr", 123L);
        Item second = new Item("Ivanov", "Ivan", 123L);
        Item third = new Item("Petrov", "Ivan", 123L);
        sql.add(first);
        sql.add(second);
        sql.add(third);
        List<Item> result = Arrays.asList(first, second);
        assertThat(sql.findByName("Ivanov"), is(result));
        sql.dropTable();
    }

    @Test
    public void whenTrackerFindsItemByIdThenReturnFirstItem() {
        TrackerSQL sql = new TrackerSQL();
        Item first = new Item("Petrov", "Ivan", 123L);
        Item second = new Item("Ivanov", "Ivan", 123L);
        sql.add(first);
        sql.add(second);
        assertThat(sql.findById("1"), is(first));
        sql.dropTable();
    }
}