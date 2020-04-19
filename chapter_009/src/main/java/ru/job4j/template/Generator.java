package ru.job4j.template;

import java.util.Map;

/**
 * Interface Generator
 *
 * @author Kseniya Dergunova
 * @since 19.04.2020
 */
public interface Generator {
    String produce(String template, Map<String, String> args);
}
