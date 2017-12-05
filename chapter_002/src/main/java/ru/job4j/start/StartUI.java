package ru.job4j.start;

import ru.job4j.tracker.ConsoleInput;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

/**
 * Class StartUI.
 * @author  shustovakv
 * @since 30.11.2017
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для вывода всех заявок.
     */
    private static final String SHOWALL = "1";
    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки.
     */
    private static final String DEL = "3";
    /**
     * Константа меню для поиска заявки по id.
     */
    private static final String FINDID = "4";
    /**
     * Константа меню для поиска заявки по имени.
     */
    private static final String FINDNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;
    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;
    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String unswer = this.input.ask("Введите пункт меню: ");
            if (ADD.equals(unswer)) {
                //добавление заявки вынесено в отдельный метод.
                this.createItem();
            } else if (SHOWALL.equals(unswer)) {
                this.findAllItems();
            } else if (EDIT.equals(unswer)) {
                this.updateItem();
            } else if (DEL.equals(unswer)) {
                this.deleteItem();
            } else if (FINDID.equals(unswer)) {
                this.findItemById();
            } else if (FINDNAME.equals(unswer)) {
                this.findItemsByName();
            } else if (EXIT.equals(unswer)) {
                exit = true;
            }
        }
    }
    /**
     * Метод реализует добавление новый заявки в хранилище.
     */
    public void createItem() {
        System.out.println("----------Добавление новой заявки----------");
        String name = this.input.ask("Введите имя заявки : ");
        String desk = this.input.ask("Введите описание заявки : ");
        Long create = Long.valueOf(this.input.ask("Введите дату создания заявки в милисекундах : "));
        Item item = new Item(name, desk, create);
        this.tracker.add(item);
        System.out.println("------------" + item.getName() + " заявка с Id : " + item.getId() + " добавлена в хранилище-----------");
    }
    /**
     * Метод реализует обновление значения заявки.
     */
    public void updateItem() {
        System.out.println("----------Обновление заявки----------");
        String id = this.input.ask("Введите ID заявки : ");
        Item byId = this.tracker.findById(id);
        if (byId == null) {
            System.out.println("Вы ввели некорректный ID");
        } else {
            String name = this.input.ask("Введите новое имя заявки : ");
            String desk = this.input.ask("Введите новое описание заявки : ");
            Long create = Long.valueOf(this.input.ask("Введите новую дату создания заявки в милисекундах : "));
            Item item = new Item(name, desk, create);
            item.setId(byId.getId());
            this.tracker.update(item);
            System.out.println("---------- Заявка с Id : " + byId.getId() + " обновлена---------");
        }
    }
    /**
     * Метод реализует удаление заявки.
     */
    public void deleteItem() {
        System.out.println("----------Удаление заявки----------");
        String id = this.input.ask("Введите ID заявки : ");
        Item byId = this.tracker.findById(id);
        if (byId == null) {
            System.out.println("Вы ввели некорректный ID");
        } else {
            this.tracker.delete(byId);
            System.out.println("---------- Заявка с Id : " + byId.getId() + " удалена---------");
        }
    }
    /**
     * Метод реализующий получение списка всех заявок.
     */
    public void findAllItems() {
        System.out.println("----------Список заявок----------");
        Item[] result = this.tracker.findAll();
        if (result.length == 0) {
            System.out.println("Заявки отсутствуют");
        } else {
            for (Item item : result) {
                System.out.println("Заявка ID: " + item.getId() + " Имя: " + item.getName() + " Описание: " + item.getDesc());
            }
        }
        System.out.println("----------Конец списка заявок----------");
    }
    /**
     * Метод реализующие получение списка по имени.
     */
    public void findItemsByName() {
        System.out.println("----------Поиск заявок по имени----------");
        String key = this.input.ask("Введите имя заявки : ");
        Item[] result = tracker.findByName(key);
        System.out.println("----------Список заявок c getName : " + key + "----------");
        for (Item item : result) {
            System.out.println(item);
        }
    }
    /**
     * Метод реализующий поиск заявки по ID.
     */
    public void findItemById() {
        System.out.println("----------Поиск заявки по ID----------");
        String id = this.input.ask("Введите ID заявки : ");
        Item resultID = this.tracker.findById(id);
        System.out.println("----------Заявка c ID : " + id + "----------");
        System.out.println("Имя заявки : " + resultID.getName() + " Описание заявки : " + resultID.getDesc() + " Дата создания : " + resultID.getCreated());
    }
    /**
     * Метод показывает меню.
     */
    private void showMenu() {
        // добавить остальные пункты меню.
        System.out.println("Меню.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        System.out.println("Select: ");
    }
    /**
     * Запуск программы.
     * @param args аргументы.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
