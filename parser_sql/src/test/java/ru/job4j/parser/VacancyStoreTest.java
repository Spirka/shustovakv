package ru.job4j.parser;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class VacancyStoreTest {

    private Connection init() {
        try (InputStream in = VacancyStore.class.getClassLoader()
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
        try (VacancyStore store = new VacancyStore(ConnectionRollBack.create(this.init()))) {
            assertThat(store.init(), is(true));
        }
    }

    @Test
    public void whenAddThenAdded() throws SQLException {
        try (VacancyStore store = new VacancyStore(ConnectionRollBack.create(this.init()))) {
            Entry vacancy = new Entry("JAVA программист",
                    "vacancy description",
                    "https://www.sql.ru/forum/1315359/java-programmist-dlya-razrabotki-resheniya-na-baze-messendzhera-signal",
                    null);
            store.add(Collections.singletonList(vacancy));
            List<Entry> expected = Collections.singletonList(vacancy);
            List<Entry> result = store.getVacancies();
            assertThat(expected, is(result));
        }
    }

    @Test
    public void whenDuplicateAddThenItNotAdded() throws SQLException {
        try (VacancyStore store = new VacancyStore(ConnectionRollBack.create(this.init()))) {
            Entry first = new Entry("JAVA программист",
                    "vacancy description",
                    "https://www.sql.ru/forum/1315359/java-programmist-dlya-razrabotki-resheniya-na-baze-messendzhera-signal",
                    null);
            Entry second = new Entry("Системный программист C++/Java от 200тр",
                    "vacancy desc",
                    "https://www.sql.ru/forum/1314951/sistemnyy-programmist-c-java-ot-200tr",
                    null);
            store.add(Arrays.asList(first, second));
            List<Entry> expected = Arrays.asList(first, second);
            List<Entry> result = store.getVacancies();
            assertThat(expected, is(result));
            Entry duplicate = new Entry("JAVA программист",
                    "vacancy desc",
                    "https://www.sql.ru/forum/1314951/sistemnyy-programmist-c-java-ot-200tr",
                    null);
            Entry third = new Entry("Java разработчик, ИТБ банк (Москва)",
                    "description",
                    "https://www.sql.ru/forum/1316572/java-razrabotchik-itb-bank-moskva");
            store.add(Arrays.asList(duplicate, third));
            List<Entry> exp = Arrays.asList(first, second, third);
            assertThat(store.getVacancies(), is(exp));
        }
    }
}