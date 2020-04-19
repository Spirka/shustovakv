package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * Interface Report
 *
 * @author Kseniya Dergunova
 * @since 19.04.2020
 */
public interface IReport {

    String generate(Predicate<Employee> filter);
}
