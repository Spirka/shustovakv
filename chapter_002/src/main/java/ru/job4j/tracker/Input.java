package ru.job4j.tracker;
/**
 * Interface Input.
 * @author  shustovakv
 * @since 30.11.2017
 */
public interface Input {
    String ask(String question);
    int ask(String question, int[] range);
}
