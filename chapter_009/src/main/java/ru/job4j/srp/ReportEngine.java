package ru.job4j.srp;

import java.util.function.Predicate;

/**
 * Class ReportEngine
 *
 * @author Kseniya Dergunova
 * @since 19.04.2020
 */
public class ReportEngine implements IReport {

    private static final String LN = System.lineSeparator();
    private Store store;

    double gross = 1.20;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            double salary = employee.getSalary() * gross;
            text.append(LN);
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(salary).append(";")
                    .append(" gross;")
                    .append(LN);
        }
        return text.toString();
    }
}
