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
        // Создаем объект класса трекер.
        Tracker tracker = new Tracker();
        // Создаем новую заявку.
        Item item = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер.
        tracker.add(item);
        // Проверяем, что в трекере есть наша заявка.
        assertThat(tracker.findAll()[0], is(item));
    }
    /**
     * Test update.
     */
    @Test
    public void whenUpdateNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.update(next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
    /**
     * Test delete.
     */
    @Test
    public void whenDeleteItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Удаляем заявку в трекере.
        tracker.delete(previous);
        Item[] result = {};
        // Проверяем, что заявка next удалена.
        assertThat(tracker.findAll(), is(result));
    }
    /**
     * Test findAll.
     */
    @Test
    public void whenFindAllItemsThenReturnAllItems() {
        Tracker tracker = new Tracker();
        // Создаем новую заявку.
        Item first = new Item("test1", "testDescription1", 123L);
        // Добавляем заявку в трекер.
        tracker.add(first);
        Item[] result = {first};
        // Получаем массив заявок items без null.
        assertThat(tracker.findAll(), is(result));
    }
    /**
     * Test findByName.
     */
    @Test
    public void whenFindItemsByNameThenReturnItems() {
        Tracker tracker = new Tracker();
        // Создаем новую заявку.
        Item first = new Item("test1", "testDescription1", 123L);
        // Добавляем заявку в трекер.
        tracker.add(first);
        Item[] result = {first};
        assertThat(tracker.findByName("test1"), is(result));
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
