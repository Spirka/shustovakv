package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Interface Cinema
 *
 * @author Kseniya Dergunova
 * @since 11.04.2020
 */
public interface Cinema {
    List<Session> find(Predicate<Session> filter);
    Ticket buy(Account account, int row, int column, Calendar date);
    void add(Session session);
}
