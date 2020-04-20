package ru.job4j.srp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.function.Predicate;

/**
 * Class ReportJSON
 *
 * @author Kseniya Dergunova
 * @since 20.04.2020
 */
public class ReportJSON implements IReport {

    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = this.store.findBy(filter);
        JSONObject employeesJSON = new JSONObject();
        JSONArray employeeArray = new JSONArray();
        for (Employee person : employees) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", person.getName());
            jsonObject.put("hired", person.getHired());
            jsonObject.put("fired", person.getFired());
            jsonObject.put("salary", person.getSalary());
            employeeArray.add(jsonObject);
        }
        employeesJSON.put("employees", employeeArray);
        return employeesJSON.toJSONString();
    }
}
