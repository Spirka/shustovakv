package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Class ReportXML
 *
 * @author Kseniya Dergunova
 * @since 20.04.2020
 */
public class ReportXML implements IReport {

    private static final String LN = System.lineSeparator();
    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = this.store.findBy(filter);
        StringBuilder text = new StringBuilder();
        text.append("<?xml version=\"1.0\"?>")
                .append(LN)
                .append("<Employees>")
                .append(LN);
        for (Employee person: employees) {
            text.append("<Name>").append(person.getName()).append("</Name>")
                    .append(LN)
                    .append("<Hired>").append(person.getHired()).append("</Hired>")
                    .append(LN)
                    .append("<Fired>").append(person.getFired()).append("</Fired>")
                    .append(LN)
                    .append("<Salary>").append(person.getSalary()).append("</Salary>")
                    .append(LN);
        }
        text.append("</Employees>");
        return text.toString();
    }
}
