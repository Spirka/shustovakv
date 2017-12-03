package ru.job4j.tracker;

import java.util.*;

/**
 * Class Item.
 * @author  shustovakv
 * @since 30.11.2017
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
