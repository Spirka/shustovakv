package ru.job4j.tracker;

import java.util.List;

/**
 * Interface ITracker
 *
 *  @author shustovakv
 *  @since 24.05.2019
 */
public interface ITracker {
    Item add(Item item);
    void replace(String id, Item item);
    void delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);
}
