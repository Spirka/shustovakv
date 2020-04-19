package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Interface Store
 *
 * @author Kseniya Dergunova
 * @since 19.04.2020
 */
public interface Store {

    List<Employee> findBy(Predicate<Employee> filter);
}
