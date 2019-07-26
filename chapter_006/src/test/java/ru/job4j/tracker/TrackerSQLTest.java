package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {

    private Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(Objects.requireNonNull(in));
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void checkConnection() throws SQLException {
       try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
           assertThat(tracker.init(), is(true));
       }
    }

    @Test
    public void whenAddItemThenAdded() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("Petrov", "Ivan", 1564043579L);
            trackerSQL.add(item);
            Item res = trackerSQL.findById(item.getId());
            assertThat(res.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenReplaceItemThenReplaced() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item first = new Item("Petrov", "Ivan", 1564043579L);
            trackerSQL.add(first);
            Item second = new Item("Ivanov", "Petr", 123L);
            trackerSQL.replace(first.getId(), second);
            Item result = trackerSQL.findById(first.getId());
            assertThat(second, is(result));
        }
    }

    @Test
    public void whenDeleteItemThenDeleted() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item first = new Item("Petrov", "Ivan", 123L);
            Item second = new Item("Socolov", "Maxim", 123L);
            sql.add(first);
            sql.add(second);
            sql.delete(first.getId());
            List<Item> result = Collections.singletonList(second);
            assertThat(sql.findAll(), is(result));
        }
    }

    @Test
    public void whenTrackerFindsAllItemsThenReturnTwoItems() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item first = new Item("Petrov", "Ivan", 123L);
            Item second = new Item("Ivanov", "Petr", 123L);
            sql.add(first);
            sql.add(second);
            List<Item> result = Arrays.asList(first, second);
            assertThat(sql.findAll(), is(result));
        }
    }

    @Test
    public void whenTrackerFindsItemByNameThenReturnTwoItems() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item first = new Item("Ivanov", "Petr", 123L);
            Item second = new Item("Ivanov", "Ivan", 123L);
            Item third = new Item("Petrov", "Ivan", 123L);
            sql.add(first);
            sql.add(second);
            sql.add(third);
            assertThat(sql.findByName("Ivanov").size(), is(2));
        }
    }

    @Test
    public void whenTrackerFindsItemByIdThenReturnFirstItem() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item first = new Item("Petrov", "Ivan", 123L);
            Item second = new Item("Ivanov", "Ivan", 123L);
            sql.add(first);
            sql.add(second);
            assertThat(sql.findById(first.getId()), is(first));
        }
    }
}