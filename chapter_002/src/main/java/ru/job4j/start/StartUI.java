package ru.job4j.start;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;

/**
 * Class StartUI.
 * @author  shustovakv
 * @since 30.11.2017
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;
    /**
     * Диапазон значений меню.
     */
    private int[] ranges = new int[] {0, 1, 2, 3, 4, 5, 6};
    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;
    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Основой цикл программы.
     */
    void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            menu.select(input.ask("Выберите пункт меню: ", ranges));
        } while (!"6".equals(this.input.ask("Вы уверены, что хотите выйти? да(6) : ")));
    }
    /**
     * Запуск программы.
     * @param args аргументы.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}