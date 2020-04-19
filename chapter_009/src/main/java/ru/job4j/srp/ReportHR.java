package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Class ReportText
 *
 * @author Kseniya Dergunova
 * @since 19.04.2020
 */
public class ReportHR implements IReport {

    private static final String LN = System.lineSeparator();
    private SortBySalary emp = new SortBySalary();
    private Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(LN);
        List<Employee> employees = store.findBy(filter);
        employees.sort(emp);
        for (Employee employer : employees) {
            text.append(employer.getName()).append(";")
                    .append(employer.getSalary()).append(";")
                    .append(LN);
        }
        return text.toString();
    }
}
