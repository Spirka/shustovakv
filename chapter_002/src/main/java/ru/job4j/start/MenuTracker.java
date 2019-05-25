package ru.job4j.start;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Class MenuTracker.
 * @author  shustovakv
 * @since 11.12.2017
 */
public class MenuTracker {

    private Input input;
    private ITracker tracker;
    private ArrayList<BaseAction> actions = new ArrayList<>();

    /**
     * Constructor.
     * @param input
     * @param tracker
     */
    public MenuTracker(Input input, ITracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method fill actions.
     */
    public void fillActions() {
        this.actions.add(new AddItem("AddItem", 0));
        this.actions.add(new MenuTracker.ShowItems("ShowItems", 1));
        this.actions.add(new EditItem("EditItem", 2));
        this.actions.add(new DeleteItem("DeleteItem", 3));
        this.actions.add(new FindItemsByName("FindItemsByName", 4));
        this.actions.add(new FindItemById("FindItemById", 5));
    }


    /**
     * Method select.
     * @param key
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    public void show() {
        for (BaseAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Class AddItem.
     */
    private class AddItem extends BaseAction {

        /**
         * Constructor
         * @param name
         * @param key
         */
        private AddItem(String name, int key) {
            super(name, key);
        }

        /**
         * Method execute.
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("----------Добавление новой заявки----------");
            String name = input.ask("Введите имя заявки : ");
            String desk = input.ask("Введите описание заявки : ");
            Long create = Long.valueOf(input.ask("Введите дату создания заявки в милисекундах : "));
            Item item = new Item(name, desk, create);
            tracker.add(item);
            System.out.println("------------" + item.getName() + " заявка с Id : " + item.getId() + " добавлена в хранилище-----------");
        }
    }

    /**
     * Class show items.
     */
    private static class ShowItems extends BaseAction {

        /**
         * Constructor.
         * @param name
         * @param key
         */
        private ShowItems(String name, int key) {
            super(name, key);
        }

        /**
         * Method execute.
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("----------Список заявок----------");
            List<Item> result = tracker.findAll();
            if (result.isEmpty()) {
                System.out.println("Заявки отсутствуют");
            } else {
                for (Item item : result) {
                    System.out.println("Заявка ID: " + item.getId() + " Имя: " + item.getName() + " Описание: " + item.getDesc());
                }
            }
            System.out.println("----------Конец списка заявок----------");
        }
    }

    /**
     * Class EditItem.
     */
    public class EditItem extends BaseAction {

        /**
         * Constructor.
         * @param name
         * @param key
         */
        private EditItem(String name, int key) {
            super(name, key);
        }

        /**
         * Method execute.
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("----------Обновление заявки----------");
            String id = input.ask("Введите ID заявки : ");
            Item byId = tracker.findById(id);
            if (byId == null) {
                System.out.println("Вы ввели некорректный ID");
            } else {
                String name = input.ask("Введите новое имя заявки : ");
                String desk = input.ask("Введите новое описание заявки : ");
                Long create = Long.valueOf(input.ask("Введите новую дату создания заявки в милисекундах : "));
                Item item = new Item(name, desk, create);
                item.setId(id);
                tracker.replace(item.getId(), item);
                System.out.println("---------- Заявка с Id : " + byId.getId() + " обновлена---------");
            }
        }
    }

    /**
     * Class DeleteItem.
     */
    private class DeleteItem extends BaseAction {

        /**
         * Constructor.
         * @param name
         * @param key
         */
        private DeleteItem(String name, int key) {
            super(name, key);
        }

        /**
         * Method key
         * @return 3
         */
        @Override
        public int key() {
            return 3;
        }

        /**
         * Method execute.
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("----------Удаление заявки----------");
            String id = input.ask("Введите ID заявки : ");
            Item item = tracker.findById(id);
            if (item == null) {
                System.out.println("Вы ввели некорректный ID");
            } else {
                tracker.delete(item.getId());
                System.out.println("---------- Заявка с Id : " + item.getId() + " удалена---------");
            }
        }
    }

    /**
     * Class FindItemsByName
     */
    private class FindItemsByName extends BaseAction {

        /**
         * Constructor.
         * @param name
         * @param key
         */
        private FindItemsByName(String name, int key) {
            super(name, key);
        }

        /**
         * Method key
         * @return 4
         */
        @Override
        public int key() {
            return 4;
        }

        /**
         * Method execute.
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("----------Поиск заявок по имени----------");
            String key = input.ask("Введите имя заявки : ");
            List<Item> result = tracker.findByName(key);
            System.out.println("----------Список заявок c getName : " + key + "----------");
            for (Item item : result) {
                System.out.println(item);
            }
        }
    }

    /**
     * Class FindItemById.
     */
    private class FindItemById extends BaseAction {

        /**
         * Constructor.
         * @param name
         * @param key
         */
        private FindItemById(String name, int key) {
            super(name, key);
        }

        /**
         * Method key
         * @return 5
         */
        @Override
        public int key() {
            return 5;
        }

        /**
         * Method execute
         * @param input
         * @param tracker
         */
        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("----------Поиск заявки по ID----------");
            String id = input.ask("Введите ID заявки : ");
            Item resultID = tracker.findById(id);
            System.out.printf("----------Заявка c ID : %s----------%n", id);
            System.out.printf("Имя заявки : %s Описание заявки : %s Дата создания : %d%n", resultID.getName(), resultID.getDesc(), resultID.getCreated());
        }
    }
}
