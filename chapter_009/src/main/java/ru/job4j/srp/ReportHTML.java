package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Class ReportHTML
 *
 * @author Kseniya Dergunova
 * @since 19.04.2020
 */
public class ReportHTML implements IReport {

    private Store store;

    private static final String LN = System.lineSeparator();

    public ReportHTML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = this.store.findBy(filter);
        StringBuilder html = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append(LN)
                .append("<html>")
                .append(LN)
                .append("<body>")
                .append(LN)
                .append("<p>");
        for (Employee person : employees) {
            html.append("Name: ")
                    .append(person.getName()).append(";")
                    .append("Hired: ")
                    .append(person.getHired()).append(";")
                    .append("Fired: ")
                    .append(person.getFired()).append(";")
                    .append("Salary: ")
                    .append(person.getSalary()).append(";")
                    .append("</p>")
                    .append(LN);
        }
        html.append("</body>")
                .append(LN)
                .append("</html>")
                .append(LN);

        return html.toString();
    }
}
