package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * Class TrackerSQL.
 *
 * @author shustovakv
 * @since 24.05.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);

    private Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(Objects.requireNonNull(in));
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        Item result = null;
        @SuppressWarnings("SqlResolve") String sql = "INSERT INTO items (name, description, created) VALUES (?, ?, ?)";
        try (PreparedStatement pst = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, item.getName());
            pst.setString(2, item.getDesc());
            pst.setTimestamp(3, new Timestamp(item.getCreated()));
            pst.executeUpdate();
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getString(1));
                }
            }
            result = item;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void replace(String id, Item item) {
        @SuppressWarnings("SqlResolve") String sql = "UPDATE items SET name = ?, description = ?, created = ? WHERE id = ?";
        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            item.setId(id);
            pst.setString(1, item.getName());
            pst.setString(2, item.getDesc());
            pst.setTimestamp(3, new Timestamp(item.getCreated()));
            pst.setInt(4, Integer.valueOf(id));
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String id) {
        @SuppressWarnings("SqlResolve") String sql = "DELETE FROM items WHERE id = ?";
        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            pst.setInt(1, Integer.valueOf(id));
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        @SuppressWarnings("SqlResolve") String sql = "SELECT * FROM items";
        try (Statement st = this.connection.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Item item = new Item(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("created").getTime());
                item.setId(rs.getString("id"));
                items.add(item);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        @SuppressWarnings("SqlResolve") String sql = "SELECT name, description, created FROM items WHERE name = ?";
        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            pst.setString(1, key);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Item item = new Item(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("created").getTime());
                items.add(item);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        @SuppressWarnings("SqlResolve") String sql = "SELECT * FROM items WHERE id = ?";
        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            pst.setInt(1, Integer.valueOf(id));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = new Item(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getTimestamp("created").getTime());
                result.setId(String.valueOf(rs.getInt("id")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws SQLException {
        this.connection.close();
    }

    public void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS Items (id serial primary key , name varchar(50), description text, created timestamp)";
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void dropTable() {
        @SuppressWarnings("SqlResolve") String sql = "DROP TABLE items";
        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            pst.executeUpdate();
        }
        catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
