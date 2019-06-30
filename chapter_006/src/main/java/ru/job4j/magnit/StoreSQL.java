package ru.job4j.magnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class StoreSQL.
 *
 * @author shustovakv
 * @since 27.06.2019
 */
public class StoreSQL implements AutoCloseable {

    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
        init();
    }

    private void init() {
        try {
            this.connect = DriverManager.getConnection(config.get("url"));
            if (this.connect != null) {
                DatabaseMetaData meta = connect.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + e);
        }
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS entry (field INTEGER)";
        try (Statement statement = this.connect.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + e);
        }
    }

    private void dropTable() {
        @SuppressWarnings("SqlResolve") String sql = "DROP TABLE IF EXISTS entry";
        try (PreparedStatement pst = this.connect.prepareStatement(sql)) {
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + e);
        }
    }

    public void generate(int size) throws SQLException {
        dropTable();
        createTable();
        @SuppressWarnings("SqlResolve") String sql = "INSERT INTO entry (field) VALUES (?)";
        this.connect.setAutoCommit(false);
        try (PreparedStatement pst = this.connect.prepareStatement(sql)) {
            for (int i = 0; i < size; i++) {
                pst.setInt(1, i + 1);
                pst.addBatch();
            }
            pst.executeBatch();
        }
        catch (SQLException e) {
            this.connect.rollback();
            this.connect.setAutoCommit(true);
            System.out.println(e.getMessage() + e);
        }
        this.connect.commit();
        this.connect.setAutoCommit(true);
    }

     public List<Entry> load() {
        List<Entry> entries = new ArrayList<>();
         @SuppressWarnings("SqlResolve") String sql = "SELECT field FROM entry";
        try (PreparedStatement pst = this.connect.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                entries.add(new Entry(rs.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage() + e);
        }
        return entries;
     }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
