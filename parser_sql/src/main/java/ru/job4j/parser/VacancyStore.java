package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * Class VacancyStore.
 *
 * @author shustovakv
 * @since 30.08.2019
 */
public class VacancyStore implements AutoCloseable {

    private Connection connect;

    final static Logger LOG = LogManager.getLogger(VacancyStore.class.getName());

    public VacancyStore(Connection connection) {
        this.connect = connection;
    }

    public boolean init() {
        try (InputStream in = VacancyStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(Objects.requireNonNull(in));
            Class.forName(config.getProperty("driver-class-name"));
            this.connect = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return this.connect != null;
    }

    public void add(List<Entry> vacancies) {
        String sql = "INSERT INTO vacancies.public.vacancies_sql_ru (name, text, link) VALUES (?, ?, ?)";
        try (PreparedStatement pst = this.connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < vacancies.size(); i++) {
                if (checkForNoDuplicates(vacancies.get(i))) {
                    pst.setString(1, vacancies.get(i).getName());
                    pst.setString(2, vacancies.get(i).getDesc());
                    pst.setString(3, vacancies.get(i).getLink());
                    pst.addBatch();
                }
            }
            pst.executeBatch();
        } catch (SQLException e) {
            LOG.error("Error add elements to DataBase...", e);
        }
    }

    private boolean checkForNoDuplicates(Entry entry) {
        String vacancyName = entry.getName();
        try (PreparedStatement pst = this.connect.prepareStatement("SELECT * FROM vacancies.public.vacancies_sql_ru WHERE name = ?")) {
            pst.setString(1, vacancyName);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Entry> getVacancies() throws SQLException {
        List<Entry> result = new ArrayList<>();
        String sql = "SELECT * FROM vacancies.public.vacancies_sql_ru";
        try (Statement st = this.connect.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                result.add(new Entry(
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("link")
                ));
            }
        }
        return result;
    }

    @Override
    public void close() throws SQLException {
        if (this.connect != null) {
            this.connect.close();
        }
    }
}
