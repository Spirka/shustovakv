package ru.job4j.srp;

import java.util.Comparator;

/**
 * Class SortBySalary
 *
 * @author Kseniya Dergunova
 * @since 19.04.2020
 */
public class SortBySalary implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o2.getSalary(), o1.getSalary());
    }
}
