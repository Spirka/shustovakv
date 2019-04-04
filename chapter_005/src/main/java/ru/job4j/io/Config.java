package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Class Config.
 *
 * @author shustovakv
 * @since 02.04.2019
 */
public class Config {

    /**
     * Separator of key = value
     */
    private static final String SP = "=";

    /**
     * File with key = value
     */
    private final String path;

    /**
     * Container for pairs key = value
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Constructor
     * @param path file properties
     */
    public Config(final String path) {
        this.path = path;
    }

    /**
     * Load properties
     * @return instance of configuration
     */
    public void load() {
        this.values.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(
                    element -> {
                        if (element.contains(SP)) {
                            int pos = element.indexOf(SP);
                            this.values.put(element.substring(0, pos), element.substring(pos + 1));
                        } else {
                            this.values.put(element, "");
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get value by key
     * @param key for value
     * @return value
     */
    public String value(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * Method for starting programme
     * @param args path to file with configurations
     */
    public static void main(String[] args) {
        Config config = new Config("app.properties");
        config.load();
        System.out.println(config.values);
    }
}
