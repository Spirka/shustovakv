package ru.job4j.magnit;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class Config.
 *
 * @author shustovakv
 * @since 27.06.2019
 */
public class Config {

    private final Properties values = new Properties();

    public Config() {
        init();
    }

    private void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app2.properties")) {
            this.values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
