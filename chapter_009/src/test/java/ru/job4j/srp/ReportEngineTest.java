package ru.job4j.srp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportEngineTest {

    private static final String LN = System.lineSeparator();

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(LN)
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(LN);
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        IReport engine = new ReportHTML(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append(LN)
                .append("<html>")
                .append(LN)
                .append("<body>")
                .append(LN)
                .append("<p>").append("Name: ").append(worker.getName()).append(";")
                .append("Hired: ").append(worker.getHired()).append(";")
                .append("Fired: ").append(worker.getFired()).append(";")
                .append("Salary: ").append(worker.getSalary()).append(";").append("</p>")
                .append(LN)
                .append("</body>")
                .append(LN)
                .append("</html>")
                .append(LN);
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 300);
        Employee worker3 = new Employee("Stephan", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        IReport engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(LN)

                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(LN)

                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(LN)

                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(LN);

        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountingReportGenerated() {
        double gross = 1.20;
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        IReport engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(LN)
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() * gross).append(";")
                .append(" gross;")
                .append(LN);
        assertThat(engine.generate(employee -> true), is(expect.toString()));
    }

    @Test
    public void whenReportXMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 300);
        store.add(worker);
        store.add(worker2);
        IReport engine = new ReportXML(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\"?>")
                .append(LN)
                .append("<Employees>")
                .append(LN)
                .append("<Name>").append(worker.getName()).append("</Name>")
                .append(LN)
                .append("<Hired>").append(worker.getHired()).append("</Hired>")
                .append(LN)
                .append("<Fired>").append(worker.getFired()).append("</Fired>")
                .append(LN)
                .append("<Salary>").append(worker.getSalary()).append("</Salary>")
                .append(LN)
                .append("<Name>").append(worker2.getName()).append("</Name>")
                .append(LN)
                .append("<Hired>").append(worker2.getHired()).append("</Hired>")
                .append(LN)
                .append("<Fired>").append(worker2.getFired()).append("</Fired>")
                .append(LN)
                .append("<Salary>").append(worker2.getSalary()).append("</Salary>")
                .append(LN)
                .append("</Employees>");
        assertThat(engine.generate(employee -> true), is(expect.toString()));
    }

    @Test
    public void whenReportJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        IReport engine = new ReportJSON(store);
        JSONObject expect = new JSONObject();
        JSONArray employeeArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", worker.getName());
        jsonObject.put("hired", worker.getHired());
        jsonObject.put("fired", worker.getFired());
        jsonObject.put("salary", worker.getSalary());
        employeeArray.add(jsonObject);
        expect.put("employees", employeeArray);
        assertThat(engine.generate(employee -> true), is(expect.toJSONString()));
    }
}
